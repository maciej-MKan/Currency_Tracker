FROM node:18

WORKDIR /usr/src/app

COPY . /usr/src/app

COPY package.json /usr/src/app

RUN npm install -g npm@latest

RUN npm install -g typescript

RUN npm install -g @angular/cli

RUN npm install --force

EXPOSE 4200

RUN ["chmod", "+x", "./config_and_run.sh"]

ENTRYPOINT ["/bin/sh", "-c", "./config_and_run.sh"]
