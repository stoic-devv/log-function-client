# Log Function Client

This repository serves as a client for an AWS lambda function that searches over application log files.
For more info about the lambda function, check out this repository: [stoic-devv/log-search-func](https://github.com/stoic-devv/log-search-func).

## Build and Run Configuration
1. `OpenJDK == 1.8`
2. `sbt >= 1.5.5`
3. Intellij and Intellij scala plugin (or your favorite editor for Scala)

Sbt commands for this project:
`> compile` and `> test` must run successfully with following output
```console
[info] Run completed in 1 second, 104 milliseconds.
[info] Total number of tests run: 2
[info] Suites: completed 2, aborted 0
[info] Tests: succeeded 2, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
```

> :warning: since there is only one method in this project test cases < 5

To run the project:
1. Create a fat jar: `> sbt assembly`
2. Run application using: <br/>

`java -jar {proj-dir}\target\scala-3.0.2\log-function-client-assembly-0.1.jar args0 args1`

where `args0` & `args1` are the time stamp and time interval within which we want to search for the log 
messages. They are of the form `HH:MM:SS.sss`. eg: `19:39:37.360 00:00:05.000`

## Functionality
This client application makes a REST GET call to the deployed lambda 
function. The URL for this lambda function is configured in `resources/application.conf`.
The response of the GET call is prited on the console.

## Youtube video
For more details on using this repo check out this [youtube video](https://youtu.be/yyXu3mo2VJo)