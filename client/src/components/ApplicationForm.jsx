import { useState } from "react";
import { submitApplication } from "../services/api";

function ApplicationForm() {
  const [form, setForm] = useState({
    fullName: "",
    panNumber: "",
    dateOfBirth: "",
    annualIncome: ""
  });

  const [result, setResult] = useState(null);
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setResult(null);

    try {
      const payload = {
        fullName: form.fullName,
        panNumber: form.panNumber,
        dateOfBirth: form.dateOfBirth,
        annualIncome: Number(form.annualIncome)
      };

      const response = await submitApplication(payload);
      setResult(response);
    } catch (err) {
      setError(err.message || 'Failed to submit');
    }
  };

  return (
    <div>
      <h2>Apply for Credit Card</h2>

      <form onSubmit={handleSubmit}>
        <input name="fullName" placeholder="Full Name" value={form.fullName} onChange={handleChange} /><br /><br />
        <input name="panNumber" placeholder="PAN Number" value={form.panNumber} onChange={handleChange} /><br /><br />
        <input name="dateOfBirth" type="date" placeholder="Date of Birth" value={form.dateOfBirth} onChange={handleChange} /><br /><br />
        <input name="annualIncome" type="number" placeholder="Annual Income" value={form.annualIncome} onChange={handleChange} /><br /><br />

        <button type="submit">Apply</button>
      </form>

      {error && <p style={{ color: 'red' }}>{error}</p>}

      {result && (
        <div>
          <h3>Application Result</h3>
          <p>Status: {result.status}</p>
          <p>Application ID: {result.applicationId}</p>
        </div>
      )}
    </div>
  );
}

export default ApplicationForm;