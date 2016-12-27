#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../"
find . -type d -name build | xargs rm -rf
find . -type d -name xtend-gen | xargs rm -rf
