#!/bin/bash

git clone https://github.com/hibernate/hibernate-orm.git
cd hibernate-orm
./docker_db.sh gaussdb
./gradlew clean test -Pdb=gaussdb -DdbHost=localhost:8000

