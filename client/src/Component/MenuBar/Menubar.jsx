import { NavLink, useNavigate } from 'react-router-dom';
import { assets } from '../../assets/assets';
import './MenuBar.css'
import { useContext } from 'react';
import { AppContext } from '../../Context/AppContext';

const Menubar = () => {
  const navigate = useNavigate()
  const {setAuthData} = useContext(AppContext)
  const logout = () => {
    localStorage.removeItem("token")
    localStorage.removeItem("role")
    setAuthData({ token: null, role: null })
    navigate("/login")
  }
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-2">
      <a className="navbar-brand" href="#">
        <img
          src={assets.logo}
          alt="Logo"
          height="40"
        />
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse p-2" id="navbarNav">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
          <li className="nav-item">
            <NavLink className="nav-link" to="/dashboard">Dashboard</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/explore">
              Explore
            </NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/items">
              Manage Item
            </NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/category">
              Manage Categories
            </NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/users">
              Manage Users
            </NavLink>
          </li>
        </ul>

        <ul className="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
          <li className="nav-item dropdown">
            <a href="#" className="nav-link dropdown-toggle" id='navbarDropdown' role='button' data-bs-toggle="dropdown" aria-expanded="false">
              <img src={assets.profile} alt="" height={32} width={32} />
            </a>
            <ul className="dropdown-menu dropdown-menu-end" aria-labelledby='navbarDropdown'>
              <li>
                <a href="#!" className="dropdown-item">Settings</a>
                <a href="#!" className="dropdown-item">Activity Logs</a>
                <li><hr className='dropdown-divider'/></li>
                <a href="#!" className="dropdown-item" onClick={logout}>Log Out</a>
              </li>
            </ul>
          </li>
        </ul>
        </div>
    </nav>
  );
};

export default Menubar;
