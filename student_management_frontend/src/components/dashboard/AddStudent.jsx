import React from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const containerStyle = {
  backgroundColor: "lightgrey",
  zIndex: "1000",
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%,-50%)",
  width: "500px",
  height: "250px",
};

const inputStyle = {
  marginLeft: "40px",
  height: "40px",
  width: "400px",
  padding: "0px 10px",
  fontSize: "20px",
};

const labelStyle = {
  fontSize: "20px",
};

const inputContainerStyle = {
  display: "flex",
  justifyContent: "space-between",
  margin: "10px 0",
};

const buttonStyle = {
  margin: "0 10px",
  height: "40px",
  width: "90px",
  border: "none",
  cursor: "pointer",
};

const AddStudent = () => {
  const navigate = useNavigate();
  const logoutHandler = () => {
    return navigate("/dashboard");
  };

  const formSubmitHandler = async (e) => {
    e.preventDefault();
    const { id, userName } = JSON.parse(localStorage.getItem("user"));
    const user = {
      id: id,
      email: userName,
    };
    const student = {
      name: e.target[0].value,
      age: e.target[1].value,
      fatherName: e.target[2].value,
      motherName: e.target[3].value,
      bloodGroup: e.target[4].value,
      studentClass: {
        grade: e.target[5].value,
        user: user,
      },
      user: user,
    };
    console.log(student);
    try {
      const res = await axios.post(
        "http://localhost:8080/student/add",
        student,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log(res);
    } catch (e) {
      // if any ecception occures show error modal
      console.log(e.message);
    }
  };

  return (
    <div>
      <button style={buttonStyle} onClick={logoutHandler}>
        Back
      </button>
      <h1>Add Student</h1>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          flexDirection: "column",
          marginTop: "100px",
        }}
      >
        <form style={{ width: "600px" }} onSubmit={formSubmitHandler}>
          <div style={inputContainerStyle}>
            <label style={labelStyle} name="studentName">
              Student name
            </label>
            <input style={inputStyle} type="text" />
          </div>
          <div style={inputContainerStyle}>
            <label style={labelStyle}>Age</label>
            <input style={inputStyle} type="number" min={6} />
          </div>
          <div style={inputContainerStyle}>
            <label style={labelStyle}>Father name</label>
            <input style={inputStyle} type="text" />
          </div>
          <div style={inputContainerStyle}>
            <label style={labelStyle}>Mother name</label>
            <input style={inputStyle} type="text" />
          </div>
          <div style={inputContainerStyle}>
            <label style={labelStyle}>Bloog group</label>
            <input style={inputStyle} type="text" />
          </div>
          <div style={inputContainerStyle}>
            <label style={labelStyle}>Class</label>
            <input style={inputStyle} type="number" min={1} />
          </div>
          <button style={buttonStyle}>Submit</button>
        </form>
      </div>
    </div>
  );
};

export default AddStudent;
