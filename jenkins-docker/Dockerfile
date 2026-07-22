FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y \
        git \
        docker.io \
        maven && \
    apt-get clean

USER jenkins