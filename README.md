## Sistema Inventarios
Api para el manejo de Inventario en los que se incluye creacion, edicion y calculo de depreciacion para activos.
### Tecnologia Utilizadas
- Java Development Kit (JDK) 19
- Maven
- H2 Database
- SpringBoot 3
- Swagger

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalado lo siguiente:
- Java Development Kit (JDK) 19
- Maven


## Despliegue Local
### Configuración de la Base de Datos para conexion

1. Archivo de configuración `application.yaml` ubicado en la carpeta `src/main/resources`.
2. La URL de conexión de la base de datosH2: jdbc:h2:mem:testdb User: sa Password:
3. Para conectar a la base de datos en browser: Una vez el proyecto este corriendo puede ingresar 
   a la url: 
   http://localhost:8080/h2-console los datos de conexion son los anteriormente descritos 

### Compilación y Ejecución

1. Abre una terminal o línea de comandos y navega hasta el directorio raíz del proyecto.
2. Ejecuta el siguiente comando para compilar el proyecto:

   ```shell
   mvn clean package

3. Una vez sea exitosa la compilacion, ejecute el siguiente comando para despliegue
4.    ```shell
      mvn spring-boot:run
      
5. Cuando termine de realizar despliegue, puedes probar los endpoint de la API entrando a
   la url de swagger http://localhost:8080/swagger-ui/index.html

###   API
Sistema de Inventarios cuenta con los siguientes endpoints
    -  Creacion de Activo
    -  Edicion de Activo
    -  Calculo Depreciacion



## Despliegue en Docker

Puedes desplegar la aplicación en un contenedor Docker siguiendo estos pasos:

### Requisitos Previos

Antes de comenzar, asegúrate de tener Docker instalado en tu sistema. Puedes descargar e instalar Docker desde [https://www.docker.com/get-started](https://www.docker.com/get-started).

### Pasos de Despliegue

1. Abre una terminal o línea de comandos y navega hasta el directorio raíz del proyecto.

2. Crea una imagen de Docker ejecutando el siguiente comando:

   ```shell
   docker build -t sistema .

Una vez que la imagen se haya creado correctamente, puedes ejecutar un contenedor Docker con el siguiente comando:
   ```shell
   docker run -p 8080:8080 sistema

La aplicación ahora debería estar en ejecución dentro del contenedor Docker.

Puedes acceder a la API REST en http://localhost:8080 

