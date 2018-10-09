#!/bin/sh

docker run --name api-gateway-nginx --network host -p 8080:8080 -v /home/nikola/bookstore/nginx.conf:/etc/nginx/nginx.conf:ro -d nginx
