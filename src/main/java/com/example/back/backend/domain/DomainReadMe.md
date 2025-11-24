# Domain Layer (Enterprise Business Rules)

The **core** of the application.  
This layer holds business rules and entities independent from any framework.

### Responsibilities
- Domain models (pure objects)
- Domain repository interfaces (ports)
- Business rules (if needed)

### DO:
✔ Completely framework-agnostic (no Spring, no JPA)  
✔ Define interfaces used by application services  
✔ Represent the business concepts (ex: MemberModel)

### DON'T:
✘ Do not import Spring  
✘ Do not import JPA annotations  
✘ Do not depend on infrastructure  
