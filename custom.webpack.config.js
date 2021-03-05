const merge = require('webpack-merge');
const generated = require('./scalajs.webpack.config');
const path = require("path");
const CopyWebpackPlugin = require('copy-webpack-plugin');

const local = {
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.(ttf|eot|woff|png|glb|svg)$/,
                use: 'file-loader'
            },
            {
                test: /\.(eot)$/,
                use: 'url-loader'
            }
        ]
    },
    plugins: [
        new CopyWebpackPlugin({
            patterns: [
                {
                    from: path.resolve(__dirname, "../../../../src/main/js")
                }
            ]
        })
    ]
};

module.exports = merge(generated, local);
