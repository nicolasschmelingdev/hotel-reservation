# Hotel Reservation System

## Descrição

Este projeto é um sistema de reserva de hotéis desenvolvido para lidar com um grande volume de acessos simultâneos.
Ele permite aos usuários pesquisar, comparar e reservar quartos de hotel de forma eficiente, além
de enviar notificações para confirmar o processamento de check-in/check-out.

## Funcionalidades

* Pesquisa de Hotéis: Permite que os usuários pesquisem hotéis com base em critérios como destino, datas de check-in e
  check-out, número de quartos e número de hóspedes.
* Comparação de Opções: Os usuários podem comparar diferentes opções de hotéis com base em preço, localização,
  comodidades e avaliações de outros usuários.
* Reserva de Quartos: Os usuários podem selecionar um hotel e reservar quartos, inserindo informações como nome, contato
  e detalhes de pagamento.
* Sistema de Notificações: Envia notificações para confirmar o status da reserva usando Kafka.

## Tecnologias Utilizadas

* Java 21
* Spring Boot 3.3.3
* PostgreSQL: Escolhido pela facilidade de configuração com Docker Compose.
* Liquibase: Utilizado para migração e versionamento do banco de dados.
* Kafka: Utilizado para gerenciamento de mensagens e notificações.
* QueryDSL: Usado para construir consultas tipadas de forma segura e eficiente.
* kaczmarzyk: Biblioteca utilizada para criação de consultas dinâmicas de forma simples.
* Docker Compose: Utilizado para orquestração de containers, facilitando o setup do ambiente.
* Swagger: Documentação da API.
* Clean Architecture e DDD: Padrões de arquitetura aplicados para garantir a separação de responsabilidades e
  escalabilidade do sistema.

## Como Executar

1. `git clone https://github.com/nicolasschmelingdev/hotel-reservation.git`
2. `cd hotel-reservation`
3. `sudo docker-compose up`

#### A API estará disponível em http://localhost:8989 e a documentação do Swagger em

`http://localhost:8989/swagger-ui/index.html#/`

## Testes

### Foram implementados testes unitários e de integração para validar o funcionamento dos principais endpoints.

#### Os seguintes testes foram realizados:

* `searchHotels_ShouldReturnListOfHotels_WhenValidInput`: Testa a pesquisa de hotéis com base em critérios válidos.
* `createReservation_ShouldReturnCreated_WhenValidInput`: Testa a criação de uma reserva válida.
* `createReview_ShouldReturnCreated_WhenValidInput`: Testa a criação de uma avaliação de hotel.

### Para Executar os Testes

#### Utilize o Maven para rodar os testes: `mvn test`

## Melhorias Futuras

* Implementação de `Prometheus` e `Grafana` para monitoramento e observabilidade do sistema.
* Uso do `Dozzle` para acompanhamento dos logs em tempo real em produção.
* Implementação de um sistema de envio de e-mails usando uma fila `Redis` para notificar os usuários.
* `Kubernetes` para orquestrar e escalar a aplicação de forma automática.
* Criação de uma interface de frontend em `React.js` para consumo da API.
* Integração com o `Keycloak` para autenticação e autorização.

## Deploy

O deploy da aplicação foi pensado para ser realizado em ambientes Docker e pode ser facilmente estendido para ambientes
como AWS, utilizando serviços como ECS ou EKS para escalar automaticamente.