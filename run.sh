#!/bin/sh
./gradlew packageJvmFatJar
java -jar "build/libs/Grizznos Adventure-all-4.10.1.jar"