# EVRYTHNG API Java Wrapper

This is a Java wrapper for the [EVRYTHNG API](http://api.evrythng.com). It lets you use all the features provided by our API with pure Java constructs. 
You need an API token for authentication which you can get on [http://dev.evrythng.com/].

IMPORTANT: On Dec. 14 2012. We will release a complete rewrite of the wrapper. 
Until then you can checkout the source but might experience some problems. Thanks for your understanding!

## Getting Started

### Setup
The easiest way is to get the latest `.jar` from the [Downloads](https://github.com/evrythng/evrythng-java-wrapper/downloads) section and adding it to your Java project.

The project is also available on maven:

    <dependency>
     <groupId>com.evrythng</groupId>
     <artifactId>evrythng-java-wrapper</artifactId>
     <version>1.0.0</version>
    </dependency>

from our repository:

    <repository>
      <id>evrythng-releases</id>
      <name>EVRYTHNG Releases</name>
      <url>https://repo.evrythng.com/public/releases</url>
    </repository>

### Hello world!

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
        ApiManager api = new ApiManager("YOUR_EVRYTHNG_API_KEY");
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

To get you started with Android and EVRYTHNG have a look at the [FreezeMe](https://github.com/webofthings/FreezeMe) sample app. It is a freezer management application using QR codes, NFC tags and the EVRYTHNG API.

Note that the examples won't work out of the box on Android unless you include [SLF4j for Android](http://www.slf4j.org/android/) in your project.
You can do that but we suggest to rather simply use the Hello-World example above (which does work) and use the native Android logging.

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

