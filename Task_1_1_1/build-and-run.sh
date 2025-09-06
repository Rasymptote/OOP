#!/bin/bash

javac -d bin ./src/main/java/ru/nsu/babich/*.java

jar cfm heapsort.jar manifest.mf -C bin/ .

javadoc -d docs -sourcepath src/main/java -subpackages ru.nsu.babich

java -jar heapsort.jar
