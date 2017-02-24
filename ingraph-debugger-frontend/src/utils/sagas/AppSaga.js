import {call, put, takeEvery} from 'redux-saga/effects';

import API from '../API';

function* watchModifyQuery(action) {
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

    yield put({
        type: 'MODIFY_QUERY_DEFINITION',
        id: action.query.get('id'),
        definition: action.definition,
    });

}

export default function*() {
    yield [
        takeEvery("REGISTER_QUERY_DEFINITION", watchModifyQuery),
    ]
};