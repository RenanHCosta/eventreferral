

## Visão geral
O sistema de indicação de inscrição em eventos permite que os usuários se inscrevam em um evento e recebam um código de indicação. Os usuários ganham pontos para cada nova inscrição feita incluindo um código de indicação. Um endpoint de placar de líderes é fornecido para exibir a classificação de pontos com suporte a paginação.

## Tecnologias
- Spring Boot
- MongoDB

## Endpoints
- `POST /event/members`: Registra um novo membro no evento. É gerado um código de indicação e retornado junto com os dados do membro.
- `GET /event/members`: Lista os membros registrados no evento ordenado por pontuação. Tem suporte a paginação.

## Componentes principais
- Registro do usuário
- Geração de ID Único de indicação
- Alocação de pontos
- Endpoint do leaderboard

## Registro de usuário
Os usuários devem fornecer as seguintes informações durante o registro:

- Nome
- E-mail
- Código de indicação (opcional)

## Geração de ID Único de indicação
Um Identificador único é gerado para cada usuário, seguindo as seguintes regras:

- O identificador deve ter no mínimo 5 e no máximo 10 caracteres
- Apenas letras e números são permitidos no identificador

## Alocação de pontos
Quando um usuário se registra usando um código de indicação:

- Tanto o usuário que indicou quanto o novo usuário recebem 1 ponto cada.

## Ponto de extremidade da tabela de classificação
O ponto de extremidade da tabela de classificação retorna os seguintes dados:

- Nome de usuário
- Pontos
- Classificação

### Paginação
A paginação é tratada especificando o número e o tamanho da página na solicitação.


