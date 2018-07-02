package com.kv.j8;
/* class My extends KeyAdapter and sense key strok event and controll the motion of padel
class P extend JPanel and draw game through paint() method
class Th extend Thread and controlls the entire motion of ball
class Mahal contain main() method. Hence point of execution of game. */

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;
import javax.sound.midi.*;

class Mahal extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TextField tf; 					// text field displays the score on screen
	Pp p = new Pp(); 				// class that actully draw game, extends panel
	JButton jb1, jb2, jb3; 			// jb1=start button, jb2=stop button, jb3=exit button
	JLabel jl = new JLabel("SCORE");
	static int d, score, x, y, x1, y1, wid, heg; 
	/* d=1 mean code inside the
	 thread's run() method
	 will run, score=hold the
	 current score
	 wid=complet width of current area of clip screen, heg=height of current
	 area of clip screen
	 x&y are padel's first point on screen
	 x1&y1 are balls point on screen*/
	
	static int flag[][] = new int[5][30]; 
/*	 array to take decision which
	 bricks will draw and which not.
	 value having 1 mean draw the
	 brick and having 0 mean not drawn,
	 after hitting through bouncing
	 balls.*/
	
	static int rang[][] = new int[5][30]; 
	/* array to kept rang of bricks.
	 Taking decision that whether
	 balls hit at exactly pixel rang ,
	 so that make flag[][]=0*/
	
	static int rang1[] = new int[5]; // vertically height of 5 rows of bricks

	Mahal() {
		super("My Game");
		for (int i = 0; i < flag.length; i++) // set the value 1, so that initially all the bricks will draw on screen
		{
			for(int j : flag[i]){
				flag[i][j] = 1;
			}
		}
		jb1 = new JButton("START");
		jb2 = new JButton("STOP");
		jb3 = new JButton("EXIT");

		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);

		tf = new TextField();
		tf.setSize(250, 40);
		jb1.addKeyListener(new My(p));

		JPanel pp = new JPanel();
		JPanel pp1 = new JPanel();
		JPanel pp2 = new JPanel();
		pp1.add(jb1);
		pp1.add(jb2);
		pp1.add(jb3);
		pp2.add(jl);
		pp2.add(tf);
		pp.setLayout(new BorderLayout());
		pp.add(pp2, BorderLayout.WEST);
		pp.add(pp1, BorderLayout.CENTER);

		Container cn = getContentPane();
		cn.add(p, BorderLayout.CENTER);
		cn.add(pp, BorderLayout.SOUTH);
		setSize(500, 500);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s == "START") {
			d = 1;
			String score1 = String.valueOf(score);
			tf.setText(score1);
		}
		if (s == "STOP")
			d = 0;
		if (s == "EXIT")
			System.exit(0);
	}

	public void play() {
		try {
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track tr = seq.createTrack();

			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1, 50, 100);
			MidiEvent on = new MidiEvent(a, 1);
			tr.add(on);
			
			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, 50, 100);
			MidiEvent off = new MidiEvent(b, 10);
			tr.add(off);
			
			player.setSequence(seq);
			player.setTempoInBPM(220);
			player.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String... arg) {
		Mahal m = new Mahal();
		Th t = new Th(m.p, m);
		t.start();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Pp extends JPanel {
	int wid = 50, d = 0, count; // count is a variable holding number of bricks
								// in a raw. That is drawn at run time by taking
								// current system's screen clip bound as(5
								// bricks in one row)
	int heg = 15; // bricks point on screen as(xx,yy,wid,heg)
	int xx = 20;
	int yy = 20;
	int pause;

	public void paint(Graphics g) {
		Rectangle r = g.getClipBounds();
		Mahal.heg = r.height;
		Mahal.wid = r.width;
		if (Mahal.d == 0) {
			Mahal.x = r.width / 2 - 55;
			Mahal.x1 = r.width / 2 - 15;
			Mahal.y1 = r.height - 50;
			Mahal.y = r.height - 20;
		}
		g.setColor(Color.white);
		g.fill3DRect(0, 0, Mahal.wid, Mahal.heg, false); // clears the screen
															// every time before
															// drawing next
															// sceen by filling
															// it with white
															// color
		g.setColor(Color.red);
		g.fill3DRect(Mahal.x, Mahal.y, 110, 20, false); // draw the padel
		g.fillOval(Mahal.x1, Mahal.y1, 30, 30); // draw the ball
		for (int j = 0; j < Mahal.flag.length; j++) {
			if (j == 0)
				count = 0;
			for (int i = 0; i < Mahal.flag[j].length; i++) {
				if (xx + 50 <= r.width) // draw horigently bricks upto the clip
										// rang
				{
					Mahal.rang[j][i] = xx; // putting x points of each brick
											// which is drawn in array
					if (j == 0)
						count++; // increase the number of brick in row
					if (Mahal.flag[j][i] == 1) // check from flag whwther the
												// brick will draw or nt
						g.fill3DRect(xx, yy, wid, heg, false);// draw the brick
					xx += (wid + 10);
				}
			}
			yy += (heg + 20);
			xx = 20;
			Mahal.rang1[j] = yy; // outing the y points of each brick in array
		}
		xx = 20; // set the intial value of bricks point after paint the one
					// sceen
		yy = 20;
	}
}

class Th extends Thread {
	Pp p;
	Mahal m;
	int d = 1, d1, dd1 = 11; // d=1 mean ball will move in upword direction and
								// d=0 mean ball will move in downword direction
	// dd1 is variable that holds value return from dialog box. to take further
	// action according user's choice
	// d1=0 mean ball will move toward right and d1=1 mean ball will move toward
	// left

	Th(Pp p, Mahal m) {
		this.p = p;
		this.m = m;
	}

	public void run() {
		for (int i = 0; i < 9;) {
			while (Mahal.d == 1) {
				while (d == 1) {
					for (int r = Mahal.rang.length - 1; r >= 0; r--) {
						for (int k = 0; k < p.count; k++) {
							if (((Mahal.y1 == Mahal.rang1[r])
									&& (Mahal.x1 + 15 >= Mahal.rang[r][k] && Mahal.x1 + 15 <= Mahal.rang[r][k] + p.wid)
									&& (Mahal.flag[r][k] == 1)) || Mahal.y1 == 0) {
								if (Mahal.y1 != 0) {
									m.play();
									Mahal.score += 5;
									String score1 = String.valueOf(Mahal.score);
									m.tf.setText(score1);
									Mahal.flag[r][k] = 0;
								}
								d = 0;
								break;
							}
						}
					}
					if (p.pause == 0) {
						if (d1 == 0)
							Mahal.x1++;
						if (d1 == 1)
							Mahal.x1--;
						Mahal.y1--;
						if (Mahal.x1 > Mahal.wid - 30)
							d1 = 1;
						if (Mahal.x1 < 10)
							d1 = 0;
						p.repaint();
					}
					int win = 0;
					for (int r = Mahal.rang.length - 1; r >= 0; r--) {
						for (int k = 0; k < p.count; k++) {
							if (Mahal.flag[r][k] == 1)
								win = 1;
						}
					}
					if (win == 0) {
						dd1 = JOptionPane.showConfirmDialog(m, "Congratulation !! You won the Game\nYour Score : "
								+ Mahal.score + "\nDo you want continue game ?");
						Mahal.d = 0;
						if (dd1 == 0) {
							Mahal.score = 0;
							String score1 = String.valueOf(Mahal.score);
							m.tf.setText(score1);
							for (int r = Mahal.rang.length - 1; r >= 0; r--) {
								for (int k = 0; k < Mahal.rang[r].length; k++)
									Mahal.flag[r][k] = 1;
							}
							p.repaint();
						}
						if (dd1 == 2 || dd1 == 1)
							System.exit(0);
					}

					try {
						Thread.sleep(4);
					} catch (Exception e) {
					}
				}

				while (d == 0) {
					if (Mahal.y1 == Mahal.heg - 50 && (Mahal.x1 + 15 > Mahal.x && Mahal.x1 + 15 < Mahal.x + 110)) {
						d = 1;
						break;
					}
					if (p.pause == 0) {
						if (d1 == 0)
							Mahal.x1++;
						if (d1 == 1)
							Mahal.x1--;
						Mahal.y1++;
						if (Mahal.x1 > Mahal.wid - 30)
							d1 = 1;
						if (Mahal.x1 < 10)
							d1 = 0;
						p.repaint();
					}
					if (Mahal.y1 + 20 > Mahal.heg - 20) {
						dd1 = JOptionPane.showConfirmDialog(m, "You lost the Game with Score : " + Mahal.score
								+ "\n Do you want to start a new game ?");
						Mahal.d = 0;
						if (dd1 == 0) {
							Mahal.score = 0;
							String score1 = String.valueOf(Mahal.score);
							m.tf.setText(score1);
							for (int r = Mahal.rang.length - 1; r >= 0; r--) {
								for (int k = 0; k < Mahal.rang[r].length; k++)
									Mahal.flag[r][k] = 1;
							}
							p.repaint();
						}
						if (dd1 == 2 || dd1 == 1)
							System.exit(0);
					}
					try {
						Thread.sleep(4);
					} catch (Exception e) {
					}
				}
			} // while(Mahal.d=1)

			if (Mahal.d == 0) {
				p.pause = 0;
				p.repaint();
			}

		} // for loop
	}// run()
}// class

class My extends KeyAdapter {
	Pp p;
	int dd1 = 11;

	My(Pp p) {
		this.p = p;
	}

	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		if ((key == KeyEvent.VK_LEFT) && (Mahal.x >= 1) && (p.pause != 1)) {
			if (Mahal.x - 40 <= 1)
				Mahal.x = 0;
			else
				Mahal.x -= 40;
		}
		if ((key == KeyEvent.VK_RIGHT) && (Mahal.x + 110 <= Mahal.wid) && (p.pause != 1)) {
			if (Mahal.x + 110 + 40 >= Mahal.wid) {
				int diff = Mahal.x + 110 + 40 - Mahal.wid;
				Mahal.x += diff;
			} else
				Mahal.x += 40;
		}
		if (key == KeyEvent.VK_SPACE) {
			int pause = 0;
			if (pause == 0 && p.pause == 0) {
				p.pause = 1;
				pause = 1;
			}
			if (pause == 0 && p.pause == 1) {
				p.pause = 0;
				pause = 1;
			}
		}
		p.repaint();
	}

}