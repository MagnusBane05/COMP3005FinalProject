# COMP3005 Final Project

You will need to edit application.properties in accordance to your own MySQL sever localhost connection.

To set up the database use DDL.sql and DML.sql

login and passwords:

  admin -> nimda

  customer -> pass


``***How to run it:***``
<br/>*Note: you must have local MySql server running<br/>

1- Run **DDL.sql** and **DML.sql** script to create the database.<br/>
2- Setup ``application.properties`` accordance to your own MySQL sever localhost connection.<br/>
2- Run ``mvn clean install`` to clean install a jar.<br/>
3- Open CMD if you're in windows and navigate to ``/target``<br/>
4- Run ``java -jar CMS-0.0.1-SNAPSHOT.jar``<br/>
5- Open your browser and go ``localhost:8080``
