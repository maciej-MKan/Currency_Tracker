FROM node:16

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN npm install -g @angular/cli

RUN npm install

RUN ng build

EXPOSE 4200

CMD ["ng", "serve", "--host", "0.0.0.0"]
