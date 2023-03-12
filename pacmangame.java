import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
//20195096 ������.           
 class pacman {
	 
	

	
	private static Random random;
	private static int pacmanH, pacmanW, enemyH, enemyW, numOfDot, where, start;
	private static int fieldMin=0, fieldMax=13;
	private static Icon temp1, temp2, temp3, temp4, temp;
	
	private static final int FRAME_WIDTH = 690;
	private static final int FRAME_HEIGHT = 650;
	
	static Timer t;

	public pacman(){
	final JFrame frame = new JFrame();//���� ������
	final JFrame frame1 = new JFrame();//���ӿ� ���������� �����Ǵ� ������
	//������ ����鼭 �ʿ��ߴ� �̹�����.
	final ImageIcon successIcon = new ImageIcon("gameoverImage.png");
	final ImageIcon gameoverIcon = new ImageIcon("gameover.png");
	final ImageIcon smallDot = new ImageIcon("smallDot.png");
	final ImageIcon rebutton = new ImageIcon("rebutton.png");
	final ImageIcon gcbtn = new ImageIcon("gameclearbtn.png");
	
	final ImageIcon wall = new ImageIcon("wall.png");
	final ImageIcon enemy = new ImageIcon("enemy.png");
	final ImageIcon enemy1 = new ImageIcon("enemy1.png");
	final ImageIcon enemy2 = new ImageIcon("enemy2.png");
	final ImageIcon enemy3 = new ImageIcon("enemy3.png");
	
	final ImageIcon pacman0 = new ImageIcon("pacman.png");
	final ImageIcon pacman1 = new ImageIcon("pacman1.png");
	final ImageIcon pacman2 = new ImageIcon("pacman2.png");
	final ImageIcon pacman3 = new ImageIcon("pacman3.png");
	final ImageIcon pacman4 = new ImageIcon("pacman4.png");
	final ImageIcon pacman5 = new ImageIcon("pacman5.png");
	final ImageIcon empty = new ImageIcon("empty.png");
	final JButton  gameclearbtn =new JButton(gcbtn);
	final JButton  rebtn =new JButton(rebutton);
	final JLabel gover=new JLabel();
	final JLabel end=new JLabel();
	
	
	end.setIcon(successIcon);
	gover.setIcon(gameoverIcon);
	gover.setBounds(0,0,690,650);
	rebtn.setBounds(225,455,240,50);
	rebtn.setBorderPainted(false);//��ư �׵θ� ����
	rebtn.setFocusPainted(false);//��Ŀ�� ǥ��
	rebtn.setContentAreaFilled(false);//��ư���� ���ǥ�� ����

	rebtn.addActionListener(new ActionListener() {//������ �ٽ� �����ϱ� ���� �׼Ǹ�����.
		@Override
		public void actionPerformed(ActionEvent e) {
			frame1.dispose();
			pacman c=new pacman();
					}
	});
	
	
	gameclearbtn.addActionListener(new ActionListener() {//������ Ŭ�����ϸ� �ý����� ����ǰ��ϴ� �׼Ǹ�����.

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
	});
	
	Random random = new Random();
	

	
	pacmanH=12;  pacmanW=7;  enemyH=7;  enemyW=7;  numOfDot=79;  start=3;  temp=empty;
	//���⼭ ��ŸƮ�� 2�̻��� ������ ������ �ʴ´ٸ�, ���� �������� �ֱ� ������ ������ 2�̻��� ������ �����������.
	
	final JLabel[][] f = new JLabel[14][14];//�� �迭�� �����Ͽ� �� �󺧿� �̹����� �������.
	
	for (int i=0; i<14; i++) {
		for(int j=0; j<14; j++) {
			f[i][j] = new JLabel();
		}
	}
	

	
	
	class TListener implements ActionListener {   //timerŬ���� ActionListener(���� �������� timerŬ�������� ��Ÿ����.�ð��� ���� ���������� ���� ������.)
		public void actionPerformed(ActionEvent event)
		{
			if(start<=0) where = 1+random.nextInt(4);//1~4������ ���� �������� ��.
			else { 
				where = 1; start--; //���� ��ŸƮ�� ���� 1�̶�� ������ �̹��忡�� 0�̵Ǿ� �ѹ� �ö󰬴ٰ� ������ �������� �ְԉ�.
			}
			switch(where) {
			case 1:
				if(!(f[enemyH-1][enemyW].getIcon()).equals(wall)) {//������ġ����  ���������� �������� ���� �����ʴٸ� if�� ����.
					temp1=f[enemyH-1][enemyW].getIcon();//temp1�� ������ġ���� �Ʒ������� �������� ������.(��temp1�� �����ִ� �������̉�)
					f[enemyH-1][enemyW].setIcon(enemy2);//����ġ�� ���̹����� ������.(��,���� �Ʒ��� �̵���Ŵ�� �ǹ�)
					f[enemyH][enemyW].setIcon(temp);//�׸����� ���� ���� �ִ� �ڸ��� temp�� ������.
					temp=temp1;//temp1�� ��ġ�� �������� temp�� ����.(temp�� ���� �ִ� ���������� ����������μ�, ���� ������ �����ʰ� �����̴� ���°� ��)
					enemyH--;//���� ���̸� ������ ���� f[enemyH-1][enemyW] �� �̵��Ѵ�.
				}//temp1���� �����ϴ� ������ ���� ���� ������ �ȵű� ������, ���� ������ �ڸ��� �ٽ� ���� ���������ֱ� ���ؼ�. 
				break;
			case 2:
				if(!(f[enemyH+1][enemyW].getIcon()).equals(wall)) {//where�� 2�ϰ��� �Ʒ��� ������
					temp2=f[enemyH+1][enemyW].getIcon();
					f[enemyH+1][enemyW].setIcon(enemy3);
					f[enemyH][enemyW].setIcon(temp);
					temp=temp2;
					enemyH++;
				}
				break;
			case 3:
				if(!(f[enemyH][enemyW-1].getIcon()).equals(wall)) {//where�� 3�ϰ��� �������� �̵�.
					temp3=f[enemyH][enemyW-1].getIcon();
					f[enemyH][enemyW-1].setIcon(enemy1);
					f[enemyH][enemyW].setIcon(temp);
					temp=temp3;
					enemyW--;
				}
				break;
			case 4:
				if(!(f[enemyH][enemyW+1].getIcon()).equals(wall)) {//where�� 4�ϰ��� ���������� �̵�.
					temp4=f[enemyH][enemyW+1].getIcon();
					f[enemyH][enemyW+1].setIcon(enemy);
					f[enemyH][enemyW].setIcon(temp);
					temp=temp4;
					enemyW++;
				}
				break;
			}
			if(enemyH==pacmanH && enemyW==pacmanW) {//�Ѹǰ� ���� ��ġ�� ���ٸ� frame1 ui�� �ҷ��� ���ӿ���ȭ���� ���� ��.
				f[enemyH][enemyW].setIcon(enemy);
				t.stop();//Ÿ�̸� ��ž�� �������� ������ �������� ������״��� Ÿ�̸Ӵ� �����Ǳ� ������ �ʼ��� ����Ͽ��� ������ �ٽ� �����Ҽ� ����.
				frame.dispose();
				frame1.add(rebtn);
				frame1.add(gover);
				frame1.setTitle("PacmanGame");
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.setVisible(true);
				frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
				
			}
			
			if(enemyH==5 && enemyW==7) { //���� ������ ���� ��ġ�� 5,7�� ��ġ�ϸ� ���ڸ��� ���� �����Ͽ� ������ ������ ���� ������.
				f[6][7].setIcon(wall); 
				}//���� ���� ���ڸ��� ��������.
			
	
		}
	}
	class KListener extends KeyAdapter{  //Ű����͸� ��ӹ޾� �������̽��� �������������� �� ���� ���ϴ� �޼ҵ常 �ҷ��ü�����.
		public void keyPressed(KeyEvent e) {
			if(numOfDot<1) {//���� ���� ������ �������� ���� �������� �������.
				frame.dispose();//����������
				frame1.add(gameclearbtn);
				gameclearbtn.setBounds(150,400,377,59);
				gameclearbtn.setBorderPainted(false);
				frame1.add(end);
				frame1.setTitle("PacmanGame");
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.setVisible(true);
				frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
				
				
			}
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_UP:
				if((f[pacmanH-1][pacmanW].getIcon()).equals(smallDot) || (f[pacmanH-1][pacmanW].getIcon()).equals(empty)) {
					if((f[pacmanH-1][pacmanW].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman3)) {
						numOfDot--;
						}
					else if(f[pacmanH-1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman2)) {
						numOfDot--;
					}
					else if(f[pacmanH-1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman4)) {
						numOfDot--;
					}
					else if(f[pacmanH-1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman5)) {
						numOfDot--;
					}
					f[pacmanH-1][pacmanW].setIcon(pacman3);
					f[pacmanH][pacmanW].setIcon(empty);
					pacmanH--;
				}
				if((f[pacmanH-1][pacmanW].getIcon()).equals(enemy)||f[pacmanH-1][pacmanW].getIcon().equals(enemy1)||f[pacmanH-1][pacmanW].getIcon().equals(enemy2)||f[pacmanH-1][pacmanW].getIcon().equals(enemy3)) {
					f[enemyH][enemyW].setIcon(enemy);
					t.stop();					
					frame.dispose();					
					frame1.add(rebtn);
					frame1.add(gover);
					frame1.setTitle("PacmanGame");
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true);
					frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
					
					
				}
				break;
			case KeyEvent.VK_DOWN:
				if((f[pacmanH+1][pacmanW].getIcon()).equals(smallDot) || (f[pacmanH+1][pacmanW].getIcon()).equals(empty)) {
					if((f[pacmanH+1][pacmanW].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman3)) {
						numOfDot--;
						}
					else if(f[pacmanH+1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman2)) {
						numOfDot--;
					}
					else if(f[pacmanH+1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman4)) {
						numOfDot--;
					}
					else if(f[pacmanH+1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman5)) {
						numOfDot--;
					}
					f[pacmanH+1][pacmanW].setIcon(pacman2);
					f[pacmanH][pacmanW].setIcon(empty);
					pacmanH++;
				}
				if((f[pacmanH+1][pacmanW].getIcon()).equals(enemy)||f[pacmanH+1][pacmanW].getIcon().equals(enemy1)||f[pacmanH+1][pacmanW].getIcon().equals(enemy2)||f[pacmanH+1][pacmanW].getIcon().equals(enemy3)){
					f[enemyH][enemyW].setIcon(enemy);
					frame.dispose();
					t.stop();
					frame1.add(rebtn);
					frame1.add(gover);
					
					frame1.setTitle("PacmanGame");
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true);
					frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
					
					
				
				}
				break;
			case KeyEvent.VK_LEFT:
				if((f[pacmanH][pacmanW-1].getIcon()).equals(smallDot) || (f[pacmanH][pacmanW-1].getIcon()).equals(empty)) {
					if((f[pacmanH][pacmanW-1].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman3)) {
						numOfDot--;
						}
					else if(f[pacmanH][pacmanW-1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman2)) {
						numOfDot--;
					}
					else if(f[pacmanH][pacmanW-1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman4)) {
						numOfDot--;
					}
					else if(f[pacmanH][pacmanW-1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman5)) {
						numOfDot--;
					}
					f[pacmanH][pacmanW-1].setIcon(pacman4);
					f[pacmanH][pacmanW].setIcon(empty);
					pacmanW--;
				}
				if((f[pacmanH][pacmanW-1].getIcon()).equals(enemy)||f[pacmanH][pacmanW-1].getIcon().equals(enemy1)||f[pacmanH][pacmanW-1].getIcon().equals(enemy2)||f[pacmanH][pacmanW-1].getIcon().equals(enemy3)){
					f[enemyH][enemyW].setIcon(enemy);
					frame.dispose();
					t.stop();
					frame1.add(rebtn);
					frame1.add(gover);
					
					frame1.setTitle("PacmanGame");
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true);
					frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
					
					
				}
				
				break;
			case KeyEvent.VK_RIGHT:
				if((f[pacmanH][pacmanW+1].getIcon()).equals(smallDot) || (f[pacmanH][pacmanW+1].getIcon()).equals(empty)) {
					if((f[pacmanH][pacmanW+1].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman3)) {
						numOfDot--;
						}
					else if(f[pacmanH][pacmanW+1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman2)) {
						numOfDot--;
					}
					else if(f[pacmanH][pacmanW+1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman4)) {
						numOfDot--;
					}
					else if(f[pacmanH][pacmanW+1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman5)) {
						numOfDot--;
					}
					f[pacmanH][pacmanW+1].setIcon(pacman5);
					f[pacmanH][pacmanW].setIcon(empty);
					pacmanW++;
				}
				if((f[pacmanH][pacmanW+1].getIcon()).equals(enemy)||f[pacmanH][pacmanW+1].getIcon().equals(enemy1)||f[pacmanH][pacmanW+1].getIcon().equals(enemy2)||f[pacmanH][pacmanW+1].getIcon().equals(enemy3)) {
					f[enemyH][enemyW].setIcon(enemy);
					frame.dispose();
					t.stop();
					frame1.add(rebtn);
					frame1.add(gover);
					
					frame1.setTitle("PacmanGame");
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true);
					frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
					
					
					
				}
				break;
			}
			
		}
	}
	KListener listener = new KListener();
	TListener tListener = new TListener();
	
	
	
	t = new Timer(150, tListener);//0.15�ʿ� �ѹ��� �������� ���� ���� ������ ����.
	t.start();

	
	
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(14,14));
	
	frame.requestFocus();
	frame.addKeyListener(new KListener());
	
	
	for(int i=0; i<14; i++) {
		for(int j=0; j<14; j++) {
			f[i][j].setIcon(wall);
			f[i][j].addKeyListener(listener);
			panel.add(f[i][j]);
		}
	}
	
	f[1][1].setIcon(smallDot);	f[2][1].setIcon(smallDot);	f[3][1].setIcon(smallDot);	f[4][1].setIcon(smallDot);	f[5][1].setIcon(smallDot);
	f[5][2].setIcon(smallDot);	f[5][3].setIcon(smallDot);	f[1][3].setIcon(smallDot);	f[2][3].setIcon(smallDot);	f[3][3].setIcon(smallDot);
	f[4][3].setIcon(smallDot);	f[1][4].setIcon(smallDot);	f[1][5].setIcon(smallDot);	f[1][6].setIcon(smallDot);	f[1][7].setIcon(smallDot);
	f[1][8].setIcon(smallDot);	f[1][9].setIcon(smallDot);	f[1][10].setIcon(smallDot);	f[1][11].setIcon(smallDot);	f[1][12].setIcon(smallDot);
	f[2][9].setIcon(smallDot);	f[2][12].setIcon(smallDot);	f[3][12].setIcon(smallDot);	f[4][12].setIcon(smallDot);	f[5][12].setIcon(smallDot);
	f[3][4].setIcon(smallDot);	f[3][5].setIcon(smallDot);	f[3][10].setIcon(smallDot);	f[3][11].setIcon(smallDot);
	f[4][5].setIcon(smallDot);	f[4][6].setIcon(smallDot);	f[4][7].setIcon(smallDot);	f[4][8].setIcon(smallDot);	f[4][9].setIcon(smallDot);
	f[4][10].setIcon(smallDot);	f[2][7].setIcon(smallDot);	f[3][7].setIcon(smallDot);	f[4][10].setIcon(smallDot);	f[4][11].setIcon(smallDot);
	f[5][11].setIcon(smallDot);	f[6][11].setIcon(smallDot);	f[7][11].setIcon(smallDot);	f[7][12].setIcon(smallDot);	f[8][12].setIcon(smallDot);
	f[9][12].setIcon(smallDot);	f[10][12].setIcon(smallDot);f[11][12].setIcon(smallDot);f[5][7].setIcon(empty);		f[6][2].setIcon(smallDot);
	f[7][1].setIcon(smallDot);	f[9][11].setIcon(smallDot);	f[11][1].setIcon(smallDot);	f[11][3].setIcon(smallDot);	f[11][11].setIcon(smallDot);
	f[7][2].setIcon(smallDot);	f[7][3].setIcon(smallDot);	f[8][1].setIcon(smallDot);	f[9][1].setIcon(smallDot);	f[9][2].setIcon(smallDot);
	f[9][3].setIcon(smallDot);	f[9][4].setIcon(smallDot);	f[9][5].setIcon(smallDot);	f[9][6].setIcon(smallDot);	f[12][1].setIcon(smallDot);
	f[12][2].setIcon(smallDot);	f[12][3].setIcon(smallDot);	f[12][4].setIcon(smallDot);	f[12][5].setIcon(smallDot);	f[12][9].setIcon(smallDot);
	f[12][10].setIcon(smallDot);f[12][11].setIcon(smallDot); f[10][5].setIcon(smallDot); f[11][5].setIcon(smallDot); f[10][6].setIcon(smallDot);
	f[10][7].setIcon(smallDot);	f[10][8].setIcon(smallDot); f[10][9].setIcon(smallDot); f[11][7].setIcon(smallDot); f[12][7].setIcon(pacman3);
	f[9][8].setIcon(smallDot); f[9][9].setIcon(smallDot); f[11][9].setIcon(smallDot); f[6][5].setIcon(empty);f[6][6].setIcon(empty);
	f[6][7].setIcon(empty); f[6][8].setIcon(empty);f[6][9].setIcon(empty); f[7][5].setIcon(empty);f[7][6].setIcon(empty);
	f[7][7].setIcon(enemy); f[7][8].setIcon(empty);f[7][9].setIcon(empty);
	
	frame.add(panel);
	frame.setTitle("PacmanGame");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	
}


	static class startUI extends JFrame{//ó�� ���� ui
		startUI(){
			setTitle("PacmanGame");
			
			final ImageIcon str = new ImageIcon("startButton2.png");
			final ImageIcon help = new ImageIcon("helpbutton1.png");
			final ImageIcon strback = new ImageIcon("startImage1.png");
			JPanel startPanel=new JPanel(null);
			JButton strbtn =new JButton(str);
			JButton helpbtn =new JButton(help);
			JLabel a=new JLabel();
			strbtn.setBorderPainted(false);
			helpbtn.setBorderPainted(false);
			helpbtn.setFocusPainted(false);
			strbtn.setFocusPainted(false);
			startPanel.add(helpbtn);
			a.setIcon(strback);
			startPanel.add(a);
			a.setBounds(0,0,440,777);
			startPanel.add(strbtn);
			helpbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new helpUI();
					dispose();
					
					
					
				}
			});
			strbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					new pacman();
					dispose();
					
				}
			});
			helpbtn.setBounds(230,230,200,33);
			strbtn.setBounds(230,170,200,31);
			add(startPanel);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setSize(449,785);
			
			
			
		}
	}
}

class helpUI extends JFrame{
	helpUI(){
		
		JPanel panel=new JPanel(null);
		add(panel);
		ImageIcon underImage = new ImageIcon("pacmans.png");
		ImageIcon explainImage = new ImageIcon("���Ӽ���.png");
		ImageIcon btnImage = new ImageIcon("ȭ��ǥ��ư.png");
		ImageIcon btnImage2 = new ImageIcon("ȭ��ǥ��ư�ݴ�.png");
		JLabel gamerule=new JLabel();
		JLabel gamestart=new JLabel("���ӽ���");
		gamestart.setFont(new Font("ExtraBOLD",Font.CENTER_BASELINE,20));
		gamestart.setForeground(Color.yellow);
		gamestart.setBounds(285,560,100,70);
		panel.add(gamestart);
		JLabel backui=new JLabel("����ȭ��");
		backui.setFont(new Font("ExtraBOLD",Font.CENTER_BASELINE,20));
		backui.setForeground(Color.yellow);
		backui.setBounds(60,560,100,70);
		panel.add(backui);

		gamerule.setIcon(underImage);
		JLabel gameexplain=new JLabel();
		gameexplain.setIcon(explainImage);
		JButton startbtn=new JButton(btnImage);
		JButton startui=new JButton(btnImage2);
		
		
		startbtn.setBounds(50, 600, 100, 66);
		startui.setBounds(280, 600, 100, 66);
		gameexplain.setBounds(0,0,448,780);
		gamerule.setBounds(0,0,449,76);
		startbtn.setBorderPainted(false);
		startbtn.setContentAreaFilled(false);//��ư������ ���ǥ�÷� ����
		startui.setFocusPainted(false);
		startui.setBorderPainted(false);
		startui.setContentAreaFilled(false);//��ư������ ���ǥ�÷� ����
		startui.setFocusPainted(false);
		
		panel.add(startbtn);
		panel.add(startui);
		panel.add(gamerule);	
		panel.add(gameexplain);
		startui.addActionListener(new ActionListener(){//������ �����ϴ� ��ư

			@Override
			public void actionPerformed(ActionEvent e) {
				new pacman();
				dispose();				
			}
			
		});
		startbtn.addActionListener(new ActionListener(){//ó��ȭ������ ���ư��� ��ư

			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.startUI a=new pacman.startUI();
				dispose();
				
			}
			
		});
	
		
		setTitle("PacmanGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		setSize(449,785);
	
	}
	
}


public class pacmangame { 
	
	
		
	public static void main(String[] args) {
		Operator opt = new Operator();
		opt.db = new Database();
		opt.mf = new MainFrame(opt);
		opt.jf = new JoinFrame(opt);
		//pacman.startUI a=new pacman.startUI();
	}
}

