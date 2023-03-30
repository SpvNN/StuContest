FROM adoptopenjdk/openjdk13-openj9:jdk-13.0.2_8_openj9-0.18.0-alpine-slim

EXPOSE 80
RUN apt-get update #&& \
    # apt-get install -y cmake cppcheck graphviz doxygen clang-format libqt5svg5-dev qtdeclarative5-dev qtmultimedia5-dev

# ADD ./src /app/src
# ADD ./tests /app/tests
# ADD CMakeLists.txt /app

VOLUME ["/app"]
WORKDIR /app

USER app

# RUN cmake .
# RUN make


# Установим точку входа
# ENTRYPOINT ["./hello_world_app"]
# ENTRYPOINT ["./tests/tests"]
