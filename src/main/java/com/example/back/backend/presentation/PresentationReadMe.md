# Presentation Layer (API / Controller)

This layer handles communication with the outside world:
- REST API controllers
- Request validation
- Response formatting

### Responsibilities
- Receive HTTP requests
- Map request → DTO
- Call application service
- Return the response DTO

### DO:
✔ Use Spring MVC annotations  
✔ Validate incoming request fields  
✔ Convert API request to InputDTO

### DON'T:
✘ Do not contain business rules  
✘ Do not call infrastructure directly  
✘ Do not manipulate domain entities  
