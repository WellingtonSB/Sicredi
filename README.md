# Desafio Sicredi-API REST 

  
#  Objetivo

Gerenciar sessões de votação com os sequintes parametros:

- Cadastrar uma nova pauta;

- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo 
determinado na chamada de abertura ou 1 minuto por default);

- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é 
identificado por um id único e pode votar apenas uma vez por pauta);

- Contabilizar os votos e dar o resultado da votação na pauta.


# Tecnologias
    - Linguagem de programação Java(versão 8)
    - SpringFramework (SpringBoot ) →(Boot para facilitar construção da API REST)
    - Hibernate →(Para realizar o gerenciamento do Banco de Dados)
    - API REST →(Para comunicação seguindo os padrões REST "Requisição e Resposta")
    - Ecplise →(Para o código em si)
    - Postman →(Testarmos nossas requisições)
    - jUnit →(Realizão de testes unitarios)


 # Padrão de Projeto
 
-  Utilizei o padrão MVC (Model, View e Controller), não foi utilizado o "view" pois não criei até o momento o Front-End

# Funcionamento da API

- O administrador possui o controle sobre as pautas. O mesmo pode cadastrar,buscar ou deletar pautas ou associados.
 
   Cadastro do administrador:
   ![1](https://i.imgur.com/vWGD1fS.png)
   
    Login administrador:
   ![2](https://i.imgur.com/P6q8Ffg.png)
   
    Cadastro pauta:
   ![3](https://i.imgur.com/t2zzxsi.png)
   
    Votar na pauta:
   ![4](https://i.imgur.com/1FWKYcK.png)
  
# Futures
  
  -Criação do front para efetuar o deploy da aplicação, plataforma há ser utilizada será o heroku.

  
