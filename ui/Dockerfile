# pull official base image
FROM node:16-alpine AS BUILD_IMAGE

# set working directory
WORKDIR /app

# install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm ci --silent

# add app
COPY tsconfig.json ./
COPY webpack.config.js ./
COPY .env ./
COPY src src

# build
RUN npm run build

# pull official base image
FROM nginx:alpine

# copy from build image
COPY --from=BUILD_IMAGE /app/dist /usr/share/nginx/html/
