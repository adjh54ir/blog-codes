import React, { Suspense } from "react";
import { Navigate, Route, Routes } from "react-router";
import LoginComponent from "../components/LoginComponent";
import MainComponenet from "../components/MainComponent";

const Routers = (props: any) => {
  return (
    <Routes>

      <Route path="/" element={<Navigate replace to="/login" {...props} />} />
      <Route path={"main"} element={<MainComponenet {...props} />} />
      <Route path={"login"} element={<LoginComponent {...props} />} />
    </Routes>
  );
};
export default Routers;
