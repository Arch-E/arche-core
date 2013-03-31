ArchE-core
==========

The core of ArchE

--To setup a developpement environment--

1 - Download and extract Eclipse Europa Winter. It is important that you use this specific version.
http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/europawinter

2 - Download the EMF 2.3.2 SDO SDK and extract its content at the parent of your eclipse folder. (e.g. Eclipse is in C:\eclipse, extract EMF at C:\).
http://www.eclipse.org/modeling/download.php?file=/modeling/emf/emf/downloads/drops/2.3.2/R200802051830/emf-sdo-SDK-2.3.2.zip

3 - Download and extract JESS 7.1. Extract each archive in its 'eclipse' folder into your eclipse folder.
Note that JESS requires a licence after the trial expires.
http://www.jessrules.com/jess/download.shtml

4 - Download and extract xmlBlaster 1.6.1. (e.g. C:\xmlBlaster)
http://www.xmlblaster.org/releases/xmlBlaster_REL_1_6_1.zip

5 - Download and install MySQL Server.
http://www.mysql.com/downloads/mysql/

6 - Setup the database.
Open mysql in a new command-line and login with the root.
Execute the arche_db.sql script like this : 
mysql> source C:/archesrc/arche-core/arche_db.sql. USE FORWARD SLASHES.
You can optionally create a user named "arche" and grant it all rights on the ArchE database:
mysql> CREATE USER 'arche'@'localhost' IDENTIFIED BY 'type_password_here';
mysql> GRANT ALL PRIVILEGES ON arche.* TO 'arche'@'localhost' WITH GRANT OPTION;

7 - Run Eclipse. 
Go to Help -> Software Updates -> Find and Install.
Select Search for new features to install.
Select'The Eclipse Project Updates' site.
In the features list, select Eclipse 3.3.2 and Eclipse 3.3.2 patches.
Restart Eclipse.

8 - Import the ArchE projects into your workspace. A dedicated workspace is recommended.

--To run the application--

9 - Build all (Ctrl + b)

10 - Make sure xmlBlaster is running by executing in a command-line:
cd path/to/xml/blaster
java -jar lib/xmlBlaster.jar
You can create a batch file that executes those two commands. 
Optionally, you can also start and stop mysql at the beginning and end
of the batch file. Your file would look like:
call net start mysql
c:
cd \xmlblaster
call java -jar lib/xmlBlaster.jar
call net stop mysql

11 - Right-click the arche.ui project. Run As -> Eclipse Application.

12 - A new Eclipse instance should have started. Go to Window -> Open Perspective -> Other. Choose ArchE.
Note that if you only have this project, you cannot do much until you attach a Reasoning Framework.
