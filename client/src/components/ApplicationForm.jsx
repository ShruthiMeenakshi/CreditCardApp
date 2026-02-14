import { useState } from "react";
import { submitApplication } from "../services/api";

function ApplicationForm() {
  const [form, setForm] = useState({
    fullName: "",
    panNumber: "",
    age: "",
    annualIncome: ""
  });

  const [result, setResult] = useState(null);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await submitApplication(form);
    setResult(response);
  };

  return (
    <div>
      <h2>Apply for Credit Card</h2>

      <form onSubmit={handleSubmit}>
        <input name="fullName" placeholder="Full Name" onChange={handleChange} /><br /><br />
        <input name="panNumber" placeholder="PAN Number" onChange={handleChange} /><br /><br />
        <input name="age" type="number" placeholder="Age" onChange={handleChange} /><br /><br />
        <input name="annualIncome" type="number" placeholder="Annual Income" onChange={handleChange} /><br /><br />

        <button type="submit">Apply</button>
      </form>

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