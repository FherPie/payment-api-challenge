# Migración de Servicios Web legados SOAP hacia REST

## 1. Problema
Este proyecto es la migración servicios web SOAP que manejan creación y consulta de ordenes de pagos dentro de una institución 
bancaria lo cual están alineada a BIAN hacia REST con Spring Boot +3, Maven 3.9.14, Java 17, OPEN API 3.0 y Docker. 


El objetivo principal fue migrar una solución inicial basada en especificaciones legadas (SOAP/WSDL) hacia una arquitectura moderna basada en:

- REST + OpenAPI
- Clean / Hexagonal Architecture
- Separación dominio / aplicación / infraestructura
- Testing automatizado
- Contenerización con Docker



# 2. Decisiones tomadas durante la migración


## Etapa inicial (legacy)

Se partió de la definición orientada a SOAP/WSDL con estructuras fuertemente acopladas.

## Decisiones aplicadas


### ✅ Contrato primero (Contract First)

Se definió `openapi.yaml` como fuente principal del contrato.

Ventajas:

- claridad entre frontend/backend
- documentación automática Swagger
- generación de DTOs/interfaces

---

### ✅ Arquitectura Hexagonal

Se separó en capas:

```texto
src/main/java/com/andres/baking
├── adapter/
│   ├── in/rest
│   └── out/persistence
├── application/
├── domain/
└── generated/
```
```
Motivo:
menor acoplamiento
mantenibilidad
testing más simple
```


### ✅ Dominio independiente

El dominio usa sus propias entidades y enums.

No depende de DTOs generados.

### ✅ Repositorio en memoria

Para simplificar el challenge se utilizó:
```Java
ConcurrentHashMap
```
como almacenamiento temporal.


✅ Tests automatizados

Se implementaron:

```text
unit tests
integration tests con MockMvc
cobertura con JaCoCo
```

## 3. Stack Tecnológico

   * Java 17
   * Spring Boot 3
   * Maven
   * OpenAPI Generator
   * JUnit 5
   * Mockito
   * MockMvc
   * JaCoCo
   * Docker
   * Docker Compose

## 4. Ejecución Local

   ### Requisitos:

   - Java 17
   - Maven 3.9+

### Compilar 

```bash 
mvn clean package
```
### Ejecutar

```bash 
mvn spring-boot:run
```
o

```bash 
java -jar target/payment-api-1.0.0.jar
```

## 5. Swagger / OpenAPI

http://localhost:8080/swagger-ui/index.html

## 6. Ejecutar con Docker


### Build imagen
```bash 
docker build -t payment-api .
```
### Run contenedor
```
docker run -p 8080:8080 payment-api
```

## 7. Ejecutar con Docker Compose

### docker-compose.yml

```yaml
version: "3.9"

services:
  payment-api:
    build: .
    container_name: payment-api
    ports:
      - "8080:8080"
```
### Levantar

```bash
docker compose up --build
```

### Apagar

```bash
docker compose down
```

## 8. Calidad de Código


### Ejecutar tests

```bash
mvn test
```

### Cobertura

```bash
mvn verify
```

### Reporte:

target/site/jacoco/index.html


## 9. Uso de Inteligencia Artificial (Asistido)

Durante el desarrollo se utilizó IA como asistente técnico para acelerar tareas repetitivas y revisión técnica.

La validación final y decisiones arquitectónicas fueron realizadas manualmente.


## 10. Evidencia IA

Se incluye carpeta:

```text
ai/
├── prompts.md
├── decisions.md
└── generations/
```

## 11. Uso de IA – Resumen

### a) Resumir WSDL y mapping a BIAN

### Prompt

```
Analiza este WSDL y propón recursos REST compatibles con BIAN.
```  
### Resultado resumido

```
PaymentOrder
PaymentStatus
POST create payment order
GET payment order
GET payment status
```

### Correcciones humanas

Se ajustaron nombres para mayor claridad REST.


### b) Borrador de openapi.yaml
### Prompt
```
Genera un OpenAPI 3 para Payment Initiation API.
```

### Resultado resumido
```
Se generó base de endpoints y schemas.
```

### Correcciones humanas
```
status codes
required fields
nombres finales
validaciones
```

### c) Esqueleto hexagonal
### Prompt
```
Genera estructura Java hexagonal para Spring Boot.
```

### Resultado

Paquetes base:

```
domain
application
adapter
config
```

### Correcciones humanas

Se reorganizó naming y responsabilidades.

### d) Generación de tests
### Prompt

```
Genera unit tests para controller usando Mockito.
```

### Resultado

Mocks base y asserts iniciales.

### Correcciones humanas

```
verify interactions
casos negativos
mejoras de cobertura
```


## 12. Validación Humana y Correcciones Manuales

Todo código generado fue revisado manualmente.

### Cambios relevantes:
### Enums

Se separó enum de dominio vs enum DTO.

### BigDecimal

Se sustituyó double en dominio para montos monetarios.

### Repository

Se corrigió:

```Java
Optional.of(...)
```
por:

```Java
Optional.ofNullable(...)
```

### Mappers

Se refactorizaron nombres y conversiones.

### Checkstyle

Se normalizó formato, imports y nomenclatura.

## 13. Conclusión

La IA fue utilizada como herramienta de productividad, no como reemplazo del criterio técnico.

Las decisiones finales de diseño, arquitectura y validación fueron humanas.



