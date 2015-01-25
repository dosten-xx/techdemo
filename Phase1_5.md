# Phase 1.5

This phase builds on phase 1 to add [Arquillian](http://arquillian.org) tests.

* Added a test class for the BookRequestBean EJB.
* Added other files needed for testing.
* Added a Maven profile for the testing.

## Pre-requisites
* Phase 1

## Usage

The following instructions are Windows centric but should be easily replicatable on Linux.

1. cd to %WILDFLY_HOME%\bin.  Run `standalone.bat` to start WildFly.
1. Run `mvn -Parq-wildfly-remote -DPOSTGRES_USER=<user> -DPOSTGRES_PASSWORD=<password> flyway:clean flyway:migrate clean test`.  This will clean and recreate the database, build and deploy a special test war then run the tests.
1. Look in target/surefire-reports to see the results.
