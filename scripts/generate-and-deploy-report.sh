#/bin/bash

# you need to set the GH_TOKEN and GH_REF environment variables for the `deploy.sh` script to work

set -e

pushd .

# Note: tech report build is outdated, so we are commenting this for now, but keep gh-pages deploy in place
#./gradlew report --stacktrace --quiet
#cd opencypher-report
#make texfot
#cd ..
scripts/deploy.sh

popd
