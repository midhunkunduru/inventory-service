1) URI
------------
http://localhost:7000/swagger-ui.html

2) Running the application
------------------------------------
A) Method #1
---------------
mvn spring-boot:run

B) Method #2
-----------------------
#Generate jar file with
mvn install -DskipTests=true

#Run
java -jar target/REST_InventoryService-1.0.jar


C) Method #3
------------------
Run the 'InventoryApplication.java' from the IDE.

D) Method #4
------------------
./run.sh dev skip

where:-
dev = profile to use
skip = skip tests


3) Maven archetype
--------------------------
Project created using the following archetype
mvn archetype:generate

8: remote -> am.ik.archetype:spring-boot-blank-archetype (Blank Project for Spring Boot)

