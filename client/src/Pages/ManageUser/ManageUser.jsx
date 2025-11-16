import USerForm from "../../Component/UserForm/UserForm";
import UserList from "../../Component/UserList/UserList";
import './ManageUser.css'
const ManageUser = () => {
    return(
        <div className="users-container text-light">
            <div className="left-column">
                <USerForm/>
            </div>
            <div className="right-column">
                <UserList/>
            </div>
        </div>
    )
}

export default ManageUser;