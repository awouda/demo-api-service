FROM anapsix/alpine-java
ENTRYPOINT ["bin/demo-api-service"]

USER root

WORKDIR /home/docker/app
ADD target/universal/demo-api-service-*.tgz /home/docker/app/


EXPOSE 9010
ENV SERVICE_PORT=9010
ENV SERVICE_9000_TAGS="app,http,private"
ENV SERVICE_9000_CHECK_HTTP=/status
ENV SERVICE_9000_CHECK_INTERVAL=15s
ENV SERVICE_9000_CHECK_TIMEOUT=1s