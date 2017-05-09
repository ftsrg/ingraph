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

    const firstLine = action.definition.split('\n')[0];
    if (firstLine.startsWith('//')) {
        yield put({
            type: 'MODIFY_QUERY_NAME',
            id: action.query.get('id'),
            name: firstLine.substr(2)
        })
    }

    const response = yield API.registerQuery(action.definition);
    if (response.status !== 200) {
        throw new Error('Query cannot be registered');
    }

    const data = yield response.json();
    if (data.status === 'OK') {

        const source = API.getNotificationSource(data.body.sessionId);
        while (true) {
            const p =new Promise((res) => {
                source.onmessage = mes => res(mes);
            });
            let message = yield p;
            console.log(message);
        }

    } else {
        yield put({
            type: 'QUERY_PARSE_FAIL',
            id: action.query.get('id'),
            reason: data.body.message,
        });
    }

}

export default function*() {
    yield [
        takeEvery("REGISTER_QUERY_DEFINITION", watchModifyQuery),
    ]
};
