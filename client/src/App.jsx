import ApplicationForm from "./components/ApplicationForm";
import ApplicationStatus from "./components/ApplicationStatus";

function App() {
  return (
    <div className="app-shell">
      <h1 style={{ textAlign: 'center', marginBottom: 18 }}>Credit Card Application System</h1>
      <ApplicationForm />
      <div style={{ width: '100%', maxWidth: 720, margin: '20px 0' }}>
        <hr />
      </div>
      <ApplicationStatus />
    </div>
  );
}

export default App;
