@Regressivo
Feature: Realizar validacao dos detalhes de um anuncio veicular (@Anuncios)

  Background: Logar no app webmotors
    Given que eu possua conta no app do webmotors
    When faco login no app com "webmotors@teste.com" e "pwd12345"


  @AnuncioVeicular
  Scenario Outline:(@DetalhesAnuncioVeicular) - Validar detalhes do anuncio de um veiculo cadastrado
    And clico em quero comprar um veiculo no app webmotors
    And realizo os filtros necessario para encontrar o veiculo "<ESTADO>","<CIDADE>","<MARCA>","<MODELO>"
    And clico em ver anuncios disponiveis
    Then devo ver a lista de veiculos disponiveis
    And seleciono o veiculo do meu interesse
    And valido as informacoes detalhadas do veiculo selecionado

    Examples:
    |ESTADO|CIDADE   |MARCA  |MODELO     |
    |SP    |OSASCO   |HYUNDAI|VELOSTER   |


