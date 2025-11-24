# Infrastructure Layer (Adapters & Frameworks)

This layer implements everything related to technical details:
- Database (JPA)
- External APIs
- File systems
- Message brokers

### Structure
- `adapter` → Implements domain repositories using JPA
- `entity` → JPA entities mapped to DB tables
- `jpa` → Spring Data JPA interfaces

### Responsibilities
- Translate Domain ↔ Entity (via common mapper)
- Implement repository interfaces (ports)
- Store and retrieve data from DB

### DO:
✔ Implement all domain repository logic  
✔ Use JPA or any persistence provider  
✔ Contain DB, cache, external API code

### DON'T:
✘ Do not put business logic here  
✘ Do not call application services  
