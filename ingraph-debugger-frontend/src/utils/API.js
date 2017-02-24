import {stringify} from 'querystring';

const baseURL = "http://localhost:8000/ingraph-debugger-backend/api/";

class API {

    static request(resource, querystring, method = 'POST') {
        let url = baseURL + resource;
        if (querystring) {
            url = url + '?' + querystring
        }

        return fetch(url, {
            method: method
        });
    }

    static registerQuery(definition) {
        const querystring = stringify({
            definition: definition
        });
        return API.request("register", querystring);
    }

    static deregisterQuery(actionId) {
        const querystring = stringify({
            id: actionId
        });
        return API.request("deregister", querystring);
    }

}

export default API;