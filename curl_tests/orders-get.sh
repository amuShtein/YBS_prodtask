#!/bin/bash

curl -X GET 'http://localhost:8080/orders' \
  | python -m json.tool

curl -X GET 'http://localhost:8080/orders?limit=30' \
  | python -m json.tool


curl -X GET 'http://localhost:8080/orders?offset=20' \
  | python -m json.tool


curl -X GET 'http://localhost:8080/orders?limit=10&offset=20' \
  | python -m json.tool