# Troubleshooting

## Eclipse

* Character encoding issues on Windows

  * **Problem:** character encoding issues in Xtend source files.
  * **Solution:** go to **Window** | **Preferences** | **General** | **Workspace** and set **Text file encoding** | **UTF-8**

* Xtend classes do not compile
  * **Problem:** Xtend classes do not compile due to character encoding errors.
  * **Solution:** change the character encoding to **UTF-8** (preferably for the whole workspace, in **Window** | **Preferences** | **General** | **Workspace** | **Text file encoding**).
* Gradle error
  * **Problem:** during import, you may encounter the following error:

    ```
    Synchronize Gradle builds with workspace failed due to an unexpected error.
    Unsupported method: HierarchicalEclipseProject.getIdentifier().
    ```
  * **Solution:** click **Previous** and set the **Gradle wrapper**'s version manually to 3.0 (in our experience, 3.1 and 3.2 do not work).

* Xtext generation error

  * **Problem:** during the `generateXtext` task, you get the following error:

    ```
    java.lang.IllegalArgumentException: The 'no null' constraint is violated
    ```
  * **Solution:** [terminate the Gradle daemon](https://github.com/xtext/xtext-gradle-plugin/issues/58#issue-167052300), e.g. use `./gradlew --stop`. If the problem persists, you might want to [disable Gradle daemons](https://docs.gradle.org/current/userguide/gradle_daemon.html#sec:disabling_the_daemon).

  * **Problem:**

    ```
    ERROR:Ecore cannot be resolved. (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 1 column : 2)
    ERROR:GenModel cannot be resolved. (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 2 column : 2)
    ERROR:A generic type in this context must refer to a classifier or a type parameter (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 10 column : null)
    ERROR:A generic type in this context must refer to a classifier or a type parameter (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 17 column : null)
    ERROR:The default value literal 'false' must be a valid literal of the attribute's type (file:/.../ingraph-relalg-model/src/main/resources/relalg.xcore line : 149 column : null)
    ```

  * **Solution:** include the `clean` task, i.e. run `./gradlew clean build`

* Scala code does not compile

  * **Problem:** "... not built due to errors in dependent project(s): ..."

  * **Solution:** clean the workspace.

  * **Problem:** java.lang.RuntimeException: Underlying compilation unit is not a Scala Compilation unit. This is most probably caused by disabled JDT weaving. Run `Scala -> Run Setup Diagnostics` to enable it.

  * **Solution:** install all components from the Scala IDE's workspace and restart Eclipse.

* VIATRA project does not compile

  * **Problem:** Compile errors

  * **Solution:** Add VIATRA nature to the project: **right click** | **Configure** | **Convert to VIATRA Query Project**.

  * **Problem:** "Error executing EValidator"

  * **Solution:** Add plug-in nature to the project: **right click** | **Configure** | **Convert to Plug-in Projects...**.

* Cannot use Scala code from Xtend

  * **Problem:** The editor throws errors for when trying to use Scala code from Xtend – even if the code compiles when using Gradle from the command line. For an example, see this [repository](https://github.com/szarnyasg/scala-xtend-gradle).

  * **Solution:** There is no trivial workaround for this. Currently, we wrap the related Scala classes to a Java class and use the Java class instead.

### IntelliJ

* `NoClassDefFoundError` for Scala code:
  * **Problem:** The code compiles, but running the errors results in the following error:

    ```
    java.lang.NoClassDefFoundError: akka/testkit/ImplicitSender$class
    ```
  * **Solution:** update the Scala SDK in your project to 2.12.


### How to run the tests

Running Xtend JUnit tests from a Gradle project is tricky. If you encounter a `ClassNotFound` exception, you should navigate the cursor to the **name of the class**, not the name of a test method, and press <kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>X</kbd>, <kbd>T</kbd>.

### How to update the relational algebra model

Go to `ingraph-relalg-model` project, navigate to the `src/main/resources` directory and open the `relalg.xcore` file. The code is regenerated on every save operation. If there are errors in the generated code, it's worth deleting the `build/xcore/main` directory manually.

### How to update the grammars for parsing the Cucumber tests

Go to the `ingraph-report` project, navigate to the `src/main/java` source folder, and right click the `ingraph.report` package's `GenerateFeature.mwe2` file and choose **Run As** | **MWE2 Workflow** and ignore the warning message.

### Opening `cypxmi` models

If you want to investigate the generated Xtext models, generate the `cypxmi` files using the provided unit tests.

To open them, you have to import the [`org.slizaa.neo4j.opencypher`](https://github.com/slizaa/slizaa-opencypher-xtext/tree/master/plugins/org.slizaa.neo4j.opencypher) project from the [slizaa-opencypher-xtext](https://github.com/slizaa/slizaa-opencypher-xtext) repository. You should use the **Sample Reflective Ecore Model Editor** for opening these models (and make that editor the default for the `cypxmi` files).

## IntelliJ IDEA

In IntelliJ, go to **File** | **Settings** | **Plug-ins**, search for `Xtend`, click **Search in repositories** and install the plug-in.

### Gradle

To build the projects from command line, use the following command:

```bash
./gradlew clean build --continue
```

If you get `duplicate class` errors for the Xtend classes, you probably omitted the `clean` target from the Gradle build.
