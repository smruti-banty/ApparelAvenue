import { Outlet } from "react-router-dom";

export default function Admin() {
  return (
    <>
      <h1>This is a Heading </h1>
      <Outlet />
      <h1>This is a Footer </h1>
    </>
  );
}
