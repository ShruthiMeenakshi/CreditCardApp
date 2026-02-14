import ApplicationForm from "./components/ApplicationForm";
import ApplicationStatus from "./components/ApplicationStatus";

function App() {
  return (
    <div style={{ padding: "30px" }}>
      <h1>Credit Card Application System</h1>
      <ApplicationForm />
      <hr />
      <ApplicationStatus />
    </div>
  );
}

export default App;
