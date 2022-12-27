import axios from "axios";

export async function getAllGrades() {
  //   const json = JSON.stringify({
  //     email: data.get("email"),
  //     password: data.get("password"),
  //   });
  //   try {
  //     const res = await axios.post(
  //       "http://localhost:8080/api/auth/getToken",
  //       json,
  //       {
  //         headers: {
  //           "Content-Type": "application/json",
  //         },
  //       }
  //     );
  //     console.log(res);
  //     if (res.data != "Some error occurred") {
  //       setIsValidUser(true);
  //       localStorage.setItem("user", JSON.stringify(res.data));
  //       return navigate("/dashboard");
  //     }
  //     console.log(res);
  //   } catch (e) {
  //     setIsValidUser(false);
  //   }
  const res = await axios.get(`http://localhost:8080/grade/getGrade/${id}`);
  return res.data;
}
