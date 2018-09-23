cd $(git rev-parse --show-toplevel) && \
  rm -rf lib/ bin/ && \
  ./gradlew clean build installDist -x test && \
  cp -R tests/build/install/tests/. . && \
  cd bin
