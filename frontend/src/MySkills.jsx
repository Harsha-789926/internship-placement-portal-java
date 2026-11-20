
import { useEffect, useState } from 'react'

const API_BASE_URL = 'http://localhost:8080/api'

function MySkills({ student, onBack }) {
  const [availableSkills, setAvailableSkills] = useState([])
  const [mySkills, setMySkills] = useState([])
  const [selectedSkill, setSelectedSkill] = useState('')
  const [loading, setLoading] = useState(true)
  const [saving, setSaving] = useState(false)
  const [deletingSkillId, setDeletingSkillId] = useState(null)
  const [error, setError] = useState('')
  const [message, setMessage] = useState('')

  const fetchSkills = async () => {
    try {
      setError('')

      const [availableResponse, mySkillsResponse] = await Promise.all([
        fetch(`${API_BASE_URL}/student-skills/available`),
        fetch(`${API_BASE_URL}/student-skills/student/${student.studentId}`)
      ])

      if (!availableResponse.ok || !mySkillsResponse.ok) {
        throw new Error('Unable to load skills.')
      }

      const availableData = await availableResponse.json()
      const mySkillsData = await mySkillsResponse.json()

      setAvailableSkills(Array.isArray(availableData) ? availableData : [])
      setMySkills(Array.isArray(mySkillsData) ? mySkillsData : [])
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchSkills()
  }, [student.studentId])

  const handleAddSkill = async (event) => {
    event.preventDefault()

    if (!selectedSkill) {
      setMessage('Please select a skill.')
      return
    }

    setSaving(true)
    setMessage('')
    setError('')

    try {
      const response = await fetch(`${API_BASE_URL}/student-skills`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          studentId: student.studentId,
          skillId: Number(selectedSkill)
        })
      })

      const data = await response.json().catch(() => ({}))

      if (!response.ok) {
        throw new Error(
          data.message || data.error || 'Unable to add skill.'
        )
      }

      setMessage('Skill added successfully!')
      setSelectedSkill('')
      await fetchSkills()
    } catch (err) {
      setMessage(err.message)
    } finally {
      setSaving(false)
    }
  }

  const handleRemoveSkill = async (skillId) => {
    const confirmed = window.confirm(
      'Are you sure you want to remove this skill?'
    )

    if (!confirmed) return

    setDeletingSkillId(skillId)
    setMessage('')
    setError('')

    try {
      const response = await fetch(
        `${API_BASE_URL}/student-skills/student/${student.studentId}/skill/${skillId}`,
        { method: 'DELETE' }
      )

      if (!response.ok) {
        const data = await response.json().catch(() => ({}))
        throw new Error(
          data.message || data.error || 'Unable to remove skill.'
        )
      }

      setMessage('Skill removed successfully!')
      await fetchSkills()
    } catch (err) {
      setMessage(err.message)
    } finally {
      setDeletingSkillId(null)
    }
  }

  const mySkillIds = mySkills.map((skill) => skill.skillId)

  const skillsToAdd = availableSkills.filter(
    (skill) => !mySkillIds.includes(skill.skillId)
  )

  return (
    <div className="my-skills-page">
      <div className="jobs-header">
        <button className="back-button" onClick={onBack}>
          ← Back to Dashboard
        </button>

        <p className="tagline">STUDENT PORTAL</p>
        <h1>My Skills</h1>
        <p>Manage your technical skills and strengthen your profile.</p>
      </div>

      <div className="skills-container">

        <div className="skills-summary">
          <h2>My Technical Skills</h2>
          <p>
            You have added <strong>{mySkills.length}</strong> skill(s)
            to your profile.
          </p>
        </div>

        {message && (
          <p className="skills-message" role="status">
            {message}
          </p>
        )}

        {error && (
          <p className="jobs-error">{error}</p>
        )}

        <div className="add-skill-card">
          <h2>Add a New Skill</h2>

          <form onSubmit={handleAddSkill}>
            <select
              value={selectedSkill}
              onChange={(event) => setSelectedSkill(event.target.value)}
              required
            >
              <option value="">Select a skill</option>

              {skillsToAdd.map((skill) => (
                <option key={skill.skillId} value={skill.skillId}>
                  {skill.skillName}
                </option>
              ))}
            </select>

            <button
              type="submit"
              className="primary-button"
              disabled={saving || skillsToAdd.length === 0}
            >
              {saving ? 'Adding...' : 'Add Skill'}
            </button>
          </form>

          {skillsToAdd.length === 0 && !loading && (
            <p className="skills-note">
              You have already added all available skills.
            </p>
          )}
        </div>

        <div className="my-skills-list">
          <h2>Skills in My Profile</h2>

          {loading && <p>Loading your skills...</p>}

          {!loading && !error && mySkills.length === 0 && (
            <p className="skills-empty">
              No skills added yet. Select a skill above to get started.
            </p>
          )}

          {!loading && mySkills.length > 0 && (
            <div className="skill-items">
              {mySkills.map((skill) => (
                <div className="skill-item" key={skill.skillId}>
                  <div>
                    <span className="skill-icon">⚡</span>
                    <strong>{skill.skillName}</strong>
                  </div>

                  <button
                    className="remove-skill-button"
                    onClick={() => handleRemoveSkill(skill.skillId)}
                    disabled={deletingSkillId === skill.skillId}
                  >
                    {deletingSkillId === skill.skillId
                      ? 'Removing...'
                      : 'Remove'}
                  </button>
                </div>
              ))}
            </div>
          )}
        </div>

      </div>
    </div>
  )
}

export default MySkills
