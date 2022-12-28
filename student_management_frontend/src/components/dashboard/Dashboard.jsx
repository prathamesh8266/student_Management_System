import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../navbar/Navbar";
import { getAllGrades } from "../helper/grades";
import { getAllStudent } from "../helper/student";
import { getAllStudentByGrade } from "../helper/student";

const buttonStyle = {
  height: "40px",
  width: "90px",
  border: "none",
  cursor: "pointer",
  border: "1px solid black",
};

const studentContainerSAtyle = {
  margin: "30px auto",
  height: "80vh",
  width: "80vw",
  border: "1px solid black",
};

const tableStyle = {
  width: "100%",
  borderCollapse: "collapse",
};

const trStyleHead = {
  backgroundColor: "lightblue",
  height: "60px",
};

const trStyle = {
  height: "60px",
  borderBottom: "1px solid lightgrey",
};

const Dashboard = () => {
  let navigate = useNavigate();
  const [grades, setGrades] = useState([]);
  const [students, setStudents] = useState([]);

  const getStudentsByGrade = (e) => {
    const studentsByGrade = getAllStudentByGrade(e);
    studentsByGrade.then((students) => {
      setStudents([]);
      students.forEach((s) => {
        setStudents((stu) => [...stu, s]);
      });
    });
  };

  const Button = ({ grade }) => {
    return (
      <button
        value={grade}
        onClick={(e) => {
          getStudentsByGrade(e.target.value);
        }}
        style={buttonStyle}
      >
        {grade}
      </button>
    );
  };

  useEffect(() => {
    const getGrades = getAllGrades();
    const getStudents = getAllStudent();
    getGrades.then((grades) => {
      setGrades([]);
      setGrades((gr) => [...gr, "all"]);
      grades.map((g) => {
        setGrades((gr) => [...gr, g.grade]);
      });
    });
    getStudents.then((student) => {
      setStudents([]);
      student.map((stu) => {
        setStudents((s) => [...s, stu]);
      });
    });
  }, []);

  return (
    <>
      <Navbar />
      <div>
        <div style={{ marginTop: "30px" }}>
          {grades.map((grade, i) => {
            return <Button key={i} grade={grade} />;
          })}
        </div>
        <div style={studentContainerSAtyle}>
          <table style={tableStyle}>
            <tbody>
              <tr style={trStyleHead}>
                <th>Name</th>
                <th>Age</th>
                <th>Blood group</th>
                <th>Father name</th>
                <th>Mother name</th>
                <th>Class</th>
              </tr>
              {students.map((student, i) => {
                // console.log(student);
                return (
                  <tr key={i} style={trStyle}>
                    <td>{student.name}</td>
                    <td>{student.age}</td>
                    <td>{student.bloodGroup}</td>
                    <td>{student.fatherName}</td>
                    <td>{student.motherName}</td>
                    <td>{student.studentClass.grade}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default Dashboard;
