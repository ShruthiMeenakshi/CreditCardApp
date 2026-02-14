import { useState } from "react";
import { getApplicationStatus } from "../services/api";

function ApplicationStatus() {
  const [appId, setAppId] = useState("");
  const [status, setStatus] = useState(null);

  const checkStatus = async () => {
    const response = await getApplicationStatus(appId);
    setStatus(response);
  };

  return (
    <div>
      <h2>Check Application Status</h2>

      <input
        placeholder="Enter Application ID"
        onChange={(e) => setAppId(e.target.value)}
      />
      <button onClick={checkStatus}>Check</button>

      {status && (
        <div>
          <p>Status: {status.status}</p>
          {status.rejectionReason && <p>Reason: {status.rejectionReason}</p>}
        </div>
      )}
    </div>
  );
}

export default ApplicationStatus;