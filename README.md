# Project Scope

This project is to demonstrate various technologies for JEE applications.  The goal is to provide an exemplar app that others can use to learn from and contribute to.  The starting point is the Duke's Bookstore case study application from the Glassfish JEE tutorial.  That app will be modified to demonstrate new stuff.

# Roadmap

1. Modify app to work with WildFly.
  1. Use PostgreSQL RDBMS.
2. Provide Docker deployments.
  1. Single container for application and database.
  2. Separate containers.
3. Integrate OWASP ESAPI for security.

# Initial Modifications

Using the dukesbookstore application from the glassfish JEE tutorials, the following modifications where made to get it to work with WildFly 8.2.0.Final:

* Moved the parent POM plugins and dependencies down to make the project standalone.
* Updated persistence.xml to use Hibernate.
* Added the dukesBookStoreDS datasource to the WildFly standalone.xml
