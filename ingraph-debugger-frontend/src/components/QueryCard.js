import React from 'react';
import Radium from 'radium';
import {MdEdit, MdDelete} from 'react-icons/lib/md';

class QueryCard extends React.Component {

    constructor() {
        super();
        this.state = {
            edit: false,
            name: "",
        }
    }

    toggleEdit = () => {
        if (this.state.edit) {
            this.setState({
                edit: false,
            });
        } else {
            this.setState({
                edit: true,
                name: this.props.query.get('name'),
            });
        }
    };

    handleNameChange = (e) => {
        if (e) {
            e.stopPropagation();
        }
        if (this.props.handleNameChange) {
            this.props.handleNameChange(this.props.query, this.state.name);
        }
    };

    handleDelete = (e) => {
        e.stopPropagation();
        if (this.props.handleDelete) {
            this.props.handleDelete(this.props.query);
        }
    };

    handleSelect = (e) => {
        e.stopPropagation();
        if (this.props.handleSelect) {
            this.props.handleSelect(this.props.query);
        }
    };

    renderTextField() {
        const handleKeydown = (e) => {
            if (e.keyCode === 27) {
                this.toggleEdit();
            }
        };

        const handleChange = (e) => {
            e.stopPropagation();
            this.setState({name: e.target.value});
        };

        const handleSubmit = (e) => {
            e.preventDefault();
            if (this.state.name.length !== 0) {
                this.handleNameChange();
                this.toggleEdit();
            }
        };

        if (this.state.edit) {
            return (
                <form style={styles.editContainer} onSubmit={handleSubmit}>
                    <input style={styles.editField} value={this.state.name} onChange={handleChange} maxLength={50}/>
                </form>
            )
        } else {
            return <p style={styles.nameText}>{this.props.query.get('name')}</p>
        }
    }

    render() {
        //console.log(this.props.query);

        return (
            <div style={[styles.mainContainer, this.props.active ? {borderLeft: '5px solid #68BDF6'} : null]}
                 onClick={this.handleSelect}>
                {this.renderTextField()}
                <div style={styles.bottomContainer}>
                    <MdEdit size={25} style={styles.editIcon} onClick={this.toggleEdit}/>
                    <MdDelete size={25} style={styles.deleteIcon} onClick={this.handleDelete}/>
                </div>
            </div>
        )
    }
}
QueryCard.propTypes = {
    active: React.PropTypes.bool,
    query: React.PropTypes.object,

    handleNameChange: React.PropTypes.func,
    handleDelete: React.PropTypes.func,
    handleSelect: React.PropTypes.func,
};

const styles = {
    mainContainer: {
        height: '50px',
        paddingLeft: '5px',
        paddingRight: '5px',
        alignItems: 'center',
        borderBottom: '1px solid #1175BA',
        background: 'rgba(11, 116, 186, 0.05)',
        transition: 'height 0.25s',
        overflow: 'hidden',

        ':hover': {
            height: '85px',
        }
    },
    bottomContainer: {
        flex: 1,
        display: 'flex',
        justifyContent: 'center',
    },
    editContainer: {
        height: '50px',
        display: 'flex',
        alignItems: 'center',
    },
    nameText: {
        lineHeight: '50px',
        whiteSpace: 'nowrap',
        overflow: 'hidden',
        textOverflow: 'ellipsis',
        textAlign: 'center',
    },
    editField: {
        flex: 1,
        display: 'block',
        height: '20px',
    },
    editIcon: {
        cursor: 'pointer',
        marginLeft: '5px',
        marginRight: '5px',
        color: '#68BDF6',
    },
    deleteIcon: {
        cursor: 'pointer',
        marginLeft: '5px',
        marginRight: '5px',
        color: 'hsl(0, 100%, 75%)',
    }
};

export default Radium(QueryCard);