# Deploying binary artifacts

## Using ingraph from other projects

To deploy the artifacts in your local Maven repository, issue `gradle publishToMavenLocal`.

## Deploying to Bintray

To upload the binary package of the driver to [Bintray](https://bintray.com/ftsrg/maven/ingraph), get your [Bintray API key](https://bintray.com/profile/edit/) and use the following commands:

```
# set your username (e.g. szarnyasg)
$ export BINTRAY_USER=
# set your Bintray API key (from https://bintray.com/profile/edit/, **API Key**)
$ export BINTRAY_KEY=
# upload driver
$ ./gradlew ingraph-driver:bintray
```

Go to the Bintray [Maven repository site](https://bintray.com/ftsrg/maven/ingraph-driver) and click **Publish**.
