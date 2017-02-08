import {call, put, takeEvery} from 'redux-saga/effects';

function* watchModifyQuery(action) {
    yield put({
        type: 'MODIFY_QUERY_DEFINITION',
        id: action.id,
        definition: action.definition,
    })
}

export default function*() {
    yield [
        takeEvery("VALIDATE_QUERY_DEFINITION", watchModifyQuery),
    ]
};