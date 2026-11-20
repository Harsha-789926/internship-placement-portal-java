
function RecruiterDashboard({
  company,
  onLogout,
  onPostJob,
  onManageJobs,
  onViewApplications
}) {
  return (
    <div className="recruiter-dashboard">

      <nav className="navbar recruiter-navbar">
        <h2 className="logo">CareerBridge</h2>

        <div className="recruiter-nav-links">
          <button onClick={onPostJob}>Post Jobs</button>
          <button onClick={onManageJobs}>Manage Jobs</button>
          <button onClick={onViewApplications}>Applications</button>
        </div>

        <button className="recruiter-logout" onClick={onLogout}>
          Logout
        </button>
      </nav>

      <main className="recruiter-dashboard-content">

        <section className="recruiter-welcome">
          <p className="tagline">RECRUITER PORTAL</p>
          <h1>Welcome, {company.companyName}!</h1>
          <p>Manage your company and placement opportunities.</p>
        </section>

        <section className="company-profile-card">
          <div className="company-profile-heading">
            <h2>Company Profile</h2>
            <span className="verification-badge">
              {company.verificationStatus}
            </span>
          </div>

          <div className="company-details">
            <p><strong>Company ID:</strong> {company.companyId}</p>
            <p><strong>Company Name:</strong> {company.companyName}</p>
            <p><strong>Email:</strong> {company.email}</p>
            <p><strong>Contact:</strong> {company.contactNo}</p>
            <p><strong>Location:</strong> {company.location}</p>
          </div>
        </section>

        <section className="recruiter-features">
          <h2>Recruiter Activities</h2>
          <p className="activities-description">
            Manage your job opportunities and student applications.
          </p>

          <div className="recruiter-card-grid">

            <div className="recruiter-card">
              <div className="recruiter-icon">📢</div>
              <h3>Post Jobs</h3>
              <p>Create new internship and placement opportunities.</p>
              <button className="primary-button" onClick={onPostJob}>
                Post a Job
              </button>
            </div>

            <div className="recruiter-card">
              <div className="recruiter-icon">💼</div>
              <h3>Manage Jobs</h3>
              <p>View, edit, and delete your posted jobs.</p>
              <button className="primary-button" onClick={onManageJobs}>
                Manage Jobs
              </button>
            </div>

            <div className="recruiter-card">
              <div className="recruiter-icon">📄</div>
              <h3>Applications</h3>
              <p>View applications submitted by students.</p>
              <button className="primary-button" onClick={onViewApplications}>
                View Applications
              </button>
            </div>

          </div>
        </section>

      </main>
    </div>
  )
}

export default RecruiterDashboard
