# Security-System
An Access Control System which uses the Apache Felix Framework


# instructions

* Open the project via Eclipse (This is needed to generate the class files)
* Run the "make jar" script to auto-build the jars.
* "make jar" will automatically start up felix. Once in felix install the jars and run them as shown below
![Instructions](https://github.com/dsjiffry/Security-System/raw/master/instructions.PNG)



# Manifest Details
  1. Authentication Service
  #### Exported Packages:
  * authentication_service
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
       
  2. Reporting Service
  #### Exported Packages:
  * reporting_service
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
        
  3. Front Door
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
  * authentication_service
  * reporting_service 
  
  4. Elevator Service
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
  * authentication_service
  * reporting_service 
