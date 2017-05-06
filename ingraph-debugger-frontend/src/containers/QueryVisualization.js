import React from 'react';
import {connect} from "react-redux";
import {AutoSizer, Table, Column, CellMeasurer, CellMeasurerCache} from 'react-virtualized';

import 'react-virtualized/styles.css'

const cache = new CellMeasurerCache({
    defaultWidth: 100,
    minWidth: 75,
    fixedHeight: true
});

class QueryVisualization extends React.Component {

    constructor(props) {
        super(props);
    }

    renderCell(cellData, columnData, dataKey, isScrolling, rowData, rowIndex) {
        return (
            <CellMeasurer
                cache={cache}
                columnIndex={0}
                parent={parent}
                rowIndex={rowIndex}
            >
                <div
                    style={{
                        height: 35,
                        whiteSpace: 'nowrap'
                    }}
                >
                    {cellData.cellData}
                </div>
            </CellMeasurer>
        )
    }

    render() {
        if (this.props.activeQuery !== null && this.props.activeQuery.get('state') === 'PARSED') {
            return (
                <AutoSizer>
                    {({height, width}) => (
                        <Table
                            height={height}
                            width={width}
                            headerHeight={50}
                            rowCount={this.props.activeQuery.get('data').size}
                            rowGetter={i => this.props.activeQuery.getIn(['data', i.index])}
                        >
                            {this.props.activeQuery.get('columns').map(name =>
                                <Column
                                    key={name}
                                    dataKey={name}
                                    label={<p>{name}</p>}
                                    cellRenderer={this.renderCell}
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

const styles = {
    mainContainer: {
        flex: '1',
        backgroundColor: 'red',
    }
};

function mapStateToProps(state) {
    return {
        activeQuery: state.getIn(['app', 'queries', state.getIn(['app', 'activeId'])], null),
    }
}

export default connect(mapStateToProps)(QueryVisualization);
