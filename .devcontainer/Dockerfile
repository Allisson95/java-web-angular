# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.234.0/containers/java/.devcontainer/base.Dockerfile

# [Choice] Java version (use -bullseye variants on local arm64/Apple Silicon): 11, 17, 11-bullseye, 17-bullseye, 11-buster, 17-buster
ARG VARIANT="17-bullseye"
FROM mcr.microsoft.com/vscode/devcontainers/java:0-${VARIANT}

# [Option] Install Maven
ARG INSTALL_MAVEN="false"
ARG MAVEN_VERSION=""
# [Option] Install Gradle
ARG INSTALL_GRADLE="false"
ARG GRADLE_VERSION=""
RUN if [ "${INSTALL_MAVEN}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""; fi \
    && if [ "${INSTALL_GRADLE}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install gradle \"${GRADLE_VERSION}\""; fi

# [Choice] Node.js version: none, lts/*, 16, 14, 12, 10
ARG NODE_VERSION="none"
RUN if [ "${NODE_VERSION}" != "none" ]; then su vscode -c "umask 0002 && . /usr/local/share/nvm/nvm.sh && nvm install ${NODE_VERSION} 2>&1"; fi

# [Optional] Uncomment this section to install additional OS packages.
# RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
#     && apt-get -y install --no-install-recommends <your-package-list-here>

# [Optional] Uncomment this line to install global node packages.
# RUN su vscode -c "source /usr/local/share/nvm/nvm.sh && npm install -g <your-package-here>" 2>&1

# Install global node packages.
RUN su vscode -c "source /usr/local/share/nvm/nvm.sh && npm install --location=global @angular/cli@14" 2>&1

# Set up Angular CLI autocompletion for your terminal
RUN su vscode -c "ng completion" 2>&1

ARG WILDFLY_VERSION=26.1.1.Final
ARG WILDFLY_PREVIEW=false

RUN \
    SERVER_HOME=/usr/lib/servers \
    && \
    case ${WILDFLY_PREVIEW} in \
        "true") \
            DOWNLOAD_URL=https://github.com/wildfly/wildfly/releases/download/${WILDFLY_VERSION}/wildfly-preview-${WILDFLY_VERSION}.tar.gz; \
            ;; \
        "false") \
            DOWNLOAD_URL=https://github.com/wildfly/wildfly/releases/download/${WILDFLY_VERSION}/wildfly-${WILDFLY_VERSION}.tar.gz; \
            ;; \
    esac \
    \
    && cd /tmp \
    && wget --show-progress -O wildfly.tar.gz "${DOWNLOAD_URL}" \
    \
    && mkdir -p ${SERVER_HOME} \
    \
    && tar xvfz /tmp/wildfly.tar.gz --directory ${SERVER_HOME} \
    && rm -f wildfly.tar.gz \
    && chown -R vscode:vscode ${SERVER_HOME}/*
