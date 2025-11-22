import { NavLink } from 'react-router-dom';
import { assets } from '../../assets/assets';
import './MenuBar.css'
const Menubar = () => {
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
        </div>
    </nav>
  );
};

export default Menubar;
