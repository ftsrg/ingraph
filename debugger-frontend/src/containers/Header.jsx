import React from 'react';
import styles from './Header.mcss';

import AppBar from "@material-ui/core/es/AppBar";
import Toolbar from "@material-ui/core/es/Toolbar";

import Editor from "./Editor";
import Button from "@material-ui/core/es/Button/Button";

export default class Header extends React.Component {
    render() {
        return (
            <AppBar position="static" color="default">
                <Toolbar className={styles.toolbar}>
                    <Editor />
                    <div className={styles.buttonContainer}>
                        <Button className={styles.button} variant="outlined" color="primary">Register</Button>
                        <Button className={styles.button} variant="outlined" color="secondary">Execute</Button>
                    </div>
                </Toolbar>
            </AppBar>
        )
    }
}
