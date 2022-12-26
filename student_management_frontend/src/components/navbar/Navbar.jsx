import * as React from "react";
import { useNavigate } from "react-router-dom";

const divStyle = {
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  padding: "0 10px",
  backgroundColor: "lightblue",
};

const buttonStyle = {
  height: "40px",
  width: "90px",
  border: "none",
  cursor: "pointer",
};

const Navbar = () => {
  const navigate = useNavigate();
  const userName = JSON.parse(window.localStorage.getItem("user")).userName;

  const logoutHandler = () => {
    localStorage.removeItem("user");
    return navigate("/");
  };

  return (
    <div style={divStyle}>
      <h3>{userName}</h3>
      <h2>Dashboard</h2>
      <button style={buttonStyle} onClick={logoutHandler}>
        Logout
      </button>
    </div>
  );
};

export default Navbar;
