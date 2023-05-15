#!/bin/bash

curl -X GET 'http://localhost:8080/couriers' | python -m json.tool

#curl -X GET 'http://localhost:8080/couriers?limit=5' \
#  | python -m json.tool
#
#
#curl -X GET 'http://localhost:8080/couriers?offset=6' \
#  | python -m json.tool
#
#
#curl -X GET 'http://localhost:8080/couriers?limit=7&offset=2' \
#  | python -m json.tool
#
#curl -X GET 'http://localhost:8080/couriers?limit=100&offset=7' \
#  | python -m json.tool
#
#
#curl -X GET 'http://localhost:8080/couriers?limit=0&offset=7' \
#  | python -m json.tool
#
#
#curl -X GET 'http://localhost:8080/couriers?limit=100&offset=100' \
#  | python -m json.tool