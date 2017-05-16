import {combineReducers} from 'redux-immutable';

import AppReducer from './AppReducer';

export default combineReducers({
    app: AppReducer,
})