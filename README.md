# Security-System
An Access Control System which uses the Apache Felix Framework


# instructions

* Open the project via Eclipse (This is needed to generate the class files)
* Run the "make jar" script to auto-build the jars.
* "make jar" will automatically start up felix. Once in felix install the jars and run them as shown below
![Instructions](https://github.com/dsjiffry/Security-System/raw/master/instructions.PNG)



# Manifest Details
  ## Authentication Service
  ### Exported Packages:
  * authentication_service
  ### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
       
  ## Reporting Service
  ### Exported Packages:
  * reporting_service
  ### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
        
  ## Front Door
  ### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
  * authentication_service
  * reporting_service 
