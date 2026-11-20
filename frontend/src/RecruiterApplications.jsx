
import { useEffect, useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function RecruiterApplications({ company, onBack }) {
  const [applications, setApplications] = useState([])
  const [jobs, setJobs] = useState([])
  const [message, setMessage] = useState('')
  const [loading, setLoading] = useState(true)

  async function loadApplications() {
    try {
      setLoading(true)
      setMessage('')

      const jobsResponse = await fetch(
        `${API_BASE_URL}/jobs/company/${company.companyId}`
      )

      const jobsData = await jobsResponse.json().catch(() => [])

      if (!jobsResponse.ok) {
        throw new Error('Unable to load company jobs')
      }

      const companyJobs = Array.isArray(jobsData) ? jobsData : []
      setJobs(companyJobs)

      const applicationResults = await Promise.all(
        companyJobs.map(async (job) => {
          const response = await fetch(
            `${API_BASE_URL}/applications/job/${job.jobId}`
          )

          const data = await response.json().catch(() => [])

          if (!response.ok) {
            throw new Error(`Unable to load applications for job ${job.jobId}`)
          }

          return Array.isArray(data) ? data : []
        })
      )

      const allApplications = applicationResults.flat()
      setApplications(allApplications)
    } catch (error) {
      setMessage(error.message)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    loadApplications()
  }, [company.companyId])

  async function updateStatus(applicationId, status) {
    try {
      const response = await fetch(
        `${API_BASE_URL}/admin/applications/${applicationId}/status`,
        {
          method: 'PUT',
          headers: { 'Content-Type': 'text/plain' },
          body: status,
        }
      )

      if (!response.ok) {
        const data = await response.json().catch(() => ({}))
        throw new Error(data.message || data.error || 'Unable to update status')
      }

      setMessage(`Application ${applicationId} updated to ${status}.`)
      await loadApplications()
    } catch (error) {
      setMessage(error.message)
    }
  }

  function getJobTitle(jobId) {
    const job = jobs.find((item) => item.jobId === jobId)
    return job ? job.title : 'Unknown Job'
  }

  if (loading) {
    return <div className="recruiter-page">Loading applications...</div>
  }

  return (
    <div className="recruiter-page">
      <div className="recruiter-page-header">
        <button className="secondary-btn" onClick={onBack}>
          Back to Dashboard
        </button>
        <h2>Applications Received</h2>
        <p>Company: {company.companyName}</p>
      </div>

      {message && <p className="form-message">{message}</p>}

      {applications.length === 0 ? (
        <p>No applications have been received for your jobs yet.</p>
      ) : (
        <div className="recruiter-job-list">
          {applications.map((application) => (
            <div
              className="recruiter-job-card"
              key={application.applicationId}
            >
              <h3>Application #{application.applicationId}</h3>
              <p><strong>Job:</strong> {getJobTitle(application.jobId)}</p>
              <p><strong>Job ID:</strong> {application.jobId}</p>
              <p><strong>Student ID:</strong> {application.studentId}</p>
              <p><strong>Applied Date:</strong> {application.appliedDate}</p>
              <p><strong>Current Status:</strong> {application.status}</p>

              <label>Update Status</label>
              <select
                value={application.status}
                onChange={(event) =>
                  updateStatus(application.applicationId, event.target.value)
                }
              >
                <option value="Applied">Applied</option>
                <option value="Shortlisted">Shortlisted</option>
                <option value="Rejected">Rejected</option>
                <option value="Selected">Selected</option>
              </select>
            </div>
          ))}
        </div>
      )}
    </div>
  )
}

export default RecruiterApplications
