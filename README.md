# Aprendiendo Apring Boot

## Configuración Inicial

### Configuración de `application.properties`

- Asegúrate de tener bien configurada la sección de `application.properties`.
- **Error común**: Problemas con los acentos al compilar con Maven.
    - Solución: Configura IntelliJ IDEA para usar la codificación UTF-8. [Lee más aquí](https://blog.jetbrains.com/idea/2013/03/use-the-utf-8-luke-file-encodings-in-intellij-idea/).

### Base de Datos

- **Error común**: Puedes encontrarte con errores si la base de datos no está creada.

## Problemas Comunes en Código

### Uso de `@Data` de Lombok

- El uso de la anotación `@Data` de Lombok puede causar problemas inesperados.
    - Detalles y soluciones en [StackOverflow](https://stackoverflow.com/a/68605588/24313181).

### Relaciones `@ManyToMany`

- Problemas con `Set` en entidades relacionadas `@ManyToMany` que no devuelven correctamente la información de la tabla asociada.
    - Explicación y solución en [StackOverflow](https://stackoverflow.com/a/77421861).

- Al actualizar relaciones `@ManyToMany`, como listas de roles en la entidad de usuarios, utiliza "clear" seguido de "addAll" para evitar excepciones de inmutabilidad.

### Uso de Optional y Map

- Encadenar un `Optional` con su método `map` dentro de otro `map` puede llevar a problemas.

### Consultas y Mapeo Entidad-DTO

- **Error común**: La propiedad consultada en el repositorio debe coincidir en nombre con la propiedad de la entidad.
    - Ejemplo de consulta correcta: **findBy{propiedadEntidadRelacionada}{propiedadEntidadPrincipal}** "findByUserIdId" 
    - Más sobre este problema en [OpenAI Chat](https://chat.openai.com/share/cb7d8861-531c-4a75-9677-661632bfc8c5).

## Recursos Adicionales

### Cursos y Documentación

- **Curso de YouTube sobre Spring**
    - [Ver curso](https://www.youtube.com/watch?v=Nv2DERaMx-4&t=3075s)

- **Documentación de Spring Data JPA**
    - [Métodos de consulta](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.at-query)
    - [Consultas con `exists`](https://www.baeldung.com/spring-data-exists-query#using-a-derived-query-method)
    - [Uso de ResponseEntity en Spring](https://www.baeldung.com/spring-response-entity)
    - [Manejo de Request y Response Body en Spring](https://www.baeldung.com/spring-request-response-body)
    - [Referencia de CrudRepository](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html)
    - [Tipos de cascada en JPA](https://www.baeldung.com/jpa-cascade-types)

