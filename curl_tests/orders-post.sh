#!/bin/bash

curl -X POST -v 'http://localhost:8080/orders' \
    -H "Content-Type: application/json" \
    -d '{
        "orders": [
          {
            "weight": 1039.423,
            "regions": 4,
            "delivery_hours": [
              "10:00-12:20",
              "14:00-15:20",
              "17:00-20:20"
            ],
            "cost": 5
          }
        ]}' \
    | python -m json.tool
