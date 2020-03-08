
cd "Front Door/bin/front_door"

jar cfm frontDoor.jar ../../META-INF/MANIFEST.MF ../front_door/

mv frontDoor.jar ../../../felix-framework-4.0.3/



cd "../../../Authentication Service/bin/authentication_service"

jar cfm auth.jar ../../META-INF/MANIFEST.MF ../authentication_service/

mv auth.jar ../../../felix-framework-4.0.3/bundle/



cd "../../../Reporting/bin/reporting_service"

jar cfm reporting.jar ../../META-INF/MANIFEST.MF ../reporting_service/

mv reporting.jar ../../../felix-framework-4.0.3/bundle/



cd "../../../Elevator Service/bin/elevator_service"

jar cfm elevator.jar ../../META-INF/MANIFEST.MF ../elevator_service/

mv elevator.jar ../../../felix-framework-4.0.3/







cd "../../../felix-framework-4.0.3"
rm -r "felix-cache"

java -jar bin/felix.jar