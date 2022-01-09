<h2><b> Automação Mobile do APP WebMotors </b></h2>

Esse projeto de automação mobile realizado em Java, uilizando Cucumber em BDD, escrita em gherkin e Appium, realizado no app de prod.
<br>O fluxo de automação começa realizando um login de um usuário já cadastrado (usuário fake). Em seguida na home page, ira clicar em "Quero Comprar" e ira ser direcionado para a tela de busca. Em seguida começa a filtrar as buscas por estado, cidade, marca e modelo, ao encontrar o veiculo desejado, ira validar se as informações iniciais estão sendo exibidas como preço, marca, modelo e info básicas e clica para expandir informações detalhadas e também as validas.</br>

O projeto foi estruturado para rodar em Android e IOS.

Esse projeto possui um relatorio de execução com as envidências e informações, um Dashbord com a porcentagem de cenarios executados além de um video gerado do teste.
Para acessa-las siga os passos abaixo:

![Como acessar os relatorios e evidencias](https://user-images.githubusercontent.com/66037072/148663878-22fd54d7-cce3-47a5-96cf-de71ac6a1130.jpeg)

![Relatorio](https://user-images.githubusercontent.com/66037072/148663880-8b4336e0-4acc-49e2-84de-e66c8694215e.jpeg)

Dashboard:

![WhatsApp Image 2022-01-09 at 12 23 33](https://user-images.githubusercontent.com/66037072/148688791-2ca01c00-10c9-44a8-9d5f-9c7459637ee1.jpeg)



Para rodar o projeto, existe uma classe chamada "Runner" aonde se pode rodar os testes por tags ou fluxo completo.
Exemplos abaixo:


![ClassRunner](https://user-images.githubusercontent.com/66037072/148663989-a4ed5d52-f96c-4dc7-9a61-4143bbb383e5.jpeg)
