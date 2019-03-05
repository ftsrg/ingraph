import React from 'react';

import 'codemirror/addon/lint/lint';
import 'codemirror/addon/hint/show-hint';
import 'codemirror/addon/edit/closebrackets';
import { createCypherEditor, parse } from 'cypher-codemirror';
import CodeMirror from 'codemirror';

import 'codemirror/theme/neo.css';
import 'codemirror/lib/codemirror.css';
import 'codemirror/addon/lint/lint.css';
import 'cypher-codemirror/dist/cypher-codemirror-syntax.css';
import 'cypher-codemirror/dist/cypher-codemirror-completion.css';

import style from './Editor.mcss';

export default class Editor extends React.Component {

    constructor(props) {
        super(props);
        this.editorNodeRef = React.createRef();
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
        };

        const {editor, editorSupport} = createCypherEditor(this.editorNodeRef.current, options);

        this.editor = editor;
        this.editorSupport = editorSupport;
    }


    render() {
        return (
            <div className={style.editorContainer} ref={this.editorNodeRef}></div>
        )
    }

}
