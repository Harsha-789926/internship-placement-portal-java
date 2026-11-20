
function AdminDashboard({
  admin,
  onLogout,
  onManageStudents,
  onVerifyCompanies,
  onManageApplications
}) {
  return (
    <div className="admin-dashboard">

      <nav className="navbar admin-navbar">
        <h2 className="logo">CareerBridge</h2>

        <div className="admin-nav-links">
          <button onClick={onManageStudents}>
            Students
          </button>

          <button onClick={onVerifyCompanies}>
            Companies
          </button>

          <button onClick={onManageApplications}>
            Applications
          </button>
        </div>

        <button className="nav-button" onClick={onLogout}>
          Logout
        </button>
      </nav>

      <main className="admin-dashboard-content">

        <section className="admin-welcome">
          <p className="tagline">ADMINISTRATOR PORTAL</p>

          <h1>
            Welcome, {admin?.name || 'Administrator'}!
          </h1>

          <p>
            Manage students, verify companies, and monitor
            placement applications.
          </p>
        </section>

        <section className="admin-profile-card">
          <h2>Admin Profile</h2>

          <div className="admin-details">
            <p>
              <strong>Admin ID:</strong>{' '}
              {admin?.adminId || 'Administrator'}
            </p>

            <p>
              <strong>Name:</strong>{' '}
              {admin?.name || 'Administrator'}
            </p>

            <p>
              <strong>Email:</strong>{' '}
              {admin?.email || 'Not available'}
            </p>

            <p>
              <strong>Department:</strong>{' '}
              {admin?.department || 'Not specified'}
            </p>
          </div>
        </section>

        <section className="admin-activities">

          <h2>Administration Activities</h2>

          <p className="admin-description">
            Select a section to manage CareerBridge activities.
          </p>

          <div className="admin-card-grid">

            <div className="admin-card">
              <div className="admin-icon">🎓</div>

              <h3>Manage Students</h3>

              <p>
                View student profiles and manage student records.
              </p>

              <button
                className="primary-button"
                onClick={onManageStudents}
              >
                Manage Students
              </button>
            </div>

            <div className="admin-card">
              <div className="admin-icon">🏢</div>

              <h3>Verify Companies</h3>

              <p>
                Review company registrations and approve or reject
                verification requests.
              </p>

              <button
                className="primary-button"
                onClick={onVerifyCompanies}
              >
                Verify Companies
              </button>
            </div>

            <div className="admin-card">
              <div className="admin-icon">📄</div>

              <h3>Manage Applications</h3>

              <p>
                View student applications and update their status.
              </p>

              <button
                className="primary-button"
                onClick={onManageApplications}
              >
                Manage Applications
              </button>
            </div>

          </div>
        </section>

      </main>
    </div>
  )
}

export default AdminDashboard
