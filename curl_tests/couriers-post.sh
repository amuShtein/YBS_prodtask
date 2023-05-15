#!/bin/bash

curl -v -X POST 'http://localhost:8080/couriers' \
    -H "Content-Type: application/json" \
    -d '{
        "couriers":[
          {"courier_type": "AUTO","regions": [1, 2], "working_hours": ["00:00-00:01", "00:00-00:01"]}
        ]}' \
    | python -m json.tool
