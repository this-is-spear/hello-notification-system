#!/bin/bash

# k6 실행
k6 run --out influxdb=http://localhost:8086/k6 ./test.js
