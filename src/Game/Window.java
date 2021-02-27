package Game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import com.sun.prism.Image;

public class Window extends JFrame {
	
	/**
	 * 
	 */
	
	static JLayeredPane pane = new JLayeredPane();
	private static final long serialVersionUID = 1L;

	public Window() {
		JLabel label;
		JLabel label2;
		ImageIcon board=new ImageIcon("images/CutBoard4.png");
		label=new JLabel("",board,JLabel.CENTER);
		label.setIcon(board);
		label.setSize(860, 860);
		pane.setSize(860,859);
		pane.add(label,new Integer(0));
		this.add(pane);
		this.setSize(860, 900);
		
	}
	public void DrawPieces(int aboard[][]) {
		ImageIcon Piece ;
		pane.removeAll();
		JLabel label = new JLabel("",new ImageIcon("images/CutBoard4.png"),JLabel.CENTER);
		label.setSize(860, 859);
		pane.add(label,new Integer(0));
			for (int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					switch(aboard[i][j]) {
						case 1://Pawn
							Piece = new ImageIcon("images/New/WhitePawn.png");
							JLabel Wpawn = new JLabel("",Piece,JLabel.CENTER);
							Wpawn.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(Wpawn,new Integer(1));
							
							break;
						case 3://Knight
							Piece = new ImageIcon("images/New/WhiteKnight.png");
							JLabel WKnight = new JLabel("",Piece,JLabel.CENTER);
							WKnight.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(WKnight,new Integer(1));
							break;
						case 4://Bishop
							Piece = new ImageIcon("images/New/WhiteBishop.png");
							JLabel WBishop = new JLabel("",Piece,JLabel.CENTER);
							WBishop.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(WBishop,new Integer(1));
							break;
						case 5://Rook
							Piece = new ImageIcon("images/New/WhiteRook.png");
							JLabel WRook = new JLabel("",Piece,JLabel.CENTER);
							WRook.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(WRook,new Integer(1));
							break;
						case 9://Queen
							Piece = new ImageIcon("images/New/WhiteQueen.png");
							JLabel WQueen = new JLabel("",Piece,JLabel.CENTER);
							WQueen.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(WQueen,new Integer(1));
							break;
						case 10://King
							Piece = new ImageIcon("images/New/WhiteKing.png");
							JLabel WKing = new JLabel("",Piece,JLabel.CENTER);
							WKing.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(WKing,new Integer(1));
							break;
						case -1://BPawn
							Piece = new ImageIcon("images/New/BlackPawn.png");
							JLabel BPawn = new JLabel("",Piece,JLabel.CENTER);
							BPawn.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(BPawn,new Integer(1));
							break;
						case -3://BKnight
							Piece = new ImageIcon("images/New/BlackKnight.png");
							JLabel BlackKnight = new JLabel("",Piece,JLabel.CENTER);
							BlackKnight.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(BlackKnight,new Integer(1));
							break;
						case -4://BBishop
							Piece = new ImageIcon("images/New/BlackBishop.png");
							JLabel BBishop = new JLabel("",Piece,JLabel.CENTER);
							BBishop.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(BBishop,new Integer(1));
							break;
						case -5://BRook
							Piece = new ImageIcon("images/New/BlackRook.png");
							JLabel BRook = new JLabel("",Piece,JLabel.CENTER);
							BRook.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(BRook,new Integer(1));
							break;
						case -9://BQueen
							Piece = new ImageIcon("images/New/BlackQueen.png");
							JLabel BQueen = new JLabel("",Piece,JLabel.CENTER);
							BQueen.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(BQueen,new Integer(1));
							break;
						case -10://BKing
							Piece = new ImageIcon("images/New/BlackKing.png");
							JLabel BKing = new JLabel("",Piece,JLabel.CENTER);
							BKing.setBounds(5+j*107, 5+i*107, 95, 95);
							pane.add(BKing,new Integer(1));
							break;
					
					}
					
					
					
					
				}
			}
			
		}

	
	
	
	
	
}
