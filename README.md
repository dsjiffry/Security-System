# Security-System
An Access Control System which uses the Apache Felix Framework


# instructions

* Open the project via Eclipse 
* Run the "make jar" script to auto-build the jars.
* "make jar" will automatically start up felix. Once in felix install the jars and run them as shown below
![Instructions](https://github.com/dsjiffry/Security-System/raw/master/instructions.PNG)



# Manifest Details
  ## Autherntication Service
  ### Exported Packages:
  * authentication_service
  ### Imported Package:
  * org.osgi.framework
  * org.osgi.util.tracker
       
  ## Reporting Service
  ### Exported Packages:
  * reporting_service
  ### Imported Package:
  * org.osgi.framework
  * org.osgi.util.tracker
        
  ## Front Door
  ### Imported Package:
  * org.osgi.framework
  * org.osgi.util.tracker
  * authentication_service
  * reporting_service 
