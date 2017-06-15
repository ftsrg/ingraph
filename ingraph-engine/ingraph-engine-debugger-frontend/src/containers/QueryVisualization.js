import React from 'react';
import {connect} from "react-redux";
import {AutoSizer, Table, Column, CellMeasurer, CellMeasurerCache} from 'react-virtualized';

import 'react-virtualized/styles.css';

const cache = new CellMeasurerCache({
    defaultWidth: 100,
    minWidth: 75,
    fixedHeight: true
});


const styles = {
    mainContainer: {
        flex: '1',
        backgroundColor: 'red',
    },
    header: {
        padding: 10,
    },
    row: {
        padding: 10,
    }
};

class QueryVisualization extends React.Component {

    renderHeader({columnData, dataKey, disableSort, label, sortBy, sortDirection}) {
        return (
            <p style={styles.header}>
                {dataKey}
            </p>
        )
    }

    renderCell({cellData, columnData, dataKey, isScrolling, rowData, rowIndex}) {
        return (
            <p style={styles.row}>
                {cellData}
            </p>
        )
    }

    computeRowStyle = ({index}) => {
        return index % 2 ? {backgroundColor: '#FFFFFF'} : {backgroundColor: '#F9F9F9'};
    };

    render() {
        if (this.props.activeQuery !== null && this.props.activeQuery.get('state') === 'PARSED') {
            console.log(this.props);

            return (
                <AutoSizer>
                    {({height, width}) => (
                        <Table
                            height={height}
                            width={width}
                            headerHeight={40}
                            rowHeight={40}
                            rowStyle={this.computeRowStyle}
                            rowCount={this.props.activeQuery.get('records').size}
                            rowGetter={i => this.props.activeQuery.getIn(['records', i.index])}
                        >
                            {this.props.activeQuery.get('columns').map(name =>
                                <Column
                                    key={name}
                                    dataKey={name}
                                    label={name}
                                    width={60}
                                    flexGrow={1}
                                    cellRenderer={this.renderCell}
                                    headerRenderer={this.renderHeader}
                                />
                            )}
                        </Table>
                    )}
                </AutoSizer>
            );
        }

        return <div></div>;
    }
}

function mapStateToProps(state) {
    return {
        activeQuery: state.getIn(['app', 'queries', state.getIn(['app', 'activeId'])], null),
    }
}
export default connect(mapStateToProps)(QueryVisualization);
