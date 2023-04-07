FROM debian:stretch-slim

RUN apt-get update && \
	apt-get install -y zip unzip curl make && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /tmp/*


RUN curl -s "https://get.sdkman.io" | bash

RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
    yes | sdk install java && \
    yes | sdk install micronaut && \
    yes | sdk install gradle && \
    rm -rf $HOME/.sdkman/archives/* && \
    rm -rf $HOME/.sdkman/tmp/*"

ENV JAVA_HOME="/root/.sdkman/candidates/java/current" 
ENV PATH="$JAVA_HOME/bin:$PATH"

# FROM alpine:latest
# COPY --from=build /usr/lib/jvm /usr/lib/jvm
# ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk/
# RUN export JAVA_HOME

EXPOSE 8080

ADD ./src /app/src
ADD ./gradle /app/gradle
ADD ./.settings /app/.settings
ADD ./.gradle /app/.gradle
ADD ./.classpath /app
ADD ./.project /app
ADD ./build.gradle /app
ADD ./gradlew /app
ADD ./gradle.properties /app
ADD ./micronaut-cli.yml /app
ADD ./settings.gradle /app
ADD ./Makefile /app

WORKDIR /app

RUN ./gradlew build
