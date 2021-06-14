import java.util.*;
import java.lang.*;

class Main{
	public static Scanner scan = new Scanner(System.in);
	static int charToNumber(char ch){
		switch(ch){
			case 'A':
			return 0;
			case 'B':
			return 1;
			case 'C':
			return 2;
			case 'D':
			return 3;
			case 'E':
			return 4;
			case 'F':
			return 5;
			case 'G':
			return 6;
			case 'H':
			return 7;
		}
		return -1;
	}
	static char tradePlay(char play){
		if(play=='X'){
			return 'O';
		}
		return 'X';
	}
	public static void limpaTerminal() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    public static void type2(Damas game){
    	limpaTerminal();
		while(game.complete()=='P'){
			if(game.allPossibleMoves().size()==0){
				game.x=0;
				break;
			}
			System.out.println("És tu a jogar!");
			System.out.println("Para jogar tem que ser de um sítio para outro do tipo \"NumeroLetra NumeroLetra\"");
			Move move= game.allPossibleMoves().get(0);
			System.out.println("Exemplo: "+(move.pi+1)+""+game.intToChar(move.pj)+" "+(move.ni+1)+""+game.intToChar(move.nj));
			game.printBoard(game.nextToPlay);
			String s=scan.nextLine();
			int flag=1;
			while(!testInput(s)){
				if(flag!=1){
					System.out.println("Formato da jogada errado");
				}
				flag=0;
				s=scan.nextLine();
			}
			String[] aux = s.split(" ");
			if(aux[0].equals("DESISTO")){
				game.winner='O';
				break;
			}
			int previousN=Character.getNumericValue(aux[0].charAt(0));
			char previousC=Character.toUpperCase(aux[0].charAt(1));
			int nextN=Character.getNumericValue(aux[1].charAt(0));
			char nextC=Character.toUpperCase(aux[1].charAt(1));
			limpaTerminal();
			boolean result = game.preencher(previousN-1,charToNumber(previousC),nextN-1,charToNumber(nextC));
			if(game.complete()=='X')
				break;
			if(game.complete()!='P')
				break;
			if(result){
				System.out.println("A tua jogada:");
				game.printBoard(tradePlay(game.nextToPlay));
				System.out.println("O computador está a pensar...");
				if(game.allPossibleMoves().size()==0){
					game.o=0;
					break;
				}
				char ch = game.nextToPlay;
				System.out.println();
				long tempoInicial = System.currentTimeMillis();
				Move m=MonteCarlo.montecarlo(game,'O');
				int first=m.pi+1;
				char last=game.intToChar(m.pj);
				game.preencher(m.pi,m.pj,m.ni,m.nj);
				while(game.nextToPlay==ch){
					m=MonteCarlo.montecarlo(game,'O');
					game.preencher(m.pi,m.pj,m.ni,m.nj);
				}
				System.out.println("O método executou em " + ((System.currentTimeMillis() - tempoInicial)/1000.0)+" segundos");
				System.out.println("O método jogou: "+(first)+""+last+" "+(m.ni+1)+""+game.intToChar(m.nj));

			}
		}
		if(game.complete()=='O'){
			System.out.println("Ganhou o Computador!");
		}
		else if(game.complete()=='X'){
			System.out.println("Ganhaste, parabéns!");
		}
		else
			System.out.println("Empate!");
	
    }
    public static void type1(Damas game){
    	limpaTerminal();
		while(game.complete()=='P'){
			System.out.println("Joga o X!");
			game.printBoard(game.nextToPlay);
			if(game.allPossibleMoves().size()==0){
				game.x=0;
				break;
			}
			char ch = game.nextToPlay;
			long tempoInicial = System.currentTimeMillis();
			Move m=MonteCarlo.montecarlo(game,ch);
			int first=m.pi+1;
			char last=game.intToChar(m.pj);
			game.preencher(m.pi,m.pj,m.ni,m.nj);
			while(game.nextToPlay==ch){
				m=MonteCarlo.montecarlo(game,'O');
				game.preencher(m.pi,m.pj,m.ni,m.nj);
			}
			System.out.println("O método executou em " + ((System.currentTimeMillis() - tempoInicial)/1000.0)+" segundos");
			System.out.println("O método jogou: "+(first)+""+last+" "+(m.ni+1)+""+game.intToChar(m.nj));
			System.out.println();
			System.out.println("Joga o O!");
			game.printBoard(game.nextToPlay);
			if(game.allPossibleMoves().size()==0){
				game.o=0;
				break;
			}
			if(game.complete()!='P')
				break;
			ch = game.nextToPlay;
			tempoInicial = System.currentTimeMillis();
			m=MonteCarlo.montecarlo(game,ch);
			first=m.pi+1;
			last=game.intToChar(m.pj);
			game.preencher(m.pi,m.pj,m.ni,m.nj);
			while(game.nextToPlay==ch){
				m=MonteCarlo.montecarlo(game,'O');
				game.preencher(m.pi,m.pj,m.ni,m.nj);
			}
			System.out.println("O método executou em " + ((System.currentTimeMillis() - tempoInicial)/1000.0)+" segundos");
			System.out.println("O método jogou: "+(first)+""+last+" "+(m.ni+1)+""+game.intToChar(m.nj));

		}
		if(game.complete()=='O'){
			System.out.println("Ganhou o O!");
		}
		else if(game.complete()=='X'){
			System.out.println("Ganhou o X!");
		}
		else
			System.out.println("Empate");

    }
    public static boolean testInput(String s){
    	if(s.equals("DESISTO"))
    		return true;
		String[] aux = s.split(" ");
		if(aux.length!=2 || aux[0].length()!=2 || aux[1].length()!=2)
			return false;
		int previousN=Character.getNumericValue(aux[0].charAt(0));
		char previousC=Character.toUpperCase(aux[0].charAt(1));
		int nextN=Character.getNumericValue(aux[1].charAt(0));
		char nextC=Character.toUpperCase(aux[1].charAt(1));
		return true;
    }
	public static void main(String[] arg){
		Damas game = new Damas();
		System.out.println("Bem-vindo ao jogo das damas!");
		System.out.println("Escolhe um número:");
		System.out.println("(1) MonteCarlo vs MonteCarlo");
		System.out.println("(2) Player vs MonteCarlo");
		String choice = scan.next();
		while(true){
			if(choice.length()!=1){
				System.out.println("Escolhe apenas 1 ou 2!");
				choice = scan.next();
				continue;
			}
			if(Character.getNumericValue(choice.charAt(0))==1){
				type1(game);
				break;
			}
			if(Character.getNumericValue(choice.charAt(0))==2){
				type2(game);
				break;
			}
			System.out.println("Não escolheste um número válido!");
			choice = scan.next();
		}
	}
}
