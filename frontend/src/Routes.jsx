import { createBrowserRouter } from "react-router-dom";
import Admin from "./pages/admin/Admin";
import AdminDashBoard from "./pages/admin/admin.dashboard";
import AdminOrder from "./pages/admin/admin.order";
import AdminCart from "./pages/admin/admin.cart";
import AdminProduct from "./pages/admin/admin.product";
import AdminBanner from "./pages/admin/admin.banner";

const Router = createBrowserRouter([
  {
    path: "admin",
    element: <Admin />,
    children: [
      {
        path: "dashboard",
        element: <AdminDashBoard />,
        index: true,
      },
      {
        path: "order",
        element: <AdminOrder />,
      },
      {
        path: "cart",
        element: <AdminCart />,
      },
      {
        path: "product",
        element: <AdminProduct />,
      },
      {
        path: "banner",
        element: <AdminBanner />,
      },
    ],
  },
]);

export default Router;
