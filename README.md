# Evrythng API Java Wrapper

This is a Java wrapper for the Evrythng API. It lets you use all the features provided by our API with pure Java constructs. You need an API token for authentication which you can get on <http://evrythng.net/settings/tokens>.


## Setup

### Cloning the repository

	git clone git@github.com:evrythng/evrythng-java-wrapper.git

### Setting the API Token

You can set your API token on user level by adding the following profile to the your `settings.xml` file:

	<profile>
		<!-- 
			This development profile only activates when the -Dprod parameter is NOT present in command line.
		-->
		<id>evrythng-dev</id>
		<activation>
			<property>
				<name>!prod</name>
			</property>
		</activation>
		<properties>
			<evrythng.api.token>[your token]</evrythng.api.token>
		</properties>
	</profile>

### Compiling the wrapper

    mvn install
    
If you didn't provide an API token in `settings.xml` or just want to use another API key, launch the following command:
    
    mvn install -Devrythng.api.token=[your token]

If the tests fail (e.g., because you're offile or do not have a valid API key), run:

		mvn install -DskipTests

You should now find a '.jar' file in the 'target/' directory.

### Using the wrapper in your project

To use the wrapper, just add the generated '.jar' file located in the 'target/' directory to your Java project.


# Extending the wrapper with Netbeans 6.8+

Simply open the project by selecting its root folder. The Netbeans Maven plugin does the rest for you!

# Extending the wrapper with Eclipse

Simply import the project as a new Maven project (requires `m2eclipse` plugin, see <http://m2eclipse.sonatype.org/>).

# Running unit tests

## Using Maven

    mvn test

*or*

    mvn test -Devrythng.api.token=[your token]
   
## Using Eclipse
Alternatively, you can also launch unit tests directly from Eclipse IDE.

### Run with Maven (requires `m2eclipse` plugin, see <http://m2eclipse.sonatype.org/>):
* [Right-click on project] > Run As... > Maven test

If you didn't provide an API token in `settings.xml`, you can edit the launch configuration to pass your token:

* [Right-click on project] > Run Configurations... > Maven Build > New:
  * Base directory: *[browse to current project]*
  * Goals: `test`
  * Parameters:
     * evrythng.api.token=*[your token]*

### Run with JUnit
* Go to *Window > Preferences > JAVA > Installed JREs*
* Edit the JRE in use and add the following default VM argument:
  * evrythng.api.token=*[your token]*
* [Right-click on any test class or test method] > Run as JUnit Test

*or*

* Edit `src/test/resources/config.properties`:
  * evrythng.api.token=*[your token]*
* [Right-click on any test class or test method] > Run as JUnit Test

