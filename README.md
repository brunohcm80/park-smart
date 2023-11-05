# PARK SMART - Estacionamento Inteligente com uso de Parquímetros

## Índice

- <a>Inicialização do Projeto</a>
- <a>Tecnologias e Ferramentas utilizadas</a>
- <a>Soluções Implementadas</a>
- <a>Swagger das APIs do Projeto em Versão .yml</a>

## Inicialização do Projeto

- É necessário ter Instalado o MongoDB ou Imagem Docker do MongoDB

- Para Instalar a Imagem Docker do MongoDB utilize:

  docker volume create VOL_MONGO

  docker network create NET_MONGO

  docker run -d --network NET_MONGO -h mongo --name mongo -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -p 27017:27017 -v VOL_MONGO:/data/db mongo

- Para conectar ao banco pelo compass:

  mongodb://mongoadmin:secret@localhost:27017/

- Caso seja utilizado o MongoDB na versão Instalada é necessário comentar/descomentar as linha abaixo no arquivo "application.properties", para que fiquem da seguinte forma:
  
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

## Swagger das APIs do Projeto em Versão .yml

Caso não de para baixar o código fonte mas querira visualizar os endpoints, requests e responses,
basta abrir a URL *https://editor.swagger.io* e colar o seguinte yml: 

```yml
openapi: 3.0.1
info:
  title: OpenAPI definition
  version: v0
servers:
  - url: http://localhost:8080
    description: Generated server url
paths:
  /veiculos/atualizar/{placaVeiculo}:
    put:
      tags:
        - veiculo-controller
      description: Atualizar dados do veículo
      operationId: atualizarVeiculo
      parameters:
        - name: placaVeiculo
          in: path
          required: true
          schema:
            type: string
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/VeiculoRequest'
        required: true
      responses:
        '200':
          description: Retorna veiculo
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/VeiculoResponse'
        '400':
          description: Veiculo inexistente
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /precos/atualizar:
    put:
      tags:
        - preco-controller
      description: Atualiza uma tabela de preços existente
      operationId: atualizarTabelaPrecos
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/PrecoRequest'
        required: true
      responses:
        '200':
          description: Tabela de preços atualizada
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/PrecoResponse'
        '400':
          description: Tabela de preços inválida
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /estadias/finalizar/{codigoEstadia}:
    put:
      tags:
        - estadia-controller
      description: Finaliza uma estadia vigente
      operationId: finalizarEstadia
      parameters:
        - name: codigoEstadia
          in: path
          required: true
          schema:
            type: string
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/CobrancaRequest'
        required: true
      responses:
        '200':
          description: Estadia finalizada
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/EstadiaResponse'
        '400':
          description: Estadia inválida
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /condutor/atualizar:
    put:
      tags:
        - condutor-controller
      operationId: atualizarCondutor
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/CondutorRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/CondutorResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /veiculos/cadastrar:
    post:
      tags:
        - veiculo-controller
      description: Cadastra um novo veículo
      operationId: cadastrarVeiculo
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/VeiculoRequest'
        required: true
      responses:
        '201':
          description: Veículo cadastrado
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/VeiculoResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /precos/cadastrar:
    post:
      tags:
        - preco-controller
      description: Cadastra uma nova tabela de preços
      operationId: criarTabelaPrecos
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/PrecoRequest'
        required: true
      responses:
        '201':
          description: Tabela de preços cadastrada
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/PrecoResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /formapagamento/cadastrar:
    post:
      tags:
        - forma-pagamento-controller
      operationId: cadastrarFormaPagamento
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/FormaPagamentoRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/FormaPagamentoResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /estadias/iniciar:
    post:
      tags:
        - estadia-controller
      description: Inicia uma nova estadia para o veículo
      operationId: iniciarEstadia
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/EstadiaRequest'
        required: true
      responses:
        '201':
          description: Estadia do veículo cadastrada
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/EstadiaResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /condutor/cadastrar:
    post:
      tags:
        - condutor-controller
      operationId: cadastrarCondutor
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/CondutorRequest'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/CondutorResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /veiculos/buscarTodos:
    get:
      tags:
        - veiculo-controller
      description: Listar veículos
      operationId: listarVeiculos
      parameters:
        - name: paginacao
          in: query
          required: true
          schema:
            $ref: '#/components/schemas/Pageable'
      responses:
        '200':
          description: Retorna veiculos
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/PageVeiculoResponse'
        '400':
          description: Veiculos inexistentes
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /veiculos/buscar/{placaVeiculo}:
    get:
      tags:
        - veiculo-controller
      description: Consulta veículo
      operationId: obterVeiculoPorId
      parameters:
        - name: placaVeiculo
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Retorna veiculo
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/VeiculoResponse'
        '400':
          description: Veiculo inexistente
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /precos/consultar:
    get:
      tags:
        - preco-controller
      description: Consulta tabela de preços vigente
      operationId: consultarTabelaPrecos
      responses:
        '200':
          description: Retorna tabela de preços
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/PrecoResponse'
        '400':
          description: Tabela de preços inexistente
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /formapagamento/buscarTodos:
    get:
      tags:
        - forma-pagamento-controller
      operationId: listarTodos
      parameters:
        - name: paginacao
          in: query
          required: true
          schema:
            $ref: '#/components/schemas/Pageable'
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/PageFormaPagamentoResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /formapagamento/buscar/{codigoFormaPagamento}:
    get:
      tags:
        - forma-pagamento-controller
      operationId: detalharFormaPagamento
      parameters:
        - name: codigoFormaPagamento
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/FormaPagamentoResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /estadias/buscarTodas:
    get:
      tags:
        - estadia-controller
      description: Consulta todas as estadias
      operationId: listarEstadias
      parameters:
        - name: paginacao
          in: query
          required: true
          schema:
            $ref: '#/components/schemas/Pageable'
      responses:
        '200':
          description: Retorna estadias
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/PageEstadiaResponse'
        '400':
          description: Estadias inexistentes
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /estadias/buscar/{codigoEstadia}:
    get:
      tags:
        - estadia-controller
      description: Consulta estadia
      operationId: obterEstadiaPorCodigo
      parameters:
        - name: codigoEstadia
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Retorna estadia
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/EstadiaResponse'
        '400':
          description: Estadia inexistente
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /condutor/buscarTodosCondutores:
    get:
      tags:
        - condutor-controller
      operationId: listarCondutores
      parameters:
        - name: paginacao
          in: query
          required: true
          schema:
            $ref: '#/components/schemas/Pageable'
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/PageCondutorResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
  /condutor/buscar/{cpf}:
    get:
      tags:
        - condutor-controller
      operationId: detalharCondutores
      parameters:
        - name: cpf
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/CondutorResponse'
        '400':
          description: Bad Request
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErroDetalhadoResponse'
components:
  schemas:
    ErroDetalhadoResponse:
      type: object
      properties:
        titulo:
          type: string
        status:
          type: integer
          format: int32
        detalhes:
          type: array
          items:
            type: string
        timestamp:
          type: integer
          format: int64
    VeiculoRequest:
      required:
        - cor
        - marca
        - modelo
      type: object
      properties:
        placa:
          type: string
        marca:
          type: string
        modelo:
          type: string
        cor:
          type: string
    VeiculoResponse:
      type: object
      properties:
        placa:
          type: string
        marca:
          type: string
        modelo:
          type: string
        cor:
          type: string
    PrecoRequest:
      required:
        - precoHoraFixa
        - precoHoraVariavel
      type: object
      properties:
        precoHoraFixa:
          type: number
        precoHoraVariavel:
          type: number
    PrecoResponse:
      type: object
      properties:
        codigoTabelaPreco:
          type: string
        precoHoraFixa:
          type: number
        precoHoraVariavel:
          type: number
    CobrancaRequest:
      required:
        - meioPagamento
      type: object
      properties:
        meioPagamento:
          type: string
          enum:
            - DEBITO
            - CREDITO
            - PIX
    Cobranca:
      type: object
      properties:
        codigoCobranca:
          type: string
        modeloCobranca:
          type: string
          enum:
            - FIXO
            - VARIAVEL
        meioPagamento:
          type: string
          enum:
            - DEBITO
            - CREDITO
            - PIX
        valorCobranca:
          type: number
        recibo:
          $ref: '#/components/schemas/Recibo'
    Condutor:
      type: object
      properties:
        codigoCondutor:
          type: string
        cpf:
          type: string
        nome:
          type: string
        telefone:
          type: string
        email:
          type: string
        veiculos:
          type: array
          items:
            $ref: '#/components/schemas/Veiculo'
        endereco:
          $ref: '#/components/schemas/Endereco'
        formaPagamento:
          $ref: '#/components/schemas/FormaPagamento'
    Endereco:
      type: object
      properties:
        logradouro:
          type: string
        numero:
          type: string
        cep:
          type: string
        complemento:
          type: string
        cidade:
          type: string
        estado:
          type: string
          enum:
            - AC
            - AL
            - AP
            - AM
            - BA
            - CE
            - DF
            - ES
            - GO
            - MA
            - MT
            - MS
            - MG
            - PA
            - PB
            - PR
            - PE
            - PI
            - RJ
            - RN
            - RS
            - RO
            - RR
            - SC
            - SP
            - SE
            - TO
    EstadiaResponse:
      type: object
      properties:
        codigoEstadia:
          type: string
        condutor:
          $ref: '#/components/schemas/Condutor'
        veiculo:
          $ref: '#/components/schemas/Veiculo'
        codigoParquimetro:
          type: string
        horarioEntrada:
          type: string
          format: date-time
        horarioSaida:
          type: string
          format: date-time
        cobranca:
          $ref: '#/components/schemas/Cobranca'
        quantidadeHoras:
          type: integer
    FormaPagamento:
      type: object
      properties:
        codigoFormaPagamento:
          type: string
        tipoPagamentoPreferencial:
          type: string
          enum:
            - DEBITO
            - CREDITO
            - PIX
        numeroCartao:
          type: string
        titularCartao:
          type: string
        codSeguranca:
          type: string
        dtValidadeCartao:
          type: string
          format: date-time
        codigoPix:
          type: string
    Recibo:
      type: object
      properties:
        codigoRecibo:
          type: string
        horarioEmissaoRecibo:
          type: string
          format: date-time
        valorRecibo:
          type: number
    Veiculo:
      type: object
      properties:
        placa:
          type: string
        marca:
          type: string
        modelo:
          type: string
        cor:
          type: string
    CondutorRequest:
      required:
        - nome
      type: object
      properties:
        codigoCondutor:
          type: string
        cpf:
          type: string
        nome:
          type: string
        telefone:
          type: string
        email:
          type: string
        veiculos:
          type: array
          items:
            $ref: '#/components/schemas/Veiculo'
        endereco:
          $ref: '#/components/schemas/Endereco'
        formaPagamento:
          $ref: '#/components/schemas/FormaPagamento'
    CondutorResponse:
      type: object
      properties:
        codigoCondutor:
          type: string
        cpf:
          type: string
        nome:
          type: string
        telefone:
          type: string
        email:
          type: string
        veiculos:
          type: array
          items:
            $ref: '#/components/schemas/Veiculo'
        endereco:
          $ref: '#/components/schemas/Endereco'
        formaPagamento:
          $ref: '#/components/schemas/FormaPagamento'
    FormaPagamentoRequest:
      type: object
      properties:
        tipoPagamentoPreferencial:
          type: string
          enum:
            - DEBITO
            - CREDITO
            - PIX
        numeroCartao:
          type: string
        titularCartao:
          type: string
        codSeguranca:
          type: string
        dtValidadeCartao:
          type: string
          format: date-time
    FormaPagamentoResponse:
      type: object
      properties:
        codigoFormaPagamento:
          type: string
        tipoPagamentoPreferencial:
          type: string
          enum:
            - DEBITO
            - CREDITO
            - PIX
        numeroCartao:
          type: string
        titularCartao:
          type: string
        codSeguranca:
          type: string
        dtValidadeCartao:
          type: string
          format: date-time
        codigoPix:
          type: string
    EstadiaRequest:
      required:
        - codigoParquimetro
        - modeloCobranca
        - placaVeiculo
      type: object
      properties:
        cpfCondutor:
          type: string
        placaVeiculo:
          type: string
        codigoParquimetro:
          type: string
        modeloCobranca:
          type: string
          enum:
            - FIXO
            - VARIAVEL
        meioPagamento:
          type: string
          enum:
            - DEBITO
            - CREDITO
            - PIX
        quantidadeHoras:
          type: integer
    Pageable:
      type: object
      properties:
        page:
          minimum: 0
          type: integer
          format: int32
        size:
          minimum: 1
          type: integer
          format: int32
        sort:
          type: array
          items:
            type: string
    PageVeiculoResponse:
      type: object
      properties:
        totalPages:
          type: integer
          format: int32
        totalElements:
          type: integer
          format: int64
        size:
          type: integer
          format: int32
        content:
          type: array
          items:
            $ref: '#/components/schemas/VeiculoResponse'
        number:
          type: integer
          format: int32
        sort:
          $ref: '#/components/schemas/SortObject'
        first:
          type: boolean
        last:
          type: boolean
        numberOfElements:
          type: integer
          format: int32
        pageable:
          $ref: '#/components/schemas/PageableObject'
        empty:
          type: boolean
    PageableObject:
      type: object
      properties:
        offset:
          type: integer
          format: int64
        sort:
          $ref: '#/components/schemas/SortObject'
        pageNumber:
          type: integer
          format: int32
        pageSize:
          type: integer
          format: int32
        paged:
          type: boolean
        unpaged:
          type: boolean
    SortObject:
      type: object
      properties:
        empty:
          type: boolean
        sorted:
          type: boolean
        unsorted:
          type: boolean
    PageFormaPagamentoResponse:
      type: object
      properties:
        totalPages:
          type: integer
          format: int32
        totalElements:
          type: integer
          format: int64
        size:
          type: integer
          format: int32
        content:
          type: array
          items:
            $ref: '#/components/schemas/FormaPagamentoResponse'
        number:
          type: integer
          format: int32
        sort:
          $ref: '#/components/schemas/SortObject'
        first:
          type: boolean
        last:
          type: boolean
        numberOfElements:
          type: integer
          format: int32
        pageable:
          $ref: '#/components/schemas/PageableObject'
        empty:
          type: boolean
    PageEstadiaResponse:
      type: object
      properties:
        totalPages:
          type: integer
          format: int32
        totalElements:
          type: integer
          format: int64
        size:
          type: integer
          format: int32
        content:
          type: array
          items:
            $ref: '#/components/schemas/EstadiaResponse'
        number:
          type: integer
          format: int32
        sort:
          $ref: '#/components/schemas/SortObject'
        first:
          type: boolean
        last:
          type: boolean
        numberOfElements:
          type: integer
          format: int32
        pageable:
          $ref: '#/components/schemas/PageableObject'
        empty:
          type: boolean
    PageCondutorResponse:
      type: object
      properties:
        totalPages:
          type: integer
          format: int32
        totalElements:
          type: integer
          format: int64
        size:
          type: integer
          format: int32
        content:
          type: array
          items:
            $ref: '#/components/schemas/CondutorResponse'
        number:
          type: integer
          format: int32
        sort:
          $ref: '#/components/schemas/SortObject'
        first:
          type: boolean
        last:
          type: boolean
        numberOfElements:
          type: integer
          format: int32
        pageable:
          $ref: '#/components/schemas/PageableObject'
        empty:
          type: boolean
