A Continuacion Se explicará paso a paso la configuracón tecnica del microservicio y como esta estructurado paso a paso

#NOTA: Existe un video en la ruta src\main\resources el cual muestra el funcionamiento del microservicio y un breve recorrido en sus procesos

#Microservicio de Tickets – Double V Partners  
Microservicio desarrollado en Java 21 + Spring Boot 3.5.7, que permite crear, consultar, actualizar y eliminar tickets, además de manejar autenticación JWT, caché, filtros y validaciones avanzadas.

#El proyecto está diseñado bajo una arquitectura en capas:
Domain (Models, Enums, Exceptions, Repositories)
Application (Use Cases)  
Infrastructure (Security, Persistence(Repository, Adapters,Mapper, Los Entities de Migraciones hacia la BD), Interceptors)  
Presentation (Controllers y DTOs)

#El proyecto fue diseñado sobre esta arquitectura Hexagonal (Puertos y Adaptadores)
para poder enfocarse mucho en los principíos solid y patrones de diseño que fueran notables

#Definiciones Importantes
-Manejo de Contratos (Interfaces)
-Manejo de Abstracciones, Interfaces, Implementaciones y Mappers para los consumos de entrada y salida de los repositories sobre el JPA HIbernate, esto
disminuye la complejidad en el momento de querer cambiar el motor de base de datos no se afecte la integridad de la capa de negocios
-Manejo de Interceptores para poder controlar el flujo y filtro que corre entre el navegador y los controladores.
-Manejo de Exepciones para los datos requeridos y validaciones en el caso de realizar operaciones sobre la base de datos
-Manejo de Seguridad de EndPoints sensibles con Autenticacion JWT
-Para los Request y Responsese utlizaron DTO para no exponer directamente los Modelos en los controladores
-Swagger como Documentacion y tambien ya tiene datos de ejemplo para poder agilizar el proceso de revisión
-Junit para pruebas unitarias, una clasetest sobre cada caso de uso para pobrar validaciones y transaccionabilidad.
-Para el Manejo de Caché utilizó Cacheable y se muestra en el end point 
http://localhost:9090/api/v1/findcacheable_tickets/usuario/75a89fae-0e3f-4481-bed8-65b728c952df
-Cada Proceso tiene su respectiva anotacion (@RestController, @Service, @Repository,@Component,@Bean,@Mock)

---

#Tecnologías utilizadas

-Java 21
-Spring Boot 3.5.7
-Spring Web
-Spring Data JPA
-Spring Security (JWT)
-H2 Database (modo local)
-Gradle
-Lombok
-SpringDoc OpenAPI (Swagger UI)
-Spring Cache (ConcurrentMapCache)
-JUnit 5 + Mockito

---

#Requisitos previos
DEbes de Tener  instalados:

Java 21
Gradle Wrapper Incluido en el proyecto
VS Code (opcional) Última versión (Utilizó VSCode para Desarrollo y Ejecución)
Extensión Pack Java (Opcional)

---

#Configuración de entorno

El archivo principal de configuración es:
src/main/resources/application.yml

Donde se configuran:

-Base de datos en memoria H2
-Puerto del servidor (9090)
-JWT Secret y Expiración
-Consola H2
-Hibernate en modo 'create-drop'

La base de datos se genera automáticamente al iniciar  
No requiere instalación de ningún motor externo

---
#Cómo ejecutar el proyecto

#1. Desde VS Code o terminal:
./gradlew bootRun

Para compilar el proyecto completo
./gradlew clean build

Ejecutar solo los tests
./gradlew test

Reportes de pruebas con JUNIT:
build/reports/tests/test/index.html

Descargar y refrescar dependencias por si no de Descargan
./gradlew clean build --refresh-dependencies

El microservicio quedará disponible en:
http://localhost:9090


#Documentación Swagger
Una vez levantado el servicio:
Nos encontremos con una bienvenida: http://localhost:9090/
Aqui puedes Ir a La Documentacion de Swagger, Consol H2 y al archivo README.md

#Swagger
http://localhost:9090/swagger-ui/index.html

#Consola de H2
http://localhost:9090/h2-console
JDBC URL= jdbc:h2:mem:testdb
Username=sa
Password=

#Usuario de Acceso
Al iniciar el Microservicio se crea un Usuario para acceso por defecto
Usuario:admin
password:admin123

#Autenticación JWT
El login está disponible en:
POST http://localhost:9090/api/v1/auth/login

Request:
{
  "username": "admin",
  "password": "admin123"
}

Respuesta:
{
  "token": "eyJhbGc45454545XYZ",
  "type": "Bearer",
  "expiresIn": 86400000,
  "username": "admin",
  "role": "ADMIN"
}


#Para consumir endpoints sensibles protegidos, se debe de usar:
Authorization: Bearer <token>

#Recomendable usar Swagger para las pruebas por que ya se tienen datos de Ejemplo

#Opcional Ejecutar el contenedor ya el dokckerfile está configurado
Asegurarse de Tener Docker Instalado
docker build -t tickets-service .
docker run -p 9090:9090 tickets-service
El microservicio quedará disponible en: 
Web:http://localhost:9090, 
Swagger: http://localhost:9090/swagger-ui/index.html
H2 Console:http://localhost:9090/h2-console
