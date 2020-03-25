#!/usr/bin/env bash

# 현재 stop.sh가 속해있는 경로를 찾는다.
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
# profile.sh import (펑션을 사용하기위해)
source ${ABSDIR}/profile.sh

function switch_proxy()
{
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할Port: $IDLE_PORT"
    echo "> Port 전환"
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

    echo "> 엔진엑스 Reload"
    sudo service nginx reload
}