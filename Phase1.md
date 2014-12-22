# Phase 1

Using the dukesbookstore application as a baseline, the following modifications were made to get it to work with WildFly 8.2.0.Final and PostgreSQL:

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

1. Download [WildFly 8.2.0.Final](http://wildfly.org/downloads/) and unzip to some directory (referred to as %WILDFLY_HOME%).
1. Download [PostgreSQL 9.3](http://www.postgresql.org/download/) and install.
1. Open pgAdmin and create a new database called "dukesbookstore".  If you want, you can create a user just for this database or use the sa user.
4. Add the following datasource snippet to %WILDFLY_HOME%/standalone/configuration/standalone.xml to add the JNDI datasource.  Make sure to replace the username and password with your local db user.
```
<datasource jta="false" jndi-name="java:jboss/datasources/dukesBooksStoreDS" pool-name="dukesBookStoreDS" enabled="true" use-ccm="false">
	<connection-url>jdbc:postgresql://localhost:5432/dukesbookstore</connection-url>
	<driver-class>org.postgresql.Driver</driver-class>
	<driver>postgresql-9.3-1100.jdbc4.jar</driver>
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
5. Download the PostgreSQL 9.3 JDBC [driver](http://jdbc.postgresql.org/download.html).  Copy postgresql-9.3-1100.jdbc4.jar to %WILDFLY_HOME%/standalone/deployments.  This makes the PostgreSQL JDBC driver available to our application.
6. Clone the techdemo git repository (referred to as %TECHHOME%).
7. Open a command prompt and cd to %TECHHOME%.  Run `mvn clean install`.
8. Copy target\dukes-bookstore.war to %WILDFLY_HOME%\standalone\deployments.
9. cd to %WILDFLY_HOME%\bin.  Run `standalone.bat` to start WildFly.
10. Browse to [http://localhost:8080/dukes-bookstore](http://localhost:8080/dukes-bookstore).
