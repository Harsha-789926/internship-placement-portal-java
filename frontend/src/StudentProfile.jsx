
import { useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function StudentProfile({ student, onBack, onProfileUpdated }) {
  const [formData, setFormData] = useState({
    name: student.name || '',
    email: student.email || '',
    cgpa: student.cgpa ?? '',
    branchId: student.branchId ?? '',
    resumePath: student.resumePath || ''
  })

  const [saving, setSaving] = useState(false)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')

  const handleChange = (event) => {
    const { name, value } = event.target

    setFormData((previous) => ({
      ...previous,
      [name]: value
    }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault()

    setSaving(true)
    setMessage('')
    setError('')

    const payload = {
      name: formData.name.trim(),
      email: formData.email.trim(),
      cgpa: formData.cgpa === '' ? null : Number(formData.cgpa),
      branchId: formData.branchId === '' ? null : Number(formData.branchId),
      resumePath: formData.resumePath.trim()
    }

    if (
      payload.cgpa !== null &&
      (payload.cgpa < 0 || payload.cgpa > 10)
    ) {
      setError('CGPA must be between 0 and 10.')
      setSaving(false)
      return
    }

    try {
      const response = await fetch(
        `${API_BASE_URL}/students/${student.studentId}`,
        {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(payload)
        }
      )

      const data = await response.json().catch(() => ({}))

      if (!response.ok) {
        throw new Error(
          data.message ||
          data.error ||
          'Unable to update student profile.'
        )
      }

      const updatedStudent = {
        ...student,
        ...data
      }

      onProfileUpdated(updatedStudent)

      setMessage('Profile updated successfully!')
    } catch (err) {
      setError(err.message)
    } finally {
      setSaving(false)
    }
  }

  return (
    <div className="student-profile-page">

      <div className="jobs-header">
        <button className="back-button" onClick={onBack}>
          ← Back to Dashboard
        </button>

        <p className="tagline">STUDENT PORTAL</p>
        <h1>My Profile</h1>
        <p>View and update your personal and academic details.</p>
      </div>

      <div className="student-profile-container">

        <div className="profile-information">
          <div className="profile-avatar">
            {student.name?.charAt(0).toUpperCase()}
          </div>

          <h2>{student.name}</h2>
          <p>{student.email}</p>

          <div className="profile-id">
            Student ID: {student.studentId}
          </div>
        </div>

        <form className="student-profile-form" onSubmit={handleSubmit}>

          <h2>Edit Profile Details</h2>

          <label htmlFor="name">Full Name</label>
          <input
            id="name"
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            maxLength="100"
            required
          />

          <label htmlFor="email">Email Address</label>
          <input
            id="email"
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            maxLength="100"
            required
          />

          <label htmlFor="cgpa">CGPA</label>
          <input
            id="cgpa"
            type="number"
            name="cgpa"
            min="0"
            max="10"
            step="0.01"
            value={formData.cgpa}
            onChange={handleChange}
          />

          <label htmlFor="branchId">Branch ID</label>
          <input
            id="branchId"
            type="number"
            name="branchId"
            min="1"
            value={formData.branchId}
            onChange={handleChange}
          />

          <label htmlFor="resumePath">Resume Path</label>
          <input
            id="resumePath"
            type="text"
            name="resumePath"
            placeholder="Example: resumes/student.pdf"
            value={formData.resumePath}
            onChange={handleChange}
          />

          <p className="profile-note">
            Enter the resume file path stored by your application.
            This field does not upload a file.
          </p>

          <button
            type="submit"
            className="primary-button"
            disabled={saving}
          >
            {saving ? 'Saving Changes...' : 'Save Profile'}
          </button>

          {message && (
            <p className="profile-success" role="status">
              {message}
            </p>
          )}

          {error && (
            <p className="profile-error" role="alert">
              {error}
            </p>
          )}

        </form>

      </div>
    </div>
  )
}

export default StudentProfile
