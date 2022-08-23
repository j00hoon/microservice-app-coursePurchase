import axios from "axios";
import { BASE_API_URL } from "../common/Constants";
import { authHeader } from "./Base.Service";


const API_URL = BASE_API_URL + '/api/user';


class UserService {

    changeRole(role){
        return axios.put(API_URL + '/update/' + role, {}, {headers: authHeader()});
    }

}

export default new UserService();