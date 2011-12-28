# Evrythng API Java wrapper

This is a Java wrapper for the Evrythng API. You will need an API token for authentication which you can obtain under <http://evrythng.net/settings/tokens>.

> Do not consider it as stable, that's just proof of concept.

## Installation

    mvn install -Devrythng.api.token=[your token]
    
*or*
    
    mvn install -DskipTests=true

## Running unit tests

### Using Maven

    mvn test -Devrythng.api.token=[your token]
    
### Using Eclipse
Alternatively, you can also launch unit tests directly from Eclipse IDE.

#### Run with Maven (requires `m2eclipse` plugin, see <http://m2eclipse.sonatype.org/>):
* [Right-click on project] > Run Configurations... > Maven Build > New:
  * Base directory: *[browse to current project]*
  * Goals: `test`
  * Parameters:
     * evrythng.api.token=*[your token]*

#### Run with JUnit
* Edit `src/test/resources/config.properties`:
  * evrythng.api.token=*[your token]*
* [Right-click on any test class or test method] > Run as JUnit Test