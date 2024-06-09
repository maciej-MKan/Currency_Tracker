FROM node:18-alpine

WORKDIR /usr/src/app

COPY . /usr/src/app

COPY package.json /usr/src/app

RUN npm install -g npm@latest

RUN npm install -g typescript

RUN npm install -g @angular/cli

RUN npm install --force

EXPOSE 4200

ENTRYPOINT ["ng", "serve", "--host", "0.0.0.0"]
