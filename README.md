# Aprendiendo Spring Boot

## Descripción General

Este proyecto utiliza Spring Framework para demostrar varios conceptos de desarrollo de aplicaciones con Java. A lo largo de este documento, encontrarás información útil sobre cómo configurar y solucionar problemas comunes, así como enlaces a recursos externos y videos que complementan la explicación.

## Configuración Inicial

### Properties

- **Application Properties**: Asegúrate de tener bien configurada la sección de `application.properties`.
  - Presta atención a la codificación de caracteres para evitar problemas con acentos. Una guía detallada para configurar UTF-8 en IntelliJ IDEA puede encontrarse [aquí](https://blog.jetbrains.com/idea/2013/03/use-the-utf-8-luke-file-encodings-in-intellij-idea/).

### Docker

- **Comunicación entre contenedores**: Si experimentas errores al comunicar los contenedores de Docker, asegúrate de revisar el `data.source.url` en `application.properties`.
  - Utiliza la variable de entorno `SPRING_DATASOURCE_URL` en `compose.yaml` para solucionar este problema. Más detalles [aquí](https://stackoverflow.com/a/66170886/24313181).

#### Iniciar la aplicación con Docker

- Asegúrate de tener Docker instalado.
- En la carpeta del proyecto, ejecuta `docker compose up`. El proyecto iniciará en http://localhost:8080.
- Para iniciar los servicios desde cero en cada ejecución, ejecuta `docker compose build --no-cache` y luego `docker compose up`.
- Para eliminar los contenedores ejecuta `docker compose down`
- **Errores al iniciar Docker**: Algunas veces, debido a que el servicio del que depende otro tarda en empezar, pueden ocurrir errores. Puede ser necesario ejecutar unas cuantas veces `docker compose up`.
- **Errores en Windows durante el `docker compose up`**:
  - Si encuentras errores como `/bin/sh: 1: ./mvnw: not found`, puede deberse a que Windows no trata `./mvnw` como un archivo binario. Para solucionarlo, se añadió el archivo `.gitattributes`. Más información [aquí](https://stackoverflow.com/q/72455739/24313181).
  - Si te encuentras con el error `"/usr/bin/env: ‘bash\r’: No such file or directory"`, se debe a los saltos de línea entre Linux y Windows. Solución: clonar el repositorio con la configuración adecuada: `git clone https://github.com/fruizotero/SB-Notes.git  --config core.autocrlf=input` Más información [aquí](https://github.com/tiangolo/uwsgi-nginx-flask-docker/issues/127#issuecomment-688418738).

## Documentación de la API

- Puedes encontrar la documentación de los endpoints para probar la API : https://notes.fruizotero.info/docs
- Si se quiere probar la aplicación ya desplegada simplemente reemplazar `http://localhost:8080` por `https://notes.fruizotero.info`
- **Endpoints para importarlos a `insomnia` o `postman`** 
  - [Endpoints localhost](./src/main/resources/static/Insomnia_2024-04-19_local.json) 
  - [Endpoints notes.fruizotero.info](./src/main/resources/static/Insomnia_2024-04-19_dominio.json)

## Problemas Comunes

### Errores de Entidades y Relaciones

- **@Data de Lombok**: Puede causar problemas inesperados, más información [aquí](https://stackoverflow.com/a/68605588/24313181).
- **Problemas con Sets en relaciones ManyToMany**: Asegúrate de que la información se devuelve correctamente desde la tabla asociada. Una solución puede encontrarse [aquí](https://stackoverflow.com/a/77421861).
- **Encadenar Optionals con método map**: Usar map dentro de otro map puede llevar a problemas.
- **Actualización de relaciones ManyToMany**: Al actualizar, usa "clear" seguido de "addAll" para manejar correctamente las listas en el lado inverso de la relación.

### Errores de Consultas y Mapeos

- **Consulta y Mapeo Entidad-DTO**: Asegúrate de que los nombres de los campos en las consultas coincidan con los nombres de las propiedades de la entidad. Más detalles pueden encontrarse [aquí](https://chat.openai.com/share/cb7d8861-531c-4a75-9677-661632bfc8c5).

## Herramientas y Librerías

- **Librerías utilizadas**:
  - ModelMapper: [Documentación](https://modelmapper.org/getting-started/)
  - Lombok: [Características](https://projectlombok.org/features/)
  - Insomnia Documenter: [GitHub](https://github.com/insodoc/insomnia-documenter)
  - wait-for-it: [GitHub](https://github.com/vishnubob/wait-for-it)

- **Herramientas**:
  - Insomnia
  - Docker
  - IntelliJ
  - ChatGPT

## Recursos Adicionales

- **Documentación oficial de Spring y tutoriales relacionados**:
  - [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.at-query)
  - Baeldung sobre Spring: [Exist queries](https://www.baeldung.com/spring-data-exists-query#using-a-derived-query-method), [Response Entity](https://www.baeldung.com/spring-response-entity), y más.

- **Curso de YouTube**: Visualiza el tutorial completo en YouTube para un aprendizaje más interactivo.
  - [Ver Curso](https://www.youtube.com/watch?v=Nv2DERaMx-4&t=3075s)

## Mejoras por Implementar

- Implementar autenticación.
- Mejorar la paginación en ciertos endpoints.
- Reforzar las validaciones de campos antes de ser enviados al servicio.
- Implementar más pruebas.

## Aprendizajes Clave del Proyecto

- **Inyección de dependencias**, manejo de relaciones ORM, uso de JPA/Hibernate, y manejo de errores con `@ControllerAdvice`.
- Importancia de la separación de capas y centralización del manejo de errores.
- Uso efectivo de DTOs y las ventajas de depender de interfaces para futuros cambios.
