# EVRYTHNG API Java Wrapper

This is a Java wrapper for the [EVRYTHNG API](http://dev.evrythng.net) v2.x. It lets you use all the features provided by our API with pure Java constructs. 
You need an API token for authentication which you can get on <http://evrythng.net/settings/tokens>.

## Working with the wrapper

The easiest way is to get the latest `.jar` from the [Downloads](evrythng-java-wrapper/downloads) section and adding it to your Java project.
Then, you can directly get started with something like:

    // Intialize the wrapper (and provinding the API key)
    ThngAPIWrapper wrapper = new ThngAPIWrapper(YOUR_EVRYTHNG_TOKEN_HERE);
            

    // Create a Thng
    Thng myThng = wrapper.createThng(new Thng("My-TV", "This is my TV.", true, 10.88, 50.56));
    
    [...]

See [the example file](src/main/java/net/evrythng/thng/api/examples/Examples.java) for a complete overview.

## Extending the wrapper

### Cloning the repository

	git clone git@github.com:evrythng/evrythng-java-wrapper.git


### Compiling the wrapper

    mvn install

You should now find a `.jar` file in the `target/` directory.

To build the `.jar` with all dependencies run:

    mvn assembly:single

### Using the wrapper in your project

To use the wrapper, just add the generated `.jar` file located in the `target/` directory to your Java project.

# Extending the wrapper with Netbeans 6.8+

Simply open the project by selecting its root folder. The Netbeans Maven plugin does the rest for you!

# Extending the wrapper with Eclipse

Simply import the project as a new Maven project (requires `m2eclipse` plugin, see <http://m2eclipse.sonatype.org/>).


# License

 Copyright 2012 EVRYTHNG Ltd London / Zurich

   The EVRYTHNG API wrapper is licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

