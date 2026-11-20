
const API_BASE_URL = 'http://localhost:8080/api'

// =====================================
// STUDENT LOGIN
// =====================================

export async function studentLogin(email, password) {
  const response = await fetch(`${API_BASE_URL}/students/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      email,
      password,
    }),
  })

  const data = await response.json()

  if (!response.ok) {
    throw new Error(data.message || data.error || 'Student login failed')
  }

  return data
}

// =====================================
// GET ALL JOBS
// =====================================

export async function getAllJobs() {
  const response = await fetch(`${API_BASE_URL}/jobs`)

  const data = await response.json()

  if (!response.ok) {
    throw new Error(data.message || data.error || 'Unable to fetch jobs')
  }

  return data
}

// =====================================
// APPLY FOR JOB
// =====================================

export async function applyForJob(studentId, jobId) {
  const response = await fetch(`${API_BASE_URL}/applications`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ studentId, jobId }),
  })

  const data = await response.json()

  if (!response.ok) {
    throw new Error(
      data.message || data.error || 'Unable to apply for this job'
    )
  }

  return data
}

// =====================================
// RECRUITER LOGIN
// =====================================

export async function companyLogin(email, password) {
  const response = await fetch(`${API_BASE_URL}/companies/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ email, password }),
  })

  const data = await response.json()

  if (!response.ok) {
    throw new Error(
      data.message || data.error || 'Recruiter login failed'
    )
  }

  return data
}

// =====================================
// GET COMPANY DETAILS
// =====================================

export async function getCompany(companyId) {
  const response = await fetch(`${API_BASE_URL}/companies/${companyId}`)

  const data = await response.json()

  if (!response.ok) {
    throw new Error(
      data.message || data.error || 'Unable to fetch company details'
    )
  }

  return data
}


// ADMIN LOGIN
export async function adminLogin(email, password) {
  const response = await fetch(`${API_BASE_URL}/admin/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ email, password })
  })

  const data = await response.json()

  if (!response.ok) {
    throw new Error(
      data.message || data.error || 'Admin login failed'
    )
  }

  return data
}
