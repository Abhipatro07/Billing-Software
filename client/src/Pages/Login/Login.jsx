import { useContext, useState } from 'react';
import './Login.css'
import toast from 'react-hot-toast';
import { login } from '../../Service/AuthService';
import { useNavigate } from 'react-router-dom';
import { AppContext } from '../../Context/AppContext';

const Login = () => {
    const { setAuthData } = useContext(AppContext);
    const navigate = useNavigate()
    const [loding, setLoding] = useState(false);
    const [data, setData] = useState({
        email: "",
        password: "",
    });

    const onChangeHandler = (e) => {
        setData(prev => ({
            ...prev,
            [e.target.name]: e.target.value
        }));
    };


    const onSubmirHandler = async (e) => {
        e.preventDefault();
        setLoding(true);

        try {
            const response = await login(data);
            if (response.status === 200) {
                toast.success("Login successful")
                localStorage.setItem("token", response.data.token);
                localStorage.setItem("role", response.data.role);
                setAuthData(response.data.token, response.data.role)
                navigate("/dashboard")
            }
        }
        catch (error) {
            console.error(error);
            toast.error("Email or Password Invalid");
        }
        finally {
            setLoding(false);
        }
    }
    return (
        <div className="bg-light d-flex align-items-center justify-content-center vh-100 login-background">
            <div className="card shadow-lg w-100" style={{ maxWidth: "480px" }}>
                <div className="card-body">
                    <div className="text-center">
                        <h1 className='card-title'> Sign in </h1>
                        <p className='card-text text-muted'>
                            Sign in below to access your account
                        </p>
                    </div>
                    <div className="mt-4">
                        <form onSubmit={onSubmirHandler}>
                            <div className="mb-4">
                                <label htmlFor="email" className="form-label text-muted">Email Address</label>
                                <input type="text" name="email" value={data.email} onChange={onChangeHandler} className="form-control" placeholder="yourname@example.com"/>
                            </div>

                            <div className="mb-4">
                                <label htmlFor="password" className="form-label text-muted">Password</label>
                                <input type="password" name="password" value={data.password} onChange={onChangeHandler} className="form-control" placeholder="**********"/>
                            </div>

                            <div className="d-grid">
                                <button type="submit" className="btn btn-dark btn-lg" disabled={loding}>
                                    {loding ? "Loading..." : "Sign in"}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login;