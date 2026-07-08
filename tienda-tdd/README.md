# tienda-tdd

Proyecto Spring Boot listo para abrir en IntelliJ IDEA. Implementa
`POST /api/productos/validar` siguiendo TDD (RED → GREEN → REFACTOR).

## Cómo abrirlo en IntelliJ

1. **File → Open** → selecciona la carpeta `tienda-tdd` (la que contiene `pom.xml`).
2. IntelliJ detecta que es un proyecto Maven y te preguntará si quieres
   cargarlo como proyecto Maven → **Sí / Load**.
3. Espera a que descargue las dependencias (barra de progreso abajo a la derecha).
4. Abre `ProductoValidacionControllerTest.java`
   (`src/test/java/com/ejemplo/tienda/controller/`) y dale clic al triángulo ▶
   verde junto al nombre de la clase → **Run**.
5. Deben pasar los 2 tests en verde ✅.

## Compilar y correr desde terminal (si prefieres)

```bash
cd tienda-tdd
mvn clean test        # corre los tests
mvn spring-boot:run   # levanta la app en http://localhost:8080
```

## Probar el endpoint manualmente (con la app corriendo)

```bash
curl -X POST http://localhost:8080/api/productos/validar \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Camiseta","precio":-10}'
```

Debe responder `400 Bad Request` con:
```json
{"mensaje":"El precio no puede ser negativo"}
```

## Estructura

```
tienda-tdd/
├── pom.xml
└── src
    ├── main/java/com/ejemplo/tienda/
    │   ├── TiendaApplication.java
    │   ├── controller/ProductoController.java
    │   ├── dto/ProductoDTO.java
    │   ├── dto/ErrorResponse.java
    │   └── validation/ProductoValidador.java
    └── test/java/com/ejemplo/tienda/controller/
        └── ProductoValidacionControllerTest.java
```

## Recorrido TDD para la clase

- **RED**: borra temporalmente `ProductoController.java` y `ProductoDTO.java`
  (o su contenido) y corre el test → falla.
- **GREEN**: vuelve a pegar el `ProductoDTO` y una versión mínima del
  controlador (sin la clase `ProductoValidador`, con un `if` directo) →
  corre el test → pasa.
- **REFACTOR**: reintroduce `ProductoValidador` y deja el controlador como
  está en este repo (delgado, delega la regla al validador) → corre el
  test de nuevo → sigue en verde, sin tocar el archivo de test.
