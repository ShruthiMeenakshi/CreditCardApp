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
    <div style={{ marginTop: "40px" }} className="status-card">
      <h2>Check Application Status</h2>

      <form onSubmit={checkStatus} style={{ display: 'flex', gap: 12, alignItems: 'center' }}>
        <input
          className="input"
          type="text"
          placeholder="Enter Application ID"
          value={applicationId}
          onChange={(e) => setApplicationId(e.target.value)}
          style={{ flex: 1 }}
        />
        <button className="primary-btn" type="submit">Check</button>
      </form>

      {loading && <p style={{ marginTop: 12 }}>Checking status...</p>}

      {error && <p style={{ color: "salmon", marginTop: 12 }}>{error}</p>}

      {result && (
        <div className="result-box">
          <p style={{ margin: 0 }}><strong>Application ID:</strong> {result.applicationId}</p>
          <p style={{ margin: '6px 0 0' }}><strong>Status:</strong> {result.status}</p>
          <p style={{ margin: '6px 0 0' }}><strong>Credit Limit:</strong> {result.creditLimit ?? 'N/A'}</p>
          {result.rejectionReason && (
            <p style={{ marginTop: 6 }}><strong>Reason:</strong> {result.rejectionReason}</p>
          )}
        </div>
      )}
    </div>
  );
}

export default ApplicationStatus;
