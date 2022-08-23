import axios from 'axios';
import { BASE_API_URL } from '../common/Constants';
import { authHeader } from './Base.Service';


const API_URL = BASE_API_URL + '/gateway/course';

class CourseService{

    saveCourse(course){
        return axios.post(API_URL, course, {headers: authHeader()});
    }

    deleteCourse(course){
        return axios.delete(API_URL + '/delete/' + course.id, {headers: authHeader()});
    }

    getAllCourses(){
        return axios.get(API_URL, {headers: authHeader()});
    }

    partialUpdateCourse(course){
        return axios.patch(API_URL + '/partialUpdate/' + course.id, {headers: authHeader()});
    }

}

export default new CourseService();

