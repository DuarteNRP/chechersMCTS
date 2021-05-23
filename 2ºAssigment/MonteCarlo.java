import java.util.*;
import java.lang.*;

public class MonteCarlo{
	private static int nnos=0;
	public static Move montecarlo(Damas d,char ch){
		ch=ch;
		int count=0;
		Node root=new Node(null,d,null);
		createChilds(root);
		while(count<50000){
			//lista dos visitados
			LinkedList<Node> visited= new LinkedList<Node>();
			Node n=root;

			//createChilds(n);
			visited.add(n);
			//se só tem um filho
			if(root.filhos.size()==1)
				return root.filhos.get(0).move;
			//Ver se varia no select
			double value=-1.0;
			//selecionar enquanto tem todos os filhos ja criados
			while(n.hasChilds()){
				//se selecionar algum que seja vitoria/empate/derrota
				n=select(n,ch);
				visited.add(n);
				if(n.d.complete()==ch){
					value= 1.0;
					break;
				}
				if(n.d.complete()==aNother(ch)){
					value= 0.0;
					break;
				}
				if(n.d.complete()=='E'){
					value= 0.5;
					break;
				}
			}
			int flag=0;
			//se o value nao variou
			//n.d.printBoard(n.d.nextToPlay);
			if(value==-1.0){
				if(n.visitas==0){
					value=rollOut(n,ch);
				}
				else{
					flag=1;
					createChilds(n);
				}
			}
			//se nao variou entao expandiu-se e agora faz se a simulaçao
			if(flag==1)
				value=rollOut(n.filhos.get(0),ch);
		//incrementar nos pais	
			for(Node k : visited){
				k.setVitorias(k.getVitorias()+value);
				k.incrVisitas();
			}
			count++;
		}
		long max=-1;
		Node node=null;
		for(Node n: root.filhos){
			//ver o que foi mais visitado
			//System.out.println("visitas="+n.getVisitas()+" vitorias="+n.getVitorias()+" UTC="+n.getUCT());
			if(max<n.getVisitas()){
				max=n.getVisitas();
				node=n;
			}
		}
			//System.out.println(max+" "+nnos);

		return node.move;
	}//selecionar no
	public static Node select(Node pai,char ch){
		double max=-1;
		Node node=null;
		for(Node curr : pai.getFilhos()){
			if(pai.d.nextToPlay==ch){
				if(curr.getUCT()==-1.0)
					return curr;
				if(max<curr.getUCT()){
					max=curr.getUCT();
					node=curr;
				}
			}
			else{
				if(curr.getUCT1()==-1.0)
					return curr;
				if(max<curr.getUCT1()){
					max=curr.getUCT1();
					node=curr;
				}
			}
		}
		return node;
	}//simulaçao
	public static double rollOut(Node n,char ch){
		Damas d=new Damas(n.d);
		//simular e ver se ja acabou
		while(true){
			//d.printBoard(d.nextToPlay);
			if(d.complete()==ch) {
				return 1.0;
			}
			if(d.complete()==aNother(ch)) {
				return 0.0;
			}
			if(d.complete()=='E') {
				return 0.5;
			}
			ArrayList<Move> moves = d.allPossibleMoves();
			int k =(int)(Math.random()*moves.size());
			Move m=moves.get(k);
			d.preencher(m.pi,m.pj,m.ni,m.nj);
		}

	}
	public static void createChilds(Node n){
		ArrayList<Move> moves= n.d.allPossibleMoves();
		for(Move m : moves){
			Damas aux = new Damas(n.d);
			aux.preencher(m.pi,m.pj,m.ni,m.nj);
			n.filhos.add(new Node(n,aux,m));
		}
	}
	public static char aNother(char ch){
		if(ch=='X')
			return 'O';
		return 'X';
	}

}
