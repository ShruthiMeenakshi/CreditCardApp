const BASE_URL = "http://localhost:8080/api/applications";

export async function submitApplication(data) {
  const response = await fetch(`${BASE_URL}/apply`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });

  return response.json();
}

export async function getApplicationStatus(id) {
  const response = await fetch(`${BASE_URL}/status/${id}`);
  return response.json();
}