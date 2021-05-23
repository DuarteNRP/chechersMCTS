import java.util.*;
import java.lang.*;

class Damas{
	Piece[][] board = new Piece[8][8];
	char nextToPlay='_';
	int x=0;
	int o=0;
	int count=0;
	char winner='P';
	Damas(){
		nextToPlay='X';
		x=12;
		o=12;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(i<=2){
					if((i%2==0 && j%2!=0) || (i%2!=0 && j%2==0))
						board[i][j]=new Piece('o');
					else board[i][j]=new Piece('_');
				}
				else if(i>=5){
					if((i%2==0 && j%2!=0) || (i%2!=0 && j%2==0))
						board[i][j]=new Piece('x');
					else board[i][j]=new Piece('_');
				}
				else board[i][j]=new Piece('_');
			}
		}
	}
	Damas(Damas d){
		this.nextToPlay=d.nextToPlay;
		this.x=d.x;
		this.o=d.o;
		this.count=d.count;
		this.winner = d.winner;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++)
				this.board[i][j]=new Piece(d.board[i][j].ch);
		}
	}
	char getValue(int i, int j){
		return board[i][j].ch;
	}
	char complete(){
		if(x==0){
			winner='O';
			return 'O';
		}
		if(o==0){
			winner='X';
			return'X';
		}
		if(count==20){
			winner='E';
			return 'E';
		}
		if(needToEat().size()==0 && possibleMoves().size()==0){
			tradePlayer();
			winner=nextToPlay;
			return winner;
		}
		return winner;

	}
	void printBoard(char ch){
		System.out.println("Joga "+ch+"!");
		System.out.println();
		if(ch=='X'){
			for(int i=0;i<8;i++){
				System.out.print((i+1)+" ");
				for(int j=0;j<8;j++){
					System.out.print(board[i][j].ch+" ");
				}
				System.out.println();
			}
			System.out.println("  A B C D E F G H");
		}
		else{
			for(int i=7;i>=0;i--){
				System.out.print((i+1)+" ");
				for(int j=7;j>=0;j--){
					System.out.print(board[i][j].ch+" ");
				}
				System.out.println();
			}
			System.out.println("  H G F E D C B A");
		}
		System.out.println();	
	}
	/*void inverter(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(board[i][j]=='X')
					board[i][j]='O';
				else if(board[i][j]=='O')
					board[i][j]='X';
			}
			
		}
		
	}*/
	//Falta ver a regra de ser obrigado a comer com a maior
	boolean canSee(int i,int j){
		if(i<0||i>7||j<0||j>7)
			return false;
		return true;
	}
	boolean isAnother(int i,int j,char ch){
		if(board[i][j].ch==ch)
			return true;
		return false;
	}
	int charToNumber(char ch){
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
	char intToChar(int i){
		switch(i){
			case 0:
			return 'A';
			case 1:
			return 'B';
			case 2:
			return 'C';
			case 3:
			return 'D';
			case 4:
			return 'E';
			case 5:
			return 'F';
			case 6:
			return 'G';
			case 7:
			return 'H';
		}
		return '_';
	}
	void tradePlayer(){
		if(nextToPlay=='X')
			nextToPlay='O';
		else
			nextToPlay='X';
	}
	char player(char ch){
		if(ch=='x'|| ch=='X')
			return 'X';
		if(ch=='o'|| ch=='O')
			return 'O';
		return '_';
	}
	void Xmoves(ArrayList<Move> positionsToEat){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(board[i][j].ch!='X' && board[i][j].ch!='x')
					continue;
				if(board[i][j].ch=='X'){
					if(canSee(i+1,j+1) && isAnother(i+1,j+1,'_'))
						positionsToEat.add(new Move(i,j,i+1,j+1));
					if(canSee(i+1,j-1) && isAnother(i+1,j-1,'_'))
						positionsToEat.add(new Move(i,j,i+1,j-1));
				}
				if(canSee(i-1,j-1) && isAnother(i-1,j-1,'_'))
					positionsToEat.add(new Move(i,j,i-1,j-1));
				if(canSee(i-1,j+1) && isAnother(i-1,j+1,'_'))
					positionsToEat.add(new Move(i,j,i-1,j+1));
			}
		}
	}
	void Omoves(ArrayList<Move> positionsToEat){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(board[i][j].ch!='O' && board[i][j].ch!='o')
					continue;
				if(board[i][j].ch=='O'){
					if(canSee(i-1,j-1) && isAnother(i-1,j-1,'_'))
							positionsToEat.add(new Move(i,j,i-1,j-1));
					if(canSee(i-1,j+1) && isAnother(i-1,j+1,'_'))
						positionsToEat.add(new Move(i,j,i-1,j+1));
				}
				if(canSee(i+1,j+1) && isAnother(i+1,j+1,'_'))
					positionsToEat.add(new Move(i,j,i+1,j+1));
				if(canSee(i+1,j-1) && isAnother(i+1,j-1,'_'))
					positionsToEat.add(new Move(i,j,i+1,j-1));
			}
		}
	}
	ArrayList<Move> possibleMoves(){
		ArrayList<Move> justMoves= new ArrayList<Move>();
		if(nextToPlay=='O'){
			Omoves(justMoves);

		}
		else
			Xmoves(justMoves);
		return justMoves;
	}

	boolean preencher(int pi,int pj,int ni,int nj){
		ArrayList<Move> positionsToEat=needToEat();
		if(positionsToEat.size()!=0){
			int countAux=0;
			Move move=null;
			for(Move m : positionsToEat){
				if(m.compare(pi,pj,ni,nj)){
					countAux=1;
					move=m;
					break;
				}
			}
			if(countAux==0){
				System.out.println("És obrigado a comer, por exemplo jogando : "
					+(positionsToEat.get(0).pi+1)+""+intToChar(positionsToEat.get(0).pj)+" "+
					(positionsToEat.get(0).ni+1)+""+intToChar(positionsToEat.get(0).nj));
				return false;
			}
			count=0;
			char ch = board[pi][pj].ch;
			board[pi][pj].ch='_';
			remove();
			board[move.ii][move.ij].ch='_';
			board[ni][nj].ch=queen(ch,ni,nj);
			if(ch!=board[ni][nj].ch){
				tradePlayer();
				return true;
			}
			positionsToEat = continueEating(ni,nj);
			int i=ni,j=nj;
			while(positionsToEat.size()==1){
				ch=board[i][j].ch;
				board[i][j].ch='_';
				remove();
				board[positionsToEat.get(0).ii][positionsToEat.get(0).ij].ch='_';
				board[positionsToEat.get(0).ni][positionsToEat.get(0).nj].ch=queen(ch,positionsToEat.get(0).ni,positionsToEat.get(0).nj);
				i=positionsToEat.get(0).ni;
				j=positionsToEat.get(0).nj;
				if(ch!=board[i][j].ch){
					tradePlayer();
					return true;
				}
				positionsToEat = continueEating(ni,nj);
			}
			if(positionsToEat.size()>1){
				/*System.out.println("Escolhe uma das opções que tens para comer, por exemplo: "
					+(positionsToEat.get(0).pi+1)+""+intToChar(positionsToEat.get(0).pj)+" "+
					(positionsToEat.get(0).ni+1)+""+intToChar(positionsToEat.get(0).nj));*/
				return false;
			}
			tradePlayer();
			return true;
		}
		ArrayList<Move> justMoves = possibleMoves();
		Move move=null;
		for(Move m: justMoves){
			if(m.compare(pi,pj,ni,nj))
				move=m;
		}
		if(move==null){
			System.out.println("Não podes fazer essa jogada!");
			return false;
		}
		count++;
		char ch = board[pi][pj].ch;
		board[pi][pj].ch='_';
		board[ni][nj].ch=queen(ch,ni,nj);
		tradePlayer();
		return true;
	}
	ArrayList<Move> needToEat(){
		ArrayList<Move> positionsToEat = new ArrayList<Move>();
		if(nextToPlay=='X')
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(board[i][j].ch!='x'&& board[i][j].ch!='X')
						continue;
					if(canSee(i-1,j-1) && (isAnother(i-1,j-1,'o')||isAnother(i-1,j-1,'O')))
						if(canSee(i-2,j-2) && isAnother(i-2,j-2,'_'))
							positionsToEat.add(new Move(i,j,i-1,j-1,i-2,j-2));
					if(canSee(i-1,j+1) && (isAnother(i-1,j+1,'o')||isAnother(i-1,j+1,'O')))
						if(canSee(i-2,j+2) && isAnother(i-2,j+2,'_'))
							positionsToEat.add(new Move(i,j,i-1,j+1,i-2,j+2));
					if(board[i][j].ch=='X'){
						if(canSee(i+1,j+1) && (isAnother(i+1,j+1,'o')||isAnother(i+1,j+1,'O')))
							if(canSee(i+2,j+2) && isAnother(i+2,j+2,'_'))
								positionsToEat.add(new Move(i,j,i+1,j+1,i+2,j+2));
						if(canSee(i+1,j-1) && (isAnother(i+1,j-1,'o')||isAnother(i+1,j-1,'O')))
							if(canSee(i+2,j-2) && isAnother(i+2,j-2,'_'))
								positionsToEat.add(new Move(i,j,i+1,j-1,i+2,j-2));
					}
				}
			}
		else{
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(board[i][j].ch!='O'&& board[i][j].ch!='o')
						continue;
					if(canSee(i+1,j+1) && (isAnother(i+1,j+1,'x')||isAnother(i+1,j+1,'X')))
						if(canSee(i+2,j+2) && isAnother(i+2,j+2,'_'))
							positionsToEat.add(new Move(i,j,i+1,j+1,i+2,j+2));
					if(canSee(i+1,j-1) && (isAnother(i+1,j-1,'x')||isAnother(i+1,j-1,'X')))
						if(canSee(i+2,j-2) && isAnother(i+2,j-2,'_'))
							positionsToEat.add(new Move(i,j,i+1,j-1,i+2,j-2));
					if(board[i][j].ch=='O'){
						if(canSee(i-1,j-1) && (isAnother(i-1,j-1,'x')||isAnother(i-1,j-1,'X')))
							if(canSee(i-2,j-2) && isAnother(i-2,j-2,'_'))
								positionsToEat.add(new Move(i,j,i-1,j-1,i-2,j-2));
						if(canSee(i-1,j+1) && (isAnother(i-1,j+1,'x')||isAnother(i-1,j+1,'X')))
							if(canSee(i-2,j+2) && isAnother(i-2,j+2,'_'))
								positionsToEat.add(new Move(i,j,i-1,j+1,i-2,j+2));
					}
				}
			}
		}
		return positionsToEat;
	}
	ArrayList<Move> continueEating(int i, int j){
		ArrayList<Move> positionsToEat = new ArrayList<Move>();
		if(board[i][j].ch=='x'|| board[i][j].ch=='X'){
			if(canSee(i-1,j-1) && (isAnother(i-1,j-1,'o')||isAnother(i-1,j-1,'O')))
				if(canSee(i-2,j-2) && isAnother(i-2,j-2,'_'))
					positionsToEat.add(new Move(i,j,i-1,j-1,i-2,j-2));
			if(canSee(i-1,j+1) && (isAnother(i-1,j+1,'o')||isAnother(i-1,j+1,'O')))
				if(canSee(i-2,j+2) && isAnother(i-2,j+2,'_'))
						positionsToEat.add(new Move(i,j,i-1,j+1,i-2,j+2));
			if(board[i][j].ch=='X'){
				if(canSee(i+1,j+1) && (isAnother(i+1,j+1,'o')||isAnother(i+1,j+1,'O')))
					if(canSee(i+2,j+2) && isAnother(i+2,j+2,'_'))
						positionsToEat.add(new Move(i,j,i+1,j+1,i+2,j+2));
				if(canSee(i+1,j-1) && (isAnother(i+1,j-1,'o')||isAnother(i+1,j-1,'O')))
					if(canSee(i+2,j-2) && isAnother(i+2,j-2,'_'))
						positionsToEat.add(new Move(i,j,i+1,j-1,i+2,j-2));
			}
		}
		if(board[i][j].ch=='o'|| board[i][j].ch=='O'){
			if(canSee(i+1,j+1) && (isAnother(i+1,j+1,'x')||isAnother(i+1,j+1,'X')))
				if(canSee(i+2,j+2) && isAnother(i+2,j+2,'_'))
					positionsToEat.add(new Move(i,j,i+1,j+1,i+2,j+2));
			if(canSee(i+1,j-1) && (isAnother(i+1,j-1,'x')||isAnother(i+1,j-1,'X')))
				if(canSee(i+2,j-2) && isAnother(i+2,j-2,'_'))
					positionsToEat.add(new Move(i,j,i+1,j-1,i+2,j-2));
			if(board[i][j].ch=='O'){
				if(canSee(i-1,j-1) && (isAnother(i-1,j-1,'x')||isAnother(i-1,j-1,'X')))
					if(canSee(i-2,j-2) && isAnother(i-2,j-2,'_'))
						positionsToEat.add(new Move(i,j,i-1,j-1,i-2,j-2));
				if(canSee(i-1,j+1) && (isAnother(i-1,j+1,'x')||isAnother(i-1,j+1,'X')))
					if(canSee(i-2,j+2) && isAnother(i-2,j+2,'_'))
						positionsToEat.add(new Move(i,j,i-1,j+1,i-2,j+2));
			}
		}
		return positionsToEat;
	}
	char queen(char ch,int i,int j){
		if(ch=='o' && i==7){
			count=0;
			return 'O';
		}
		if(ch=='x' && i==0){
			count=0;
			return 'X';
		}
		return ch;
	}
	void remove(){
		if(nextToPlay=='X'){
			o--;
		}
		else{
			x--;
		}
	}
	ArrayList<Move> allPossibleMoves(){
		ArrayList<Move> moves= needToEat();
		if(moves.size()!=0)
			return moves;
		return possibleMoves();
	}
}
