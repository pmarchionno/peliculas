# stage 1: Build
# elejimos la imagen de node
FROM node:18 AS build

# establecemos el directorio de trabajo en nuestra imagen de docker
# a partir de ahora, la carpeta relativa será /usr/src/app
WORKDIR /usr/src/app

# copiamos las dependencias de package.json / lock y le decimos
# que las copie en este directorio (".")
COPY package.json ./
COPY package-lock.json ./

# Agrega el path al sistema
ENV PATH="./node_modules/.bin:$PATH"

# instalamos las dependencias
RUN npm install

# Instalamos Angular de forma global
RUN npm install -g @angular/cli

# copiamos todos los archivos del proyecto en el directorio actual de docker
# El primer punto indica “tomar todos los archivos y carpetas del directorio
# donde estoy parado”. El segundo punto indica que esos archivos, los copie
# en la actual carpeta relativa de trabajo 
COPY . .

# construimos la versión para producción
RUN npm run build --prod

RUN ls -alt

# Stage 2
FROM nginx:1.17.1-alpine

# copiamos los archivos de producción al directorio de trabajo
COPY --from=build /usr/src/app/dist/peliculas_front /usr/share/nginx/html
COPY --from=build /usr/src/app/nginx.conf /etc/nginx/default.conf

# exponemos el puerto de conexión y escucha de docker
EXPOSE 4200

# specify default command to run when we run the image
# CMD ["ng", "serve", "--host", "0.0.0.0"]
CMD /usr/src/app/node_modules/.bin/ng serve --host 0.0.0.0 --disableHostCheck
# Ejecutamos el proyecto, primero el comando ng, seguido de serve
RUN node_modules/.bin/ng serve
# ["ng", "serve"]
# CMD /usr/src/app/ng serve --host 0.0.0.0 --disableHostCheck
# CMD ["ng", "serve", "--host", "0.0.0.0"]