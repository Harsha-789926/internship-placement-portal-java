
import { useEffect, useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function Education({ student, onBack }) {
  const [educationList, setEducationList] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [message, setMessage] = useState('')
  const [showForm, setShowForm] = useState(false)
  const [editingId, setEditingId] = useState(null)
  const [saving, setSaving] = useState(false)
  const [deletingId, setDeletingId] = useState(null)

  const emptyForm = {
    educationLevel: '',
    institution: '',
    percentage: '',
    cgpa: '',
    passingYear: '',
  }

  const [formData, setFormData] = useState(emptyForm)

  // Fetch education records
  const fetchEducation = async () => {
    try {
      setError('')

      const response = await fetch(
        `${API_BASE_URL}/education/student/${student.studentId}`
      )

      if (!response.ok) {
        throw new Error('Unable to load education records')
      }

      const data = await response.json()
      setEducationList(Array.isArray(data) ? data : [])
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchEducation()
  }, [student.studentId])

  // Handle form input
  const handleChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
    })
  }

  // Open Add Education form
  const handleAdd = () => {
    setFormData(emptyForm)
    setEditingId(null)
    setMessage('')
    setShowForm(true)
  }

  // Open Edit Education form
  const handleEdit = (education) => {
    setFormData({
      educationLevel: education.educationLevel || '',
      institution: education.institution || '',
      percentage: education.percentage ?? '',
      cgpa: education.cgpa ?? '',
      passingYear: education.passingYear ?? '',
    })

    setEditingId(education.educationId)
    setMessage('')
    setShowForm(true)
  }

  // Add or Update Education
  const handleSubmit = async (event) => {
    event.preventDefault()
    setMessage('')
    setSaving(true)

    const payload = {
      studentId: student.studentId,
      educationLevel: formData.educationLevel,
      institution: formData.institution,
      percentage: Number(formData.percentage),
      cgpa: Number(formData.cgpa),
      passingYear: Number(formData.passingYear),
    }

    try {
      const url = editingId
        ? `${API_BASE_URL}/education/${editingId}`
        : `${API_BASE_URL}/education`

      const response = await fetch(url, {
        method: editingId ? 'PUT' : 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      })

      const data = await response.json()

      if (!response.ok) {
        throw new Error(
          data.message || data.error || 'Unable to save education record'
        )
      }

      setMessage(
        editingId
          ? 'Education record updated successfully!'
          : 'Education record added successfully!'
      )

      setShowForm(false)
      setEditingId(null)
      setFormData(emptyForm)

      await fetchEducation()
    } catch (err) {
      setMessage(err.message)
    } finally {
      setSaving(false)
    }
  }

  // Delete Education
  const handleDelete = async (educationId) => {
    const confirmed = window.confirm(
      'Are you sure you want to delete this education record?'
    )

    if (!confirmed) return

    setDeletingId(educationId)
    setMessage('')

    try {
      const response = await fetch(
        `${API_BASE_URL}/education/${educationId}`,
        {
          method: 'DELETE',
        }
      )

      if (!response.ok) {
        const errorData = await response.json().catch(() => ({}))
        throw new Error(
          errorData.message || errorData.error || 'Unable to delete education record'
        )
      }

      setMessage('Education record deleted successfully!')
      await fetchEducation()
    } catch (err) {
      setMessage(err.message)
    } finally {
      setDeletingId(null)
    }
  }

  // Cancel form
  const handleCancel = () => {
    setShowForm(false)
    setEditingId(null)
    setFormData(emptyForm)
    setMessage('')
  }

  return (
    <div className="education-page">

      {/* Page Header */}
      <div className="jobs-header">
        <button className="back-button" onClick={onBack}>
          ← Back to Dashboard
        </button>

        <p className="tagline">STUDENT PORTAL</p>
        <h1>My Education</h1>
        <p>View and manage your academic qualifications.</p>
      </div>

      <div className="education-container">

        {/* Add Button */}
        <div className="education-actions">
          <button
            className="primary-button"
            onClick={handleAdd}
          >
            + Add Education
          </button>
        </div>

        {/* Success and Error Messages */}
        {message && (
          <p className="education-message" role="status">
            {message}
          </p>
        )}

        {/* Add/Edit Form */}
        {showForm && (
          <form className="education-form" onSubmit={handleSubmit}>
            <h2>
              {editingId ? 'Edit Education Details' : 'Add Education Details'}
            </h2>

            <label>Education Level</label>
            <input
              type="text"
              name="educationLevel"
              placeholder="Example: Undergraduate"
              value={formData.educationLevel}
              onChange={handleChange}
              required
            />

            <label>Institution</label>
            <input
              type="text"
              name="institution"
              placeholder="Enter institution name"
              value={formData.institution}
              onChange={handleChange}
              required
            />

            <label>Percentage</label>
            <input
              type="number"
              name="percentage"
              placeholder="Enter percentage"
              min="0"
              max="100"
              step="0.01"
              value={formData.percentage}
              onChange={handleChange}
              required
            />

            <label>CGPA</label>
            <input
              type="number"
              name="cgpa"
              placeholder="Enter CGPA"
              min="0"
              max="10"
              step="0.01"
              value={formData.cgpa}
              onChange={handleChange}
              required
            />

            <label>Passing Year</label>
            <input
              type="number"
              name="passingYear"
              placeholder="Example: 2026"
              min="1950"
              max="2100"
              value={formData.passingYear}
              onChange={handleChange}
              required
            />

            <div className="education-form-buttons">
              <button
                type="submit"
                className="primary-button"
                disabled={saving}
              >
                {saving
                  ? 'Saving...'
                  : editingId
                    ? 'Update Education'
                    : 'Save Education'}
              </button>

              <button
                type="button"
                className="cancel-button"
                onClick={handleCancel}
                disabled={saving}
              >
                Cancel
              </button>
            </div>
          </form>
        )}

        {/* Loading */}
        {loading && <p>Loading education records...</p>}

        {/* Error */}
        {error && <p className="jobs-error">{error}</p>}

        {/* Empty State */}
        {!loading && !error && educationList.length === 0 && (
          <p className="education-empty">
            No education records found. Add your academic details.
          </p>
        )}

        {/* Education Records */}
        {!loading && !error && educationList.map((education) => (
          <div className="education-card" key={education.educationId}>

            <h2>{education.educationLevel}</h2>

            <p>
              <strong>Institution:</strong> {education.institution}
            </p>

            <p>
              <strong>Percentage:</strong> {education.percentage}%
            </p>

            <p>
              <strong>CGPA:</strong> {education.cgpa}
            </p>

            <p>
              <strong>Passing Year:</strong> {education.passingYear}
            </p>

            <div className="education-card-buttons">
              <button
                className="edit-button"
                onClick={() => handleEdit(education)}
              >
                Edit
              </button>

              <button
                className="delete-button"
                onClick={() => handleDelete(education.educationId)}
                disabled={deletingId === education.educationId}
              >
                {deletingId === education.educationId
                  ? 'Deleting...'
                  : 'Delete'}
              </button>
            </div>

          </div>
        ))}

      </div>
    </div>
  )
}

export default Education
