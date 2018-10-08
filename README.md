
Framework
----------------
* Developed framework for web based applications testing.
* Developed Using Page Factory Model.

Technologies
----------------
* Developed by using SeleniumWD, Java, Maven and TestNG.
* Added Extent Reports.

Design Pattern of Framework
------------------------------
* Have three separate packages under src/test/java(com.pageclasse, com.testclasses and com.utilities)
* In com.pageclasses, having all the objects/elements information of required web pages to automate testcases.
* In com.testclasses, having assertions, execution order, reporting and data validations to automate testcases. 
* In com.utilities, having BaseClass and Config File utility classes.
* Added resources in Resouces Folder(like chrome, firefox and ie driver files).
* Report files will be generated in Reports Folder.
* Added Required dependencies in pom.xml.
* Added the testclasses in suitetestng.xml file to execute.

 
