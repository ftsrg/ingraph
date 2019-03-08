import React from 'react';
import styles from './Header.mcss';

import {connect} from 'react-redux';

import AppBar from "@material-ui/core/es/AppBar";
import Toolbar from "@material-ui/core/es/Toolbar";

import Editor from "./Editor";
import Button from "@material-ui/core/es/Button/Button";

class Header extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            modified: true,
            definition: ''
        };
    }

    handleRegisterQuery() {
        this.props.dispatch({
            type: 'REGISTER_QUERY'
        })
    };

    handleExecuteQuery() {
        this.props.dispatch({
            type: 'REGISTER_QUERY'
        })
    };

    render() {
        return (
            <AppBar position="static" color="default">
                <Toolbar className={styles.toolbar}>
                    <Editor/>
                    <div className={styles.buttonContainer}>
                        <Button
                            onClick={() => this.handleRegisterQuery()}
                            disabled={this.props.buttonEnabled}
                            className={styles.button}
                            variant="outlined"
                            color="primary">Register
                        </Button>
                        <Button
                            onClick={() => this.handleExecuteQuery()}
                            disabled={this.props.buttonEnabled}
                            className={styles.button}
                            variant="outlined"
                            color="secondary">Execute
                        </Button>
                    </div>
                </Toolbar>
            </AppBar>
        )
    }
}

export default connect(
    (state) => {
        return {
            buttonEnabled: !state.parseError
        }
    }
)(Header);
