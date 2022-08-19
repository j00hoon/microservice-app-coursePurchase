import { useDispatch, useSelector } from 'react-redux';
import User from '../../models/User';
import AuthenticationService from '../../services/Authentication.service';
import { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faUserNinja } from '@fortawesome/free-solid-svg-icons';
import { setCurrentUser } from '../../store/actions/User'


// function LoginPage();
function LoginPage() {

    const [user, setUser] = useState(new User('', '', ''));
    const [loading, setLoading] = useState(false);
    const [submitted, setSubmitted] = useState(false);
    const [errorMessage, setErrorMessage] = useState('')

    // Can check Redux current user
    const currentUser = useSelector(state => state.user);

    const navigate = useNavigate();

    const dispatch = useDispatch();

    // 1st parameter is useEffect() function, 2nd parameter is dependency array
    // if the 2nd array is empty, it is going to be conducted everytime when rendering happens
    useEffect( () => {
        if(currentUser?.id){
            navigate('/profile');
        }   
    }, []);

    // <input onChange=(event => handleChange(event))>
    const handleChange = (e) => {
        // target element can be "username", and the value is whatever the user input
        const {name, value} = e.target;

        // ex
        // prevState ({user : abc, password : abc}) + newKeyValue ({user : abcd}) => ({user: abcd, password : abc})
        setUser((prevState => {
            return {
                ...prevState,
                [name]: value
            };
        }));
    }

    const handleLogin = (e) => {

        e.preventDefault();

        setSubmitted(true);

        if(!user.username || !user.password){
            return;
        }

        setLoading(true);
        //navigate('/profile');

        AuthenticationService.login(user).then(_ => {
            navigate('/profile');
        }).catch(error => {
            setErrorMessage('Login Error!!!');
            setLoading(false);
        })

        AuthenticationService.login(user).then(response => {
            // set user in session
            dispatch(setCurrentUser(response.data));
            navigate('/profile');
        }).catch(error => {
            console.log(error);
            setErrorMessage('username or password is not valid');
        })

        e.target.reset();

    };


    return (
        <div className="container mt-5">
            <div className="card ms-auto me-auto p-3 shadow-lg custom-card">

                <FontAwesomeIcon icon={faUserNinja} className="ms-auto me-auto user-icon" />

                {errorMessage && 
                    <div className="alert alert-danger">
                        {errorMessage}
                    </div>
                }

                <form onSubmit={(e) => handleLogin(e)}
                noValidate
                className={submitted ? 'was-validated' : ''}>

                    <div className="form-group">
                        <label htmlFor="name">Username : </label>
                        <input type="text" 
                                className="form-control" 
                                name="username" 
                                placeholder="username" 
                                value={user.username} 
                                onChange={(e) => handleChange(e)}
                                required />
                        <div className="invalid-feedback">
                            Username is required.
                        </div>
                    </div>

                    <div className="form-group">
                        <label htmlFor="name">Password : </label>
                        <input type="password" 
                                className="form-control" 
                                name="password" 
                                placeholder="password" 
                                value={user.password} 
                                onChange={(e) => handleChange(e)}
                                required />
                        <div className="invalid-feedback">
                            Password is required.
                        </div>
                    </div>

                    <button type="submit" className="btn btn-info w-100 mt-3"
                    disabled={loading}>Sign In</button>

                </form>

                <Link to="/register" className="btn btn-link" style={{color: "darkgray"}}>
                    Create New Account!
                </Link>
                
            </div>
        </div>
    );
};

export default LoginPage;