package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ContaController contas = new ContaController();
		Scanner print = new Scanner (System.in);
		
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		 /*// Teste da Classe Conta Corrente
			ContaCorrente cc1 = new ContaCorrente(1, 123, 1, "José da Silva", 0.0f, 1000.0f);
			cc1.visualizar();
			cc1.sacar(12000.0f);
			cc1.visualizar();
			cc1.depositar(5000.0f);
			cc1.visualizar();
			
        // Teste da Classe Conta Poupança
			ContaPoupanca cp1 = new ContaPoupanca(2, 123, 2, "Maria dos Santos", 100000.0f, 15);
			cp1.visualizar();
	        cp1.sacar(1000.0f);
			cp1.visualizar();
			cp1.depositar(5000.0f);
			cp1.visualizar();
		*/
		
		while (true) {

			System.out.println(Cores.ANSI_PURPLE_BACKGROUND + Cores.TEXT_RED + 
					"*****************************************************");
			System.out.println("                                                     ");
			System.out.println(Cores.TEXT_WHITE_BOLD +"                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println(Cores.TEXT_RED +"*****************************************************");
			System.out.println("                                                     ");
			System.out.println(Cores.TEXT_WHITE + "            1 - Criar Conta                          ");
			System.out.println(Cores.TEXT_WHITE + "            2 - Listar todas as Contas               ");
			System.out.println(Cores.TEXT_WHITE + "            3 - Buscar Conta por Numero              ");
			System.out.println(Cores.TEXT_WHITE + "            4 - Atualizar Dados da Conta             ");
			System.out.println(Cores.TEXT_WHITE + "            5 - Apagar Conta                         ");
			System.out.println(Cores.TEXT_WHITE + "            6 - Sacar                                ");
			System.out.println(Cores.TEXT_WHITE + "            7 - Depositar                            ");
			System.out.println(Cores.TEXT_WHITE + "            8 - Transferir valores entre Contas      ");
			System.out.println(Cores.TEXT_WHITE + "            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println(Cores.TEXT_RED +"*****************************************************");
			System.out.println(Cores.TEXT_WHITE_BOLD + "Entre com a opção desejada:                          ");
			System.out.println("                                                     ");

			try {
			opcao = print.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("\nDigite valores inteiros válidos!");
				print.nextLine();
				opcao = 0;
			}
			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
                 print.close();
				System.exit(0);
			}

			switch (opcao) {
				case 1:
					System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
					System.out.println("Digite o Número da Agência: ");
					agencia = print.nextInt();
					System.out.println("Digite o nome do Titular: ");
					print.skip("\\R?");
					titular = print.nextLine();
					
					do {
						System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
						tipo = print.nextInt();
					} while(tipo < 1 && tipo > 2);
					
					System.out.println("Digite o saldo da conta(R$): ");
					saldo = print.nextFloat();
					
					switch(tipo) {
						case 1:
							System.out.println("Digite o Limite de Crédito (R$): ");
							limite = print.nextFloat();
							contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
							break;
							
						case 2:
							System.out.println("Digite o dia do aniversário da Conta: ");
							aniversario = print.nextInt();
							contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
							break;
					}					
					
					keyPress();
					break;
				case 2:
					System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
					contas.listarTodas();
					keyPress();  
					break;
				case 3:
					System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = print.nextInt();
					
					contas.procurarPorNumero(numero);
					
					keyPress();
					break;
				case 4:
					System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
					
					System.out.println("Digite o núemro da conta: ");
					numero = print.nextInt();
					
					var buscaConta = contas.buscarNaCollection(numero);
					
					if(buscaConta != null) {
						
						tipo = buscaConta.getTipo();
						
						System.out.println("Digite o número da agência: ");
						agencia = print.nextInt();
						System.out.println("Digite o Nome do Titular: ");
						print.nextLine();
						titular = print.nextLine();
						
						System.out.println("Digite o saldo da conta: ");
						saldo = print.nextFloat();
						
						switch(tipo) {
							case 1:
								System.out.println("Digite o Limite de Crédito (R$): ");
								limite = print.nextFloat();
								contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
								break;
								
							case 2:
								System.out.println("Digite o dia do aniversário da Conta: ");
								aniversario = print.nextInt();
								contas.cadastrar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
								break;
								
							default:
								System.out.println("Tipo de conta inválido!");
						}
					} else {
						System.out.println("A conta não foi encontrada!");
					}
					
					keyPress();
					break;
				case 5:
					System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = print.nextInt();
					
					contas.deletar(numero);
					
					keyPress();
					break;
				case 6:
					System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
					
					System.out.println("Digite o número da conta: ");
					
					numero = print.nextInt();
					
					do {
						System.out.println("Digite o valor do Saque (R$):");
						valor = print.nextFloat();
					} while(valor <= 0);
					
					contas.sacar(numero, valor);
					
					keyPress();
					break;
				case 7:
					System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = print.nextInt();
					
					do {
						System.out.println("Digite o valor do depósito (R$): ");
						valor = print.nextFloat();
						
					} while(valor <=0);
					
					contas.depositar(numero, valor);
					
					keyPress();
					break;
				case 8:
					System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
					
					System.out.println("Digite o número da conta de origem: ");
					numero = print.nextInt();
					
					System.out.println("Digite o numero da conta de destino: ");
					numeroDestino = print.nextInt();
					
					do {
						System.out.println("Digite o valor da transferência (R$): ");
						valor = print.nextFloat();
					} while (valor <=0);
					
					contas.tranferir(numero, numeroDestino, valor);
					
					keyPress();
					break;
				default:
					System.out.println(Cores.TEXT_WHITE + "\nOpção Inválida!\n");
					keyPress();
					break;
			}
		}
	}
    
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: RAyanne Dias");
		System.out.println("Generation Brasil - generation@generation.org");
		System.out.println("github.com/conteudoGeneration");
		System.out.println("*********************************************************");
	}
	
	public static void keyPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}
}