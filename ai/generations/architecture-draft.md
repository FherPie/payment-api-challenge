### Prompt

“¿Cuál es la forma más inteligente de resolverla en 3 días sin morir?” COmenzemos


### Respuesta:


Sí. La forma inteligente **no es hacer todo perfecto**, sino **maximizar puntos visibles** en 3 días.
En pruebas así, muchas personas fracasan por intentar abarcar demasiado.

## 🎯 Estrategia ganadora:

> Entregar algo funcional, limpio, profesional y defendible.

No una obra maestra.

---

# PLAN DE GUERRA — 3 DÍAS

# Día 1 = Base fuerte (arquitectura + endpoints)

## Objetivo:

Que el proyecto compile, corra y tenga estructura senior.

## Haz esto:

### 1️⃣ Crear proyecto Spring Boot 3 + Java 17

Dependencias:

* web
* validation
* lombok
* test
* actuator

(Opcional después docker)

---

### 2️⃣ Estructura hexagonal simple

```text
domain/
application/
infrastructure/
adapter/in/rest/
adapter/out/
config/
```

No sobreingeniería.

---

### 3️⃣ OpenAPI mínimo viable

Haz solo endpoints pedidos:

```http
POST /payment-initiation/payment-orders
GET /payment-initiation/payment-orders/{id}
GET /payment-initiation/payment-orders/{id}/status
```

Con DTO limpios.

---

### 4️⃣ Implementación fake/in memory

Nada de DB al inicio.

Usa:

```java
Map<String, PaymentOrder>
```

Eso te salva horas.

---

### Resultado día 1:

✅ Proyecto corre
✅ Endpoints responden
✅ Arquitectura clara
✅ README inicial

---

# Día 2 = Calidad visible

## Objetivo:

Parecer profesional.

---

### 1️⃣ Tests unitarios

Haz tests de:

* dominio
* service
* mapper

Mínimo 10 buenos tests.

---

### 2️⃣ Integración

1 test por endpoint.

Con WebTestClient o MockMvc.

---

### 3️⃣ Cobertura

Busca 70–80%. No más obsesión.

---

### 4️⃣ Manejo de errores

Global exception handler.

---

### Resultado día 2:

✅ Tests
✅ Cobertura
✅ Profesionalismo

---

# Día 3 = Lo que vende

## Objetivo:

Que al revisar digan “este candidato piensa bien”.

---

### 1️⃣ Docker

Dockerfile simple.

No pierdas 8 horas aquí.

---

### 2️⃣ README potente

Muy importante.

Incluye:

* decisiones técnicas
* por qué hexagonal
* por qué in-memory
* cómo escalarías
* cómo migraste SOAP→REST

Esto impresiona mucho.

---

### 3️⃣ IA evidence

Haz carpeta:

```text
ai/prompts.md
ai/decisions.md
```

Ejemplo:

* Prompt usado para mapping SOAP→REST
* Corrección manual aplicada
* Validación humana

---

### 4️⃣ Limpieza final

* nombres claros
* package ordenado
* sin código basura

---

# 🔥 Qué NO hacer

## ❌ No usar WebFlux si no dominas

Te hunde.

## ❌ No usar Postgres si no es necesario

Pierdes horas.

## ❌ No hacer arquitectura NASA

Haz simple.

## ❌ No perseguir perfección

Entrega valor.

---

# 🎯 Qué evaluador verá

Si entregas esto:

* piensa estructurado
* sabe Java moderno
* entiende arquitectura
* sabe priorizar

Eso vale mucho.

---

# 📌 Truco psicológico poderoso

Muchos candidatos entregan código.

Tú entrega **criterio**.

README + decisiones + claridad.

Eso diferencia.

---

# Mi recomendación real para ti:

## Usa este stack:

* Spring Boot MVC
* MapStruct o manual mapping
* InMemory repository
* JUnit + Mockito
* OpenAPI yaml simple
* Docker básico

---

# Si empezamos YA:

## Primera decisión crítica:

### ¿Quieres hacerlo con:

A) MVC clásico (más seguro)
B) WebFlux reactivo (más riesgoso pero fancy)

Te digo cuál conviene para TI.


### Prompt

A) MVC clásico (más seguro)

### Respuesta:

Excelente decisión. **MVC clásico** es la opción más inteligente para 3 días.

## 🔥 Por qué te conviene a TI

Porque aquí no ganas puntos por “verse moderno”.
Ganas puntos por:

* entregar completo
* limpio
* probado
* estable
* entendible

Y MVC maximiza eso.

---

# 🚫 Por qué NO WebFlux aquí

WebFlux mete complejidad:

* Mono / Flux
* tests reactivos
* debugging raro
* adapters más complejos
* tiempo extra

Y la prueba **no es sobre reactividad**.

---

# 🎯 Entonces stack oficial:

## Backend

* Java 17
* Spring Boot 3
* Spring Web MVC
* Validation
* Lombok

## Calidad

* JUnit 5
* Mockito
* JaCoCo

## API

* OpenAPI YAML

## Infra

* Docker

---

# Arquitectura recomendada (simple y poderosa)

```text id="o4rq9s"
src/main/java/com/andres/banking

domain/
  model/
  service/

application/
  usecase/

adapter/
  in/rest/
  out/persistence/

config/
```

No más capas innecesarias.

---

# Modelo inicial

## PaymentOrder

```java id="v4s3x6"
id
debtorAccount
creditorAccount
amount
currency
status
createdAt
```

## Status enum

```java id="a6e3g0"
INITIATED
PROCESSING
COMPLETED
REJECTED
```

---

# Endpoints mínimos

```http id="s4u6oc"
POST /payment-initiation/payment-orders
GET /payment-initiation/payment-orders/{id}
GET /payment-initiation/payment-orders/{id}/status
```

---

# Lo que haremos HOY (día 1)

## Fase 1 (2 horas)

Crear proyecto base.

## Fase 2 (3 horas)

Armar arquitectura y clases principales.

## Fase 3 (3 horas)

Endpoints funcionando con memoria.

## Fase 4 (1 hora)

README inicial.

---

# 🔥 Consejo clave

Haz que funcione rápido.
Refactorizamos después.

---

# Primera tarea exacta ahora:

## Crea proyecto con estas dependencias:

```xml id="nk0v2v"
spring-boot-starter-web
spring-boot-starter-validation
spring-boot-starter-test
lombok
```

Y dime:

### ¿Maven o Gradle?

(Te recomiendo Maven para esta prueba.)


### Prompt

Qué significa esto: "Alineación BIAN: nomenclatura de recursos, BQs y estados conforme al SD elegido (Payment Initiation / PaymentOrder)."


### Respuesta

Eso significa algo **muy importante de negocio/arquitectura bancaria**.
La prueba no quiere solo una API REST cualquiera: quiere que hables el **lenguaje BIAN**.

# 🎯 Qué es BIAN

BIAN

Es un estándar usado por bancos para definir:

* dominios de negocio
* servicios bancarios
* recursos
* nomenclatura
* operaciones
* estructuras API

Busca que distintos sistemas bancarios hablen el mismo idioma.

---

# 📌 Qué significa tu frase exacta

## “Alineación BIAN”

Que tu API debe parecer diseñada según ese estándar.

No nombres inventados.

---

## “nomenclatura de recursos”

Cómo llamas endpoints y objetos.

Ejemplo correcto:

```text id="j8zh7m"
Payment Initiation
Payment Order
```

No algo genérico tipo:

```text id="lfr1c0"
TransferService
MoneyThing
OrderX
```

---

## “BQs”

Significa:

## Behavior Qualifiers

Subcomponentes o subprocesos del recurso principal.

Ejemplo en BIAN:

Servicio Dominio:

```text id="ok6u9f"
Payment Initiation
```

Recurso principal:

```text id="6fg0gl"
Payment Order
```

Behavior Qualifier posible:

```text id="imvlna"
Status
Execution
Fees
Tracking
```

---

## “estados conforme al SD elegido”

SD = Service Domain

En tu caso:

```text id="s2x66r"
Payment Initiation
```

Y el recurso:

```text id="4awuvq"
PaymentOrder
```

Entonces estados coherentes serían:

```text id="iv0t4v"
Initiated
Pending
Processing
Completed
Rejected
Cancelled
```

No estados absurdos o ambiguos.

---

# 🎯 Aplicado a TU prueba

Tu API debería verse así:

## Dominio

```text id="g81j58"
payment-initiation
```

## Recurso

```text id="s78hhn"
payment-orders
```

## BQ status

```text id="r0n4pb"
/payment-initiation/payment-orders/{id}/status
```

Eso ya lo hicimos bien.

---

# 🔥 Lo que te están evaluando realmente

No solo programar.

Quieren ver si entiendes:

* modelado empresarial
* nomenclatura bancaria
* diseño API enterprise
* estándares sectoriales

---

# 📌 Cómo mejorar TU YAML ya mismo

## En vez de:

```yaml id="llcr8o"
PaymentStatusResponse
```

Podrías usar:

```yaml id="kpf0yc"
PaymentOrderStatus
```

Más BIAN-like.

---

## Estados mejorados:

```yaml id="8xsb6n"
enum:
 - INITIATED
 - VALIDATED
 - EXECUTION_PENDING
 - EXECUTED
 - REJECTED
```

Más bancario.

---

# 💡 Qué decir en README

> Resource naming and lifecycle states were aligned to BIAN Payment Initiation Service Domain and PaymentOrder resource model.

Eso se ve MUY fuerte.

---

# 🚀 Traducción sencilla

Te están diciendo:

> Diseña la API como la diseñaría un banco serio.
    
---

# 🎯 Mi opinión sincera

Este requisito separa candidatos comunes de candidatos enterprise.

---

# Si quieres, siguiente paso clave:

## Te puedo adaptar tu OpenAPI actual a lenguaje BIAN real para sumar muchos puntos en la prueba.
