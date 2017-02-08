import React from 'react';
import Radium from 'radium';

import {Colors} from '../StyleProvider';

class AddButton extends React.Component {

    handleClick = () => {
        if (this.props.handleClick) {
            this.props.handleClick();
        }
    };

    render() {
        return (
            <div style={styles.mainContainer} onClick={this.handleClick}>
                <p style={styles.label}>
                    {this.props.label}
                </p>
            </div>
        )
    }
}
AddButton.propTypes = {
    label: React.PropTypes.string,
    handleClick: React.PropTypes.func,
};

const styles = {
    mainContainer: {
        height: '40px',

        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',

        cursor: 'pointer',
        backgroundColor: Colors.secondary.normal,
        borderBottom: '1px solid ' + Colors.secondary.border,

        transition: 'background-color 0.3s',
        ':hover': {
            transition: 'background-color 0.3s',
            backgroundColor: Colors.secondary.hover,
        },
        ':active': {
            transition: 'background-color 0.3s',
            backgroundColor: Colors.secondary.active,
        }
    },
    label: {
        color: 'white',
    }
};

export default Radium(AddButton);