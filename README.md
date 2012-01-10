# Evrythng API Java wrapper

This is a Java wrapper for the Evrythng API. You will need an API token for authentication which you can obtain under <http://evrythng.net/settings/tokens>.

> Do not consider it as stable, that's just proof of concept.

## Setting your API token

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

## Installation

    mvn install
    
If you didn't provide an API token in `settings.xml` or just want to use another API key, launch the following command:
    
    mvn install -Devrythng.api.token=[your token]

## Running unit tests

### Using Maven

    mvn test

*or*

    mvn test -Devrythng.api.token=[your token]
    
### Using Eclipse
Alternatively, you can also launch unit tests directly from Eclipse IDE.

#### Run with Maven (requires `m2eclipse` plugin, see <http://m2eclipse.sonatype.org/>):
* [Right-click on project] > Run As... > Maven test

If you didn't provide an API token in `settings.xml`, you can edit the launch configuration to pass your token:

* [Right-click on project] > Run Configurations... > Maven Build > New:
  * Base directory: *[browse to current project]*
  * Goals: `test`
  * Parameters:
     * evrythng.api.token=*[your token]*

#### Run with JUnit
* Go to *Window > Preferences > JAVA > Installed JREs*
* Edit the JRE in use and add the following default VM argument:
  * evrythng.api.token=*[your token]*
* [Right-click on any test class or test method] > Run as JUnit Test

*or*

* Edit `src/test/resources/config.properties`:
  * evrythng.api.token=*[your token]*
* [Right-click on any test class or test method] > Run as JUnit Test