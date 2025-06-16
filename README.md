# paymentmanagement

### **Desafio 1 - Back To Basic**

Crie uma API para gerenciar pagamentos OpenFinance. A sua API deverá conter os seguintes endpoints:  
- **POST ../payments**:  para criar o pagamento  
- **PATCH ../payments/{paymentId}: para editar o pagamento  
- **DELETE ../payments/{paymentId}: para deletar o pagamento  
- **GET ../payments/{paymentId}: para consultar o pagamento  
- **GET ../payments**: para consultar todos os pagamentos
 
Essa API poderá receber dois tipos de pagamentos - pagamento simples e pagamento automático. Abaixo atributos comuns e específicos que cada tipo de pagamento poderá ter.
 
**Pagamentos simples**:
```
{  
    "id": "275b8364-40d4-4133-b37e-c1504f623578",  
    "amount": "10.50",  
    "creationDate": 1739890337,  
    "description": "Pagamentos ocasionais",  
    "status": "ACCEPTED",  
    “personDocument”: "49349349361"
}
```
 
**Pagamento automático**: valor, data criação, descrição, status, numero documento empresa, data agendamento
```
{  
    "id": "481f0f39-6125-4a1a-91c1-5e3bd7681890",  
    "amount": "2050.50"  
    "creationDate": 1739890337,  
    "scheduledDate": 1739890350,  
    "description": "Pagamento de compras xpto",  
    "status": "SCHEDULED",  
    "businessDocumentNumber":"44000111000144"
}
```
 
Deverá ser disponibilizado toda lógica para gerenciamento de pagamentos, respeitando o padrão Rest.
 
A aplicação deverá trabalhar em cima de uma lista em tempo real de execução. Ao fechar a execução, a lista será deletada.
 
Deverá permitir que no endpoint consultivo geral que o usuário coloque filtros para consultar todos os pagamentos dado o tipo - simples ou automático.
 
Também deverá ter um range máximo de 90 dias entre data inicial e data final da consulta (baseando na data criação)
 
A aplicação deverá conter logs informativos e de erros padronizados e respostas padronizadas de erro
 
Também deverá seguir as regras abaixo de resposta:  
- 404 para recurso não encontrado  
- 422 caso não seja possível processar a requisição  
- Sempre devolver o payload abaixo para erros 
```
{ 
    "error_code": "xpto", 
    "error_description": "xpto" 
}
```

## Estratégia de Resolução 

Ferramentas: `IntelliJ`, `Insomnia`, `Java 17`, `maven 3.5`, `SpringBoot`, `H2`




 
