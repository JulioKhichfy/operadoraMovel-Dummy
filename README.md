# operadora Móvel Dummy #

	Esse Módulo está configurado para servir na porta 8888, contudo pode ser alterado em application.properties.
	
	Sua responsabilidade é receber requisições do módulo servicoSMS, processar tais dados recebidos, e enviar 
	uma resposta de sucesso ou erro informando o HTTP STATUS ao módulo serviceSMS.
	
	/*************************************************************/
			responses:
 				"201": description: SMS sent 
				"500": description: Internal Server Error 
				"405": description: Validation exception 
				"404": description: Mobile User not found
				...
	/*************************************************************/


## 1: Instalacao ##

	+JAVA
 	
 	O projeto foi desenvolvido e testado com JAVA 1.8.0_131.
 	Caso tenha mais de um JAVA instalada em sua máquina, por favor
 	utilize a última versão do java8.
 	
	+ Maven
	
 	O projeto foi desenvolvido com o maven version 3.5.0
 	Certifique-se que tenha o Maven instalado e configurado corretamente. https://maven.apache.org/install.html
 	
 
## 2: Execucao ##

	# Utilizando o MAVEN #
	1- Abra um prompt/terminal e realize o clone do projeto [ git clone https://github.com/JulioKhichfy/operadoraMovel-Dummy.git ]
	2- Depois de realizado o clone do projeto em sua pasta de preferência, acesse a pasta raiz.
	3- Execute a goal 'mvn package'
	4- Acesse a pasta target gerada
	5- Execute java -jar operadora-0.0.1-SNAPSHOT.jar
	6- Para certificar que o serviço está no ar acesse http://localhost:8888/api/v1/test e verifique se exibe "Operadora Móvel Dummy - L I G A D O"
	
	# Utilizando o ECLIPSE #
	1-	Importe o projeto como "Existing Maven Project"
	2- Acesse a pasta raiz do projeto e clique em next.
	3- Procure pela Main do projeto(OperadoraMovelApplication) 
	4- Configure em 'Run Configurations'
	5- Na aba "Main" Escolha o 'Project' servicoSMS, assim como sua 'Main Class'(br.com.sms.configuracao.ServicoSMSApplication)
	6- Na aba "JRE" aproveite para verificar se a JRE está na versão 1.8
	7- Por fim clique na classe Main e execute em 'Run As' como 'Java Application'.
	8- Para certificar que o serviço está no ar acesse http://localhost:8888/api/v1/test e verifique se exibe "Operadora Móvel Dummy - L I G A D O"
	

