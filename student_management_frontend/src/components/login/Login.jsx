import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import ErrorModel from "../error/ErrorModel";

function Copyright(props) {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      {...props}
    >
      {"Copyright © "}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

const theme = createTheme();

export default function Login() {
  const [isValidUser, setIsValidUser] = React.useState(true);
  const setValidUser = (bool) => {
    setIsValidUser(bool);
  };
  let navigate = useNavigate();

  React.useEffect(() => {
    validateLoginIfTokenExists();
  }, []);

  const validateLoginIfTokenExists = async () => {
    var user = localStorage.getItem("user");
    if (user != undefined) {
      user = JSON.parse(localStorage.getItem("user"));
      try {
        // console.log(user.token);
        const res = await axios.post(
          `http://localhost:8080/api/auth/isValidToken`,
          { token: user.token },
          {
            headers: {
              "Content-Type": "application/json",
              // Authorization: "Bearer " + user.token,
            },
          }
        );
        // console.log(res);
        if (res.data != "Some error occurred") {
          setIsValidUser(true);
          return navigate("/dashboard");
        }
      } catch (e) {}
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const json = JSON.stringify({
      email: data.get("email"),
      password: data.get("password"),
    });
    try {
      const res = await axios.post(
        "http://localhost:8080/api/auth/getToken",
        json,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log(res);
      if (res.data != "Some error occurred") {
        setIsValidUser(true);
        localStorage.setItem("user", JSON.stringify(res.data));
        return navigate("/dashboard");
      }
      console.log(res);
    } catch (e) {
      // if any ecception occures show error modal
      setIsValidUser(false);
    }
  };

  const errorModalShower = () => {
    return (
      <>
        <ErrorModel />
      </>
    );
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          {!isValidUser && (
            <ErrorModel
              resourcceName={"User"}
              fieldName={"Email or Password"}
              fieldValue={""}
              navigateTo={""}
              setIsValidUser={setValidUser}
            />
          )}
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link href="#" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 8, mb: 4 }} />
      </Container>
    </ThemeProvider>
  );
}
