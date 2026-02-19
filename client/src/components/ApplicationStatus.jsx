import { useState } from "react";
import { getApplicationStatus } from "../services/api";

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
      const data = await getApplicationStatus(applicationId);
      setResult(data);
    } catch (err) {
      setError(err.message || 'Failed to fetch status');
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
          <p><strong>Application ID:</strong> {result.applicationId}</p>
          <p><strong>Status:</strong> {result.status}</p>
          <p><strong>Credit Limit:</strong> {result.creditLimit ?? 'N/A'}</p>
          {result.rejectionReason && (
            <p><strong>Reason:</strong> {result.rejectionReason}</p>
          )}
        </div>
      )}
    </div>
  );
}

export default ApplicationStatus;
