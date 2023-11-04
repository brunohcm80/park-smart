# PARK SMART - Estacionamento Inteligente com uso de Parquímetros

## Índice

- <a>Inicialização do Projeto</a>
- <a>Tecnologias e Ferramentas utilizadas</a>
- <a>Soluções Implementadas</a>

## Inicialização do Projeto

- É necessário ter Instalado o MongoDB ou Imagem Docker do MongoDB
- Para ver as APIs disponíveis e sua documentação acessar o Endereço "/swagger-ui/index.htm", após execução do Software

## Tecnologias e Ferramentas utilizadas

* Java 17 (Padrão Spring Initializr)
* Spring boot 3.1.0 (Padrão Spring Initializr)
    * Lombok (Facilitar criação de métodos acessores e construtores quando necessário)
    * Spring Web (Para usar uma API REST)
    * Open API (Habilitar Swagger) URL: **/swagger-ui/index.html*
    * Spring Data JPA (Para implementar paginação)
    * Bean Validation (Para fazer validações de campos na borda mais externa da API, as REQUESTS)
* GIT (Controle de versão do projeto)
* IDE's (Eclipse, Intellij, VS Code)
* Postman (Testes da API)
* MongoDB (Persistir os dados)
* Docker (Para subir um container com MongoDB)

## Soluções Implementadas

Fluxo de Negócio Principal (Passo-a-Passo):

É necessário seguir o seguinte fluxo para utilização da Solução ParkSmart:

1º Efetuar o Cadastramento da Tabela de Precos através da API "/precos/cadastrar"

2º Efetuar o Cadastramento do Condutor, através da api "/condutor/cadastrar"

3º Efetuar o Cadastramento do Meio de pagamento do Condutor, através da api "/formapagamento/cadastrar"

4º Efetuar o Cadastramento do Veículo, através da api "/veiculos/cadastrar"

5º Registrar o início da Estadia do Veículo no Parquímetro através da api "/estadias/iniciar"

6º Registrar o final da Estadia do Veículo via api "/estadias/finalizar" 
