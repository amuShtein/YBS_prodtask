#!/bin/bash

curl -X POST -v 'http://localhost:8080/orders/complete' \
    -H "Content-Type: application/json" \
    -d '{
          "complete_info": [
            {
              "courier_id": 34,
              "order_id": 2,
              "complete_time": "2023-04-26T14:03:53.471Z"
            }
          ]
        }' \
    | python -m json.tool
