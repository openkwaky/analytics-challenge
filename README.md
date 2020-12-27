# analytics-challenge

Introduction
------------

* This project contains a library that sends analytics tags to an http server and a demo app to test the library integration.
* It uses Room for database and Retrofit for http requests
* The demo app is configured to use Flipper for debug

Integration
-----------

* To configure the library, users need to call a builder and instanciate the Analytics class : 
  ```
     val analytics = Analytics(
            Configuration.Builder().withDelay(30).withUrl("http://localhost:3000").build(),
            this
        )
  ```
  NB : the delay is in seconds
* Then to send a tag, just call the sendTag method like :
  ```
     analytics.addTag(Tag(TagType.CLICK, "Test"))
  ```
* Then, the lib will automatically send all tags every "delay" seconds

Hpw to test
-----------

just run 
```
   ./gradlew app:installDebug
```

Click on the FAB button to send a basic tag (MainActivity.kt)

Possible improvements
---------------------

* The library is developed with the clean architecture in mind but by lack of time it needs some additional interfaces and POJOs to be fully compliant with the architecture and to respect the SOLID principles
* Dependency injection uses a very poor implementation to not disturb any application integration
* Unit tests are not ready yet
* It would be preferrable to send a list of tags to the http server, not one by one
* Upload on jcenter when ready

