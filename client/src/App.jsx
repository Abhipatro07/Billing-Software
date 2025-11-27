import { Route, Routes, useLocation } from "react-router-dom";
import Menubar from "./Component/MenuBar/Menubar";
import Dashboard from "./Pages/Dashboard/Dashboard";
import ManageCategory from "./Pages/ManageCategory/ManageCategory";
import Explore from "./Pages/Explore/Explore";
import ManageUser from "./Pages/ManageUser/ManageUser";
import ManageItems from "./Pages/ManageItems/ManageItems";
import { Toaster } from "react-hot-toast";
import Login from "./Pages/Login/Login";

const App = () => {
  const location = useLocation();
  return (
    <div>
      {location.pathname !== "/login" && <Menubar/>}
      <Toaster/>
      <Routes>
        <Route path='/login' element={<Login/>}/> 
        <Route path='/dashboard' element={<Dashboard/>}/>
        <Route path='/category' element={<ManageCategory/>}/>
        <Route path='/explore' element={<Explore/>}/>
        <Route path='/users' element={<ManageUser/>}/>
        <Route path='/items' element={<ManageItems/>}/> 
        <Route path='/' element={<Dashboard/>}/>

      </Routes>
    </div>
  )
}

export default App;