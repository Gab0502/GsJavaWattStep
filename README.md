# Projeto de Gerenciamento de Energia  

Este projeto fornece uma API REST para gerenciar instalações, contratos, consumo e produção de energia, utilizando o banco de dados H2 e comandos do Gradle para execução.

---

## 🛠️ Integrantes  

- Gabriel Marquez Trevisan -- 99227
- Enricco Rossi de Souza Carvalho Miranda -- RM551717
---

---

## 🛠️ Tecnologias Utilizadas  

- **Java 17**
- **Spring Boot 3.x**
- **Gradle 7.x**
- **Banco de Dados H2**

---

## 🚀 Inicialização do Projeto  

### Pré-requisitos
- **JDK 17+**: Instale aqui
- **Gradle**: Instale aqui ou utilize o wrapper incluso no projeto (`./gradlew` no Linux/macOS ou `gradlew.bat` no Windows)

### Configuração do Banco de Dados
O projeto utiliza o banco de dados H2 em modo persistente. As configurações padrão estão no arquivo `application.properties`.

### Executando o Projeto
1. No terminal, navegue até o diretório do projeto:
   ```bash
   cd /caminho/para/seu/projeto
   ```
2. Execute o comando para iniciar o servidor:
   ```bash
   ./gradlew bootRun
   ```
3. Acesse a aplicação em: http://localhost:8080

### Acessando o Console do H2
- URL do console: http://localhost:8080/h2-console
- Credenciais:
  - **JDBC URL**: `jdbc:h2:~/energydb`
  - **Username**: `sa`
  - **Password**: `password`

---

## 🌐 Rotas Disponíveis

### Clientes

#### Criar Cliente
- **POST** `/clientes`
- Exemplo de Body:
  ```json
  {
    "nome": "João Silva",
    "endereco": "Rua A, 123",
    "documento": "12345678900",
    "tipo": "Físico",
    "cep": "12345-678"
  }
  ```

#### Listar Todos os Clientes
- **GET** `/clientes`

#### Buscar Cliente por UUID
- **GET** `/clientes/{cliente_uuid}`

#### Deletar Cliente
- **DELETE** `/clientes/{cliente_uuid}`

### Instalações

#### Criar Instalação
- **POST** `/instalacoes`
- Exemplo de Body:
  ```json
  {
    "endereco": "Rua das Flores, 41",
    "cep": "12345-678"
  }
  ```

#### Listar Todas as Instalações
- **GET** `/instalacoes`

#### Buscar Instalação por UUID
- **GET** `/instalacoes/{instalacao_uuid}`

#### Deletar Instalação
- **DELETE** `/instalacoes/{instalacao_uuid}`

### Contratos

#### Criar Contrato
- **POST** `/contratos`
- Exemplo de Body:
  ```json
  {
    "instalacao_uuid": "84b4b063-58a4-4dab-bf4f-fd13954c328c",
    "cliente_uuid": "84b4b063-58a4-4dab-bf4f-fd13954c328c",
    "timeframe": 180
  }
  ```

#### Buscar Contrato por UUID
- **GET** `/contratos/{contrato_uuid}`

#### Deletar Contrato
- **DELETE** `/contratos/{contrato_uuid}`

### Consumo

#### Registrar Consumo
- **POST** `/consumo`
- Exemplo de Body:
  ```json
  {
    "instalacao_uuid": "7da41106-5109-45f4-8d09-9ca405c33e5c",
    "consumo_kwh": 410.90,
    "medicao_timestamp": 1731284100
  }
  ```

#### Obter Consumo Mensal
- **GET** `/consumo/{instalacao_uuid}`

### Produção

#### Registrar Produção
- **POST** `/producao`
- Exemplo de Body:
  ```json
  {
    "instalacao_uuid": "7da41106-5109-45f4-8d09-9ca405c33e5c",
    "producao_kwh": 15.51,
    "medicao_timestamp": 1731370500
  }
  ```

#### Obter Produção Mensal
- **GET** `/producao/{instalacao_uuid}`
