### Prompt

"Definamos el yaml te voy a pasar el WSDL y arma una propuesta de Contract-First
con Open Api 3.0"


### Respuesta

Perfecto. **Ahora sí estamos trabajando profesionalmente.** Ya analicé tu WSDL y podemos hacer un **Contract-First
OpenAPI 3.0 real**, no inventado.

# 🎯 Resumen del WSDL legado

## Servicio SOAP:

`PaymentOrderService`

## Operaciones reales:

### 1️⃣ SubmitPaymentOrder

Crear orden de pago.

Request:

* externalId
* debtorIban
* creditorIban
* amount
* currency
* remittanceInfo (opcional)
* requestedExecutionDate

Response:

* paymentOrderId
* status

---

### 2️⃣ GetPaymentOrderStatus

Consultar estado.

Request:

* paymentOrderId

Response:

* paymentOrderId
* status
* lastUpdate

---

# 🧠 Conversión inteligente SOAP → REST

En vez de copiar nombres SOAP, modernizamos.

## REST propuesto:

### Crear orden

```http
POST /payment-initiation/payment-orders
```

### Obtener orden completa

```http
GET /payment-initiation/payment-orders/{paymentOrderId}
```

### Obtener estado

```http
GET /payment-initiation/payment-orders/{paymentOrderId}/status
```

---

# 📌 ¿Por qué agrego GET detalle?

Porque REST moderno suele permitir consultar recurso completo.
Y te mejora la prueba.

---

# 🔥 OpenAPI 3.0 inicial profesional

Guárdalo en:

```text
src/main/resources/static/openapi/payment-api.yaml
```

```yaml
openapi: 3.0.3

info:
  title: Payment Order API
  version: 1.0.0
  description: REST API migrated from legacy SOAP PaymentOrderService

servers:
  - url: http://localhost:8080

paths:

  /payment-initiation/payment-orders:
    post:
      summary: Create payment order
      operationId: createPaymentOrder
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/CreatePaymentOrderRequest'
      responses:
        '201':
          description: Payment order created
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/CreatePaymentOrderResponse'

  /payment-initiation/payment-orders/{paymentOrderId}:
    get:
      summary: Get payment order
      operationId: getPaymentOrder
      parameters:
        - name: paymentOrderId
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Payment order found
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/PaymentOrder'

        '404':
          description: Not found

  /payment-initiation/payment-orders/{paymentOrderId}/status:
    get:
      summary: Get payment order status
      operationId: getPaymentOrderStatus
      parameters:
        - name: paymentOrderId
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Current payment status
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/PaymentStatusResponse'

        '404':
          description: Not found

components:

  schemas:

    CreatePaymentOrderRequest:
      type: object
      required:
        - externalId
        - debtorIban
        - creditorIban
        - amount
        - currency
        - requestedExecutionDate
      properties:
        externalId:
          type: string
        debtorIban:
          type: string
        creditorIban:
          type: string
        amount:
          type: number
          format: decimal
        currency:
          type: string
          example: EUR
        remittanceInfo:
          type: string
        requestedExecutionDate:
          type: string
          format: date

    CreatePaymentOrderResponse:
      type: object
      properties:
        paymentOrderId:
          type: string
        status:
          type: string
          example: INITIATED

    PaymentOrder:
      type: object
      properties:
        paymentOrderId:
          type: string
        externalId:
          type: string
        debtorIban:
          type: string
        creditorIban:
          type: string
        amount:
          type: number
          format: decimal
        currency:
          type: string
        remittanceInfo:
          type: string
        requestedExecutionDate:
          type: string
          format: date
        status:
          type: string
        lastUpdate:
          type: string
          format: date-time

    PaymentStatusResponse:
      type: object
      properties:
        paymentOrderId:
          type: string
        status:
          type: string
        lastUpdate:
          type: string
          format: date-time
```

---

# 🎯 Lo bueno de este contrato

✅ Respeta WSDL real
✅ Moderniza a REST
✅ Profesional
✅ Listo para Swagger
✅ Listo para controllers

---

# 📌 Siguiente paso recomendado YA

Implementar:

```Java
@PostMapping("/payment-initiation/payment-orders")
@GetMapping("/payment-initiation/payment-orders/{id}")
@GetMapping("/payment-initiation/payment-orders/{id}/status")
```

---

# 💡 En README podrás decir:

> Contract-first API designed from legacy SOAP WSDL preserving core business capabilities while exposing modern REST endpoints.

Eso suena fuerte.

---

# 🔥 Siguiente nivel (muy importante)

Puedo ayudarte ahora a generar:

## DTO Java exactos desde este YAML

o

## Controllers Spring Boot exactos alineados al contrato

Solo dime: **DTOs o Controllers primero**




