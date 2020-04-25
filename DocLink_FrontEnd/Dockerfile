FROM node:13

RUN mkdir /app

WORKDIR /app

COPY . .

RUN yarn global add @vue/cli

RUN yarn install

ENTRYPOINT [ "yarn", "build", "--watch" ]
