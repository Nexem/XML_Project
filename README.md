Vincent 	DAIRIEN
Damien 		LEBRET
Baptiste 	PERREAUX

Here are a few short steps to get you started...


0. Prerequisite
===============

Requires :
- jre 1.8.0_171
- Apache Tomcat v8.0
- mysql-connector-java-5.1.23-bin.jar


1. Installing 
=============

- Under Eclipse Neon, import rest.first project
- Create server Tomcat 8.0 and bind it to the project in the build path configuration
- Resolve build path error by editing mysql-connector-java-5 path to mysql-connector-java-5.1.23-bin.jar


- Set up the database by importing cinema.sql file into phpMyAdmin
- Copy Cinema folder at Projet/Cinema/ under your www folder (WAMP) or htdocs (XAMPP)
- Go into /rest.first/src/rest/first/Database.java and edit your identifiers to connect to the database (lines 31 & 32) according to your phpMyAdmin identifiers.

- Run the tomcat server

- In your web browser, enter the following url : http://localhost:8080/rest.first/rest/
Copy the path displayed to your file browser. Copy the folder "pages" into this path.

Now, you're good to go


2. Starting
===========

Launch WAMP or XAMPP.

In your web browser, enter the following url :
http://localhost:8080/rest.first/rest/

Login : bapt
Passw : 1234

Enjoy !