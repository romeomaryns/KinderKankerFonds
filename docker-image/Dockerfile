### Dockerfile

FROM openjdk:8-jre-alpine

CMD mkdir /usr/kkf

COPY . /usr/kkf

RUN apk add --update ttf-dejavu fontconfig

CMD java -Dapp.home=/usr/kkf -jar /usr/kkf/kkf.jar
