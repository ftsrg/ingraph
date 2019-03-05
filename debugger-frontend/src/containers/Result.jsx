import React from 'react';
import style from  './Result.mcss';

export default class Result extends React.Component {
    render() {
        return (
            <div className={style.resultContainer}>
                <p className={style.resultPlaceholder}>No query registered</p>
            </div>
        )
    }
}
