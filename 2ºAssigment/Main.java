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
	public static void limpaTerminal() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
	public static void main(String[] arg){
		Scanner scan = new Scanner(System.in);
		Damas game = new Damas();
		game.board[0][0].ch='_';
		game.board[0][1].ch='_';
		game.board[0][2].ch='_';
		game.board[0][3].ch='_';
		game.board[0][4].ch='_';
		game.board[0][5].ch='_';
		game.board[0][6].ch='_';
		game.board[0][7].ch='_';
		game.board[1][0].ch='_';
		game.board[1][1].ch='_';
		game.board[1][2].ch='_';
		game.board[1][3].ch='_';
		game.board[1][4].ch='_';
		game.board[1][5].ch='_';
		game.board[1][6].ch='_';
		game.board[1][7].ch='_';
		game.board[2][0].ch='_';
		game.board[2][1].ch='_';
		game.board[2][2].ch='_';
		game.board[2][3].ch='o';
		game.board[2][4].ch='_';
		game.board[2][5].ch='_';
		game.board[2][6].ch='_';
		game.board[2][7].ch='_';
		game.board[3][0].ch='_';
		game.board[3][1].ch='_';
		game.board[3][2].ch='_';
		game.board[3][3].ch='_';
		game.board[3][4].ch='_';
		game.board[3][5].ch='_';
		game.board[3][6].ch='_';
		game.board[3][7].ch='_';
		game.board[4][0].ch='_';
		game.board[4][1].ch='x';
		game.board[4][2].ch='_';
		game.board[4][3].ch='_';
		game.board[4][4].ch='_';
		game.board[4][5].ch='_';
		game.board[4][6].ch='_';
		game.board[4][7].ch='_';
		game.board[5][0].ch='_';
		game.board[5][1].ch='_';
		game.board[5][2].ch='_';
		game.board[5][3].ch='_';
		game.board[5][4].ch='_';
		game.board[5][5].ch='_';
		game.board[5][6].ch='_';
		game.board[5][7].ch='_';
		game.board[6][0].ch='_';
		game.board[6][1].ch='_';
		game.board[6][2].ch='_';
		game.board[6][3].ch='_';
		game.board[6][4].ch='_';
		game.board[6][5].ch='_';
		game.board[6][6].ch='_';
		game.board[6][7].ch='_';
		game.board[7][0].ch='_';
		game.board[7][1].ch='_';
		game.board[7][2].ch='_';
		game.board[7][3].ch='_';
		game.board[7][4].ch='_';
		game.board[7][5].ch='_';
		game.board[7][6].ch='_';
		game.board[7][7].ch='_';
		limpaTerminal();
		while(game.complete()=='P'){
			if(game.allPossibleMoves().size()==0){
				game.x=0;
				break;
			}
			limpaTerminal();
			System.out.println("Para jogar tem que ser de um sítio para outro do tipo \"NumeroLetra NumeroLetra\"");
			Move move= game.allPossibleMoves().get(0);
			System.out.println("Exemplo: "+(move.pi+1)+""+game.intToChar(move.pj)+" "+(move.ni+1)+""+game.intToChar(move.nj));
			game.printBoard(game.nextToPlay);
			String s=scan.nextLine();
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
			if(result){
				if(game.allPossibleMoves().size()==0){
					game.o=0;
					break;
				}
				char ch = game.nextToPlay;
				Move m=MonteCarlo.montecarlo(game,'O');
				game.preencher(m.pi,m.pj,m.ni,m.nj);
				while(game.nextToPlay==ch){
					m=MonteCarlo.montecarlo(game,'O');
					game.preencher(m.pi,m.pj,m.ni,m.nj);
				}

			}
		}
		if(game.complete()=='O'){
			System.out.println("AHAHAHAHA LOSER!!!");
		}
		else{
			System.out.println("won:"+game.complete());
		}
	}
}
