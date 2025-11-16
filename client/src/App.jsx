import { Route, Routes } from "react-router-dom";
import Menubar from "./Component/MenuBar/Menubar";
import Dashboard from "./Pages/Dashboard/Dashboard";
import ManageCategory from "./Pages/ManageCategory/ManageCategory";
import Explore from "./Pages/Explore/Explore";
import ManageUser from "./Pages/ManageUser/ManageUser";
import ManageItems from "./Pages/ManageItems/ManageItems";

const App = () => {
  return (
    <div>
      <Menubar/>
      <Routes>
        <Route path='/' element={<Dashboard/>}/>
        <Route path='/dashboard' element={<Dashboard/>}/>
        <Route path='/category' element={<ManageCategory/>}/>
        <Route path='/explore' element={<Explore/>}/>
        <Route path='/users' element={<ManageUser/>}/>
        <Route path='/items' element={<ManageItems/>}/>

      </Routes>
    </div>
  )
}

export default App;