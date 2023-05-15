#!/bin/bash

curl -v -X GET 'http://localhost:8080/couriers/meta-info/1?startDate=2022-04-23&endDate=2022-04-24' \
  | python -m json.tool
