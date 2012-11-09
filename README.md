# EVRYTHNG API Java Wrapper

This is a Java wrapper for the [EVRYTHNG API](http://api.evrythng.com). It lets you use all the features provided by our API with pure Java constructs. 
You need an API token for authentication which you can get on [http://dev.evrythng.com/].

## Working with the wrapper for Java projects

The easiest way is to get the latest `.jar` from the [Downloads](https://github.com/evrythng/evrythng-java-wrapper/downloads) section and adding it to your Java project.

Then you should look at the [examples](https://github.com/evrythng/evrythng-java-wrapper/tree/master/src/main/java/com/evrythng/java/wrapper/examples) or, if you are impatient, get started with the snippet below:

    import com.evrythng.java.wrapper.ApiConfiguration;
    import com.evrythng.java.wrapper.ApiManager;
    import com.evrythng.java.wrapper.exception.EvrythngClientException;
    import com.evrythng.java.wrapper.exception.EvrythngException;
    import com.evrythng.java.wrapper.service.ThngService;
    import com.evrythng.thng.resource.model.store.Thng;
    import java.util.ArrayList;
    import java.util.List;

    public class EVRYTHNGWrapperTests {

    public static void main(String[] args) throws EvrythngClientException, EvrythngException {
    ApiManager api = new ApiManager(new ApiConfiguration("YOUR_EVRYTHNG_API_KEY"));
    ThngService thngService = api.thngService();

        // Create a thng
        Thng myThng = new Thng();
        myThng.setName("My hiking watch");
        myThng.setDescription("This is the watch I use for hiking.");
        myThng.addCustomFields("color", "blue");
        // do create!
        myThng = thngService.thngCreator(myThng).execute();
    
        // Add a temporal property
        List<Property> props = new ArrayList<Property>();
        props.add(new Property("temperature", "22"));
        props.add(new Property("altitude", "4500"));
        // do add!
        props = thngService.propertiesCreator(myThng.getId(), props).execute();
    
        // And get the thng + properties back
        myThng = thngService.thngReader(myThng.getId()).execute();
        System.out.println(myThng.getProperties());
      }
    }

See [examples](https://github.com/evrythng/evrythng-java-wrapper/tree/master/src/main/java/com/evrythng/java/wrapper/examples) for a complete overview of the functionality.

## Working with the wrapper for Android projects

You can also use the wrapper for your Android applications as it compiles on the Dalvik virtual machine! To get started get the latest `.jar` from the [Downloads](https://github.com/evrythng/evrythng-java-wrapper/downloads) section and adding it to your Android project's libraries. Make sure that the wrapper library is exported to your final Android application.

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

# Extending the wrapper with Eclipse

Simply import the project as a new Maven project (requires `m2eclipse` plugin, see <http://m2eclipse.sonatype.org/>).

# Extending the wrapper with Netbeans

Simply open the project by selecting its root folder. The Netbeans Maven plugin does the rest for you!


# License

 Copyright EVRYTHNG Ltd London / Zurich

   The EVRYTHNG API wrapper is licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

