package Game;
public class Log {
	int LogMove[];
	int Piece;
	int empessantmove[];
	public Log(int a[],int b) {
		LogMove=a;
		Piece=b;
		empessantmove=setEmpessantmove(a,b);
		
	}
	public int[] setEmpessantmove(int move[],int piece) {
		int emp[]= new int[2];
		if((piece == -1 || piece == 1) && Math.pow(move[2]-move[0], 2) == 4) {
			if(piece == 1) {emp[0]=move[2]+1;emp[1]=move[3];	}
			if(piece == -1) {emp[0]=move[2]-1;emp[1]=move[3];	}

		}
		else {emp[0]=-1;emp[1]=-1;}
		return emp;
		
	}
}