
import { useEffect, useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function AdminCompanies({ onBack }) {
  const [companies, setCompanies] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [message, setMessage] = useState('')

  const fetchCompanies = async () => {
    try {
      setLoading(true)
      setError('')

      const response = await fetch(`${API_BASE_URL}/admin/companies`)
      const data = await response.json()

      if (!response.ok) {
        throw new Error(data.message || data.error || 'Unable to fetch companies')
      }

      setCompanies(data)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchCompanies()
  }, [])

  const updateCompanyStatus = async (companyId, action) => {
    const actionText = action === 'verify' ? 'verify' : 'reject'

    const confirmed = window.confirm(
      `Are you sure you want to ${actionText} company ID ${companyId}?`
    )

    if (!confirmed) return

    try {
      setMessage('')
      setError('')

      const response = await fetch(
        `${API_BASE_URL}/admin/companies/${companyId}/${action}`,
        { method: 'PUT' }
      )

      const text = await response.text()

      if (!response.ok) {
        throw new Error(text || `Unable to ${actionText} company`)
      }

      setMessage(text || `Company ${actionText} action successful.`)

      await fetchCompanies()
    } catch (err) {
      setError(err.message)
    }
  }

  return (
    <div className="admin-companies-page">
      <nav className="navbar admin-module-navbar">
        <h2 className="logo">CareerBridge</h2>

        <button className="secondary-button" onClick={onBack}>
          ← Back to Dashboard
        </button>
      </nav>

      <main className="admin-companies-content">
        <div className="admin-module-heading">
          <p className="tagline">ADMINISTRATION</p>
          <h1>Company Verification</h1>
          <p>Review registered companies and manage their verification status.</p>
        </div>

        <div className="admin-company-summary">
          <div>
            <h2>Total Companies: {companies.length}</h2>
            <p>
              Pending: {companies.filter(c => c.verificationStatus === 'Pending').length}
              {' | '}
              Verified: {companies.filter(c => c.verificationStatus === 'Verified').length}
              {' | '}
              Rejected: {companies.filter(c => c.verificationStatus === 'Rejected').length}
            </p>
          </div>

          <button
            className="primary-button"
            onClick={fetchCompanies}
            disabled={loading}
          >
            {loading ? 'Loading...' : 'Refresh'}
          </button>
        </div>

        {message && <p className="admin-success-message">{message}</p>}
        {error && <p className="admin-error-message">{error}</p>}

        {loading ? (
          <p className="admin-loading">Loading companies...</p>
        ) : companies.length === 0 ? (
          <p className="admin-empty-message">No companies found.</p>
        ) : (
          <div className="admin-table-container">
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Company ID</th>
                  <th>Company Name</th>
                  <th>Email</th>
                  <th>Contact</th>
                  <th>Location</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>

              <tbody>
                {companies.map(company => (
                  <tr key={company.companyId}>
                    <td>{company.companyId}</td>
                    <td>{company.companyName}</td>
                    <td>{company.email}</td>
                    <td>{company.contactNo || 'N/A'}</td>
                    <td>{company.location || 'N/A'}</td>
                    <td>
                      <span className={`company-status ${company.verificationStatus?.toLowerCase()}`}>
                        {company.verificationStatus}
                      </span>
                    </td>
                    <td>
                      {company.verificationStatus === 'Pending' ? (
                        <div className="company-action-buttons">
                          <button
                            className="admin-verify-button"
                            onClick={() => updateCompanyStatus(company.companyId, 'verify')}
                          >
                            Verify
                          </button>

                          <button
                            className="admin-delete-button"
                            onClick={() => updateCompanyStatus(company.companyId, 'reject')}
                          >
                            Reject
                          </button>
                        </div>
                      ) : (
                        <span>No action required</span>
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

export default AdminCompanies
