# Application Layer

This layer contains all **use cases** and **application logic**.

It orchestrates the flow between:
- `presentation` (controllers / API)
- `domain` (business logic)
- `infrastructure` (DB, external services)

### Responsibilities
- Validate input (via DTO)
- Convert DTO → Domain Model
- Execute business use case
- Return output DTO to controller

### DO:
✔ Only depend on `domain`  
✔ Call domain repositories  
✔ Contain service classes (ex: TKD0100ASVC)

### DON'T:
✘ Do not access the database directly  
✘ Do not contain business rules  
✘ Do not use JPA or entity classes
