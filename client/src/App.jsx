import { Navigate, Route, Routes, useLocation } from "react-router-dom";
import Menubar from "./Component/MenuBar/Menubar";
import Dashboard from "./Pages/Dashboard/Dashboard";
import ManageCategory from "./Pages/ManageCategory/ManageCategory";
import Explore from "./Pages/Explore/Explore";
import ManageUser from "./Pages/ManageUser/ManageUser";
import ManageItems from "./Pages/ManageItems/ManageItems";
import { Toaster } from "react-hot-toast";
import Login from "./Pages/Login/Login";
import OrderHistory from "./Pages/OrderHistory/OrderHistory";
import { useContext } from "react";
import { AppContext } from "./Context/AppContext";

const App = () => {
  const location = useLocation();
  const {auth} = useContext(AppContext)
  const LoginRoute = ({ element }) => {
  const token = auth?.token || localStorage.getItem("token");

  if (token) {
    return <Navigate to="/dashboard" replace />;
  }
  return element;
};


  const ProtectedRoute = ({ element, allowedRoles }) => {
  const token = auth?.token || localStorage.getItem("token");
  const role = auth?.role || localStorage.getItem("role");

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  if (allowedRoles && !allowedRoles.includes(role)) {
    return <Navigate to="/dashboard" replace />;
  }

  return element;
};

  return (
    <div>
      {location.pathname !== "/login" && <Menubar/>}
      <Toaster/>
      <Routes>
        <Route path='/dashboard' element={<Dashboard/>}/>
        <Route path='/explore' element={<Explore/>}/>
        {/* Admin Only Routes */}
        <Route path='/category' element={<ProtectedRoute element={<ManageCategory/>} allowedRoles={['ROLE_ADMIN']}/>}/>
        <Route path='/users' element={<ProtectedRoute element={<ManageUser/>} allowedRoles={['ROLE_ADMIN']}/>}/>
        <Route path='/items' element={<ProtectedRoute element={<ManageItems/>} allowedRoles={['ROLE_ADMIN']}/>}/> 

        <Route path='/login' element={<LoginRoute element={<Login/>}/>}/> 
        <Route path='/orders' element={<OrderHistory/>}/> 
        <Route path='/' element={<Dashboard/>}/>

      </Routes>
    </div>
  )
}

export default App;