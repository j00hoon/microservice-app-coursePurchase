import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import User from '../../models/User';
import AuthenticationService from '../../services/Authentication.service';
import './RegisterPage.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { faUserNinja } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';


// function RegisterPage();
const RegisterPage = () => {

    const [user, setUser] = useState(new User('', '', ''));
    const [loading, setLoading] = useState(false);
    const [submitted, setSubmitted] = useState(false);
    const [errorMessage, setErrorMessage] = useState('')

    // Can check Redux current user
    const currentUser = useSelector(state => state.user);

    const navigate = useNavigate();

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

    const handleRegister = (e) => {

        e.preventDefault();


        setSubmitted(true);

        if(!user.username || !user.password || !user.name){
            return;
        }

        setLoading(true);

        // AuthenticationService.register(user).then(_ => {
        //     navigate('/login');
        // }).catch(error => {
        //     setErrorMessage('Register Error!!!');
        //     setLoading(false);
        // })
        
        
        AuthenticationService.register(user).then(_ => {
            navigate('/login');
        }).catch(error => {
            console.log(error);
            if(error?.response?.status === 409) {
                setErrorMessage('username or password is not valid.');
            } else {
                console.log(error.response.status);
                setErrorMessage('Unexpected error occurred.');
                setUser("")
            }
            setLoading(false);
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

                <form onSubmit={(e) => handleRegister(e)}
                noValidate
                className={submitted ? 'was-validated' : ''}>

                    <div className="form-group">
                        <label htmlFor="name">Full name : </label>
                        <input type="text" 
                                className="form-control" 
                                name="name" 
                                placeholder="name" 
                                value={user.name} 
                                onChange={(e) => handleChange(e)}
                                required />
                        <div className="invalid-feedback">
                            Full name is required.
                        </div>
                    </div>

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
                    disabled={loading}>Sign up</button>

                </form>

                <Link to="/login" className="btn btn-link" style={{color: "darkgray"}}>
                    I have an Account!
                </Link>
                
            </div>
        </div>
    );
};

export {RegisterPage};