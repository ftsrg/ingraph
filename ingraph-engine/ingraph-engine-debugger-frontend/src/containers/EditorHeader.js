import React from 'react';
import {connect} from 'react-redux';
import CodeMirror from 'react-codemirror';
import {MdSave} from 'react-icons/lib/md';

import 'codemirror/lib/codemirror.css';
import 'codemirror/theme/neo.css';
import 'codemirror/mode/cypher/cypher';

import Button from '../components/Button';
import {Colors} from '../StyleProvider';

class EditorHeader extends React.Component {

    constructor() {
        super();
        this.state = {
            definition: '',
        };
    }

    handleChange = (newValue) => {
        this.setState({
            modified: newValue !== this.props.activeQuery.get('definition'),
            definition: newValue,
        })
    };

    handleSave = () => {
        if (this.state.modified) {
            this.props.dispatch({
                type: 'REGISTER_QUERY_DEFINITION',
                query: this.props.activeQuery,
                definition: this.state.definition,
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
                    <CodeMirror value={this.state.definition} options={options} onChange={this.handleChange}/>
                </div>
                <Button
                    style={styles.submitButton}
                    enabled={this.state.modified}
                    handleClick={this.handleSave}>
                    <MdSave color="white" size={30} />
                </Button>
            </div>
        )
    }

    componentWillReceiveProps(newProps) {
        if (newProps.activeQuery) {
            this.setState({
                modified: false,
                definition: newProps.activeQuery.get('definition'),
            });
        }
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
        height: '100px',
        width: '40px',
        marginLeft: '10px',
        borderRadius: '10px',
    },
};

//rgb(114,12,232)

function mapStateToProps(state) {
    return {
        activeQuery: state.getIn(['app', 'queries', state.getIn(['app', 'activeId'])]) || null,
    }
}

export default connect(mapStateToProps)(EditorHeader);