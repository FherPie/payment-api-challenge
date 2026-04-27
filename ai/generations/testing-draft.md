
### Prompt

"Primero el dame tres test y en donde poner dentro de la arquitectura hexagonal"

### Respuesta
Sí. Vamos a hacerlo **alineado con hexagonal** y además útil para tu prueba.

# 🎯 Dónde van los tests en arquitectura hexagonal

Normalmente en `src/test/java` replicando la estructura principal.

```text id="c8q1va"
src/main/java/com/example/
   adapter/in/rest/
   application/
   domain/
   adapter/out/memory/

src/test/java/com/example/
   adapter/in/rest/
   application/
   domain/
```

Así cada capa prueba su propia responsabilidad.

---

# 🎯 Los 3 tests que más valor te dan YA

## ✅ Test 1 — MapperTest

Prueba conversión DTO → Command

### Ubicación

```text id="p1r8ly"
src/test/java/com/example/adapter/in/rest/mapper/PaymentMapperTest.java
```

### Qué valida

* externalReference
* debtor iban
* creditor iban
* amount/currency

### Valor

Mucho coverage rápido.

---

## Código ejemplo

```java id="f4u2xn"
class PaymentMapperTest {

    private final PaymentMapper mapper = new PaymentMapper();

    @Test
    void shouldMapRequestToCommand() {

        CreatePaymentOrderRequest req = new CreatePaymentOrderRequest();
        req.setExternalReference("EXT-1");

        Account debtor = new Account();
        debtor.setIban("EC123");

        Account creditor = new Account();
        creditor.setIban("EC999");

        InstructedAmount amt = new InstructedAmount();
        amt.setAmount(100.0);
        amt.setCurrency("USD");

        req.setDebtorAccount(debtor);
        req.setCreditorAccount(creditor);
        req.setInstructedAmount(amt);

        var cmd = mapper.toCommand(req);

        assertEquals("EXT-1", cmd.externalReference());
        assertEquals("EC123", cmd.debtorIban());
        assertEquals("USD", cmd.currency());
    }
}
```

---

# ✅ Test 2 — PaymentServiceTest

Prueba caso de uso crear orden.

### Ubicación

```text id="s0m5eh"
src/test/java/com/example/application/PaymentServiceTest.java
```

### Qué valida

* genera id
* estado INITIATED
* guarda en repo

---

## Código ejemplo

```java id="w2t9jq"
class PaymentServiceTest {

   private final PaymentRepository repo =
       new InMemoryPaymentRepository();

   private final PaymentService service =
       new PaymentService(repo);

   @Test
   void shouldCreatePaymentOrder() {

      var cmd = new CreatePaymentOrderCommand(
          "EXT-1","EC1","EC2",
          new BigDecimal("50"),
          "USD",
          "Pago"
      );

      String id = service.create(cmd);

      assertNotNull(id);
      assertTrue(repo.findById(id).isPresent());
   }
}
```

---

# ✅ Test 3 — Controller Integration Test

Prueba flujo HTTP real.

### Ubicación

```text id="j6v4zt"
src/test/java/com/example/adapter/in/rest/PaymentControllerIT.java
```

---

## Qué valida

POST responde 201.

---

## Código ejemplo

```java id="m8y1pk"
@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerIT {

   @Autowired
   MockMvc mockMvc;

   @Test
   void shouldCreatePaymentOrder() throws Exception {

      String json = """
      {
        "externalReference":"EXT-1",
        "debtorAccount":{"iban":"EC1"},
        "creditorAccount":{"iban":"EC2"},
        "instructedAmount":{"amount":100,"currency":"USD"}
      }
      """;

      mockMvc.perform(post("/payment-initiation/payment-orders")
          .contentType(MediaType.APPLICATION_JSON)
          .content(json))
          .andExpect(status().isCreated());
   }
}
```

---

# 🎯 Por qué estos 3 primero

Cubren:

```text id="x7p3gw"
adapter/in
application
mapping
repo indirectamente
http flow
```

Muchísimo valor.

---

# 📌 En hexagonal se leen así

## MapperTest

Adapter in

## ServiceTest

Application core

## ControllerIT

Sistema integrado

---

# 🔥 Si solo haces estos 3 hoy

Ya muestras madurez.

---

# 🎯 Siguiente después

4. buscar por id inexistente
5. GET status
6. validaciones 400

---

# 🚀 Mi consejo práctico

Haz primero:

1. PaymentServiceTest
2. MapperTest
3. ControllerIT

Porque te desbloquean.

---

# Si me dices qué clases exactas ya tienes creadas, te adapto estos 3 tests para que compilen directo en tu proyecto.
