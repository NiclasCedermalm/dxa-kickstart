DXA Kickstart Environment
===================================================

This is bundled kickstart environment for the Digital Experience Accelerator (DXA) for Java.
The kickstart environment is a bundled “all-in-one” environment containing the following: 

* Tomcat 8.x
* DXA.Java web application with all needed configuration
* OData Preview web service
* SDL Tridion Deployer service
* Hypersonic in-memory broker database 
 
Note: SDL does not support Hypersonic as content delivery database, so do not use this in a production setup. It should only be used for development and test purposes. 

## Install the DXA kickstart environment

To install DXA you need to do the following steps:

### Step 1: Prepare environment

Create a DXA directory somewhere on your computer. In there create a 'code'-directory. Clone this repository in your [dxa.home]/code directory:


    dxa/code> git clone https://github.com/NiclasCedermalm/dxa-kickstart
    
Copy the env.properties.sample to env.properties. Open the file in an editor and edit the following properties:

* 'dxa.home' - path to your created DXA directory
* 'dxa.licensePath' - path to your cd_license.xml file 

### Step 2: Install DXA code
    
Install all DXA source code + needed dependencies by doing the following in the dxa-kickstart directory:


    dxa/code/dxa-kickstart> mvn install -Pinstall-code 
 
### Step 3: Install DXA publications
    
Copy the 'dxa-dist\import\DXA Application Data Definition.xml' file and paste it to:  %TRIDION_HOME%\config\ImportExport\ApplicationData\. Run the following in PowerShell (in the import\-directory):


    dxa/dxa-dist/import>  .\import.ps1 -importType all-publications -cmsUrl http://[TRIDION_HOST]
    
Run the script again to install the DXA standard roles:


    dxa/dxa-dist/import> .\import.ps1 –importType rights-permissions –cmsUrl http://[TRIDION_HOST]

Verify so the DXA publications have been created in Tridion.


### Step 4: Install Tomcat

Modify the env.properties file and update the publication ID property (dxa.publicationId) to match the newly created publication '400 Example Site'. Run the following command to install a complete DXA.Java distribution including Tomcat and embedded Broker DB:


     dxa/code/dxa-kickstart> mvn install -Ptomcat 
      
Start the Tomcat server by go into the 'dxa-tomcat\bin' directory and start 'startup.bat' alt. 'startup.sh'. Wait for the server start up. Verify the installation by go the following URL in a browser: http://[DXA_HOST - for example localhost]:8080/cd_upload/httpupload 

### Step 5: Publish Example Site

Create a new publication target in Tridion & publish out the DXA example site using the following instructions: http://docs.sdl.com/LiveContent/content/en-US/SDL%20DXA-v2/GUID-267CDBBF-723A-4633-8C17-9F02EAE1EC96.
Use the following data when setting up the publication target: 

* HTTP destination: http://[DXA_HOST]:8080/cd_upload/httpupload 
* Session Preview - Content Delivery Endpoint URL:  http://[DXA_HOST]:8080/cd_preview_webservice/ws/odata.svc 
* Add 'http://[DXA_HOST]:8080' to Session Preview - Website URLs
    
Go to http://[DXA_HOST]:8080 to verify that your new DXA site is operational.

## Setup DXA development environment

To setup DXA development environment in an IDE such as Intellij IDEA or Eclipse you can do the following:

1. Import the dxa-web-application-java (optional) & dxa-kickstart maven projects into the IDE
2. Create a Tomcat configuration and point to the Tomcat instance in the 'dxa-tomcat'-directory 
3. Add the dxa-webapp to the Tomcat configuration
4. Run the server within the IDE


## To enable addon modules

The DXA demo environment contains configuration for the following modules:

* CID (T.B.D.)
* Example module

To enable the modules you use combinations of Maven profiles. The following profiles exists:

* cid - enable CID (Contextual Image Delivery)
* example - to enable the example module
* tomcat - to enable Tomcat environment generation

So for example if you want to make DXA Tomcat distribution containing the SmartTarget & the example module you can do the following:

    mvn package -Ptomcat -Pexample

# License

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.








