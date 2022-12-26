import React from "react";
import { useNavigate } from "react-router-dom";

const ErrorModel = ({
  resourcceName,
  fieldName,
  fieldValue,
  navigateTo,
  setIsValidUser,
}) => {
  const navigate = useNavigate();
  const buttonClickHandler = () => {
    setIsValidUser(true);
    return navigate("/" + navigateTo);
  };
  return (
    <div
      style={{
        backgroundColor: "lightgrey",
        zIndex: "1000",
        position: "absolute",
        top: "50%",
        left: "50%",
        transform: "translate(-50%,-50%)",
        width: "500px",
        height: "250px",
      }}
    >
      <h1>Error</h1>
      <p>
        {resourcceName} not found with {fieldName} : {fieldValue}
      </p>
      <div>
        <button
          style={{
            backgroundColor: "red",
            color: "white",
            border: "none",
            height: "50px",
            width: "150px",
            fontSize: "150%",
            cursor: "pointer",
            marginTop: "20px",
          }}
          onClick={buttonClickHandler}
        >
          cancel
        </button>
      </div>
    </div>
  );
};

export default ErrorModel;
