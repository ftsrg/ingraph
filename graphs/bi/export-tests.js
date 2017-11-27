#!/usr/bin/env node

var fs = require('fs');

for (let i = 1; i <= 25; i++) {

    let rawParams = fs.readFileSync(`${("00" + i).slice(-2)}/parameters`, {
        encoding: 'UTF-8'
    })
    let params = JSON.parse(rawParams.substr(1))
}