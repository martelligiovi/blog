# Demo Application

 ![Build](https://img.shields.io/badge/build-passing-brightgreen.svg) ![Cobertura de Pruebas](https://img.shields.io/badge/tests-100%25-success.svg)

## Descripción

Este proyecto es una aplicación web desarrollada con Spring Boot y Java, fue creada para una materia de la facultad la cual tenia la consigna de crear una web app con 2 cruds con sus test y con su capa de vista. Proporciona una API REST para gestionar usuarios y gatos.

## Tabla de Contenidos
- [Requisitos previos](#Requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Características](#características)
- [Documentación de la API](#documentación-de-la-api)
- [Autores](#autores)

## Requisitos previos

Antes de poder ejecutar este proyecto, necesitarás tener instalado MySQL y configurar algunas variables de entorno.

### Configuración de la base de datos

Este proyecto utiliza MySQL como base de datos. Asegúrate de tener una instancia de MySQL en ejecución. Puedes descargar MySQL desde [aquí](https://dev.mysql.com/downloads/mysql/).

### Configuración de las variables de entorno

Este proyecto utiliza las siguientes variables de entorno para la configuración de la base de datos:

- `DB_HOST`: El host de la base de datos.
- `DB_PORT`: El puerto de la base de datos.
- `DB_NAME`: El nombre de la base de datos.
- `DB_USER`: El nombre de usuario para la base de datos.
- `DB_PASSWORD`: La contraseña para la base de datos.

Puedes configurar estas variables de entorno en tu sistema operativo o en tu IDE. En IntelliJ IDEA, puedes hacerlo en la configuración de la ejecución de la aplicación, en la sección de "Variables de entorno".

## Instalación

Instrucciones paso a paso sobre cómo instalar y configurar el proyecto.

```bash
# Clonar el repositorio
git clone https://github.com/martelligiovi/arquiWeb-master.git

# Entrar al directorio del proyecto
cd arquiWeb-master/demo

# Instalar dependencias con Maven
mvn install
```

## Uso

Para iniciar la aplicación, ejecute el siguiente comando en la raíz del proyecto:

```bash
mvn spring-boot:run
```

La aplicación se ejecutará en http://localhost:8080.

## Características

- Gestión de usuarios
- Gestión de gatos
- API REST

## Documentación de la API

La documentación de la API se encuentra en el archivo `/API_DOCUMENTATION.md`.

## Contribución

Si desea contribuir al proyecto, por favor haga un fork del repositorio y proponga un pull request.



## Autores

- martelligiovi

