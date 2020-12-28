# analytics-challenge

Introduction
------------

* This project contains a library that sends analytics tags to an http server and a demo app to test the library integration.
* It uses Room for database, Retrofit for http requests and Dagger2 for dependency injection
* The demo app is configured to use Flipper for debug

Integration
-----------

* To configure the library, users need to call a builder and instanciate the Analytics class : 
  ```kotlin
     val analytics = Analytics(
            Configuration.Builder().withDelay(30).withUrl("http://localhost:3000").build(),
            this
        )
  ```
  NB : the delay is in seconds
* Then to send a tag, just call the **addTag** method like :
  ```kotlin
     analytics.addTag(Tag(TagType.CLICK, "Test"))
  ```
* Then, the lib will automatically send all tags every **delay** seconds

How to test
-----------

just run 
```shell script
   ./gradlew app:installDebug
```

Click on the FAB button to send a basic tag (MainActivity.kt)

Possible improvements
---------------------

* The library is developed with the clean architecture in mind but by lack of time it needs some additional interfaces and POJOs to be fully compliant with the architecture and to respect the SOLID principles
* Unit tests are not ready yet
* It would be preferable to send a list of tags to the http server, not one by one
* Upload on Jcenter when ready

