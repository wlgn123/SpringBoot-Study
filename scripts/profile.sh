#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real1이 사용중이면 real2가 쉬고있고, 반대면 real1이 쉬고있음.

function find_idle_profile()
{
    # 현재 엔진엑스가 바라보고 있는 스프링부트가 정상적으로 수행중인지 확인.
    # 응답값을 httpstatus올 받음.
    # 정상이면 200, 오류가 발생하면 400 ~ 503 사이로 발생하니 400이상은 모두 예외로 보고 real2를 현재 profile로 사용한다.
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] # 400보다 크면 (즉 에러가 발생한 경우)
    then
        CURRENT_PROFILE=real2
    else
        CURRENT_PROFILE=$(curl -s http://llocalhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ]
    then
        IDLE_PROFILE=real2  # 엔진엑스와 연결되지 않은 profile, 스프링 부트 프로젝트를 이 profile로 결하기위해 반환한다.
    else
        IDLE_PROFILE=real1
    fi

    # bash 스크립트에는 값을 반환하는 기능이 없기떄문에 echo로 결과를 출력해야한다.
    # 중간에 echo를 사용해선 안된다.
    echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == real1 ]
    then
        echo "8081"
    else
        echo "8082"
    fi
}