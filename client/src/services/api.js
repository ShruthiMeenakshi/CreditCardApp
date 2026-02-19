const BASE_URL = "http://localhost:8080/api/applications";

export async function submitApplication(data) {
  const response = await fetch(`${BASE_URL}/apply`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });

  if (!response.ok) {
    const errText = await response.text();
    throw new Error(errText || 'Failed to submit application');
  }

  return response.json();
}

export async function getApplicationStatus(id) {
  const response = await fetch(`${BASE_URL}/${id}`, {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' }
  });

  if (!response.ok) {
    const errText = await response.text();
    throw new Error(errText || 'Application not found');
  }

  return response.json();
}