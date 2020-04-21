# Security-System
An Access Control System which uses the Apache Felix Framework


# instructions

* Open the project via Eclipse (This is needed to generate the class files)
* Run the "make jar" script to auto-build the jars.
* "make jar" will automatically start up felix. Once in felix install the jars and run them as shown below
<img src="https://github.com/dsjiffry/Security-System/raw/master/Front%20Door%20Service.PNG" width="70%">
<img src="https://github.com/dsjiffry/Security-System/raw/master/Barcode%20Reader%20Service.PNG" width="70%">
<img src="https://github.com/dsjiffry/Security-System/raw/master/Elevator%20Service.PNG" width="70%">
<img src="https://github.com/dsjiffry/Security-System/raw/master/Doors%20And%20Lights%20Service.PNG" width="70%">

# Work Distribution
#### 1. IT18050318:	M. A. Zeid	->	Authentication Service
#### 2. IT18060690:	M. R. M. Rifan	->	Doors and Lights Service
#### 3. IT18200034:	M. A. F. Hasna	->	Elevator Service
#### 4. IT17029896:	D. S. Jiffry	->	Front Door service

#### 5. Common Effort -> Barcode Service, Reporting Service



# Manifest Details
 ## 1. Authentication Service
  #### Exported Packages:
  * authentication_service
  * auth_util
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
       
 ## 2. Reporting Service
  #### Exported Packages:
  * reporting_service
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
        
 ## 3. Front Door
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
  * authentication_service
  * auth_util
  * reporting_service 
  
 ## 4. Elevator Service
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
  * authentication_service
  * auth_util
  * reporting_service 

 ## 5. Barcode Reader Service
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
  * authentication_service
  * auth_util
  * reporting_service 
  
 ## 6. Doors and Lights Service
  #### Imported Packages:
  * org.osgi.framework
  * org.osgi.util.tracker
  * authentication_service
  * auth_util
  * reporting_service 
  
  
  
  
  
  
  
  
  
  
  
