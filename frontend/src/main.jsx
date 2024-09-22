import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import AdminHome from "./pages/admin/index.jsx";
import CustomerHome from "./pages/customer/index.jsx";
import Login from "./pages/Login.jsx";
import Signup from "./pages/Signup.jsx";

createRoot(document.getElementById("root")).render(
  <BrowserRouter>
    <App />
    <Routes>
      <Route path="/admin" element={<AdminHome />}></Route>
      <Route path="/customer" element={<CustomerHome />}></Route>
      <Route path="/authorization" element={<Login />}></Route>
      <Route path="/signup" element={<Signup />}></Route>
    </Routes>
  </BrowserRouter>
);
