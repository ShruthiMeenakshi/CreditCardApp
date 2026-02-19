import { useState } from "react";

function ApplicationStatus() {
  const [applicationId, setApplicationId] = useState("");
  const [result, setResult] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const checkStatus = async (e) => {
    e.preventDefault();
    setError("");
    setResult(null);

    if (!applicationId.trim()) {
      setError("Please enter Application ID");
      return;
    }

    try {
      setLoading(true);

      const response = await fetch(
        `http://localhost:8080/api/applications/status/${applicationId}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (!response.ok) {
        throw new Error("Application not found");
      }

      const data = await response.json();
      setResult(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ marginTop: "40px" }}>
      <h2>Check Application Status</h2>

      <form onSubmit={checkStatus}>
        <input
          type="text"
          placeholder="Enter Application ID"
          value={applicationId}
          onChange={(e) => setApplicationId(e.target.value)}
          style={{ padding: "8px", marginRight: "10px" }}
        />
        <button type="submit">Check</button>
      </form>

      {loading && <p>Checking status...</p>}

      {error && <p style={{ color: "red" }}>{error}</p>}

      {result && (
        <div style={{ marginTop: "20px" }}>
          <p><strong>Status:</strong> {result.status}</p>
          <p><strong>Credit Score:</strong> {result.creditScore}</p>
          {result.rejectionReason && (
            <p><strong>Reason:</strong> {result.rejectionReason}</p>
          )}
        </div>
      )}
    </div>
  );
}

export default ApplicationStatus;
