
import { useEffect, useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function AdminStudents({ onBack }) {
  const [students, setStudents] = useState([])
  const [loading, setLoading] = useState(true)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')

  // Fetch all students
  const fetchStudents = async () => {
    try {
      setLoading(true)
      setError('')

      const response = await fetch(`${API_BASE_URL}/admin/students`)
      const data = await response.json()

      if (!response.ok) {
        throw new Error(data.message || data.error || 'Unable to fetch students')
      }

      setStudents(data)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchStudents()
  }, [])

  // Delete student
  const handleDelete = async (studentId) => {
    const confirmed = window.confirm(
      `Are you sure you want to delete student ID ${studentId}?`
    )

    if (!confirmed) return

    try {
      setMessage('')
      setError('')

      const response = await fetch(
        `${API_BASE_URL}/admin/students/${studentId}`,
        {
          method: 'DELETE'
        }
      )

      const text = await response.text()

      if (!response.ok) {
        throw new Error(text || 'Unable to delete student')
      }

      setMessage(text || 'Student deleted successfully.')

      // Refresh student list
      await fetchStudents()

    } catch (err) {
      setError(err.message)
    }
  }

  return (
    <div className="admin-students-page">

      <nav className="navbar admin-module-navbar">
        <h2 className="logo">CareerBridge</h2>

        <button className="secondary-button" onClick={onBack}>
          ← Back to Dashboard
        </button>
      </nav>

      <main className="admin-students-content">

        <div className="admin-module-heading">
          <p className="tagline">ADMINISTRATION</p>
          <h1>Student Management</h1>
          <p>View and manage registered student accounts.</p>
        </div>

        <div className="admin-student-summary">
          <h2>Total Students: {students.length}</h2>

          <button
            className="primary-button"
            onClick={fetchStudents}
            disabled={loading}
          >
            {loading ? 'Loading...' : 'Refresh'}
          </button>
        </div>

        {message && (
          <p className="admin-success-message">
            {message}
          </p>
        )}

        {error && (
          <p className="admin-error-message">
            {error}
          </p>
        )}

        {loading ? (
          <p className="admin-loading">Loading students...</p>
        ) : students.length === 0 ? (
          <p className="admin-empty-message">
            No students found.
          </p>
        ) : (

          <div className="admin-table-container">

            <table className="admin-table">

              <thead>
                <tr>
                  <th>Student ID</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>CGPA</th>
                  <th>Branch ID</th>
                  <th>Resume</th>
                  <th>Action</th>
                </tr>
              </thead>

              <tbody>
                {students.map((student) => (
                  <tr key={student.studentId}>

                    <td>{student.studentId}</td>

                    <td>{student.name}</td>

                    <td>{student.email}</td>

                    <td>{student.cgpa ?? 'N/A'}</td>

                    <td>{student.branchId ?? 'N/A'}</td>

                    <td>{student.resumePath || 'Not uploaded'}</td>

                    <td>
                      <button
                        className="admin-delete-button"
                        onClick={() => handleDelete(student.studentId)}
                      >
                        Delete
                      </button>
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

export default AdminStudents
