ArchE-core
==========

To build
--------
Pre-requisites:
* [Eclipse Europa Winter](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/europawinter)
* [EMF 2.3.2 SDO SDK](http://www.eclipse.org/modeling/download.php?file=/modeling/emf/emf/downloads/drops/2.3.2/R200802051830/emf-sdo-SDK-2.3.2.zip)
* [JESS 7.1](http://www.jessrules.com/jess/download.shtml)
* [xmlBlaster 1.6.1] (http://www.xmlblaster.org/releases/xmlBlaster_REL_1_6_1.zip)
* [MySQL Server 5.0](http://www.mysql.com/downloads/mysql/5.0.html)


Steps:

First clone a copy of the arche-core repo.
```
git clone https://github.com/Arch-E/arche-core.git
```
Extract the Eclipse zip file on your computer (e.g. C:\eclipse). You will have a new folder named eclipse.

Extract the EMF zip file at the same place you extracted eclipse (e.g. C:\eclipse). It should add the plugin files needed.

Extract JESS on your computer (e.g. C:\Jess71p2) and extract the content of each archive file inside the eclipse folder of JESS into your Eclipse folder.

Extract xmlBlaster on your computer. (e.g. C:\xmlBlaster)

Install MySQL Server 5.0.

Login with root on your MySQL server and run the database creation script:

```
mysql> source C:/arche-core/arche_db.sql
```

Open Eclipse and Create an empty workspace.

Install Eclipse update:
* Go to Help > Software Updates > Find and Install.
* Select Search for new features to install.
* Select 'The Eclipse Project Updates' site.
* In the features list, select Eclipse 3.3.2 and Eclipse 3.3.2 patches.
* Restart Eclipse.

Import the content of your local repo into your eclipse workspace. Do not copy the projects in your workspace. Build All (Ctrl + B).

Make sure xmlBlaster is running by executing in a command-line:

```
cd path/to/xmlBlaster
java –jar lib/xmlBlaster.jar
```

Right-click the arche.ui project. Run As > Eclipse Application.

A new Eclipse instance should have started. Go to Window > Open Perspective > Other. Choose ArchE.

Please note that the arche-core doesn’t do much. You need to get at least one reasoning framework.
