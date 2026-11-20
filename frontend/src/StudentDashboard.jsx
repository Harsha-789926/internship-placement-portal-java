
function StudentDashboard({
  student,
  onLogout,
  onBrowseJobs,
  onMyApplications,
  onEducation,
  onMySkills,
  onEditProfile
}) {
  return (
    <div className="dashboard">

      <nav className="navbar student-navbar">
        <h2 className="logo">CareerBridge</h2>

        <div className="student-nav-links">
          <button onClick={onBrowseJobs}>Browse Jobs</button>
          <button onClick={onMyApplications}>My Applications</button>
          <button onClick={onEducation}>Education</button>
          <button onClick={onMySkills}>My Skills</button>
        </div>

        <button className="nav-button" onClick={onLogout}>
          Logout
        </button>
      </nav>

      <section className="dashboard-content">

        <div className="dashboard-welcome">
          <p className="tagline">STUDENT PORTAL</p>
          <h1>Welcome, {student.name}!</h1>
          <p>Your career journey continues here.</p>
        </div>

        <div className="dashboard-profile">
          <div className="profile-heading">
            <h2>My Profile</h2>
            <button
              className="edit-profile-button"
              onClick={onEditProfile}
            >
              Edit Profile
            </button>
          </div>

          <div className="profile-details">
            <p><strong>Student ID:</strong> {student.studentId}</p>
            <p><strong>Name:</strong> {student.name}</p>
            <p><strong>Email:</strong> {student.email}</p>
            <p><strong>CGPA:</strong> {student.cgpa}</p>
            <p><strong>Branch ID:</strong> {student.branchId ?? 'Not specified'}</p>
            <p><strong>Resume:</strong> {student.resumePath || 'Not uploaded'}</p>
          </div>
        </div>

        <h2 className="dashboard-heading">My Career Activities</h2>

        <div className="dashboard-cards">

          <div className="dashboard-card">
            <div className="feature-icon">💼</div>
            <h3>Browse Jobs</h3>
            <p>Explore available internships and job opportunities.</p>
            <button className="primary-button" onClick={onBrowseJobs}>
              Explore Jobs
            </button>
          </div>

          <div className="dashboard-card">
            <div className="feature-icon">📄</div>
            <h3>My Applications</h3>
            <p>Track your application and selection status.</p>
            <button className="primary-button" onClick={onMyApplications}>
              View Applications
            </button>
          </div>

          <div className="dashboard-card">
            <div className="feature-icon">🎓</div>
            <h3>Education</h3>
            <p>View and manage your academic details.</p>
            <button className="primary-button" onClick={onEducation}>
              View Education
            </button>
          </div>

          <div className="dashboard-card">
            <div className="feature-icon">⚡</div>
            <h3>My Skills</h3>
            <p>View and manage your technical skills.</p>
            <button className="primary-button" onClick={onMySkills}>
              Manage Skills
            </button>
          </div>

        </div>
      </section>
    </div>
  )
}

export default StudentDashboard
