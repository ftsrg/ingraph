import React from 'react';
import {connect} from 'react-redux';
import Immutable from 'immutable';

import Button from '../components/AddButton';
import QueryCard from '../components/QueryCard';

class QuerySidebar extends React.Component {

    handleAddClick = () => {
        this.props.dispatch({type: 'ADD_QUERY'});
    };

    handleQuerySelect = (query) => {
        this.props.dispatch({type: 'SELECT_QUERY', id: query.get('id')});
    };

    handleQueryDelete = (query) => {
        this.props.dispatch({type: 'DELETE_QUERY', id: query.get('id')});
    };

    handleQueryNameChange = (query, name) => {
        this.props.dispatch({type: 'MODIFY_QUERY_NAME', id: query.get('id'), name: name});
    };

    render() {
        return (
            <div style={styles.mainContainer}>
                <Button label='Add query' handleClick={this.handleAddClick}/>
                {
                    this.props.queries.map(
                        (q, id) => {
                            return <QueryCard
                                key={id}
                                query={q}
                                active={id === this.props.activeId}
                                handleSelect={this.handleQuerySelect}
                                handleDelete={this.handleQueryDelete}
                                handleNameChange={this.handleQueryNameChange}
                            />
                        }
                    )
                }
            </div>
        );
    }
}

const styles = {
    mainContainer: {
        width: '250px',
        flex: '0 0 250px',
        backgroundColor: '#F5F5F5',
        borderRight: '1px solid #DDDDDD',
    },
};

// text color #444444
// accent1  #FD756E
// accent2  #0B74BA

function mapStateToProps(state) {
    return {
        activeId: state.getIn(['app', 'activeId']),
        queries: state.getIn(['app', 'queries']),
    }
}

export default connect(mapStateToProps)(QuerySidebar);