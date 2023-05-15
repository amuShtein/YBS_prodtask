#!/bin/bash

curl -X GET 'http://localhost:8080/couriers/2' \
  | python -m json.tool

curl -X GET 'http://localhost:8080/couriers/3' \
  | python -m json.tool
