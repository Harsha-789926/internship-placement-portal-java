
import { useEffect, useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function AdminApplications({ onBack }) {
  const [applications, setApplications] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [message, setMessage] = useState('')
  const [updatingId, setUpdatingId] = useState(null)

  const fetchApplications = async () => {
    try {
      setLoading(true)
      setError('')

      const response = await fetch(`${API_BASE_URL}/admin/applications`)
      const data = await response.json()

      if (!response.ok) {
        throw new Error(data.message || data.error || 'Unable to fetch applications')
      }

      setApplications(data)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchApplications()
  }, [])

  const updateStatus = async (applicationId, newStatus) => {
    try {
      setUpdatingId(applicationId)
      setError('')
      setMessage('')

      const response = await fetch(
        `${API_BASE_URL}/admin/applications/${applicationId}/status`,
        {
          method: 'PUT',
          headers: {
            'Content-Type': 'text/plain'
          },
          body: newStatus
        }
      )

      const text = await response.text()

      if (!response.ok) {
        throw new Error(text || 'Unable to update application status')
      }

      setMessage(text || `Application ${applicationId} updated successfully.`)

      await fetchApplications()
    } catch (err) {
      setError(err.message)
    } finally {
      setUpdatingId(null)
    }
  }

  const statusOptions = [
    'Applied',
    'Shortlisted',
    'Rejected',
    'Selected'
  ]

  return (
    <div className="admin-applications-page">

      <nav className="navbar admin-module-navbar">
        <h2 className="logo">CareerBridge</h2>

        <button className="secondary-button" onClick={onBack}>
          ← Back to Dashboard
        </button>
      </nav>

      <main className="admin-applications-content">

        <div className="admin-module-heading">
          <p className="tagline">ADMINISTRATION</p>
          <h1>Application Management</h1>
          <p>Monitor student applications and manage their placement status.</p>
        </div>

        <div className="admin-application-summary">
          <div>
            <h2>Total Applications: {applications.length}</h2>
            <p>
              Applied: {applications.filter(a => a.status === 'Applied').length}
              {' | '}
              Shortlisted: {applications.filter(a => a.status === 'Shortlisted').length}
              {' | '}
              Selected: {applications.filter(a => a.status === 'Selected').length}
              {' | '}
              Rejected: {applications.filter(a => a.status === 'Rejected').length}
            </p>
          </div>

          <button
            className="primary-button"
            onClick={fetchApplications}
            disabled={loading}
          >
            {loading ? 'Loading...' : 'Refresh'}
          </button>
        </div>

        {message && (
          <p className="admin-success-message">{message}</p>
        )}

        {error && (
          <p className="admin-error-message">{error}</p>
        )}

        {loading ? (
          <p className="admin-loading">Loading applications...</p>
        ) : applications.length === 0 ? (
          <p className="admin-empty-message">No applications found.</p>
        ) : (

          <div className="admin-table-container">
            <table className="admin-table">

              <thead>
                <tr>
                  <th>Application ID</th>
                  <th>Student ID</th>
                  <th>Job ID</th>
                  <th>Applied Date</th>
                  <th>Current Status</th>
                  <th>Update Status</th>
                </tr>
              </thead>

              <tbody>
                {applications.map(application => (

                  <tr key={application.applicationId}>

                    <td>{application.applicationId}</td>

                    <td>{application.studentId}</td>

                    <td>{application.jobId}</td>

                    <td>{application.appliedDate || 'N/A'}</td>

                    <td>
                      <span className={`application-status ${application.status?.toLowerCase()}`}>
                        {application.status}
                      </span>
                    </td>

                    <td>
                      <select
                        className="application-status-select"
                        value={application.status}
                        disabled={updatingId === application.applicationId}
                        onChange={(event) =>
                          updateStatus(
                            application.applicationId,
                            event.target.value
                          )
                        }
                      >
                        {statusOptions.map(status => (
                          <option key={status} value={status}>
                            {status}
                          </option>
                        ))}
                      </select>

                      {updatingId === application.applicationId && (
                        <span className="application-updating">
                          Updating...
                        </span>
                      )}
                    </td>

                  </tr>

                ))}
              </tbody>

            </table>
          </div>

        )}

      </main>
    </div>
  )
}

export default AdminApplications
