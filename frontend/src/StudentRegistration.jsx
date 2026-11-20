
import { useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function StudentRegistration({ onBack, onRegistrationSuccess }) {
  const [formData, setFormData] = useState({
    studentId: '',
    name: '',
    email: '',
    password: '',
    cgpa: '',
    branchId: ''
  })

  const [loading, setLoading] = useState(false)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')

  const handleChange = (event) => {
    const { name, value } = event.target
    setFormData((previous) => ({
      ...previous,
      [name]: value
    }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault()
    setMessage('')
    setError('')

    const cgpa = Number(formData.cgpa)

    if (!Number.isFinite(cgpa) || cgpa < 0 || cgpa > 10) {
      setError('CGPA must be between 0 and 10.')
      return
    }

    const payload = {
      studentId: Number(formData.studentId),
      name: formData.name.trim(),
      email: formData.email.trim(),
      password: formData.password,
      cgpa,
      branchId: formData.branchId === '' ? null : Number(formData.branchId)
    }

    if (!Number.isInteger(payload.studentId) || payload.studentId <= 0) {
      setError('Enter a valid Student ID.')
      return
    }

    if (payload.branchId !== null &&
        (!Number.isInteger(payload.branchId) || payload.branchId <= 0)) {
      setError('Enter a valid Branch ID.')
      return
    }

    setLoading(true)

    try {
      const response = await fetch(`${API_BASE_URL}/students/register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      })

      const data = await response.json().catch(() => ({}))

      if (!response.ok) {
        throw new Error(
          data.message || data.error || 'Registration failed. Please try again.'
        )
      }

      setMessage('Registration successful! You can now log in.')

      setFormData({
        studentId: '',
        name: '',
        email: '',
        password: '',
        cgpa: '',
        branchId: ''
      })

      if (onRegistrationSuccess) {
        onRegistrationSuccess()
      }
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="registration-page">
      <div className="registration-container">

        <button className="back-button" onClick={onBack}>
          ← Back
        </button>

        <div className="registration-heading">
          <p className="tagline">CAREERBRIDGE STUDENT PORTAL</p>
          <h1>Create Your Student Account</h1>
          <p>Register to explore internships and placement opportunities.</p>
        </div>

        <form className="registration-form" onSubmit={handleSubmit}>

          <label htmlFor="studentId">Student ID</label>
          <input
            id="studentId"
            name="studentId"
            type="number"
            min="1"
            value={formData.studentId}
            onChange={handleChange}
            required
          />

          <label htmlFor="name">Full Name</label>
          <input
            id="name"
            name="name"
            type="text"
            maxLength="100"
            value={formData.name}
            onChange={handleChange}
            required
          />

          <label htmlFor="email">Email Address</label>
          <input
            id="email"
            name="email"
            type="email"
            maxLength="100"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <label htmlFor="password">Password</label>
          <input
            id="password"
            name="password"
            type="password"
            minLength="6"
            maxLength="100"
            value={formData.password}
            onChange={handleChange}
            required
          />

          <label htmlFor="cgpa">CGPA</label>
          <input
            id="cgpa"
            name="cgpa"
            type="number"
            min="0"
            max="10"
            step="0.01"
            value={formData.cgpa}
            onChange={handleChange}
            required
          />

          <label htmlFor="branchId">Branch ID</label>
          <input
            id="branchId"
            name="branchId"
            type="number"
            min="1"
            value={formData.branchId}
            onChange={handleChange}
          />

          <p className="registration-note">
            Student ID must be unique. Enter an existing Branch ID from your database.
          </p>

          <button
            type="submit"
            className="primary-button"
            disabled={loading}
          >
            {loading ? 'Registering...' : 'Create Account'}
          </button>

          {message && (
            <p className="registration-success" role="status">
              {message}
            </p>
          )}

          {error && (
            <p className="registration-error" role="alert">
              {error}
            </p>
          )}

        </form>
      </div>
    </div>
  )
}

export default StudentRegistration
