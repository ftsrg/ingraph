#!/usr/bin/env node

var fs = require('fs');

for (let i = 1; i <= 25; i++) {

    let file = `${("00" + i).slice(-2)}/parameters`
    //console.log(`Loading file ${file}`)
    let rawParams = fs.readFileSync(file, {
        encoding: 'UTF-8'
    })
    let params = JSON.parse(rawParams)
    let scalaParams = Object.keys(params).map(key => `"${key}" -> ${params[key]}`).join(',')

    console.log(
        `TestCase("bi", ${i}) -> Map(${scalaParams}),`
    )
}