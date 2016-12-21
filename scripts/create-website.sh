#!/bin/bash

# clear and re-create the out directory
rm -rf out || exit 0
mkdir out

# run our compile script, discussed above
cp -r site/* out/ || true
