import React from 'react';

import EditorHeader from './containers/EditorHeader';
import QuerySidebar from './containers/QuerySidebar';
import QueryVisualization from './containers/QueryVisualization';

class App extends React.Component {
    render() {
        return (
            <div style={styles.mainContainer}>
                <EditorHeader/>
                <div style={styles.contentContainer}>
                    <QuerySidebar />
                    <QueryVisualization />
                </div>
            </div>
        );
    }
}

const styles = {
    mainContainer: {
        display: 'flex',
        flexFlow: 'column',
        height: '100vh',
        margin: 0,
        padding: 0,
    },
    contentContainer: {
        display: 'flex',
        flex: 1,
    },
};

export default App;