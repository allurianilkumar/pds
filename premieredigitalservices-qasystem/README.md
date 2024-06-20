# Selenium Test Suite for PDS Portal #

This repository contains test suite for PDS Portal. This document gives overview of the technologies and services used for development of the system

### Technologies and Services ###

* Selenium Web Driver 2.42.2
* Java 1.7
* Maven 3.2.3
* TestNG 6.1.1
* Eclipse Helios and greater
* Saucelabs infrastructure for executing the test cases on different browsers and platforms

### Installation ###

### How to setup the project ###

* Clone the source folder using ```git clone <repository>```
* Then cd to the root of the project folder and then from command line do ```mvn install```
* The project is built for Eclipse IDE. You can import this existing project into Eclipse
* The project is now ready for development

### Contribution guidelines ###

* Fork the repository and work on your fork for developing test cases
* Do not create branches in this fork
* Then create a pull request for the code
* On successful code review, the code will be merged into master

### Guidelines for writing test cases ###

* Test cases are documented in the test plan document in Confluence at this link (todo: link to be provided)
* For every test case, please include the test case number from the document at the beginning of the test method
* Test cases shall be executed on IE, Chrome, Firefox, Safari before committing and creating a PR

### Who do I talk to? ###

* This document is updated periodically. For more upto date information, please contact ```Alluri``` at ```kumarallurianil@gmail.com```

### Running on Bamboo ###
