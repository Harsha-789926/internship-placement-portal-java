
import { useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function PostJob({ company, onBack }) {
  const [formData, setFormData] = useState({
    jobId: '',
    title: '',
    description: '',
    packageAmount: '',
    location: '',
    minimumCgpa: '',
    deadline: '',
  })

  const [message, setMessage] = useState('')
  const [loading, setLoading] = useState(false)

  function handleChange(event) {
    const { name, value } = event.target
    setFormData({ ...formData, [name]: value })
  }

  async function handleSubmit(event) {
    event.preventDefault()
    setMessage('')

    const payload = {
      jobId: Number(formData.jobId),
      companyId: company.companyId,
      title: formData.title.trim(),
      description: formData.description.trim(),
      packageAmount: formData.packageAmount.trim(),
      location: formData.location.trim(),
      minimumCgpa: Number(formData.minimumCgpa),
      deadline: formData.deadline,
    }

    try {
      setLoading(true)

      const response = await fetch(`${API_BASE_URL}/jobs`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      })

      const data = await response.json().catch(() => ({}))

      if (!response.ok) {
        throw new Error(data.message || data.error || 'Unable to post job')
      }

      setMessage('Job posted successfully!')

      setFormData({
        jobId: '',
        title: '',
        description: '',
        packageAmount: '',
        location: '',
        minimumCgpa: '',
        deadline: '',
      })
    } catch (error) {
      setMessage(error.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="recruiter-page">
      <div className="recruiter-page-header">
        <button className="secondary-btn" onClick={onBack}>
          Back to Dashboard
        </button>
        <h2>Post a New Job</h2>
        <p>Company: {company.companyName}</p>
      </div>

      <form className="recruiter-form" onSubmit={handleSubmit}>
        <label>Job ID</label>
        <input
          type="number"
          name="jobId"
          value={formData.jobId}
          onChange={handleChange}
          min="1"
          required
        />

        <label>Job Title</label>
        <input
          type="text"
          name="title"
          value={formData.title}
          onChange={handleChange}
          maxLength="100"
          required
        />

        <label>Job Description</label>
        <textarea
          name="description"
          value={formData.description}
          onChange={handleChange}
          maxLength="200"
          rows="4"
        />

        <label>Package</label>
        <input
          type="text"
          name="packageAmount"
          value={formData.packageAmount}
          onChange={handleChange}
          placeholder="Example: 5 LPA"
          maxLength="100"
        />

        <label>Location</label>
        <input
          type="text"
          name="location"
          value={formData.location}
          onChange={handleChange}
          maxLength="200"
        />

        <label>Minimum CGPA</label>
        <input
          type="number"
          name="minimumCgpa"
          value={formData.minimumCgpa}
          onChange={handleChange}
          min="0"
          max="10"
          step="0.01"
          required
        />

        <label>Application Deadline</label>
        <input
          type="date"
          name="deadline"
          value={formData.deadline}
          onChange={handleChange}
          min={new Date().toLocaleDateString('en-CA')}
          required
        />

        <button type="submit" disabled={loading}>
          {loading ? 'Posting...' : 'Post Job'}
        </button>

        {message && <p className="form-message">{message}</p>}
      </form>
    </div>
  )
}

export default PostJob
