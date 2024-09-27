import { createRoot } from "react-dom/client";
import "./index.css";
import Router from "./Routes.jsx";
import { RouterProvider } from "react-router-dom";

createRoot(document.getElementById("root")).render(
  <RouterProvider router={Router} />
);
