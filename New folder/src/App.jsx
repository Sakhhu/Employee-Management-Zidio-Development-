import React, { useState, useEffect } from 'react';
import axios from 'axios';

// आपके बैकएंड स्प्रिंग बूट का बिल्कुल सही URL और पोर्ट
const API_BASE = "http://localhost:8082/api";

function App() {
  const [employees, setEmployees] = useState([]);
  const [activeTab, setActiveTab] = useState('employees');
  const [selectedEmpPayroll, setSelectedEmpPayroll] = useState(null);

  useEffect(() => {
    fetchEmployees();
  }, []);

  const fetchEmployees = async () => {
    try {
      const res = await axios.get(`${API_BASE}/employees`);
      setEmployees(res.data);
    } catch (err) { 
      console.error("Error fetching data:", err); 
    }
  };

  const markAttendance = async (id) => {
    try {
      await axios.post(`${API_BASE}/attendance/checkin/${id}`);
      alert("Attendance Marked Successfully!");
    } catch (err) { 
      alert("Failed to mark attendance"); 
    }
  };

  const processPayroll = async (id) => {
    try {
      const res = await axios.post(`${API_BASE}/payroll/generate/${id}`);
      setSelectedEmpPayroll(res.data);
      alert("Salary Slip Generated Successfully!");
    } catch (err) { 
      alert("Failed to generate payroll"); 
    }
  };

  return (
    <div style={{ fontFamily: 'Arial', backgroundColor: '#f4f7fe', minHeight: '100vh', margin: 0, padding: 0 }}>
      {/* Top Navbar */}
      <nav style={{ backgroundColor: '#2b3674', color: 'white', padding: '20px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <h2 style={{ margin: 0 }}>NexusHR - AI Dashboard</h2>
        <div>
          <button onClick={() => { setActiveTab('employees'); setSelectedEmpPayroll(null); }} style={{ background: 'none', border: 'none', color: 'white', marginLeft: '20px', cursor: 'pointer', fontSize: '16px', fontWeight: activeTab === 'employees' ? 'bold' : 'normal' }}>Employees</button>
          <button onClick={() => { setActiveTab('attendance'); setSelectedEmpPayroll(null); }} style={{ background: 'none', border: 'none', color: 'white', marginLeft: '20px', cursor: 'pointer', fontSize: '16px', fontWeight: activeTab === 'attendance' ? 'bold' : 'normal' }}>Attendance</button>
          <button onClick={() => { setActiveTab('payroll'); setSelectedEmpPayroll(null); }} style={{ background: 'none', border: 'none', color: 'white', marginLeft: '20px', cursor: 'pointer', fontSize: '16px', fontWeight: activeTab === 'payroll' ? 'bold' : 'normal' }}>Payroll</button>
        </div>
      </nav>

      {/* Main Content Area */}
      <div style={{ padding: '40px' }}>
        {/* Tab 1: Employee Directory */}
        {activeTab === 'employees' && (
          <div>
            <h3 style={{ color: '#1b2559' }}>Employee Directory</h3>
            <table style={{ width: '100%', borderCollapse: 'collapse', backgroundColor: 'white', borderRadius: '15px', overflow: 'hidden', boxShadow: '0 4px 12px rgba(0,0,0,0.05)' }}>
              <thead>
                <tr style={{ backgroundColor: '#f7f9fa', borderBottom: '1px solid #e9edf7', textAlign: 'left' }}>
                  <th style={{ padding: '15px' }}>Name</th><th style={{ padding: '15px' }}>Email</th><th style={{ padding: '15px' }}>Department</th><th style={{ padding: '15px' }}>Designation</th>
                </tr>
              </thead>
              <tbody>
                {employees.length === 0 ? (
                  <tr><td colSpan="4" style={{ padding: '20px', textAlign: 'center', color: '#a3eed2' }}>No employees found. Make sure backend is running!</td></tr>
                ) : (
                  employees.map(emp => (
                    <tr key={emp.id} style={{ borderBottom: '1px solid #e9edf7' }}>
                      <td style={{ padding: '15px' }}>{emp.firstName} {emp.lastName}</td>
                      <td style={{ padding: '15px' }}>{emp.email}</td>
                      <td style={{ padding: '15px' }}>{emp.department}</td>
                      <td style={{ padding: '15px' }}>{emp.designation}</td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        )}

        {/* Tab 2: Attendance */}
        {activeTab === 'attendance' && (
          <div>
            <h3 style={{ color: '#1b2559' }}>Mark Daily Attendance</h3>
            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))', gap: '20px' }}>
              {employees.map(emp => (
                <div key={emp.id} style={{ backgroundColor: 'white', padding: '20px', borderRadius: '15px', boxShadow: '0 4px 12px rgba(0,0,0,0.05)', textAlign: 'center' }}>
                  <h4 style={{ margin: '0 0 15px 0', color: '#1b2559' }}>{emp.firstName} {emp.lastName}</h4>
                  <button onClick={() => markAttendance(emp.id)} style={{ backgroundColor: '#05cd99', color: 'white', border: 'none', padding: '10px 20px', borderRadius: '8px', cursor: 'pointer', fontWeight: 'bold' }}>Check-In</button>
                </div>
              ))}
            </div>
          </div>
        )}

        {/* Tab 3: Payroll */}
        {activeTab === 'payroll' && (
          <div>
            <h3 style={{ color: '#1b2559', marginBottom: '20px' }}>Payroll Processing</h3>
            <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '40px' }}>
              {/* Left Side: Employee List */}
              <div>
                <h4 style={{ color: '#1b2559' }}>Select Employee to Generate Salary</h4>
                {employees.map(emp => (
                  <div key={emp.id} style={{ backgroundColor: 'white', padding: '15px', borderRadius: '10px', marginBottom: '10px', display: 'flex', justifyContent: 'space-between', alignItems: 'center', boxShadow: '0 2px 5px rgba(0,0,0,0.05)' }}>
                    <span style={{ fontWeight: 'bold', color: '#2b3674' }}>{emp.firstName} {emp.lastName}</span>
                    <button onClick={() => processPayroll(emp.id)} style={{ backgroundColor: '#4318ff', color: 'white', border: 'none', padding: '8px 15px', borderRadius: '6px', cursor: 'pointer', fontWeight: 'bold' }}>Generate Slip</button>
                  </div>
                ))}
              </div>

              {/* Right Side: Salary Slip Display */}
              {selectedEmpPayroll && (
                <div style={{ backgroundColor: 'white', padding: '30px', borderRadius: '15px', boxShadow: '0 4px 12px rgba(0,0,0,0.1)', borderTop: '5px solid #4318ff' }}>
                  <h3 style={{ textAlign: 'center', margin: '0 0 20px 0', color: '#1b2559', letterSpacing: '1px' }}>SALARY SLIP</h3>
                  <p style={{ color: '#2b3674' }}><strong>Date:</strong> {selectedEmpPayroll.paymentDate}</p>
                  <p style={{ color: '#2b3674' }}><strong>Status:</strong> <span style={{ color: '#05cd99', fontWeight: 'bold' }}>{selectedEmpPayroll.status}</span></p>
                  <hr style={{ border: 'none', borderTop: '1px dashed #e9edf7', margin: '15px 0' }}/>
                  <p style={{ display: 'flex', justifyContent: 'space-between', color: '#2b3674' }}><span>Base Salary:</span> <span>₹{selectedEmpPayroll.baseSalary}</span></p>
                  <p style={{ display: 'flex', justifyContent: 'space-between', color: '#2b3674' }}><span>Allowances:</span> <span>₹{selectedEmpPayroll.allowances}</span></p>
                  <p style={{ display: 'flex', justifyContent: 'space-between', color: 'red' }}><span>Deductions:</span> <span>-₹{selectedEmpPayroll.deductions}</span></p>
                  <hr style={{ border: 'none', borderTop: '1px dashed #e9edf7', margin: '15px 0' }}/>
                  <h4 style={{ display: 'flex', justifyContent: 'space-between', color: '#1b2559', margin: 0 }}><span>Net In-Hand:</span> <span>₹{selectedEmpPayroll.netSalary}</span></h4>
                </div>
              )}
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;