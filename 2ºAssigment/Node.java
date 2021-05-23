import java.util.*;
import java.lang.*;
public class Node /*implements Comparable<No> */{
		long visitas;
		double vitorias;
		Node pai;
		ArrayList<Node> filhos;
		Damas d;
		Move move;
		public Node(Node p,Damas d,Move m){
			filhos= new ArrayList<Node>();
			visitas=0;
			vitorias=0.0;
			pai=p;
			this.d=new Damas(d);
			this.move=m;
		}
		/*public Node(Node n){
			this.visitas=n.visitas;
			this.vitorias=n.vitorias;
			this.pai=n.pai;
			for(Node n:n.filhos){
				this.filhos.add(n);
			}
			this.d=new Damas(n.d);
		}*/
		public ArrayList<Node> getFilhos(){
			return filhos;
		}
		public double getVitorias(){
			return vitorias;
		}
		public long getVisitas(){
			return visitas;
		}
		public double getUCT(){
			if(visitas==0){
				return -1.0;
			}

			return (vitorias/visitas) + 1/Math.sqrt(2) *Math.sqrt(2*Math.log(pai.getVisitas())/visitas);
		}
		public double getWinRate(){
			return (vitorias/visitas);
		}
		public double getUCT1(){
			if(visitas==0){
				return -1.0;
			}

			return ((visitas-vitorias)/visitas) + 1/Math.sqrt(2) *Math.sqrt(2*Math.log(pai.getVisitas())/visitas);
		}
		public void incrVisitas() {
			visitas++;
		}
		public void setVitorias(double v) {
			vitorias = v;
		}
		public void setPai(Node pai){
			pai=pai;
		}
		/*public boolean ifilhos(){
			for(int i=0;i<7;i++){
				if(filhos[i]!=null)
					return true;
			}
			return false;

		}*/
		public boolean hasChilds(){
			if(filhos.size()!=0)
				return true;
			return false;
		}

	}

