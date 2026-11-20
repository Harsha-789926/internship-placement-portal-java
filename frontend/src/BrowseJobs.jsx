
import { useEffect, useState } from 'react'
import { getAllJobs, applyForJob } from './services/api'

function BrowseJobs({ student, onBack }) {
  const [jobs, setJobs] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [applicationMessages, setApplicationMessages] = useState({})
  const [applyingJobId, setApplyingJobId] = useState(null)

  useEffect(() => {
    async function fetchJobs() {
      try {
        const data = await getAllJobs()
        setJobs(Array.isArray(data) ? data : [])
      } catch (err) {
        setError(err.message)
      } finally {
        setLoading(false)
      }
    }

    fetchJobs()
  }, [])

  const handleApply = async (jobId) => {
    setApplyingJobId(jobId)

    try {
      await applyForJob(student.studentId, jobId)

      setApplicationMessages((previous) => ({
        ...previous,
        [jobId]: 'Application submitted successfully!',
      }))
    } catch (err) {
      setApplicationMessages((previous) => ({
        ...previous,
        [jobId]: err.message,
      }))
    } finally {
      setApplyingJobId(null)
    }
  }

  return (
    <div className="browse-jobs-page">
      <div className="jobs-header">
        <button className="back-button" onClick={onBack}>
          ← Back to Dashboard
        </button>

        <p className="tagline">CAREER OPPORTUNITIES</p>
        <h1>Explore Jobs & Internships</h1>
        <p>Discover opportunities and take the next step in your career.</p>
      </div>

      <div className="jobs-container">
        {loading && <p>Loading available jobs...</p>}

        {error && <p className="jobs-error">{error}</p>}

        {!loading && !error && jobs.length === 0 && (
          <p>No job opportunities are currently available.</p>
        )}

        {!loading && !error && jobs.map((job) => (
          <div className="job-card" key={job.jobId}>
            <h2>{job.title}</h2>

            <p className="job-description">
              {job.description || 'No description available.'}
            </p>

            <div className="job-details">
              <p><strong>Company ID:</strong> {job.companyId}</p>
              <p><strong>Package:</strong> {job.packageAmount || 'Not specified'}</p>
              <p><strong>Location:</strong> {job.location || 'Not specified'}</p>
              <p><strong>Minimum CGPA:</strong> {job.minimumCgpa}</p>
              <p><strong>Deadline:</strong> {job.deadline}</p>
            </div>

            <button
              className="primary-button"
              onClick={() => handleApply(job.jobId)}
              disabled={applyingJobId === job.jobId}
            >
              {applyingJobId === job.jobId ? 'Applying...' : 'Apply Now'}
            </button>

            {applicationMessages[job.jobId] && (
              <p className="application-message">
                {applicationMessages[job.jobId]}
              </p>
            )}
          </div>
        ))}
      </div>
    </div>
  )
}

export default BrowseJobs
