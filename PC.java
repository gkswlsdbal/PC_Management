package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PC extends JFrame implements ActionListener, Runnable {

	private ImageIcon image, clock, exit, member, money, computer, imgseat, chatting;
	private JScrollPane scrollPane, scrollPane1;
	private JLabel lblseat, timelbl;
	private JPanel background, screen;
	private JButton btnclock, btnexit, btnmem, btnmoney, btncom, btncash, btnInfo, btnmessage;
	private JPanel[] seat;

	public Seat pan[] = new Seat[50];

	private int mcount = 0;

	public PC(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setLocationRelativeTo(this);
		setLayout(null);
		image = new ImageIcon("images/back.png");
		clock = new ImageIcon("images/clock.png");
		exit = new ImageIcon("images/off.png");
		member = new ImageIcon("images/member.png");
		money = new ImageIcon("images/money.png");
		computer = new ImageIcon("images/computer.png");


		background = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(image.getImage(), 0, 0, 1400, 770, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};

		background.setLayout(null);
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);

		
		screen = new JPanel();
		screen.setLayout(new GridLayout(5, 10, 5, 5));
		screen.setBounds(50, 100, 1140, 600);
		screen.setBackground(Color.BLACK);

		pan = new Seat[50];

		
		for (int i = 0; i < 50; i++) {
			pan[i] = new Seat(i);
			pan[i].setBackground(Color.DARK_GRAY);

			pan[i].setLayout(null);
			screen.add(pan[i]);
		}

		
		btnclock = new JButton(clock);
		btnclock.setBounds(520, 30, 50, 50);
		btnclock.setBorderPainted(false);
		btnclock.setContentAreaFilled(false);
		btnclock.setFocusPainted(false);
		background.add(btnclock);

		btnexit = new JButton(exit);
		btnexit.setBounds(1225, 620, 50, 50);
		btnexit.setBorderPainted(false);
		btnexit.setContentAreaFilled(false);
		btnexit.setFocusPainted(false);
		btnexit.addActionListener(this);
		background.add(btnexit);

		btnmem = new JButton(member);
		btnmem.setBounds(1225, 540, 50, 50);
		btnmem.setBorderPainted(false);
		btnmem.setContentAreaFilled(false);
		btnmem.setFocusPainted(false);
		btnmem.addActionListener(this);
		background.add(btnmem);

		btnmoney = new JButton(money);
		btnmoney.setBounds(1225, 460, 60, 50);
		btnmoney.setBorderPainted(false);
		btnmoney.setContentAreaFilled(false);
		btnmoney.setFocusPainted(false);
		btnmoney.addActionListener(this);
		background.add(btnmoney);


		btncom = new JButton(computer);
		btncom.setBounds(1220, 380, 60, 50);
		btncom.setBorderPainted(false);
		btncom.setContentAreaFilled(false);
		btncom.setFocusPainted(false);
		btncom.addActionListener(this);
		background.add(btncom);

		
		JLabel exitlbl = new JLabel("종료");
		exitlbl.setFont(new Font("SansSerif", Font.BOLD, 30));
		exitlbl.setSize(100, 60);
		exitlbl.setForeground(new Color(204, 051, 205));
		exitlbl.setLocation(1290, 620);
		background.add(exitlbl);

		JLabel memlbl = new JLabel("회원");
		memlbl.setFont(new Font("SansSerif", Font.BOLD, 30));
		memlbl.setSize(100, 60);
		memlbl.setForeground(new Color(204, 051, 205));
		memlbl.setLocation(1290, 540);
		background.add(memlbl);

		JLabel moneylbl = new JLabel("매상");
		moneylbl.setFont(new Font("SansSerif", Font.BOLD, 30));
		moneylbl.setSize(100, 60);
		moneylbl.setForeground(new Color(204, 051, 205));
		moneylbl.setLocation(1290, 460);
		background.add(moneylbl);


		JLabel comlbl = new JLabel("컴퓨터");
		comlbl.setFont(new Font("SansSerif", Font.BOLD, 30));
		comlbl.setSize(100, 60);
		comlbl.setForeground(new Color(204, 051, 205));
		comlbl.setLocation(1280, 380);
		background.add(comlbl);

		JLabel titlelbl = new JLabel("TARING");
		titlelbl.setFont(new Font("SansSerif", Font.BOLD, 50));
		titlelbl.setSize(200, 60);
		titlelbl.setForeground(new Color(0, 0, 0));
		titlelbl.setLocation(40, 30);
		background.add(titlelbl);

		JLabel sub = new JLabel("TA");
		sub.setFont(new Font("SansSerif", Font.BOLD, 100));
		sub.setSize(150, 100);
		sub.setForeground(new Color(204, 051, 205));
		sub.setLocation(1230, 240);
		background.add(sub);

		timelbl = new JLabel("00:00:00");
		timelbl.setBounds(580, 40, 300, 40);
		timelbl.setForeground(new Color(0, 0, 0));
		timelbl.setFont(new Font("SansSerif", Font.BOLD, 40));
		background.add(timelbl);

		background.add(screen);

		setVisible(true);

		// Clock(); 

	}

	
	public void Clock() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

		int i = 0;
		do {
			try {
				Thread.sleep(1000);
				timelbl.setText(sdf.format(new java.util.Date()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);

	}

	public static void main(String[] args) {
		SaleDB.init();
		new PC("PC방 관리 프로그램", 1400, 800);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj == btnexit) {
			System.exit(0);
		} else if (obj == btnmoney) {
			new Sales("매상", 00, 00);
		} else if (obj == btncom) {
			new computer("컴퓨터 정보", 400, 400);
		}else if(obj==btnmem) {
			new MemberInfo("",0,0);
		}

	}

	@Override
	public void run() {
		Clock();
	}
}
