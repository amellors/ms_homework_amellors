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
java --jar target/fs_homework-0.2.jar
```

## Design thoughts and wishes

Ideas I wanted/did implement beyond the scope of the initial requirements:
- [ ] REPL like interface to interact with filesystem
- [ ] Pushd & Popd
- [ ] Move & Copy