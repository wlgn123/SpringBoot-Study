#!/usr/bin/env bash

# 현재 stop.sh가 속해있는 경로를 찾는다.
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
# profile.sh import (펑션을 사용하기위해)
source ${ABSDIR}/profile.sh

IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT 에서 구동 중인 애플리케이션 PID 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않았습니다."
else
    echo "> kill -15 $IDLE_PID"
    kill -15 $IDLE_PID
    sleep 5
fi