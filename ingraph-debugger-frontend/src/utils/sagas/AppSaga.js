import {call, put, takeEvery} from 'redux-saga/effects';

import API from '../API';

function* watchModifyQuery(action) {
    yield put({
        type: 'MODIFY_QUERY_DEFINITION',
        id: action.query.get('id'),
        definition: action.definition,
    });

    if (action.query.get('handlerId')) {
        const response = yield API.deregisterQuery(action.query.get('handlerId'));
        if (response.status !== 200) {
            throw new Error('Query cannot be deregistered');
        }
    }

    const response = yield API.registerQuery(action.definition);
    console.log(response);
    if (response.status !== 200) {
        throw new Error('Query cannot be registered');
    }

    const data = yield response.json();
    console.log(data);
    if (data.status === 'OK') {
        yield put({
            type: 'QUERY_PARSE_OK',
            id: action.query.get('id'),
            sessionId: data.body.sessionId,
        });
    } else {
        console.log(data.body.message);
        yield put({
            type: 'QUERY_PARSE_FAIL',
            id: action.query.get('id'),
            message: data.body.message,
        });
    }

}

export default function*() {
    yield [
        takeEvery("REGISTER_QUERY_DEFINITION", watchModifyQuery),
    ]
};