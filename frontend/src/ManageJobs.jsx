
import { useEffect, useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function ManageJobs({ company, onBack }) {
  const [jobs, setJobs] = useState([])
  const [editingJob, setEditingJob] = useState(null)
  const [message, setMessage] = useState('')
  const [loading, setLoading] = useState(true)

  async function loadJobs() {
    try {
      setLoading(true)

      const response = await fetch(
        `${API_BASE_URL}/jobs/company/${company.companyId}`
      )

      const data = await response.json().catch(() => [])

      if (!response.ok) {
        throw new Error(data.message || data.error || 'Unable to load jobs')
      }

      setJobs(Array.isArray(data) ? data : [])
    } catch (error) {
      setMessage(error.message)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    loadJobs()
  }, [company.companyId])

  function startEditing(job) {
    setEditingJob({
      ...job,
      minimumCgpa: job.minimumCgpa ?? '',
    })
    setMessage('')
  }

  function handleEditChange(event) {
    const { name, value } = event.target
    setEditingJob({ ...editingJob, [name]: value })
  }

  async function updateJob(event) {
    event.preventDefault()

    try {
      const payload = {
        jobId: editingJob.jobId,
        companyId: company.companyId,
        title: editingJob.title,
        description: editingJob.description,
        packageAmount: editingJob.packageAmount,
        location: editingJob.location,
        minimumCgpa: Number(editingJob.minimumCgpa),
        deadline: editingJob.deadline,
      }

      const response = await fetch(
        `${API_BASE_URL}/jobs/${editingJob.jobId}`,
        {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload),
        }
      )

      const data = await response.json().catch(() => ({}))

      if (!response.ok) {
        throw new Error(data.message || data.error || 'Unable to update job')
      }

      setMessage('Job updated successfully!')
      setEditingJob(null)
      await loadJobs()
    } catch (error) {
      setMessage(error.message)
    }
  }

  async function deleteJob(jobId) {
    const confirmed = window.confirm(
      'Are you sure you want to delete this job?'
    )

    if (!confirmed) return

    try {
      const response = await fetch(`${API_BASE_URL}/jobs/${jobId}`, {
        method: 'DELETE',
      })

      if (!response.ok) {
        const data = await response.json().catch(() => ({}))
        throw new Error(data.message || data.error || 'Unable to delete job')
      }

      setMessage('Job deleted successfully!')
      await loadJobs()
    } catch (error) {
      setMessage(error.message)
    }
  }

  if (loading) {
    return <div className="recruiter-page">Loading jobs...</div>
  }

  return (
    <div className="recruiter-page">
      <div className="recruiter-page-header">
        <button className="secondary-btn" onClick={onBack}>
          Back to Dashboard
        </button>
        <h2>Manage Jobs</h2>
        <p>Company: {company.companyName}</p>
      </div>

      {message && <p className="form-message">{message}</p>}

      {editingJob && (
        <form className="recruiter-form" onSubmit={updateJob}>
          <h3>Edit Job #{editingJob.jobId}</h3>

          <label>Job Title</label>
          <input
            name="title"
            value={editingJob.title || ''}
            onChange={handleEditChange}
            maxLength="100"
            required
          />

          <label>Description</label>
          <textarea
            name="description"
            value={editingJob.description || ''}
            onChange={handleEditChange}
            maxLength="200"
            rows="4"
          />

          <label>Package</label>
          <input
            name="packageAmount"
            value={editingJob.packageAmount || ''}
            onChange={handleEditChange}
            maxLength="100"
          />

          <label>Location</label>
          <input
            name="location"
            value={editingJob.location || ''}
            onChange={handleEditChange}
            maxLength="200"
          />

          <label>Minimum CGPA</label>
          <input
            type="number"
            name="minimumCgpa"
            value={editingJob.minimumCgpa}
            onChange={handleEditChange}
            min="0"
            max="10"
            step="0.01"
            required
          />

          <label>Deadline</label>
          <input
            type="date"
            name="deadline"
            value={editingJob.deadline || ''}
            onChange={handleEditChange}
            min={new Date().toLocaleDateString('en-CA')}
            required
          />

          <button type="submit">Save Changes</button>
          <button
            type="button"
            className="secondary-btn"
            onClick={() => setEditingJob(null)}
          >
            Cancel
          </button>
        </form>
      )}

      <div className="recruiter-job-list">
        {jobs.length === 0 ? (
          <p>You have not posted any jobs yet.</p>
        ) : (
          jobs.map((job) => (
            <div className="recruiter-job-card" key={job.jobId}>
              <h3>{job.title}</h3>
              <p><strong>Job ID:</strong> {job.jobId}</p>
              <p><strong>Description:</strong> {job.description}</p>
              <p><strong>Package:</strong> {job.packageAmount}</p>
              <p><strong>Location:</strong> {job.location}</p>
              <p><strong>Minimum CGPA:</strong> {job.minimumCgpa}</p>
              <p><strong>Deadline:</strong> {job.deadline}</p>

              <div className="job-actions">
                <button onClick={() => startEditing(job)}>Edit</button>
                <button
                  className="danger-btn"
                  onClick={() => deleteJob(job.jobId)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  )
}

export default ManageJobs
