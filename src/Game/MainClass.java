package Game;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
public class MainClass implements MouseListener 	 {
	static String Turn = "White";
	boolean clicked=false;
	
	static int move[] = new int[4];
	static int board[][]=new int[8][8];
	Thread thread;
	Window mainframe;
	static int Count=0;
	ArrayList<Log> log = new ArrayList<Log>();
	public static void main(String args[]) throws IOException {
		
		for (int i=0;i<4;i++) {move[i]=-1;}
		for (int i = 0 ; i<8 ;i++) { for (int j = 0 ;j<8;j++) {board[i][j]=0;}}
		board[0][0]=-5;board[0][1]=-3;board[0][2]=-4;board[0][3]=-9;board[0][4]=-10;board[0][5]=-4;board[0][6]=-3;board[0][7]=-5;
		for (int i =0; i<8;i++) {board[1][i]=-1;board[6][i]=1;board[7][i]=board[0][i]*-1;}
		
		MainClass clas= new MainClass();
		
	}
	public MainClass() {
		
		mainframe = new Window();
		mainframe.setLocation(700,40);
		mainframe.setResizable(false);
		mainframe.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		
		mainframe.setVisible(true);
		mainframe.addMouseListener(this);
		mainframe.DrawPieces(board);
		
		
		thread= new Thread(){
			public void run() {
		
			while(true) {
			
			}
				
				}
				};
			
			}		
		
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
	
		if(move[0]==-1) {
			move[0]=(int)(y-35)/107;
			move[1]=(int)(x-5)/107;
			System.out.println("First Click");
			System.out.println(board[move[0]][move[1]]);
		}
		else if(move[2]==-1 && board[move[0]][move[1]] != 0) {
			
			System.out.println("Second Click");
			if((Turn == "White" && board[move[0]][move[1]] >0) || (Turn=="Black" && board[move[0]][move[1]]<0)) {
			move[2]=(int)(y-35)/107;
			move[3]=(int)(x-5)/107;
	
			int Check[]=new int [2];
			
			if(IllegalMove(board,move,Turn) == true) {
				int mymove[] = new int[4];
				for(int i=0;i<4;i++) {mymove[i]=move[i];}
				int piece = board[move[0]][move[1]];
				log.add(new Log(mymove,piece));
				if(log.size() > 1 ) {
					if(log.get(log.size()-2).empessantmove[0] == move[2] && log.get(log.size()-2).empessantmove[1] == move[3] ) {
					board[log.get(log.size()-2).LogMove[2]][log.get(log.size()-2).LogMove[3]]=0;
					} 
				}
				if((piece == 10 || piece == -10) && move[3]-move[1] > 1 ) {//right
					board[move[0]][move[1]+2]=piece;
					board[move[0]][move[1]+1]=piece/2;
					board[move[0]][7]=0;
					board[move[0]][move[1]]=0;
				}
				else if((piece == 10 || piece == -10)&& move[3]-move[1]<-1) {//left
					board[move[0]][move[1]-2] = piece;
					board[move[0]][move[1]-1]=piece/2;
					board[move[0]][0]=0;
					board[move[0]][move[1]]=0;
				}
				
				else {
					board[log.get(log.size()-1).LogMove[0]][log.get(log.size()-1).LogMove[1]]=0;
					board[move[2]][move[3]]=log.get(log.size()-1).Piece;
				}
				if(board[move[2]][move[3]] == 1 && move[2] == 0) {board[move[2]][move[3]]=9;}//Quuening
				if(board[move[2]][move[3]] == -1 && move[2] == 7) {board[move[2]][move[3]]=-9;}//Quuening
				mainframe.DrawPieces(board);
				Turn = (Turn=="White")? "Black":"White";
			}
			if(CheckTie()) {System.out.println("Tie!!");System.exit(0);}
			Check=KingCheck(board);
			if(Check[0] != 0 ||Check[1] != 0) {
				System.out.println("Check");
				System.out.println(CheckMate(board,Turn));
				if(CheckMate(board,Turn)) {
					System.exit(0);
					System.out.println("End of the game!");
				}
			}
			
			}
		
		
			for(int i=0;i<4;i++) {move[i]=-1;}
		
		}
		else { 
			
			for(int i=0;i<4;i++) {move[i]=-1;}
		}
		
		}

	
	public  boolean IllegalMove(int Mirrorboard[][], int Move[],String anyturn) {
		int Boarda[][]= new int[8][8];
		int Check []= new int[2];
		String turn = anyturn;
		Check[0]=0;Check[1]=0;
		for(int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				Boarda[i][j]=Mirrorboard[i][j];
			}
		}
		int movement[] = new int[4];
		movement=Move;
		if(Moves(Boarda,movement)) {
				Boarda[movement[2]][movement[3]] = Boarda[movement[0]][movement[1]];
				Boarda[movement[0]][movement[1]]=0;
				turn = (turn=="White")? "Black":"White";
				Check=KingCheck(Boarda);
				if(Check[0] == 0 && Check[1] == 0 || (turn == "White" && Check[0]==0 || (turn == "Black" &&Check[1]==0))){//(turn == "Black" && Check[0]== 0 )||(turn == "White" && Check[1]==0) ) {
					return true;
				}
		}
		return false;
		}
	
	
	
	
	
	
	
	
	
	
	
	public boolean CheckMate(int Mirrorboard[][],String turn) {
		int Board[][]= new int[8][8];
		for(int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				Board[i][j]=Mirrorboard[i][j];
			}
		}
		int move[] = new int[2];
		int nextmove[] = new int [4];
		if(turn == "White") {
			
			Stack<Integer> positions = new Stack<Integer>();
			for(int i = 0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(Board[i][j]  > 0 ) {
						positions.push(i);
						positions.push(j);
					}
				}
			}
			while(!positions.isEmpty()) {
				move[1]= positions.pop();
				move[0]= positions.pop();
				for(int i = 0;i<8;i++) {
					for(int j=0;j<8;j++) {
						nextmove[0]=move[0];nextmove[1]=move[1];
						nextmove[2]=i;nextmove[3]=j;
						if(nextmove[2] != nextmove[0] || nextmove[3] != nextmove[1]) {
							if(IllegalMove(Board,nextmove,turn)) {
								
								return false;
							}
						}
					}
				}
			}
			return true;
		}
		else {
			Stack<Integer> positions = new Stack<Integer>();
			for(int i = 0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(Board[i][j]  < 0 ) {
						positions.push(i);
						positions.push(j);
					}
				}
			}
			while(!positions.isEmpty()) {
				move[1]= positions.pop();
				move[0]= positions.pop();
				for(int i = 0;i<8;i++) {
					for(int j=0;j<8;j++) {
						nextmove[0]=move[0];nextmove[1]=move[1];
						nextmove[2]=i;nextmove[3]=j;
						if(IllegalMove(Board,nextmove,turn)) {
							return false;
						}
					}
				}
			}
			return true;
		}
	}	
		
		
		
	
	
	
	public int[] KingCheck(int MirrorBoard[][]) {
		int anyBoard[][]=new int[8][8];
		int Check[]= new int [2];
		Check[0]=0;Check[1]=0;
		for(int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				anyBoard[i][j]=MirrorBoard[i][j];
			}
		}
		int BKingposition[]= new int [2];
		int BKnightposition[]= new int[4];
		int WKnightposition[]=new int[4];
		int WKingposition[]=new int [2];
		BKnightposition[0]= -1;BKnightposition[2]=-1;
		WKnightposition[0]=-1;WKnightposition[2]=-1;
		for (int i = 0 ; i < 8 ;i++) {	for (int j=0;j<8;j++) {
			if(anyBoard[i][j] == 10) {WKingposition[0]=i;WKingposition[1]=j; }	
			if(anyBoard[i][j] == -10) {BKingposition[0]=i;BKingposition[1]=j;}
			if(anyBoard[i][j] == -3) {if(BKnightposition[0] != -1) {BKnightposition[0]=i;BKnightposition[1]=j;}else {BKnightposition[2]=i;BKnightposition[3]=j;}}
			if(anyBoard[i][j] == 3) {if(WKnightposition[0] != -1) {WKnightposition[0]=i;WKnightposition[1]=j;}else {WKnightposition[2]=i;WKnightposition[3]=j;}}
			}	
		}//Find White kings position
		int Wforward=WKingposition[0],Wbackward=WKingposition[0],Wright=WKingposition[1],Wleft=WKingposition[1];
		int Bforward = BKingposition[0],Bbackward = BKingposition[0],Bright= BKingposition[1],Bleft=BKingposition[1];
		int Wflags[]=new int[8];
		int Bflags[] = new int[8];
		for (int i =0; i <8;i++) {Wflags[i]=0;Bflags[i]= 0;}
		while(true) {
				if(Wforward <7)
					Wforward++;
				if(Wbackward>0)
					Wbackward--;
				if(Wright<7)
					Wright++;
				if(Wleft>0)
					Wleft--;
				
				
				if(Bforward <7)
					Bforward++;
				if(Bbackward > 0)
					Bbackward--;
				if(Bright < 7)
					Bright++;
				if(Bleft >0)
					Bleft--;
				
				//White King Check
				if(anyBoard[Wforward][WKingposition[1]] != 0 && Wflags[0] == 0) { Wflags[0]=anyBoard[Wforward][WKingposition[1]]; }
				if(anyBoard[Wbackward][WKingposition[1]] != 0 && Wflags[1] == 0) {Wflags[1] = anyBoard[Wbackward][WKingposition[1]];	}
				if(anyBoard[WKingposition[0]][Wright] != 0 && Wflags[2] == 0) {Wflags[2] = anyBoard[WKingposition[0]][Wright]; }
				if(anyBoard[WKingposition[0]][Wleft] !=0 && Wflags[3] == 0 ) {Wflags[3] = anyBoard[WKingposition[0]][Wleft]; }
				if((Math.pow(Wforward-WKingposition[0],2) == Math.pow(Wright-WKingposition[1], 2)) && anyBoard[Wforward][Wright] != 0 && Wflags[4]==0) {Wflags[4]=anyBoard[Wforward][Wright];}
				if((Math.pow(Wforward-WKingposition[0],2) == Math.pow(Wleft-WKingposition[1], 2)) && anyBoard[Wforward][Wleft] != 0 && Wflags[5]== 0 ) {Wflags[5]=anyBoard[Wforward][Wleft];}
				if((Math.pow(Wbackward-WKingposition[0],2) == Math.pow(Wright-WKingposition[1], 2)) && anyBoard[Wbackward][Wright] !=0 && Wflags[6] ==0) {Wflags[6] = anyBoard[Wbackward][Wright];}
				if((Math.pow(Wbackward-WKingposition[0],2) == Math.pow(Wleft-WKingposition[1], 2)) && anyBoard[Wbackward][Wleft] != 0 && Wflags[7] == 0) {Wflags[7]= anyBoard[Wbackward][Wleft];}
				
				
				//Black KingCheck
				if(anyBoard[Bforward][BKingposition[1]] != 0 && Bflags[0] == 0) {Bflags[0] = anyBoard[Bforward][BKingposition[1]];}
				if(anyBoard[Bbackward][BKingposition[1]] != 0 && Bflags[0] == 0) {Bflags[1] = anyBoard[Bbackward][BKingposition[1]];}
				if(anyBoard[BKingposition[0]][Bright] !=0 && Bflags[3] == 0) {Bflags[3] = anyBoard[BKingposition[0]][Bleft];}
				if((Math.pow(Bforward-BKingposition[0],2) == Math.pow(Bright-BKingposition[1], 2)) && anyBoard[Bforward][Bright] != 0 && Bflags[4]==0) {Bflags[4]=anyBoard[Bforward][Bright];}
				if((Math.pow(Bforward-BKingposition[0],2) == Math.pow(Bleft-BKingposition[1], 2)) && anyBoard[Bforward][Bleft] != 0 && Bflags[5]== 0 ) {Bflags[5]=anyBoard[Bforward][Bleft];}
				if((Math.pow(Bbackward-BKingposition[0],2) == Math.pow(Bright-BKingposition[1], 2)) && anyBoard[Bbackward][Bright] !=0 && Bflags[6] ==0) {Bflags[6] = anyBoard[Bbackward][Bright];}
				if((Math.pow(Bbackward-BKingposition[0],2) == Math.pow(Bleft-BKingposition[1], 2)) && anyBoard[Bbackward][Bleft] != 0 && Bflags[7] == 0) {Bflags[7]= anyBoard[Bbackward][Bleft];}
				
				if(Wforward== 7 && Wbackward ==0 && Wright == 7 && Wleft == 0 && Bforward== 7 && Bbackward ==0 && Bright == 7 && Bleft == 0) {break;}
		}
		
		if(Wflags[0]==-5 || Wflags[0] ==-9 || Wflags[1]==-5 || Wflags[1] == -9 || Wflags[2]==-5 || Wflags[2] == -9 || Wflags[3] == -5 || Wflags[3] == -9 ||  Wflags[4] == -4 || Wflags[4] == -9||  Wflags[5] == -4 || Wflags[5] == -9 || Wflags[6] == -4 || Wflags[6] == -9||Wflags[7] == -4 || Wflags[7] == -9  )
				{Check[1]=1;}
		if(WKingposition != BKingposition && Math.pow(WKingposition[0]-BKingposition[0], 2) +Math.pow(WKingposition[1]-BKingposition[1], 2) < 3 ) {Check[0]=1;Check[1]=1;}
		if((BKnightposition[0] != -1 && (Math.pow(WKingposition[0]-BKnightposition[0], 2) + Math.pow(WKingposition[1]-BKnightposition[1], 2) == 5))||(BKnightposition[2] != -1 && (Math.pow(WKingposition[0]-BKnightposition[2], 2) + Math.pow(WKingposition[1]-BKnightposition[3], 2) == 5)) ) 
				{Check[1]=1;}
		if (Wflags[6] == -1)
			{if( anyBoard[WKingposition[0]-1][WKingposition[1]+1]==-1) {
			Check[1]=1;}
			} 
		if(Wflags[7] == -1) {
			if(anyBoard[WKingposition[0]-1][WKingposition[1]-1]==-1) {Check[1]=1;}
		}
		if((WKnightposition[0] != -1 && (Math.pow(BKingposition[0]-WKnightposition[0], 2) + Math.pow(BKingposition[1]-WKnightposition[1], 2) == 5))||(WKnightposition[2] != -1 && (Math.pow(BKingposition[0]-WKnightposition[2], 2) + Math.pow(BKingposition[1]-WKnightposition[3], 2) == 5)) ) 
				{Check[0]=1;}
		if(Bflags[0]==5 || Bflags[0] ==9 || Bflags[1]==5 || Bflags[1] == 9 || Bflags[2]==5 || Bflags[2] == 9 || Bflags[3] == 5 || Bflags[3] == 9 ||  Bflags[4] == 4 || Bflags[4] == 9||  Bflags[5] == 4 || Bflags[5] == 9 || Bflags[6] == 4 || Bflags[6] == 9||Bflags[7] == 4 || Bflags[7] == 9  )
			{Check[0]=1;}
		if (Bflags[4] == 1  ){
			if( anyBoard[BKingposition[0]+1][BKingposition[1]+1]==1 ) {
				Check[0]=1;}
			}
		if( Bflags[5] == 1) {
			if( anyBoard[BKingposition[0]+1][BKingposition[1]-1]==1) {
				Check[0]=1;
		}}
		return Check;
			}
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean Moves(int anyBoard[][],int anymove[]) {
		
		int currentpiece,locatedpiece;
		
		currentpiece = anyBoard[anymove[0]][anymove[1]] ;
		locatedpiece = anyBoard[anymove[2]][anymove[3]];
		if(anyBoard[anymove[0]][anymove[1]] > 0) {
			WPieces pieces[] = WPieces.values();
			WPieces piece = null;
			for (WPieces p : pieces) {
				if(p.getValue() == currentpiece) {
					piece = p;
				}
			}
			switch(piece) {
				case Pawn://White
					if( ((anymove[2]-anymove[0] == -2  ||anymove[2]-anymove[0] == -1 )||(anymove[1]-1 == anymove[3] || anymove[1]+1 == anymove[3]) && anymove[2]-anymove[0] == -1)&& anymove[2] < anymove[0] ) {//All legal Pawn moves
						
							if(anymove[0]-anymove[2] == 2 && anymove[0] == 6 && anyBoard[anymove[2]+1][anymove[3]] == 0 && anymove[3] == anymove[1]) {//First pawn Move //2squares
								if(locatedpiece == 0) { return true;}
							}
							else if(log.size()>0 && anymove[2]-anymove[0] == -1){if(locatedpiece < 0 &&( anymove[1]-1 == anymove[3] || anymove[1]+1==anymove[3])/*En Passant*/ || (anymove[2]== log.get(log.size()-1).empessantmove[0] && log.get(log.size()-1).empessantmove[1] == anymove[3])) {return true;}}//Diagonal moves
							if(locatedpiece == 0 && anymove[3] == anymove[1] && anymove[0]-anymove[2] == 1) {return true; }//Forward Pawn Move
													// }
					}
					break;
				case Rook:
					if((anymove[0]==anymove[2] && anymove[3]-anymove[1] <8 && anymove[3] !=anymove[1])/*Horizontal move*/ || (anymove[1] == anymove[3] && anymove[2]-anymove[0] <8 && anymove[2] != anymove[0])/*Vertical move*/) {
							if(anymove[0]==anymove[2] && anymove[3]-anymove[1] <8) {//horizantal right
								if(anymove[3]-anymove[1] >0) {
									for (int i=anymove[1]+1;i< anymove[3]+1;i++) {
										if(i==anymove[3] && anyBoard[anymove[2]][i] <= 0) {
											return true;
										}
										if(anyBoard[anymove[2]][i] != 0) {
											break;
										}
									}	
								}
								if(anymove[3]-anymove[1] < 0 ) {//Horizantal left
									for (int i =anymove[1]-1;i>anymove[3]-1;i--) {
										if(i==anymove[3] && anyBoard[anymove[2]][anymove[3]] <= 0) {
											return true;
										}
										if(anyBoard[anymove[2]][i] != 0 ) {
											break;
										}
									}
								}
							}
							if(anymove[1] == anymove[3] && anymove[2] -anymove[0] <8 ) {
								if(anymove[2]-anymove[0] >0) {//Vertical down
									for (int i = anymove[0]+1;i <anymove[2]+1 ; i++) {
										if(i == anymove[2] && anyBoard[anymove[2]][anymove[3]] <=0 ) {
											return true;
										}
										if(anyBoard[i][anymove[3]] !=0) {
											break;
										}
									}
								}
								if(anymove[2] - anymove[0] < 0) {//Vertical Up
									for (int i  = anymove[0]-1;i>anymove[2]-1 ; i--) {
										if(i == anymove[2] && anyBoard[anymove[2]][anymove[3]] <=0 ) {
											return true;
										}
										if(anyBoard[i][anymove[3]] !=0) {
											break;	
									}		
							}
					}		
					}
					}
					break;
				case Knight:
		/*(c-a)^2+(d-b)^2=5*/if(((anymove[2]-anymove[0] == 2 || anymove[2]-anymove[0] == -2 ) &&( anymove[3] - anymove[1] ==1 || anymove[3]-anymove[1] == -1)) || ((anymove[3]-anymove[1] == -2 ||anymove[3]-anymove[1] == 2) && (anymove[2]-anymove[0]==1 || anymove[2]-anymove[0] == -1)))  {
							 	if(locatedpiece <= 0) {
							 			return true;
							 	}
					}
				break;
				case Bishop:
					if((Math.pow(anymove[2]-anymove[0], 2)== Math.pow(anymove[3]-anymove[1], 2))&& anymove[2]-anymove[0] != 0  ) {
						int pos1 = anymove[0],pos2=anymove[1];
						System.out.println(pos1+" "+pos2);
						while(pos1 != anymove[2] && pos2 != anymove[3]) {
							if(anymove[2] - anymove[0]  == anymove[3] - anymove[1] && anymove[2] - anymove[0] >0) {
								pos1++;pos2++;
							}
							else if(anymove[2] - anymove[0]  == anymove[3] - anymove[1] && anymove[2] - anymove[0] <0) {
								pos1--;pos2--;
							}
							else if(anymove[2] - anymove[0]  == (anymove[3] - anymove[1])*(-1) &&anymove[2]-anymove[0]>0) {

								pos1++; pos2--;
							}
							else if(anymove[2] - anymove[0]  == (anymove[3] - anymove[1])*(-1) &&anymove[2]-anymove[0]<0) {

								pos1--;	pos2++;
							}
							else {break;}
							if(anyBoard[pos1][pos2] <= 0 && pos1 == anymove[2]) {
								return true;
							}
							if(anyBoard[pos1][pos2] != 0) {
								break;
							}
						}
						}
					break;
				case Queen:
					if((anymove[0]==anymove[2] && anymove[3]-anymove[1] <8 && anymove[3] !=anymove[1])/*Horizontal move*/ || (anymove[1] == anymove[3] && anymove[2]-anymove[0] <8 && anymove[2] != anymove[0])/*Vertical move*/) {
							if(anymove[0]==anymove[2] && anymove[3]-anymove[1] <8) {//horizantal right
								if(anymove[3]-anymove[1] >0) {
									for (int i=anymove[1]+1;i< anymove[3]+1;i++) {
										if(i==anymove[3] && anyBoard[anymove[2]][i] <= 0) {
											return true;
										}
										if(anyBoard[anymove[2]][i] != 0) {
											break;
										}
									}	
								}
								if(anymove[3]-anymove[1] < 0 ) {//Horizantal left
									for (int i =anymove[1]-1;i>anymove[3]-1;i--) {
										if(i==anymove[3] && anyBoard[anymove[2]][anymove[3]] <= 0) {
											return true;
										}
										if(anyBoard[anymove[2]][i] != 0 ) {
											break;
										}
									}
								}
							}
							if(anymove[1] == anymove[3] && anymove[2] -anymove[0] <8 ) {
								if(anymove[2]-anymove[0] >0) {//Vertical down
									for (int i = anymove[0]+1;i <anymove[2]+1 ; i++) {
										if(i == anymove[2] && anyBoard[anymove[2]][anymove[3]] <=0 ) {
											return true;
										}
										if(anyBoard[i][anymove[3]] !=0) {
											break;
										}
									}
								}
								if(anymove[2] - anymove[0] < 0) {//Vertical Up
									for (int i  = anymove[0]-1;i>anymove[2]-1 ; i--) {
										if(i == anymove[2] && anyBoard[anymove[2]][anymove[3]] <=0 ) {
											return true;
										}
										if(anyBoard[i][anymove[3]] !=0) {
											break;	
									}		
							}
					}	
					}
					}
					if((Math.pow(anymove[2]-anymove[0], 2)== Math.pow(anymove[3]-anymove[1], 2))&& anymove[2]-anymove[0] != 0  ) {
						int pos1 = anymove[0],pos2=anymove[1];
						while(pos1 != anymove[2] && pos2 != anymove[3]) {
							if(anymove[2] - anymove[0]  == anymove[3] - anymove[1] && anymove[2] - anymove[0] >0) {
								
								pos1++;pos2++;
							}
							else if(anymove[2] - anymove[0]  == anymove[3] - anymove[1] && anymove[2] - anymove[0] <0) {
								
								pos1--;pos2--;
							}
							else if(anymove[2] - anymove[0]  == (anymove[3] - anymove[1])*(-1) &&anymove[2]-anymove[0]>0) {
								
								pos1++; pos2--;
							}
							else if(anymove[2] - anymove[0]  == (anymove[3] - anymove[1])*(-1) &&anymove[2]-anymove[0]<0) {
								
								pos1--;	pos2++;
							}
							
							else {break;}
							
							
							if(anyBoard[pos1][pos2] <= 0 && pos1 == anymove[2]) {
							
								return true;
							}
							if(anyBoard[pos1][pos2] != 0) {
								break;
							}
						}
						}
					break;
				case King:
						if(anymove[2]-anymove[0] <=1 && anymove[2]-anymove[0] >=-1 && anymove[3]-anymove[1] <=1 && anymove[3]-anymove[1] >=-1) {
							if(anyBoard[anymove[2]][anymove[3]] <=0) {
								return true;
							}
						}
						outerloop:
						if(anyBoard[anymove[2]][anymove[3]]==5) {
							for (Log loop : log) {
								if(loop.Piece == 10 || (loop.LogMove[0]==anymove[2] && loop.LogMove[1]==anymove[3])) {break outerloop;}
							}
							int Board[][] = new int[8][8];
							Board=anyBoard;
							if(anymove[3] > anymove[1] ) {
								for (int i = anymove[1]+1;i<anymove[3]+1;i++) {//right
									if(i == anymove[3]) {
										Board[7][4]=0;
										Board[7][5]=10;
										int check[]=new int[2];
										check=KingCheck(Board);
										if(check[1]==0) {
											Board[7][5]=0;Board[7][6]=10;check=KingCheck(Board);
											
											if(check[1]==0) {
												return true;
											}
										}
									}
									if(anyBoard[anymove[2]][i] != 0) {
										break;
									}
								}
							}	
							if(anymove[3]<anymove[1]) {
								for (int i= anymove[1]-1;i>anymove[3]-1;i--) { //left
									if(i == anymove[3]) {
										Board[7][4]=0;
										Board[7][3]=10;
										int check[]=new int[2];
										check=KingCheck(Board);
										if(check[1]==0) {
											Board[7][3]=0;Board[7][2]=10;check=KingCheck(Board);
											if(check[1]==0) {
												return true;
										}
									}
									}
									if(anyBoard[anymove[2]][i] != 0) {
										break;
									}
								}
							}
						}
					break;
				default:
					break;
			}
		}
		else {
			BPieces pieces[] = BPieces.values();
			BPieces Bpiece = null;
			for (BPieces p : pieces) {
				if(p.getValue() == currentpiece) {
					Bpiece = p;
				}
			}
			switch(Bpiece) {
			case Pawn:
				if(((anymove[2]-anymove[0] == 2  ||anymove[2]-anymove[0] == 1) || (anymove[1]-1 == anymove[3] || anymove[1]+1 == anymove[3]) && anymove[2]-anymove[0]==1)&& anymove[2] >anymove[0]) {//All legal Pawn moves	
						if(anymove[2]-anymove[0] == 2 && anymove[0] == 1 && anyBoard[anymove[2]-1][anymove[3]] == 0 && anymove[3]== anymove[1]) {//First pawn Move //2squares
							if(locatedpiece == 0) {{return true;}}
						}
						if(locatedpiece == 0 && anymove[3] == anymove[1] && anymove[2]- anymove[0] == 1 && anymove[3]== anymove[1]) {return true; }//Forward Pawn Move
						if(locatedpiece > 0 &&( anymove[1]-1 == anymove[3] || anymove[1]+1==anymove[3])/*En Passant*/ || (anymove[2]== log.get(log.size()-1).empessantmove[0] && log.get(log.size()-1).empessantmove[1] == anymove[3]) ) {return true;}//Diagonal moves
					// }
				}
				break;
			case Rook:
				if((anymove[0]==anymove[2] && anymove[3] != anymove[1])/*Horizontal move*/ || (anymove[1] == anymove[3] && anymove[2] != anymove[0])/*Vertical move*/) {
						if(anymove[0]==anymove[2] && anymove[3] != anymove[1]) {//horizantal right
							if(anymove[3]-anymove[1] >0) {
								for (int i=anymove[1]+1;i< anymove[3]+1;i++) {
									if(i==anymove[3] && anyBoard[anymove[2]][anymove[3]] >= 0) {
										return true;
									}
									if(anyBoard[anymove[2]][i] != 0) {
										break;
									}
								}	
							}
							if(anymove[3]-anymove[1] < 0 ) {//Horizantal left
								for (int i =anymove[1]-1;i>anymove[3]-1;i--) {
									if(i==anymove[3] && anyBoard[anymove[2]][anymove[3]] >= 0) {
										
										return true;
									}
									if(anyBoard[anymove[2]][i] != 0 ) {
										break;
									}
								}
							}
						}
						if(anymove[1] == anymove[3] && anymove[2] !=  anymove[0]  ) {
							if(anymove[2] > anymove[0] ) {//Vertical down
								for (int i = anymove[0]+1;i <anymove[2]+1 ; i++) {
									if(i == anymove[2] && anyBoard[anymove[2]][anymove[3]] >=0 ) {	
										return true;
									}
									if(anyBoard[i][anymove[3]] !=0) {
										break;	
									}
								}
							}
							if(anymove[2] - anymove[0] < 0) {//Vertical Up
								for (int i  = anymove[0]-1;i>anymove[2]-1 ; i--) {
									if(i == anymove[2] && anyBoard[anymove[2]][anymove[3]] >= 0 ) {
										return true;
									}
									if(anyBoard[i][anymove[3]] !=0) {
										break;	
								}	
						}
				}	
				}
				}
				break;
			case Knight:
				if(((anymove[2]-anymove[0] == 2|| anymove[2]-anymove[0] == -2 ) &&( anymove[3] - anymove[1] ==1 || anymove[3]-anymove[1] == -1)) || ((anymove[3]-anymove[1] == -2 ||anymove[3]-anymove[1] == 2) && (anymove[2]-anymove[0]==1 || anymove[2]-anymove[0] == -1)))  {
					if(locatedpiece >= 0) {
						return true;
						}
				}
			break;
			case Bishop:
				if((anymove[2]-anymove[0] == anymove[3]-anymove[1] || anymove[2] - anymove [0] == -1*(anymove[3] -anymove[1]))&& anymove[2]-anymove[0] != 0  ) {
					int pos1 = anymove[0],pos2=anymove[1];
					while(pos1 != anymove[2] && pos2 != anymove[3]) {
						if(anymove[2] - anymove[0]  == anymove[3] - anymove[1] && anymove[2] - anymove[0] >0) {
							pos1++;pos2++;
						}
						else if(anymove[2] - anymove[0]  == anymove[3] - anymove[1] && anymove[2] - anymove[0] <0) {
							pos1--;pos2--;
						}
						else if(anymove[2] - anymove[0]  == (anymove[3] - anymove[1])*(-1) &&anymove[2]-anymove[0]>0) {
							pos1++; pos2--;
						}
						else if(anymove[2] - anymove[0]  == (anymove[3] - anymove[1])*(-1) &&anymove[2]-anymove[0]<0) {
							pos1--;	pos2++;
							}
						else {break;}
						if(anyBoard[pos1][pos2] >= 0 && pos1 == anymove[2]) {
							return true;
							
						}
						if(anyBoard[pos1][pos2] != 0) {
							break;
						}
					}
					
					}
				break;
			case Queen:
				if((anymove[0]==anymove[2] && anymove[3] != anymove[1])/*Horizontal move*/ || (anymove[1] == anymove[3] && anymove[2] != anymove[0])/*Vertical move*/) {
						if(anymove[0]==anymove[2] && anymove[3] != anymove[1]) {//horizantal right
							if(anymove[3]-anymove[1] >0) {
								for (int i=anymove[1]+1;i< anymove[3]+1;i++) {
									if(i==anymove[3] && anyBoard[anymove[2]][anymove[3]] >= 0) {
										return true;
									}
									if(anyBoard[anymove[2]][i] != 0) {
										break;
									}
								}	
							}
							if(anymove[3]-anymove[1] < 0 ) {//Horizantal left
								for (int i =anymove[1]-1;i>anymove[3]-1;i--) {
									if(i==anymove[3] && anyBoard[anymove[2]][anymove[3]] >= 0) {
										return true;
									}
									if(anyBoard[anymove[2]][i] != 0 ) {
										break;
									}
								}
							}
						}
						if(anymove[1] == anymove[3] && anymove[2] !=  anymove[0]  ) {
							if(anymove[2] > anymove[0] ) {//Vertical down
								for (int i = anymove[0]+1;i <anymove[2]+1 ; i++) {
									if(i == anymove[2] && anyBoard[anymove[2]][anymove[3]] >=0 ) {	
										return true;
									}
									if(anyBoard[i][anymove[3]] !=0) {
										break;	
									}
								}
							}
							if(anymove[2] - anymove[0] < 0) {//Vertical Up
								for (int i  = anymove[0]-1;i>anymove[2]-1 ; i--) {	
									if(i == anymove[2] && anyBoard[anymove[2]][anymove[3]] >= 0 ) {
										return true;
									}
									if(anyBoard[i][anymove[3]] !=0) {
										break;
								}
						}
				}			
				}
				}
				if((anymove[2]-anymove[0] == anymove[3]-anymove[1] || anymove[2] - anymove [0] == -1*(anymove[3] -anymove[1]))&& anymove[2]-anymove[0] != 0  ) {
					int pos1 = anymove[0],pos2=anymove[1];
					while(pos1 != anymove[2] && pos2 != anymove[3]) {
						if(anymove[2] - anymove[0]  == anymove[3] - anymove[1] && anymove[2] - anymove[0] >0) {
							pos1++;pos2++;
						}
						else if(anymove[2] - anymove[0]  == anymove[3] - anymove[1] && anymove[2] - anymove[0] <0) {
							pos1--;pos2--;
						}
						else if(anymove[2] - anymove[0]  == (anymove[3] - anymove[1])*(-1) &&anymove[2]-anymove[0]>0) {
							pos1++; pos2--;
						}
						else if(anymove[2] - anymove[0]  == (anymove[3] - anymove[1])*(-1) &&anymove[2]-anymove[0]<0) {
							pos1--;	pos2++;
							}
						else {break;}
						if(anyBoard[pos1][pos2] >= 0 && pos1 == anymove[2]) {
							return true;
						}
						if(anyBoard[pos1][pos2] != 0) {
							break;
						}
					}
					}
				break;
			case King:
				int check[]=new int[2];
				check=KingCheck(board);
				if(check[0]== 0) {
					if(anymove[2]-anymove[0] <=1 && anymove[2]-anymove[0] >=-1 && anymove[3]-anymove[1] <=1 && anymove[3]-anymove[1] >=-1) {
						if(anyBoard[anymove[2]][anymove[3]] >= 0) {
							return true;
						}
					}
						outerloop:
						if(anyBoard[anymove[2]][anymove[3]]==-5) {
							//Check if king or rook moved
							for (Log loop : log) {
								if(loop.Piece == -10 || (loop.LogMove[0] == anymove[2] && loop.LogMove[1]==anymove[3])) {break outerloop;}
							}	
							int Board[][] = new int[8][8];
							Board=anyBoard;
							if(anymove[3] > anymove[1] ) {
								for (int i = anymove[1]+1;i<anymove[3]+1;i++) {//right
									if(i == anymove[3]) {
										Board[0][4]=0;
										Board[0][5]=-10;
										check=KingCheck(Board);	
										if(check[0]==0) {
											Board[0][5]=0;Board[0][6]=-10;check=KingCheck(Board);	
											if(check[0]==0) {
												return true;
											}
										}
									}
									if(anyBoard[anymove[2]][i] != 0) {
										
										break;
									}
								}
							}	
							if(anymove[3]<anymove[1]) {
								for (int i= anymove[1]-1;i>anymove[3]-1;i--) { //left
									if(i == anymove[3]) {
										Board[0][4]=0;
										Board[0][3]=-10;
										
										check=KingCheck(Board);
										if(check[0]==0) {
											Board[0][3]=0;Board[0][2]=-10;check=KingCheck(Board);
											if(check[0]==0) {
												return true;
											}
										}
									}	
									if(anyBoard[0][i] != 0) {	
										break;
									}
								}
							}
						}
				}
				break;
			default:
				break;
		}
		}
		return false;
		}
		public boolean CheckTie() {
			boolean WhitekingAlone=true;
			boolean BlackkingAlone=true;
			for (int i=0;i<8;i++) {
				for (int j=0;j<8;j++) {
					if(board[i][j] != 0 && board[i][j] != 10 && board[i][j] > 0) {
						WhitekingAlone = false;
						
					}
					if(board[i][j] != 0 && board[i][j] != -10 && board[i][j] <0) {
						BlackkingAlone = false;
					}
				}
			}
			if((WhitekingAlone && BlackkingAlone) || Count==50) {return true;}
			if((WhitekingAlone || BlackkingAlone)&& CheckMate(board,Turn)) {return true; }
			if(WhitekingAlone || BlackkingAlone) {
				Count++;
			}
			return false;
		}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	}

enum WPieces {
	Pawn(1),Rook(5),Knight(3),Bishop(4),Queen(9),King(10);
	int values;
	WPieces(int i) { values = i; }
	public int getValue() { return values;	}
}

enum BPieces{
	Pawn(-1),Rook(-5),Knight(-3),Bishop(-4),Queen(-9),King(-10);
	int values;
	BPieces(int values){ this.values=values;}
	public int getValue() { return values; }
}
enum Checks{
	BlackCheck(-1),WhiteCheck(1),IllegalMove(-10),BlockCheck(2),BothChecked(-10),NoChecks(0);
	int Control;
	Checks(int number){
		Control=number;
	}
	public int getValue() {
		return Control;
	}
}
