import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { BrowserRouter, Route, RouterProvider, Routes } from "react-router-dom";
import AdminHome from "./pages/admin/admin.dashboard.jsx";
import CustomerHome from "./pages/customer/customer.home.jsx";
import Login from "./pages/Login.jsx";
import Signup from "./pages/Signup.jsx";
import Router from "./Routes.jsx";

createRoot(document.getElementById("root")).render(
  <RouterProvider router={Router} />
);
