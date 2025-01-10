# E-commerce Spring Boot Application
Esta es una aplicación de e-commerce desarrollada con Spring Boot, implementada utilizando el enfoque de Diseño Dirigido por Dominio (DDD) y la Arquitectura Hexagonal.

# Descripción
El objetivo de esta aplicación es exponer un de endpoint que simula la aplicación de descuentos a determinados productos segun un rango horario. La estructura está diseñada para ser modular, manteniendo una clara separación de responsabilidades mediante DDD y utilizando una arquitectura hexagonal que promueve una buena organización del código y facilita las pruebas y el mantenimiento.

# Características
Diseño Dirigido por el Dominio (DDD): La aplicación está estructurada alrededor del dominio del e-commerce, separando claramente las entidades de dominio, servicios, repositorios, etc.
Arquitectura Hexagonal: La aplicación sigue una arquitectura hexagonal, también conocida como arquitectura de puertos y adaptadores, donde los adaptadores permiten la interacción con el sistema, mientras que el dominio permanece independiente de detalles externos.
Base de Datos en Memoria H2: La aplicación utiliza una base de datos en memoria H2 para facilitar el desarrollo y las pruebas.
Swagger UI: La documentación de la API está disponible a través de Swagger UI.

# Requisitos
1. JDK 11 o superior
2. Maven (o cualquier otro gestor de dependencias compatible)
3. H2 Database (se ejecuta por defecto en la aplicación al arrancarla)

# Manual de Arranque

1. Clonar el repositorio:
    git clone https://github.com/xalex55/ecommerce-demo.git
    cd ecommerce-springboot

2. Compilar y ejecutar la aplicación con Maven:
   ./mvnw spring-boot:run

3. Acceder a la documentación con Swagger:
   http://localhost:8080/ecommerce-api/swagger-ui/index.html

4. Acceder a la consola H2:
   http://localhost:8080/ecommerce-api/h2-console (usuario: sa / contraseña: password)

