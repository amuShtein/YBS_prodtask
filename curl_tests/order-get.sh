#!/bin/bash

curl -X GET 'http://localhost:8080/orders/2' \
  | python -m json.tool

curl -X GET 'http://localhost:8080/orders/10' \
  | python -m json.tool
