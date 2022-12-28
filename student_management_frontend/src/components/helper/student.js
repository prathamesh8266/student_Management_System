import axios from "axios";

export async function getAllStudent() {
  const id = JSON.parse(localStorage.getItem("user")).id;
  const res = await axios.get(`http://localhost:8080/student/getAll/${id}`);
  return res.data;
}

export async function getAllStudentByGrade(grade) {
  const id = JSON.parse(localStorage.getItem("user")).id;
  let res;
  if (grade === "all") {
    res = await axios.get(`http://localhost:8080/student/getAll/${id}`);
  } else {
    res = await axios.get(
      `http://localhost:8080/student/getAll/${id}/${grade}`
    );
  }
  return res.data;
}
