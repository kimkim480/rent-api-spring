# Jentx - Your Java Rent API

Esta é uma versão em Java com Spring da API [Rentx](https://github.com/kimkim480/rentx)
desenvolvida com fins de aprofundar meus conhecimentos na linguagem Java e no framework Spring

## Requisitos da aplicação

* **RF** -> Requisitos Funcionais
* **RNF** -> Requisitos Não Funcionais
* **RN** -> Regra de Negócios

### Cadastro de carro

**RF**
- [ ] Deve ser possível cadastrar um novo carro.

**RN**
- [ ] O usuário responsável pelo cadastro deve ser um usuário administrador.
- [ ] Não deve ser possível cadastrar um carro com uma placa já cadastrada.
- [ ] O carro deve ser cadastrado, por padrão, com disponibilidade.

### Listagem de Carros

**RF**
- [ ] Deve ser possível listar todos os carros disponíveis.
- [ ] Deve ser possível listar todos os carros disponíveis pelo nome da categoria.
- [ ] Deve ser possível listar todos os carros disponíveis pelo nome do fabricante.
- [ ] Deve ser possível listar todos os carros disponíveis pelo modelo do carro.

**RN**
- [ ] O usuário não precisa estar logado.

### Cadastro de Categoria

**RF**
- [x] Deve ser possível cadastrar uma nova categoria.

**RN**
- [ ] O usuário responsável pelo cadastro deve ser um usuário administrador.
- [ ] Não deve ser possível cadastrar uma categoria já existente.

### Cadastro de Especificação no carro

**RF**
- [ ] Deve ser possível cadastrar uma especificação para um carro.

**RN**
- [ ] O usuário responsável pelo cadastro deve ser um usuário administrador.
- [ ] Não deve ser possível cadastrar uma especificação para um carro não cadastrado.
- [ ] Não deve ser possível cadastrar uma especificação já existente para o mesmo carro.

### Cadastro de imagens do carro

**RF**
- [ ] Deve ser possível cadastrar a imagem do carro.

**RNF**
- [ ] Utilizar o multer para upload dos arquivos.

**RN**
- [ ] O usuário responsável pelo cadastro deve ser um usuário administrador.
- [ ] O usuário deve poder cadastrar mais de uma imagem para o mesmo carro.

### Aluguel de carro

**RF**
- [ ] Deve ser possível cadastrar um aluguel.

**RN**
- [ ] O usuário deve estar logado na aplicação.
- [ ] O aluguel deve ter duração mínima de 24 horas.
- [ ] Não deve ser possível cadastrar um novo aluguel para um carro alugado.
- [ ] Não deve ser possível cadastrar um novo aluguel para um usuário com um aluguel em aberto.

### Listagem de Alugueis para usuário

**RF**
- [ ] Deve ser possível realizar a busca de todos os alugueis para o usuário.

**RN**
- [ ] O usuário deve estar logado na aplicação.

### Devolução de carro

**RF**
- [ ] Deve ser possível realizar a devolução de um carro.

**RN**
- [ ] O usuário deve estar logado na aplicação.
- [ ] Se o carro for devolvido com menos de 24 horas, deverá ser cobrado a diária completa.
- [ ] Ao realizar a devolução, o carro deverá ser liberado para outro aluguel.
- [ ] Ao realizar a devolução, o usuário deverá ser liberado para outro aluguel.
- [ ] Ao realizar a devolução,  deverá ser calculado o total do aluguel.
- [ ] Caso o horário de devolução seja superior ao horário previsto de entrega, deverá ser cobrado multa proporcional aos dias de atrsaso.
- [ ] Caso haja multa, deverá ser somado ao total do aluguel.

### Recuperar senha

**RF**
- [ ] Deve sere possível o usuário recuperar a senha informando o email.
- [ ] O usuário deve receber um email com o passo a passo para a recuperação da senha.
- [ ] O usuário deve conseguir inserior uma nova senha.

**RN**
- [ ] O usuário precisa informar uma nova senha.
- [ ] O link enviado para a recuperação deve expirar em 3 horas.