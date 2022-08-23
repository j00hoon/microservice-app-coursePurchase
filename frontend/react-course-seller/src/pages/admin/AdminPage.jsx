import { useEffect, useState, useRef } from 'react';
import { CourseSave } from '../../component/CourseSave';
import CourseService from '../../services/Course.Service';
import { CourseDelete } from '../../component/CourseDelete';
import { CourseDetail } from '../../component/CourseDetails';
import Course from '../../models/Course';


// function AdminPage();
const AdminPage = () => {

    const [courseList, setCourseList] = useState([]);
    const [selectedCourse, setSelectedCourse] = useState(new Course('', '', 0));
    const [edit, setEdit] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const saveComponent = useRef();
    const deleteComponent = useRef();
    const detailComponent = useRef();

    useEffect(() => {
        CourseService.getAllCourses().then((response) => {
            setCourseList(response.data);
        });
    }, []);

    // call the child functions
    const createCourseRequest = () => {
        setSelectedCourse(new Course('', '', 0));
        setEdit(false);
        saveComponent.current?.showCourseModal();
    };

    const editCourseRequest = (courseItem) => {
        setSelectedCourse(Object.assign({}, courseItem));
        setEdit(true);
        saveComponent.current?.showCourseModal();
    }

    const detailCourseRequest = (courseItem) => {
        setSelectedCourse(courseItem);
        detailComponent.current?.showDetailModal();
    }

    const detailCourse = () => {
        if(window.confirm("Detail page?")){
            alert("If Detail page!");
        }
        else{
            alert("Else Detail page!");
        }
    }

    const saveCourseWatcher = (course) => {
        let itemIndex = courseList.findIndex(item => item.id === course.id);

        if(itemIndex !== -1){
            // Edit a course
            const newList = courseList.map((item) => {
                if(item.id === course.id){
                    return course;
                }
                return item;
            });
            setCourseList(newList);
        } 
        else {
            // Create a new course
            const newList = courseList.concat(course);
            setCourseList(newList);
        }   
    };

    const deleteCourseRequest = (course) => {
        setSelectedCourse(course);
        deleteComponent.current?.showDeleteModal();
    };

    const deleteCourse = () => {
        CourseService.deleteCourse(selectedCourse).then(() => {
            setCourseList(courseList.filter(i => i.id !== selectedCourse.id));
        }).catch((err) => {
            setErrorMessage('Delete errro!!!');
            console.log(err);
        });
    }

    // const deleteCourse = (course) => {
    //     if(window.confirm("Are you sure want to delete?")){
    //         CourseService.deleteCourse(course).then(() => {
    //             setCourseList(courseList.filter(i => i.id !== course.id));
    //         }).catch((err) => {
    //             setErrorMessage('Delete errro!!!');
    //             console.log(err);
    //         });
    //         alert("Delete completed.");
    //     }
    //     else{
    //         alert("Delete canceled.");
    //     }
        
    // };



    return (
        <div>
            <div className="container">
                <div className="pt-5">

                {errorMessage && 
                    <div className="alert alert-danger">
                        {errorMessage}
                    </div>
                }

                    <div className="card">
                        <div className="card-header">
                            <div className="row">
                                <div className="col-6">
                                    <h3>All Courses</h3>
                                </div>

                                <div className="col-6 text-end">
                                    <button className="btn btn-primary" onClick={() => createCourseRequest()}>
                                        Create Course
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div className="card-body">
                                <table className="table table-striped">

                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Title</th>
                                            <th scope="col">Subtitle</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        {courseList.map((courseItem, ind) =>
                                            <tr key={courseItem.id}>
                                            <th scope="row">{ind + 1}</th>
                                                <td>{courseItem.title}</td>
                                                <td>{courseItem.subtitle}</td>
                                                <td>{`$ ${courseItem.price}`}</td>
                                                <td>{new Date(courseItem.createdTime).toLocaleDateString('en-US')}</td>
                                                <td>
                                                    <button className="btn btn-primary me-1" onClick={() => editCourseRequest(courseItem)}>
                                                        Edit
                                                    </button>
                                                    <button className="btn btn-danger me-1" onClick={() => deleteCourseRequest(courseItem)}>
                                                        Delete
                                                    </button>
                                                    <button className="btn btn-success" onClick={() => detailCourseRequest(courseItem)}>
                                                        Details
                                                    </button>
                                            </td>
                                        </tr>
                                        )}
                                        
                                    </tbody>

                                </table>
                        </div>
                    </div>
                </div>

            </div>

            {/* if we set with "ref", we can access all the functions of CourseSave component from the AdminPage component */}
            <CourseSave ref={saveComponent} course={selectedCourse} edit={edit} onSaved={(p) => saveCourseWatcher(p)}></CourseSave>
            <CourseDelete ref={deleteComponent} onConfirmed={() => deleteCourse()}></CourseDelete>
            <CourseDetail ref={detailComponent} course={selectedCourse} onDetailed={() => detailCourse()}></CourseDetail>

        </div>
    );
};

export {AdminPage};