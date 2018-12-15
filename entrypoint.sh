#!/bin/sh -x
set -e

if [ -z "$SERVER_PORT" ]; then
  SERVER_PORT=8080
fi

if [ -z "$SPRING_PROFILES_ACTIVE" ]; then
  SPRING_PROFILES_ACTIVE=prod
fi


exec java ${JAVA_OPTS} -jar \
 -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} \
 -Dserver.port=${SERVER_PORT} \
 -Dspring.datasource.url=${SPRING_DATASOURCE_JDBC_URL} \
 -Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME} \
 -Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD} \
 pgslowpoke.jar