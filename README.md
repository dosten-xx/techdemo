# Project Scope

This project is to demonstrate various technologies for JEE applications.  The goal is to provide an exemplar app that others can use to learn from and contribute to.  The starting point is the Duke's Bookstore case study application from the Glassfish JEE tutorial.  That app will be modified to demonstrate new stuff.
Since this is based on Oracle's code, the original license still applies.  See license.txt.

# Roadmap

[Phase 1](Phase1.md) Modify app to work with WildFly.
  1. Use PostgreSQL RDBMS.
  2. Use Flyway for database management.
2. Provide Docker deployments.
  1. Single container for application and database.
  2. Separate containers.
3. Code quality improvements using FindBugs, Checkstyle and other tools.
4. Integrate OWASP ESAPI for security.
5. Use on OpenShift PaaS.
  1. Utilize CloudForge and Jenkins.
