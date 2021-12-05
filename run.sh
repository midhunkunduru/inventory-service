#!/bin/sh

if [ ! -z $1 ]; then
    SPRING_PROFILE=$1
else
    SPRING_PROFILE="local"
fi

if [ "$2" == "skip" ]; then
    SKIP_TESTS="-DskipTests"
else
    SKIP_TESTS=""
fi

clear
echo --------------------------------------------------------------------
echo Packaging 'Inventory' using $SPRING_PROFILE profile
echo --------------------------------------------------------------------
mvn clean package $SKIP_TESTS && java -Xms128m -Xmx256m -war  ./target/InventoryService-1.0.war


echo --------------------------------------------------------------------
echo Starting 'Inventory' 
echo --------------------------------------------------------------------

mvn spring-boot:run

