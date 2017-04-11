#/bin/bash

# you need to set the GH_TOKEN and GH_REF environment variables for the `deploy.sh` script to work

set -e

pushd .

./gradlew report --stacktrace --quiet
cd opencypher-report
make
cd ..
scripts/deploy.sh

popd
