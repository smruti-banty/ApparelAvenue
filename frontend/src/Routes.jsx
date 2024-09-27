import { createBrowserRouter } from "react-router-dom";
import Admin from "./pages/admin/Admin";
import AdminDashBoard from "./pages/admin/admin.dashboard";
import AdminOrder from "./pages/admin/admin.order";
import AdminCart from "./pages/admin/admin.cart";
import AdminProduct from "./pages/admin/admin.product";
import AdminBanner from "./pages/admin/admin.banner";
import CustomerHome from "./pages/customer/customer.home";
import CustomerCart from "./pages/customer/customer.cart";
import CustomerProduct from "./pages/customer/customer.product";
import CustomerProducts from "./pages/customer/customer.products";
import CustomerOrder from "./pages/customer/customer.order";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Customer from "./pages/customer/Customer";

const Router = createBrowserRouter([
  {
    path: "admin",
    element: <Admin />,
    children: [
      {
        index: true,
        element: <AdminDashBoard />,
      },
      {
        path: "admin-order",
        element: <AdminOrder />,
      },
      {
        path: "admin-cart",
        element: <AdminCart />,
      },
      {
        path: "admin-product",
        element: <AdminProduct />,
      },
      {
        path: "admin-banner",
        element: <AdminBanner />,
      },
    ],
  },
  {
    path: "/",
    element: <Customer />,
    children: [
      {
        index: true,
        element: <CustomerHome />,
      },
      {
        path: "customer-cart",
        element: <CustomerCart />,
      },
      {
        path: "customer-product",
        element: <CustomerProduct />,
      },
      {
        path: "customer-products",
        element: <CustomerProducts />,
      },
      {
        path: "customer-order",
        element: <CustomerOrder />,
      },
    ],
  },
  {
    path: "login",
    element: <Login />,
  },
  {
    path: "signup",
    element: <Signup />,
  },
]);

export default Router;
