import { forwardRef, useImperativeHandle, useState } from "react";
import Course from '../models/Course';
import CourseService from '../services/Course.Service';
import { Modal } from 'react-bootstrap';
import { useEffect } from "react";


const CourseSave = forwardRef((props, ref) => {
    useImperativeHandle(ref, () => ({
        // interaction with parent
        
        // Function to call the modal
        // call this function from the parent component, so we can call the function is accessible outside
        showCourseModal() {
            setTimeout(() => {
                setShow(true);
            }, 0);
            
        }
    }));


    // send it from parent
    useEffect(() => {
        setCourse(props.course);
        setEdit(props.edit);
    }, [props.course]);


    const [course, setCourse] = useState(new Course('', '', 0));
    const [errorMessage, setErrorMessage] = useState('');
    const [submitted, setSubmitted] = useState(false);
    const [show,setShow] = useState(false);
    const [edit, setEdit] = useState(false);



    const saveCourse = (e) => {
        
        e.preventDefault();
        
        setSubmitted(true);
        
        if(!course.id || !course.title || !course.subtitle || !course.price){
            setShow(false);
            return;
        }
        
        CourseService.saveCourse(course).then(response => {

            props.onSaved(response.data);
            
            setShow(false);
        }).catch((err) => {
            setErrorMessage('Save course error!!!');
        })

    };


    const saveEdit = (e) => {
        e.preventDefault();
        
        setSubmitted(true);
        console.log(course.id, ' ', course.title, ' ', course.subtitle, ' ', course.price);
        if(!course.id || !course.title || !course.subtitle || !course.price){
            setShow(false);
            return;
        }

        CourseService.partialUpdateCourse(course).then(response => {
            props.onSaved(response.data);
            
            setShow(false);
        }).catch((err) => {
            setErrorMessage('Save course error!!!');
        });
        
    };



    // <input name ="" onChange=(event) => handleChange(event)> 
    const handleChange = (e) => {
        // target element : course title
        // target value : input from user
        const {name, value} = e.target;

        setCourse((prevState => {
            return {
                ...prevState,
                [name] : value
            };
        }));
    };

    const setShowAndEdit = () => {
        setShow(false);
        setEdit(false);
    };



    return (
        <Modal show={show}>
            <form onSubmit={(e) => saveCourse(e)}
            noValidate
            className={submitted ? 'was-validated' : ''}>

                <div className="modal-header">
                    <h5 className="modal-title">
                        Course Details
                    </h5>
                    <button type="button" className="btn-close" onClick={() => setShow(false)}></button>
                </div>

                <div className="modal-body">

                    {errorMessage && 
                        <div className="alert alert-danger">
                            {errorMessage}
                        </div>
                    }

                    <div className="form-group">
                        <label htmlFor="id">ID : </label>
                        <input type="text" 
                                className="form-control" 
                                name="id" 
                                placeholder="id" 
                                value={course.id} 
                                onChange={(e) => handleChange(e)}
                                required />
                        <div className="invalid-feedback">
                            ID is required.
                        </div>
                    </div>

                    <div className="form-group">
                        <label htmlFor="title">Title : </label>
                        <input type="text" 
                                className="form-control" 
                                name="title" 
                                placeholder="title" 
                                value={course.title} 
                                onChange={(e) => handleChange(e)}
                                required />
                        <div className="invalid-feedback">
                            Title is required.
                        </div>
                    </div>

                    <div className="form-group">
                        <label htmlFor="subtitle">Subtitle : </label>
                        <input type="text" 
                                className="form-control" 
                                name="subtitle" 
                                placeholder="subtitle" 
                                value={course.subtitle} 
                                onChange={(e) => handleChange(e)}
                                required />
                        <div className="invalid-feedback">
                            Subtitle is required.
                        </div>
                    </div>

                    <div className="form-group">
                        <label htmlFor="price">Price : </label>
                        <input type="number"
                                min="1"
                                step="any" 
                                className="form-control" 
                                name="price" 
                                placeholder="price" 
                                value={course.price} 
                                onChange={(e) => handleChange(e)}
                                required />
                        <div className="invalid-feedback">
                            Price is required and should be greater than 0.
                        </div>
                    </div>
                    

                </div>

                <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" onClick={() => setShowAndEdit()}>Close</button>
                    {edit ? 
                        <button type="submit" className="btn btn-primary" onClick={() => setEdit(true)}>Save Edit</button> : 
                        <button type="submit" className="btn btn-primary">Create New Course</button>
                    }
                </div>

            </form>
        </Modal>
    );
});

export {CourseSave};
