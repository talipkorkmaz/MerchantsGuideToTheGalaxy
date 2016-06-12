
# Merchant's Guide To The Galaxy

The output of this program is a jar file.

You can generate jar file with mvn package command.

The build tool for dependency management and packaging is done with maven __3__.

Java version is __1.8__.

The jar file name is __"MerchantsGuideToTheGalaxy-1.0.jar"__.

This program needs one parameter which specifies the path of input file.
So you can execute program with the command below.
> jdk8/java.exe -jar MerchantsGuideToTheGalaxy-1.0.jar /home/talipk/input.txt

***************************************

Program have a static main in the Main class.

Main class calls a service called MainApplicationService which is the manager of processing input and generating output.

This project follow's the logic of service oriented architecture.

Also all services are implemented interfaces and theirs implementor classes.

By seperation of concern rules reading input, analyzing input , calculation characters and answering questions logics are coded in different services.

Naming conventions are specifically written careful for not writing extra comments in code.

Used design patterns are singleton, factory and builder.

src/test folder contains the test classes and resources.
Test classes can be executed via mvn test command.Maybe the coverage of unit tests are not full but I tried to write test classes before coding the business services with thinking of TDD/BDD. 


