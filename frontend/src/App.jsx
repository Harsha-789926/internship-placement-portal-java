
import { useState } from 'react'
import { studentLogin, companyLogin, adminLogin } from './services/api'
import './App.css'

import StudentDashboard from './StudentDashboard'
import RecruiterDashboard from './RecruiterDashboard'
import AdminDashboard from './AdminDashboard'
import AdminCompanies from './AdminCompanies'
import AdminApplications from './AdminApplications'

import BrowseJobs from './BrowseJobs'
import MyApplications from './MyApplications'
import Education from './Education'
import MySkills from './MySkills'
import StudentProfile from './StudentProfile'
import StudentRegistration from './StudentRegistration'
import AdminStudents from './AdminStudents'

import PostJob from './PostJob'
import ManageJobs from './ManageJobs'
import RecruiterApplications from './RecruiterApplications'

function App() {

  // Restore saved student information
  const [studentData, setStudentData] = useState(() => {
    try {
      const savedStudent = localStorage.getItem('careerbridgeStudent')
      return savedStudent ? JSON.parse(savedStudent) : null
    } catch {
      localStorage.removeItem('careerbridgeStudent')
      return null
    }
  })

  // Restore saved recruiter information
  const [recruiterData, setRecruiterData] = useState(() => {
    try {
      const savedRecruiter = localStorage.getItem('careerbridgeCompany')
      return savedRecruiter ? JSON.parse(savedRecruiter) : null
    } catch {
      localStorage.removeItem('careerbridgeCompany')
      return null
    }
  })

  // Restore saved admin information
  const [adminData, setAdminData] = useState(() => {
    try {
      const savedAdmin = localStorage.getItem('careerbridgeAdmin')
      return savedAdmin ? JSON.parse(savedAdmin) : null
    } catch {
      localStorage.removeItem('careerbridgeAdmin')
      return null
    }
  })

  // Initial page
  const [page, setPage] = useState(() => {
    if (studentData) return 'studentDashboard'
    if (recruiterData) return 'recruiterDashboard'
    if (adminData) return 'adminDashboard'
    return 'home'
  })

  const [role, setRole] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const [loading, setLoading] = useState(false)
  const [loginMessage, setLoginMessage] = useState('')

  // Student module navigation
  const [showBrowseJobs, setShowBrowseJobs] = useState(false)
  const [showMyApplications, setShowMyApplications] = useState(false)
  const [showEducation, setShowEducation] = useState(false)
  const [showMySkills, setShowMySkills] = useState(false)
  const [showStudentProfile, setShowStudentProfile] = useState(false)

  // Recruiter module navigation
  const [recruiterPage, setRecruiterPage] = useState('dashboard')

  // Admin module navigation
  const [adminPage, setAdminPage] = useState('dashboard')

  // =====================================
  // LOGIN
  // =====================================

  
const handleLogin = async (event) => {
  event.preventDefault()

  setLoginMessage('')
  setLoading(true)

  try {
    const cleanEmail = email.trim()

    if (role === 'Student') {
      const data = await studentLogin(cleanEmail, password)

      setStudentData(data)
      localStorage.setItem('careerbridgeStudent', JSON.stringify(data))

      setLoginMessage('Student login successful!')
      setPage('studentDashboard')

    } else if (role === 'Recruiter') {
      const data = await companyLogin(cleanEmail, password)

      setRecruiterData(data)
      localStorage.setItem('careerbridgeCompany', JSON.stringify(data))

      setRecruiterPage('dashboard')
      setLoginMessage('Recruiter login successful!')
      setPage('recruiterDashboard')

    } else if (role === 'Admin') {
      const data = await adminLogin(cleanEmail, password)

      setAdminData(data)
      localStorage.setItem('careerbridgeAdmin', JSON.stringify(data))

      setAdminPage('dashboard')
      setLoginMessage('Admin login successful!')
      setPage('adminDashboard')

    } else {
      throw new Error('Please select a valid login role.')
    }

  } catch (error) {
    setLoginMessage(error.message || 'Login failed. Please try again.')
  } finally {
    setLoading(false)
  }
}

  // =====================================
  // LOGOUT
  // =====================================

  const handleLogout = () => {
    setStudentData(null)
    setRecruiterData(null)
    setAdminData(null)

    localStorage.removeItem('careerbridgeStudent')
    localStorage.removeItem('careerbridgeCompany')
    localStorage.removeItem('careerbridgeAdmin')

    setPage('home')
    setRole('')

    setEmail('')
    setPassword('')
    setLoginMessage('')

    setShowBrowseJobs(false)
    setShowMyApplications(false)
    setShowEducation(false)
    setShowMySkills(false)
    setShowStudentProfile(false)

    setRecruiterPage('dashboard')
    setAdminPage('dashboard')
  }

  // =====================================
  // LOGIN AND REGISTRATION NAVIGATION
  // =====================================

  const openLogin = (selectedRole) => {
    setRole(selectedRole)
    setEmail('')
    setPassword('')
    setLoginMessage('')
    setPage('login')
  }

  const openRegistration = () => {
    setRole('Student')
    setLoginMessage('')
    setPage('register')
  }

  const handleRegistrationSuccess = () => {
    setRole('Student')
    setEmail('')
    setPassword('')
    setLoginMessage('')
    setPage('login')
  }

  // =====================================
  // STUDENT NAVIGATION
  // =====================================

  const backToDashboard = () => {
    setShowBrowseJobs(false)
    setShowMyApplications(false)
    setShowEducation(false)
    setShowMySkills(false)
    setShowStudentProfile(false)
  }

  const handleProfileUpdated = (updatedStudent) => {
    setStudentData(updatedStudent)

    localStorage.setItem(
      'careerbridgeStudent',
      JSON.stringify(updatedStudent)
    )
  }

  // =====================================
  // RECRUITER DASHBOARD AND MODULES
  // =====================================

  if (page === 'recruiterDashboard' && recruiterData) {

    if (recruiterPage === 'postJob') {
      return (
        <PostJob
          company={recruiterData}
          onBack={() => setRecruiterPage('dashboard')}
        />
      )
    }

    if (recruiterPage === 'manageJobs') {
      return (
        <ManageJobs
          company={recruiterData}
          onBack={() => setRecruiterPage('dashboard')}
        />
      )
    }

    if (recruiterPage === 'applications') {
      return (
        <RecruiterApplications
          company={recruiterData}
          onBack={() => setRecruiterPage('dashboard')}
        />
      )
    }

    return (
      <RecruiterDashboard
        company={recruiterData}
        onLogout={handleLogout}
        onPostJob={() => setRecruiterPage('postJob')}
        onManageJobs={() => setRecruiterPage('manageJobs')}
        onViewApplications={() => setRecruiterPage('applications')}
      />
    )
  }

  // =====================================
  // ADMIN DASHBOARD
  // =====================================
// ADMIN DASHBOARD


if (page === 'adminDashboard' && adminData) {

  if (adminPage === 'students') {
    return (
      <AdminStudents
        onBack={() => setAdminPage('dashboard')}
      />
    )
  }

  if (adminPage === 'companies') {
    return (
      <AdminCompanies
        onBack={() => setAdminPage('dashboard')}
      />
    )
  }

  if (adminPage === 'applications') {
    return (
      <AdminApplications
        onBack={() => setAdminPage('dashboard')}
      />
    )
  }

  return (
    <AdminDashboard
      admin={adminData}
      onLogout={handleLogout}
      onManageStudents={() => setAdminPage('students')}
      onVerifyCompanies={() => setAdminPage('companies')}
      onManageApplications={() => setAdminPage('applications')}
    />
  )
}



  // =====================================
  // STUDENT DASHBOARD AND MODULES
  // =====================================

  if (page === 'studentDashboard' && studentData) {

    if (showBrowseJobs) {
      return (
        <BrowseJobs
          student={studentData}
          onBack={backToDashboard}
        />
      )
    }

    if (showMyApplications) {
      return (
        <MyApplications
          student={studentData}
          onBack={backToDashboard}
        />
      )
    }

    if (showEducation) {
      return (
        <Education
          student={studentData}
          onBack={backToDashboard}
        />
      )
    }

    if (showMySkills) {
      return (
        <MySkills
          student={studentData}
          onBack={backToDashboard}
        />
      )
    }

    if (showStudentProfile) {
      return (
        <StudentProfile
          student={studentData}
          onBack={backToDashboard}
          onProfileUpdated={handleProfileUpdated}
        />
      )
    }

    return (
      <StudentDashboard
        student={studentData}
        onLogout={handleLogout}
        onBrowseJobs={() => setShowBrowseJobs(true)}
        onMyApplications={() => setShowMyApplications(true)}
        onEducation={() => setShowEducation(true)}
        onMySkills={() => setShowMySkills(true)}
        onEditProfile={() => setShowStudentProfile(true)}
      />
    )
  }

  // =====================================
  // MAIN APPLICATION PAGES
  // =====================================

  return (
    <div className="app">

      {/* HOME PAGE */}
      {page === 'home' && (
        <>
          <nav className="navbar">

            <h2 className="logo">CareerBridge</h2>

            <div className="nav-links">
              <a href="#home">Home</a>
              <a href="#about">About</a>
              <a href="#features">Features</a>
              <a href="#contact">Contact</a>
            </div>

            <button
              className="nav-button"
              onClick={() => setPage('roles')}
            >
              Get Started
            </button>

          </nav>

          <section className="hero" id="home">

            <div className="hero-content">

              <p className="tagline">
                YOUR CAREER JOURNEY STARTS HERE
              </p>

              <h1>
                Connect Your Skills.
                <br />
                Discover Your Future.
              </h1>

              <p>
                CareerBridge connects students, recruiters, and
                administrators through one platform for internships
                and placement opportunities.
              </p>

              <button
                className="primary-button"
                onClick={() => setPage('roles')}
              >
                Explore CareerBridge
              </button>

            </div>

          </section>

          <section className="features-section" id="about">

            <p className="tagline">ABOUT CAREERBRIDGE</p>

            <h2>One Platform. Multiple Opportunities.</h2>

            <p className="section-description">
              Manage placements, discover opportunities, and
              connect talented students with recruiters.
            </p>

            <div className="features-grid" id="features">

              <div className="feature-card">
                <div className="feature-icon">🎓</div>
                <h3>Students</h3>
                <p>
                  Create your profile, manage skills, explore jobs,
                  and track applications.
                </p>
              </div>

              <div className="feature-card">
                <div className="feature-icon">🏢</div>
                <h3>Recruiters</h3>
                <p>
                  Connect with talented students and manage
                  internship and placement opportunities.
                </p>
              </div>

              <div className="feature-card">
                <div className="feature-icon">⚙️</div>
                <h3>Administrators</h3>
                <p>
                  Manage students, verify companies, and monitor
                  placement activities.
                </p>
              </div>

            </div>

          </section>

          <footer className="footer" id="contact">
            <h2>CareerBridge</h2>

            <p>
              Connecting students with career opportunities.
            </p>

            <p>
              © 2026 CareerBridge. All rights reserved.
            </p>
          </footer>
        </>
      )}

      {/* ROLE SELECTION PAGE */}
      {page === 'roles' && (
        <section className="role-page">

          <button
            className="back-button"
            onClick={() => setPage('home')}
          >
            ← Back to Home
          </button>

          <div className="role-header">

            <p className="tagline">
              WELCOME TO CAREERBRIDGE
            </p>

            <h1>Choose Your Role</h1>

            <p>
              Select your account type to continue.
            </p>

          </div>

          <div className="role-cards">

            {/* Student */}
            <div className="role-card">

              <div className="feature-icon">🎓</div>

              <h2>Student</h2>

              <p>
                Access your profile, discover jobs, and manage
                your applications.
              </p>

              <button
                className="primary-button"
                onClick={() => openLogin('Student')}
              >
                Student Login
              </button>

              <button
                className="secondary-button"
                onClick={openRegistration}
              >
                Create Student Account
              </button>

            </div>

            {/* Recruiter */}
            <div className="role-card">

              <div className="feature-icon">🏢</div>

              <h2>Recruiter</h2>

              <p>
                Manage your company profile and placement
                opportunities.
              </p>

              <button
                className="primary-button"
                onClick={() => openLogin('Recruiter')}
              >
                Recruiter Login
              </button>

            </div>

            {/* Admin */}
            <div className="role-card">

              <div className="feature-icon">⚙️</div>

              <h2>Admin</h2>

              <p>
                Manage students, companies, and placement
                applications.
              </p>

              <button
                className="primary-button"
                onClick={() => openLogin('Admin')}
              >
                Admin Login
              </button>

            </div>

          </div>

        </section>
      )}

      {/* STUDENT REGISTRATION PAGE */}
      {page === 'register' && (
        <StudentRegistration
          onBack={() => setPage('roles')}
          onRegistrationSuccess={handleRegistrationSuccess}
        />
      )}

      {/* LOGIN PAGE */}
      {page === 'login' && (
        <section className="login-page">

          <div className="login-card">

            <button
              className="back-button"
              onClick={() => setPage('roles')}
            >
              ← Back
            </button>

            <p className="tagline">
              CAREERBRIDGE PORTAL
            </p>

            <h1>{role} Login</h1>

            <p className="login-description">
              Enter your account details to continue.
            </p>

            <form onSubmit={handleLogin}>

              <label>Email Address</label>

              <input
                type="email"
                placeholder="Enter your email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                required
              />

              <label>Password</label>

              <input
                type="password"
                placeholder="Enter your password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                required
              />

              <button
                type="submit"
                className="primary-button login-button"
                disabled={loading}
              >
                {loading ? 'Logging in...' : 'Login'}
              </button>

            </form>

            {loginMessage && (
              <p className="login-message" role="status">
                {loginMessage}
              </p>
            )}

            {/* Student Registration Link */}
            {role === 'Student' && (
              <p className="registration-link">
                Don't have a student account?{' '}

                <button
                  type="button"
                  onClick={openRegistration}
                >
                  Register here
                </button>
              </p>
            )}

          </div>

        </section>
      )}

    </div>
  )
}

export default App
