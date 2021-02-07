#!/usr/bin/env bash

cd $(dirname $0) || exit 13

docker-compose up -d
