import React from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../navbar/Navbar";

const Dashboard = () => {
  let navigate = useNavigate();
  return (
    <>
      <Navbar />
      <div>
        <div>Buttons</div>
        <div>Students</div>
      </div>
    </>
  );
};

export default Dashboard;
