// import { combineReducers, createStore } from 'redux';
import { configureStore, } from '@reduxjs/toolkit';
import userReducer from "./reducers/User";
// import { combineReducers } from 'redux'



// const allReducers = combineReducers({
    
// });




// Store
// Keep checking the status of current "State" and then let "View" knows the updated data
const store = configureStore({
    reducer:{
        user: userReducer,
    }
});

export default store;