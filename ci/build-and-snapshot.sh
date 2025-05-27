#!/bin/bash

git clone https://github.com/HuaweiCloudDeveloper/hibernate-orm.git
cd hibernate-orm
git checkout -B 6.6.x origin/6.6.x
./docker_db.sh gaussdb
./gradlew clean build -Pdb=gaussdb -DdbHost=localhost:8000

cd ..
./mvnw -B deploy -P snapshot -s settings.xml
