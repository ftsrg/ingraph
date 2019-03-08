import React from 'react';

import {connect} from 'react-redux';

import 'codemirror/addon/lint/lint';
import 'codemirror/addon/hint/show-hint';
import 'codemirror/addon/edit/closebrackets';
import {createCypherEditor} from 'cypher-codemirror';

import 'codemirror/theme/neo.css';
import 'codemirror/lib/codemirror.css';
import 'codemirror/addon/lint/lint.css';
import 'cypher-codemirror/dist/cypher-codemirror-syntax.css';
import 'cypher-codemirror/dist/cypher-codemirror-completion.css';

import style from './Editor.mcss';

class Editor extends React.Component {

    constructor(props) {
        super(props);
        this.editorNodeRef = React.createRef();
    }

    handleTextChange(event) {
       const definition = this.editor.getValue();
       const parseError = this.editorSupport.parseErrors.length > 0;

       this.props.dispatch({
           type: "DEFINITION_CHANGED",
           definition: definition,
           parseError: parseError
       });
    }

    componentDidMount() {
        const options = {
            lineNumbers: true,
            mode: "cypher",
            theme: 'neo',
            gutters: ['cypher-hints'],
            lineWrapping: true,
            autofocus: true,
            smartIndent: false,
            lint: true,
            extraKeys: {
                'Ctrl-Space': 'autocomplete',
            },
            hintOptions: {
                completeSingle: false,
                closeOnUnfocus: false,
                alignWithWord: true,
                async: true
            },
            autoCloseBrackets: {
                explode: ''
            },
            onChange: this.handleTextChange
        };

        const {editor, editorSupport} = createCypherEditor(
            this.editorNodeRef.current,
            options
        );

        this.editor = editor;
        this.editorSupport = editorSupport;

        editor.on('change', (event) => {
            this.handleTextChange(event)
        })
    }


    render() {
        return (
            <div className={style.editorContainer} ref={this.editorNodeRef}>
            </div>
        )
    }

}

export default connect()(Editor);
