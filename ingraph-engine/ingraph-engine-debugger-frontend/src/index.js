import React from 'react';
import ReactDOM from 'react-dom';
import {createStore, applyMiddleware} from 'redux';
import {Provider} from 'react-redux';
import {composeWithDevTools} from 'remote-redux-devtools';
import createSagaMiddleware from 'redux-saga';


import sagas from './utils/sagas';
import reducers from './utils/reducers';
import App from './App';

const sagaMiddleware = createSagaMiddleware();
const store = createStore(
    reducers,
    composeWithDevTools({realtime: true, port: 8000})(applyMiddleware(sagaMiddleware))
);
sagaMiddleware.run(sagas);

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);