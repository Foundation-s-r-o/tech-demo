# pull official base image
FROM node:16-alpine AS BUILD_IMAGE

ARG APP_API_SERVER_URL
ARG PLAYWRIGHT_SLOW_MO 1000
ARG ADMIN_USERNAME
ARG ADMIN_PASS

# set working directory
WORKDIR /app

# install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm ci --silent

# add app
COPY tsconfig.json ./
COPY webpack.config.js ./
COPY src src

ENV APP_API_SERVER_URL $APP_API_SERVER_URL
ENV PLAYWRIGHT_SLOW_MO $PLAYWRIGHT_SLOW_MO
ENV ADMIN_USERNAME $ADMIN_USERNAME
ENV ADMIN_PASS $ADMIN_PASS

# build
RUN npm run build

# pull official base image
FROM nginx:alpine

# copy from build image
COPY --from=BUILD_IMAGE /app/dist /usr/share/nginx/html/
