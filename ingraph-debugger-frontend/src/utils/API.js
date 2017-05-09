import {stringify} from 'querystring';

const baseURL = "http://localhost:8080/ingraph-debugger-backend-0.2.0/api/";

class API {

    static request(resource, body, method = 'POST') {
        let url = baseURL + resource;

        return fetch(url, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body),
        });
    }

    static registerQuery(definition) {
        return API.request("register", {
            definition: definition
        });
    }

    static deregisterQuery(actionId) {
        return API.request("deregister", {
            id: actionId
        });
    }

    static getNotificationSource(sessionId) {
        return new EventSource(
            baseURL + 'notification?id=' + sessionId,
        );
    }
}

export default API;
