import axios from "axios";

export async function getAllGrades() {
  const id = JSON.parse(localStorage.getItem("user")).id;
  const res = await axios.get(`http://localhost:8080/grade/getGrade/${id}`);
  return res.data;
}
