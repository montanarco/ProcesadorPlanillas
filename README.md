# ProcesadorPlanillas

  

## Ejercicio tecnico PGD - enero 2024

  

instruciones para construir y ejecutar la API

  
  

### Ejecucion local

  

    mvn test

  

ejecuta las pruebas unitarias

  

    mvn clean install

  

construye el proyecto y genera un archivo .jar en target/ProcesadorPlanillas-0.0.1-PGD.jar

  

    mvn spring-boot:run

  

despliega el proyecto usando el tomcat de spring boot que queda accesible via

  

http://localhost:8080/swagger-ui/index.html#/controlador-planillas/healthCheck

  

se puede cambiar el puerto en el archivo *application.properties* cambiando el valor de la variable *server.port*

  
  

## Ejecucion Contenedor

  

    docker build -t procesador-planillas .

  

crea la imagen de docker con el tag "procesador-planillas"

  
  

ejecuta el contenedor con la imagen generada

  

    docker run -p 8040:8080 procesador-planillas

  

http://localhost:8040/swagger-ui/index.html#/controlador-planillas/healthCheck

  

### Notas

  

este proyecto fue desarrollado con Spring boot 3.2

  

este compila con Jakarta EE por lo que requiere un entorno JDK 17

  

> esta fue la que se uso: java version "17.0.2" 2022-01-18 LTS