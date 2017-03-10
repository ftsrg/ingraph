import React from 'react';
import {AutoSizer, Table, Column} from 'react-virtualized';
import 'react-virtualized/styles.css'

class QueryVisualization extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <AutoSizer>
                {({ height, width }) => (
                    <Table
                        headerHeight={30}
                        height={height}
                        rowHeight={50}
                        width={width}
                    >
                        <Column
                            label='Index'
                            width={60}
                        />
                        <Column
                            label='Index'
                            width={60}
                        />
                    </Table>
                )}
            </AutoSizer>
        );
    }
}

const styles = {
    mainContainer: {
        flex: '1',
        backgroundColor: 'red',
    }
};

export default QueryVisualization;
