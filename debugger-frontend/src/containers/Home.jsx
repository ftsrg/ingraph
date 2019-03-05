import React from 'react';
import style from  './Home.mcss';

import Header from './Header';
import Result from "./Result";

export default class Home extends React.Component {
    render() {
        return (
            <div className={style.homeContainer}>
                <Header />
                <Result />
            </div>
        )
    }
}
