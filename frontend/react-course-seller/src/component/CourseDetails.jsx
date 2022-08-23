import { useEffect } from "react";
import { forwardRef, useImperativeHandle, useState } from "react";
import { Modal } from 'react-bootstrap';
import Course from '../models/Course';

const CourseDetail = forwardRef((props, ref) => {

    const [show, setShow] = useState(false);
    const [course, setCourse] = useState(new Course('', '', 0));

    useImperativeHandle(ref, () => ({
        showDetailModal(){
            setShow(true);
        }
    }));

    useEffect(() => {
        setCourse(props.course);
    }, [props.course]);

    const detailCourse = () => {
        props.onDetailed();
        setShow(false);
    }

    
    return (
        <Modal show={show}>
            
            <div className="modal-header">
                <h5 className="modal-title">
                    Course Details
                </h5>
                <button type="button" className="btn-close" onClick={() => setShow(false)}></button>
            </div>

            <div className="modal-body">

                <div className="form-group">
                    <label htmlFor="id">ID<p className="form-control">{course.id} </p></label>
                </div>
                <div className="form-group">
                    <label htmlFor="id">Title<p className="form-control">{course.title} </p></label>
                </div>
                <div className="form-group">
                    <label htmlFor="id">Subtitle<p className="form-control">{course.subtitle} </p></label>
                </div>
                <div className="form-group">
                    <label htmlFor="id">Price<p className="form-control">{course.price} </p></label>
                </div>
                <div className="form-group">
                    <label htmlFor="id">Created time<p className="form-control">{course.createdTime} </p></label>
                </div>

            </div>

            <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={() => setShow(false)}>Cancel</button>
                <button type="button" className="btn btn-danger" onClick={() => detailCourse()}>Close detail</button>
            </div>

        </Modal>
    )
    
});

export { CourseDetail };