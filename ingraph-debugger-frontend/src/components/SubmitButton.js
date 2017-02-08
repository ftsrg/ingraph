import React from 'react';
import Radium from 'radium';

import {MdSave} from 'react-icons/lib/md';

import {Colors} from '../StyleProvider';

class SubmitButton extends React.Component {

    handleClick = () => {
        if (this.props.handleClick) {
            this.props.handleClick();
        }
    };

    render() {
        return (
            <div style={styles.mainContainer} onClick={this.handleClick}>
                <MdSave color="white" size={30} />
            </div>
        )
    }
}
SubmitButton.propTypes = {
    label: React.PropTypes.string,
    handleClick: React.PropTypes.func,
};

const styles = {
    mainContainer: {
        height: '100px',
        width: '40px',
        marginLeft: '10px',

        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',

        cursor: 'pointer',
        backgroundColor: Colors.secondary.normal,
        borderRadius: '10px',

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

export default Radium(SubmitButton);