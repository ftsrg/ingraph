const HtmlPlugin = require("html-webpack-plugin");

module.exports = {
    mode: process.env.NODE_ENV || "development",
    devtool: 'source-map',

    entry: __dirname + "/src/Index.jsx",

    output: {
        path: __dirname + "/dist",
        filename: "bundle.js"
    },

    module: {
        rules: [
            {
                test: /\.(jx|jsx)$/,
                loader: "babel-loader",
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                loader: [
                    'style-loader',
                    'css-loader',
                    'sass-loader'
                ],
            },
            {
                test: /\.mcss$/,
                use: [
                    'style-loader',
                    {
                        loader: 'css-loader',
                        options: {
                            modules: true
                        },
                    },
                    'sass-loader'
                ],
            }
        ]
    },
    plugins: [
        new HtmlPlugin({
            template: __dirname + '/res/index.html'
        })
    ],
    resolve: {
        extensions: ['.js', '.jsx'],
    }
};
