import { Outlet } from "react-router-dom";

export default function Customer() {
  return (
    <>
      <h1>This is the Header</h1>
      <Outlet />
      <h1>This is the Footer</h1>
    </>
  );
}
