# Making the jars and moving them to the felix environment for easy discovery.

echo "Building the jars"

cd "Front Door/bin/front_door"

jar cfm frontDoor.jar ../../META-INF/MANIFEST.MF ../front_door/

mv frontDoor.jar ../../../felix-framework-4.0.3/


cd "../../../Barcode Reader/bin/barcode_reader"

jar cfm barcodeReader.jar ../../META-INF/MANIFEST.MF ../barcode_reader/

mv barcodeReader.jar ../../../felix-framework-4.0.3/


cd "../../../Authentication Service/bin/authentication_service"

jar cfm auth.jar ../../META-INF/MANIFEST.MF ../authentication_service/ ../auth_util/

mv auth.jar ../../../felix-framework-4.0.3/bundle/



cd "../../../Reporting/bin/reporting_service"

jar cfm reporting.jar ../../META-INF/MANIFEST.MF ../reporting_service/

mv reporting.jar ../../../felix-framework-4.0.3/bundle/



cd "../../../Elevator Service/bin/elevator_service"

jar cfm elevator.jar ../../META-INF/MANIFEST.MF ../elevator_service/

mv elevator.jar ../../../felix-framework-4.0.3/



cd "../../../Doors And Lights/bin/doors_and_lights"

jar cfm doors.jar ../../META-INF/MANIFEST.MF ../doors_and_lights/

mv doors.jar ../../../felix-framework-4.0.3/




cd "../../../felix-framework-4.0.3"
rm -r "felix-cache"





# Running the felix framework
echo "Starting felix"

java -jar bin/felix.jar









