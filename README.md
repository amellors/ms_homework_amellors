# ms_homework_amellors
Homework for Material Security

## Setup

### Dependancies:
* Java 8+ (Developed and tested with OpenJDK Runtime Environment Zulu18.32+13-CA (build 18.0.2.1+1))
* Maven 3.9+ (Ran and tested with Apache Maven 3.9.5)

### Building
Run maven package to get the .jar built and tested
```bash 
mvn package
```

### Running
Run the .jar that is built:
```bash 
java -jar target/fs_homework-0.2.jar
```

### Coverage Report
To run the coverage report:
```bash
mvn jacoco:prepare-agent test install jacoco:report
```

## Design thoughts and wishes

Ideas I wanted/did implement beyond the scope of the initial requirements:
- [ ] REPL like interface to interact with filesystem
- [X] Pushd & Popd
- [ ] Move & Copy
- [X] Exceptions instead of logs to console for issues
- * As I was going through this exercise I wasn't sure that exceptions were the right thing to do in most of these cases since not finding a file might be ok in some cases, but others it would be
- [X] Recursive Find

Notes:
* I need to learn more about how to Mock items in Java, having to use other parts of the system to build the mocks made some refactors a bit bigger than needed. (I'm looking at the Exceptions changes)
* Soo many things I want to do, I can easily talk though nearly all the extentions in the assignment, but also want to turn something in to be reviewed