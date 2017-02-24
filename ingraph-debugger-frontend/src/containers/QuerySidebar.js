import React from 'react';
import {connect} from 'react-redux';
import Immutable from 'immutable';

import {Colors} from '../StyleProvider';
import Button from '../components/Button';
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
                <Button style={styles.addButton} label='Add query' handleClick={this.handleAddClick}>
                    <p style={styles.buttonText}>Add Query</p>
                </Button>
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
        backgroundColor: Colors.gray.normal,
        borderRight: '1px solid ' + Colors.gray.dark,
    },
    addButton: {
        height: '40px',
    },
    buttonText: {
        color: 'white',
    }
};

function mapStateToProps(state) {
    return {
        activeId: state.getIn(['app', 'activeId']),
        queries: state.getIn(['app', 'queries']),
    }
}

export default connect(mapStateToProps)(QuerySidebar);