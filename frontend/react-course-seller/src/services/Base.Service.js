
import store from '../store'

// equal to function authHeader();
export const authHeader = () => {
    const currentUser = store.getState().user;

    return {
        'Content-Type' : 'application/json',
        'authorization' : 'Bearer ' + currentUser?.token,
    };
}