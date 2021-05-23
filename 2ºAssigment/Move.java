import java.util.*;
import java.lang.*;

class Move{
	int pi;
	int pj;
	int ii;
	int ij;
	int ni;
	int nj;
	Move(int pi,int pj,int ii,int ij,int ni,int nj){
		this.pi=pi;
		this.pj=pj;
		this.ii=ii;
		this.ij=ij;
		this.ni=ni;
		this.nj=nj;
	}
	Move(int pi,int pj,int ni,int nj){
		this.pi=pi;
		this.pj=pj;
		this.ii=-1;
		this.ij=-1;
		this.ni=ni;
		this.nj=nj;
	}

	boolean compare(int pi,int pj,int ni,int nj){
		if(this.pi==pi && this.pj==pj && this.ni==ni && this.nj==nj)
			return true;
		return false;
	}
}
