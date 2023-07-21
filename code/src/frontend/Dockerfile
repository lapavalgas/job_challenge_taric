FROM node:18-alpine3.15

WORKDIR /frontend

ENV PATH /app/node_modules/.bin:$PATH

COPY ./package.json ./

RUN npm install -g npm

RUN npm install 

COPY ./ ./

EXPOSE 8080
EXPOSE 4173

CMD ["npm", "run", "dev"]
# CMD ["npm", "run", "preview"]