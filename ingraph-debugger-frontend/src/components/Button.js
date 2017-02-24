import React from 'react';
import Radium from 'radium';

import {Colors} from '../StyleProvider';

class Button extends React.Component {

    handleClick = () => {
        if (this.props.handleClick && this.props.enabled) {
            this.props.handleClick();
        }
    };

    render() {
        return (
            <div
                style={[this.props.style, styles.mainContainer, (this.props.enabled ? styles.enabledState : styles.disabledState)]}
                onClick={this.handleClick}>
                    {this.props.children}
            </div>
        )
    }
}
Button.propTypes = {
    enabled: React.PropTypes.bool,
    style: React.PropTypes.string,
    handleClick: React.PropTypes.func,
};
Button.defaultProps = {
    enabled: true,
};

const styles = {
    mainContainer: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',

        transition: 'background-color 0.3s',
    },
    enabledState: {
        cursor: 'pointer',
        backgroundColor: Colors.secondary.normal,

        ':hover': {
            backgroundColor: Colors.secondary.hover,
        },
        ':active': {
            backgroundColor: Colors.secondary.active,
        }
    },
    disabledState: {
        backgroundColor: Colors.gray.dark,
    },
    label: {
        color: 'white',
    }
};

export default Radium(Button);