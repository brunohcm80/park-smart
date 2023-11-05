# PARK SMART - Estacionamento Inteligente com uso de Parquímetros

## Índice

- <a>Inicialização do Projeto</a>
- <a>Tecnologias e Ferramentas utilizadas</a>
- <a>Soluções Implementadas</a>

## Inicialização do Projeto

- É necessário ter Instalado o MongoDB ou Imagem Docker do MongoDB

- Para Instalar a Imagem Docker do MongoDB utilize:

  docker volume create VOL_MONGO

  docker network create NET_MONGO

  docker run -d --network NET_MONGO -h mongo --name mongo -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -p 27017:27017 -v VOL_MONGO:/data/db mongo

- Para conectar ao banco pelo compass:

  mongodb://mongoadmin:secret@localhost:27017/

- Caso seja utilizado o MongoDB na versão Instalada é necessário comentar/descomentar as linha abaixo no arquivo "application.properties", para que fiquem da seguinte forma:
- 
  #spring.data.mongodb.uri=mongodb://mongoadmin:secret@localhost:27017/

   spring.data.mongodb.uri=mongodb://localhost
  
- Para ver as APIs disponíveis e sua documentação acessar o Endereço "/swagger-ui/index.htm" com o Software em Execução

## Tecnologias e Ferramentas utilizadas

* Java 17 (Padrão Spring Initializr)
* Spring boot 3.1.4 (Padrão Spring Initializr)
    * Lombok (Facilitar criação de métodos acessores e construtores quando necessário)
    * Spring Web (Para usar uma API REST)
    * Open API (Habilitar Swagger) URL: **/swagger-ui/index.html*
    * Spring Data JPA (Para implementar paginação)
    * Bean Validation (Para fazer validações de campos na borda mais externa da API, as REQUESTS)
    * Spring Data MongoDB (Para acesso ao Banco de Dados NoSQL MongoDB)
* GIT (Controle de versão do projeto)
* IDE's (Eclipse, Intellij, VS Code)
* Postman (Testes da API)
* MongoDB (Persistir os dados)
* Docker (Para subir um container com MongoDB)

## Soluções Implementadas

- Fluxo de Negócio Principal (Passo-a-Passo):

1º Efetuar o Cadastramento da Tabela de Precos através da API "/precos/cadastrar"

2º Efetuar o Cadastramento do Condutor, através da api "/condutor/cadastrar"

3º Efetuar o Cadastramento da Forma de Pagamento do Cliente, através da api "/formapagamento/cadastrar"

4º Efetuar o Cadastramento do Veículo, através da api "/veiculos/cadastrar"

5º Registrar o início da Estadia do Veículo no Estacionamento/Parquímetro através da api "/estadias/iniciar"

6º Registrar o final da Estadia do Veículo no Estacionamento via api "/estadias/finalizar" 

- Padrões adotados:

  Definimos alguns métodos para conversão de objetos de uma *Request* para um *Model* e de *Model* para um *Response*, para prover segurança adicional nos dados em trânsito.
  Foram criados endpoints especificos para cada funcionalidade de cada API (Cadastrar, buscar, buscarTodos e outros).
