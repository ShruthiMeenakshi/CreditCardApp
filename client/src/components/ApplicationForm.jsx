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
    <div className="form-card">
      <h2>Apply for Credit Card</h2>

      <form onSubmit={handleSubmit}>
        <div className="form-grid">
          <div>
            <label className="field-label">Full Name</label>
            <input className="input" name="fullName" placeholder="Full Name" value={form.fullName} onChange={handleChange} />
          </div>

          <div>
            <label className="field-label">PAN Number</label>
            <input className="input" name="panNumber" placeholder="PAN Number" value={form.panNumber} onChange={handleChange} />
          </div>

          <div>
            <label className="field-label">Date of Birth</label>
            <input className="input" name="dateOfBirth" type="date" placeholder="Date of Birth" value={form.dateOfBirth} onChange={handleChange} />
          </div>

          <div>
            <label className="field-label">Annual Income</label>
            <input className="input" name="annualIncome" type="number" placeholder="Annual Income" value={form.annualIncome} onChange={handleChange} />
          </div>

          <div className="form-row btn-row">
            <button className="primary-btn" type="submit">Apply</button>
            <button type="reset" className="secondary-btn" onClick={() => { setForm({ fullName: '', panNumber: '', dateOfBirth: '', annualIncome: '' }); setResult(null); setError(''); }}>Reset</button>
          </div>
        </div>
      </form>

      {error && <p style={{ color: 'salmon', marginTop: 12 }}>{error}</p>}

      {result && (
        <div className="result-box">
          <h3 style={{ margin: 0 }}>Application Result</h3>
          <p style={{ margin: '6px 0 0' }}><strong>Status:</strong> {result.status}</p>
          <p style={{ margin: '4px 0 0' }}><strong>Application ID:</strong> {result.applicationId}</p>
        </div>
      )}
    </div>
  );
}

export default ApplicationForm;