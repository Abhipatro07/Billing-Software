import { useEffect, useState } from "react";
import USerForm from "../../Component/UserForm/UserForm";
import UserList from "../../Component/UserList/UserList";
import './ManageUser.css'
import toast from "react-hot-toast";
import { fetchUsers } from "../../Service/UserService";

const ManageUser = () => {
    const [users , setUsers] = useState([])
    const [loading , setLoading] = useState(false)

    useEffect(() => {
        async function loadUsers() {
            try{
                setLoading(true)
                const response = await fetchUsers()
                setUsers(response.data)
            }
            catch(error){
                console.error(error);
                toast.error("Unable to fetch users")                
            }
            finally{
                setLoading(false);
            }
        }
        loadUsers()
    } , [])
    return(
        <div className="users-container text-light">
            <div className="left-column">
                <USerForm setUsers={setUsers}/>
            </div>
            <div className="right-column">
                <UserList users={users} setUsers={setUsers}/>
            </div>
        </div>
    )
}

export default ManageUser;