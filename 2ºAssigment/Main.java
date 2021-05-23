import java.util.*;
import java.lang.*;

class Main{
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
	public static void main(String[] arg){
		Scanner scan = new Scanner(System.in);
		System.out.println("1vs1");
		System.out.println("Para jogar tem que ser de um sítio para outro do tipo \"NumeroLetra NumeroLetra\"");
		System.out.println("Exemplo: \"6A 5B\"");
		Damas game = new Damas();
		while(game.complete()=='P'){
			if(game.allPossibleMoves().size()==0){
				game.x=0;
				break;
			}
			game.printBoard(game.nextToPlay);
			String s=scan.nextLine();
			String[] aux = s.split(" ");
			int previousN=Character.getNumericValue(aux[0].charAt(0));
			char previousC=aux[0].charAt(1);
			int nextN=Character.getNumericValue(aux[1].charAt(0));
			char nextC=aux[1].charAt(1);
			boolean result = game.preencher(previousN-1,charToNumber(previousC),nextN-1,charToNumber(nextC));
			if(game.complete()=='X')
				break;
			if(result){
				if(game.allPossibleMoves().size()==0){
					game.o=0;
					break;
				}
				char ch = game.nextToPlay;
				Move m=MonteCarlo.montecarlo(game,'O');
				game.preencher(m.pi,m.pj,m.ni,m.nj);
				while(game.nextToPlay==ch){
					MonteCarlo.montecarlo(game,'O');
				}
			}
		}
		System.out.println("won:"+game.complete());
	}
}
