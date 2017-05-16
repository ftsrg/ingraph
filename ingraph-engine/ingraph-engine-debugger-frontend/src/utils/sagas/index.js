import {fork} from "redux-saga/effects";

import AppSaga from "./AppSaga";

export default function* mainSaga() {
    yield [
        fork(AppSaga),
    ]
};