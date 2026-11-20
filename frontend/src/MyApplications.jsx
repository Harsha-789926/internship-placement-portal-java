
import { useEffect, useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function MyApplications({ student, onBack }) {
  const [applications, setApplications] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    async function fetchApplications() {
      try {
        const response = await fetch(
          `${API_BASE_URL}/applications/student/${student.studentId}`
        )

        if (!response.ok) {
          throw new Error('Unable to load applications')
        }

        const data = await response.json()
        setApplications(Array.isArray(data) ? data : [])
      } catch (err) {
        setError(err.message)
      } finally {
        setLoading(false)
      }
    }

    fetchApplications()
  }, [student.studentId])

  return (
    <div className="my-applications-page">
      <div className="jobs-header">
        <button className="back-button" onClick={onBack}>
          ← Back to Dashboard
        </button>
        <p className="tagline">STUDENT PORTAL</p>
        <h1>My Applications</h1>
        <p>Track the progress of your job applications.</p>
      </div>

      <div className="applications-container">
        {loading && <p>Loading your applications...</p>}

        {error && <p className="jobs-error">{error}</p>}

        {!loading && !error && applications.length === 0 && (
          <p>You haven't applied for any jobs yet.</p>
        )}

        {!loading && !error && applications.map((application) => (
          <div className="application-card" key={application.applicationId}>
            <h2>Job ID: {application.jobId}</h2>
            <p>
              <strong>Application ID:</strong> {application.applicationId}
            </p>
            <p>
              <strong>Applied Date:</strong> {application.appliedDate}
            </p>
            <p>
              <strong>Status:</strong> {application.status}
            </p>
            <span className="application-status">
              {application.status}
            </span>
          </div>
        ))}
      </div>
    </div>
  )
}

export default MyApplications
