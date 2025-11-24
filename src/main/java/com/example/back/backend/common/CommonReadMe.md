# Common Layer

This layer contains shared utilities and helper classes that are not tied to any specific layer.

### Responsibilities
- Shared mappers (Domain ↔ Entity)
- Utility classes
- Constants
- Global exception helpers

### DO:
✔ Keep logic stateless  
✔ Keep functions generic (usable by all layers)

### DON'T:
✘ Do not put business logic here  
✘ Do not access infrastructure (DB, JPA, etc.)
