import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../navbar/Navbar";
import { getAllGrades } from "../helper/grades";

const Button = (grade) => {
  return <button>{grade}</button>;
};

const Dashboard = () => {
  let navigate = useNavigate();
  const [grades, setGrades] = useState([]);
  useEffect(() => {
    setGrades(getAllGrades());
  }, []);
  console.log(grades);
  return (
    <>
      <Navbar />
      <div>
        <div>
          {grades?.forEach((grade) => {
            console.log(grade);
            return <Button grade={grade} />;
          })}
        </div>
        <div>Students</div>
      </div>
    </>
  );
};

export default Dashboard;
