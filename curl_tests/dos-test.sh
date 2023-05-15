#!/bin/bash

for i in {1..13}
do
  echo $i
  curl -X GET 'http://localhost:8080/couriers'
  echo ""
done
