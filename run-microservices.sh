#!/bin/bash

cd discovery-service && mvn clean install spring-boot:run
cd twitter-service && mvn cean install spring-boot:run
cd weather-service && mvn clean install spring-boot:run
cd sample-nodejs && node index.js
cd random-letter-sidecar && mvn clean install spring-boot:run
cd gateway-service && mvn clean install spring-boot:run