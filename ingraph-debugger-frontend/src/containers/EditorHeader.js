import React from 'react';
import {connect} from 'react-redux';
import CodeMirror from 'react-codemirror';

import 'codemirror/lib/codemirror.css';
import 'codemirror/theme/neo.css';
import 'codemirror/mode/cypher/cypher';

import SubmitButton from '../components/SubmitButton';
import {Colors} from '../StyleProvider';

class EditorHeader extends React.Component {

    constructor() {
        super();
        this.state = {};
    }

    handleChange = (newValue) => {
        this.setState({
            modified: true,
            modifiedQuery: newValue,
        })
    };

    handleSave = () => {
        if (this.state.modified) {
            this.props.dispatch({
                type: 'VALIDATE_QUERY_DEFINITION',
                id: this.props.activeQuery.get('id'),
                definition: 'Something'
            })
        }
    };

    renderPlaceholder() {
        return (
            <div style={styles.mainContainer}>
                <p style={styles.placeholderText}>Add a new query</p>
            </div>
        )
    }

    renderEditor() {
        const options = {
            lineNumbers: true,
            mode: 'cypher',
            theme: 'neo',
        };

        return (
            <div style={styles.mainContainer}>
                <div style={styles.editorContainer}>
                    <CodeMirror value={this.props.activeQuery.get('definition')} options={options}
                                onChange={this.handleChange}/>
                </div>
                <SubmitButton handleClick={this.handleSave}/>
            </div>
        )
    }

    componentWillReceiveProps() {
        this.setState({
            modified: false,
        })
    }

    render() {
        return this.props.activeQuery === null ? this.renderPlaceholder() : this.renderEditor();
    }

}

const styles = {
    mainContainer: {
        display: 'flex',
        height: '100px',
        padding: '10px',
        boxShadow: '0 0 10px rgba(0,0,0,0.25)',
        borderBottom: ('1px solid ' + Colors.primary.dark),
        backgroundColor: Colors.primary.normal,
        zIndex: 1,
    },
    editorContainer: {
        flex: 1,
    },
    placeholderText: {
        flex: 1,
        cursor: 'default',
        lineHeight: '100px',
        color: 'white',
        fontSize: 26,
        fontWeight: '100',
        textAlign: 'center',
    },
    submitButton: {
        width: '200px',
        height: '100px',
        backgroundColor: 'red'
    },
};

//rgb(114,12,232)

function mapStateToProps(state) {
    return {
        activeQuery: state.getIn(['app', 'queries', state.getIn(['app', 'activeId'])]) || null,
    }
}

export default connect(mapStateToProps)(EditorHeader);