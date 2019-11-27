# Projeto - PRIS Software
PRS - Property Rental System

O projeto foi desenvolvido em JAVA utilizando a IDE Eclipse 2019-09 no sistema operacional Linux Fedora. 
O banco de dados é do Microsoft SQL Server, sendo que o arquivo (sql.txt) contém o script de criação das tabelas gerado pelo software Azure Data Studio. 

<h3><b> Sobre o sistema: </b></h3>

<b>Funções do menu interativo</b>
<ul>
	<li> Cadastrar novo usuário (cpf e senha)
	<li> Login de usuário (cpf e senha)
	<li> Criar novo imóvel (CEP, número do imóvel, complemento, número de quartos, vagas de estacionamento e banheiros, área em metros quadrados, preço do aluguel e descrição)
	<li> Valida entrada de CEP e retorna parte do endereço automaticamente a partir dele (API Via CEP)
	<li> Selecionar o tipo de imóvel desejado: Apartamento (taxa de condomínio e se é duplex) e Casa (número de andares)
	<li> Alterar um imóvel já existente (altera todos os atributos, exceto o id)
	<li> Exclui um imóvel do banco de dados
	<li> Função de listar imóveis (os que já estão alugados constam como indisponíveis)
	<li> Função de alugar imóvel (atribui o cpf do usuário logado ao imóvel selecionado e ele se torna indisponível)
</ul>

<b>Outros</b>
<ul> 
	<li> Teste unitário com JUnit na função ConnectionFactory, que verifica se a conexão está funcionando
	<li> Arquitetura MVC foi implementada obedecendo os diretórios do projeto (controller, view (ui) e model (DAO))
	<li> Herança implementada para diferenciar Apartamento e Casa, sendo que Imóvel (Property) é uma classe abstrata
	<li> Interface (IRepository) utilizada para fazer a conexão do controller com o model
</ul>


<b>Desenvolvedora</b>
<ul>
	<li>Rafaela Milagres Moreira
	<li>moreiramrafaela@gmail.com
	<li>https://www.linkedin.com/in/rafaela-moreira/
</ul>
