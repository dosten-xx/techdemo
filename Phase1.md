# Phase 1

Using the dukesbookstore application as a baseline, the following modifications were made to get it to work with WildFly and PostgreSQL:

* Moved the parent POM plugins and dependencies down to make the project standalone.
* Updated persistence.xml to use Hibernate.
* Added the dukesBookStoreDS datasource to the WildFly standalone.xml pointing to a PostgreSQL db

## Pre-requisites
* Maven 3.2.3 or higher
* Java JDK 1.7
* A git client configured so you can clone github repos.
* You don't need an IDE to run anything but it makes it nicer to look at the code and future phases will use Eclipse (specifically the JBoss Developer Studio version).

## Usage

The following instructions are Windows centric but should be easily replicatable on Linux.

1. Download [WildFly 8.2.1.Final](http://wildfly.org/downloads/) and unzip to some directory (referred to as %WILDFLY_HOME%).
1. Download [PostgreSQL 9.6.3](http://www.postgresql.org/download/) and install.
1. Open pgAdmin and create a new database called "dukesbookstore".  If you want, you can create a user just for this database or use the sa user.
1. Download the PostgreSQL 9.6.3 JDBC [driver](http://jdbc.postgresql.org/download.html).  Copy the jar to %WILDFLY_HOME%/standalone/deployments.  This makes the PostgreSQL JDBC driver available to the application.
1. Add the following datasource snippet to %WILDFLY_HOME%/standalone/configuration/standalone.xml to add the JNDI datasource.  Make sure to replace USERNAME and PASSWORD with your local db user and $JDBDJAR with the file name of the JDBD driver downloaded above.
```
<datasource jta="false" jndi-name="java:jboss/datasources/dukesBooksStoreDS" pool-name="dukesBookStoreDS" enabled="true" use-ccm="false">
	<connection-url>jdbc:postgresql://localhost:5432/dukesbookstore</connection-url>
	<driver-class>org.postgresql.Driver</driver-class>
	<driver>$JDBCJAR</driver>
	<security>
		<user-name>$USERNAME</user-name>
		<password>$PASSWORD</password>
	</security>
	<validation>
		<validate-on-match>false</validate-on-match>
		<background-validation>false</background-validation>
	</validation>
	<timeout>
		<set-tx-query-timeout>false</set-tx-query-timeout>
		<blocking-timeout-millis>0</blocking-timeout-millis>
		<idle-timeout-minutes>0</idle-timeout-minutes>
		<query-timeout>0</query-timeout>
		<use-try-lock>0</use-try-lock>
		<allocation-retry>0</allocation-retry>
		<allocation-retry-wait-millis>0</allocation-retry-wait-millis>
	</timeout>
	<statement>
		<share-prepared-statements>false</share-prepared-statements>
	</statement>
</datasource>
```
1. Clone the techdemo git repository (referred to as %TECHHOME%).   
1. Open a command prompt and cd to %TECHHOME%.  Run `mvn clean install -DskipTests`.  Unit Tests are skipped since they require wildfly running and are covered in Phase 1.5.
1. Copy target\dukes-bookstore.war to %WILDFLY_HOME%\standalone\deployments.  You can also use `mvn wildfly:deploy` to do a remote deployment to a running WildFly server.
1. Run `mvn flyway:clean flyway:migrate` to clean and configure the database.  Be sure to set the environment variables POSTGRES_USER and POSTGRES_PASSWORD.
1. cd to %WILDFLY_HOME%\bin.  Run `standalone.bat` to start WildFly.
1. Browse to [http://localhost:8080/dukes-bookstore](http://localhost:8080/dukes-bookstore).
