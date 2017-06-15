import Immutable from 'immutable';
import uuid from 'uuid/v1';

const initialState = Immutable.fromJS({
    queries: Immutable.fromJS({
        'test': {
            name: 'TEST',
            state: 'PARSED',
            sessionId: null,
            columns: ['test', 'test2'],
            records: [
                {'test': 1, 'test2': 'A'},
                {'test': 2, 'test2': 'B'},
                {'test': 3, 'test2': 'C'}
            ]
        }
    }),
    activeId: 'test',
});

export default (state = initialState, action) => {
    switch (action.type) {
        case "ADD_QUERY": {
            const id = uuid();
            return state.setIn(['queries', id],
                Immutable.fromJS({
                    id: id,
                    name: 'Query #' + (state.get('queries').count() + 1),
                    definition: '',
                })
            ).set(
                'activeId',
                id
            );
        }
        case "SELECT_QUERY": {
            return state.set(
                'activeId',
                action.id
            );
        }
        case "DELETE_QUERY": {
            const deleted = state.deleteIn(['queries', action.id]);
            return deleted.set(
                'activeId',
                deleted.get('queries').count() > 0 ? deleted.get('queries').lastKeyOf() : null,
            );
        }
        case "MODIFY_QUERY_NAME": {
            return state.setIn(['queries', action.id, 'name'], action.name);
        }
        case "MODIFY_QUERY_DEFINITION": {
            return state
                .deleteIn(['queries', action.id, 'sessionId'])
                .deleteIn(['queries', action.id, 'records'])
                .setIn(['queries', action.id, 'definition'], action.definition)
        }
        case "QUERY_PARSED": {
            return state
                .setIn(['queries', action.id, 'state'], 'PARSED')
                .setIn(['queries', action.id, 'sessionId'], action.sessionId)
                .setIn(['queries', action.id, 'columns'], Immutable.fromJS(action.columns))
                .setIn(['queries', action.id, 'records'], Immutable.fromJS(action.initialResults))
        }
        case "QUERY_PARSE_FAIL": {
            return state
                .setIn(['queries', action.id, 'state'], 'FAILED')
                .setIn(['queries', action.id, 'failReason'], action.reason)
        }
        default:
            return state;
    }
}
