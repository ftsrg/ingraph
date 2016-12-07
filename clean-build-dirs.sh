#!/bin/bash

find . -type d -name build | xargs rm -rf 
find . -type d -name xtend-gen | xargs rm -rf
