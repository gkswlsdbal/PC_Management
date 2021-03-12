package Project;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Seat extends JPanel implements ActionListener {

	private ImageIcon chatting, imback, pcIcon, pcIcon2;
	private JButton btnct;
	private JPopupMenu pMenu;
	private JMenuItem end, info, charge, pcon, pcoff;
	private static String time;
	private JLabel lbltime, lblmoney, lblend, lbl, lblPC;
	private static int count = 0;
	private int num = 0, check = 0, mcount = 0, sum = 0;
	private String str = "0";
	private boolean flag;
	private JPanel back;

	private Image img1, img2, pcimg2, pcimg1;
	private BufferedImage pic;

	private int interval;
	private Timer timer;

	public Seat(int i) {
		// TODO Auto-generated constructor stub

		chatting = new ImageIcon("images/chatting.png");
		imback = new ImageIcon("images/sea.png");
		pcIcon = new ImageIcon("images/red.png");
		pcIcon2 = new ImageIcon("images/green.png");
		check = i;

		// ImageIcon ũ�⸦ �����Ѵ�.
		img1 = pcIcon.getImage();
		pcimg1 = img1.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		pcIcon = new ImageIcon(pcimg1);

		img2 = pcIcon2.getImage();
		pcimg2 = img2.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		pcIcon2 = new ImageIcon(pcimg2);

		back = new PicPanel("images/sea.png");
		back.setLayout(null);
		back.setBounds(0, 0, 200, 200);
		add(back);

		lbl = new JLabel("");
		lbl.setText((i + 1) + ".컴퓨터");
		lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
		lbl.setForeground(Color.WHITE);
		lbl.setBounds(15, 5, 65, 20);

		back.add(lbl);

		lblPC = new JLabel(new ImageIcon(pcimg1));
		lblPC.setBounds(2, 10, 10, 10);
		back.add(lblPC);

		btnct = new JButton(chatting);
		btnct.setBounds(77, 5, 25, 25);
		btnct.setBorderPainted(false);
		btnct.setContentAreaFilled(false);
		btnct.setFocusPainted(false);
		btnct.addActionListener(this);

		back.add(btnct);

		pMenu = new JPopupMenu();
		charge = new JMenuItem("충전");
		charge.addActionListener(this);
		end = new JMenuItem("정산");
		end.addActionListener(this);
		info = new JMenuItem("회원 정보");
		info.addActionListener(this);
		pcon = new JMenuItem("전원 켜기");
		pcon.addActionListener(this);
		pcoff = new JMenuItem("전원 끄기");
		pcoff.addActionListener(this);

		pMenu.add(charge);
		pMenu.add(end);
		pMenu.add(info);
		pMenu.add(pcon);
		pMenu.add(pcoff);

		lblmoney = new JLabel();
		lblmoney.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblmoney.setForeground(Color.WHITE);
		lblmoney.setBounds(15, 35, 65, 20);
		back.add(lblmoney);

		lbltime = new JLabel("");
		lbltime.setFont(new Font("SansSerif", Font.BOLD, 13));
		lbltime.setForeground(Color.WHITE);
		lbltime.setBounds(15, 55, 65, 20);
		back.add(lbltime);

		lblend = new JLabel("");
		lblend.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblend.setForeground(Color.WHITE);
		lblend.setBounds(15, 85, 120, 20);
		lblend.setVisible(false);
		back.add(lblend);

		addMouseListener(new MousePopupListener());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object obj = e.getSource();
		if (obj == charge && flag == true) {
			time = JOptionPane.showInputDialog("충전할 금액을 입력해주세요(100원당 1분)");
			num += Integer.parseInt(time);
			str = String.valueOf(num);
			lblmoney.setText(str + "원");
			lblend.setText("");

			int delay = 1000;
			int period = 2000;
			timer = new Timer();
			interval = Integer.parseInt(time);
			lbltime.setText(interval / 100 + ":" + "00분");

			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {

					lbltime.setText(String.valueOf(setInterval() / 100) + ":"
							+ String.valueOf((setInterval() % 100) - 40) + "분");
				}
			}, delay, period);

			// �������

		} else if (obj == end) {
			timer.cancel();

			String sql = "INSERT INTO SALE(COMPUTER,PRICE) " + "VALUES('" + check + "','" + num + "')";
			System.out.println(sql);
			ResultSet rs = SaleDB.getResultSet(sql);

			lbltime.setText("");
			lblmoney.setText("");
			lblend.setText("");
		} else if (obj == info) {
			CoputerInfo info = new CoputerInfo((check + 1) + "번 컴퓨터 정보", 300, 200);
			info.setMoney(str);
			info.setCheck(flag);
		} else if (obj == btnct && flag == true) {
			new Chatting((check + 1) + "번 컴퓨터 채팅", 300, 250);
		} else if (obj == pcon) {
			flag = true;
			lblPC.setIcon(new ImageIcon(pcimg2));

		} else if (obj == pcoff) {
			flag = false;

			lblPC.setIcon(new ImageIcon(pcimg1));

			num = 0;
			lbltime.setText("");
			lblmoney.setText("");
			lblend.setText("");

		}
	}

	private int setInterval() {
		// TODO Auto-generated method stub
		if (interval <= 41) {
			timer.cancel();
			lblPC.setIcon(new ImageIcon(pcimg1));
			lblend.setText("사용시간 종료");
			lblend.setVisible(true);
		}
		if (interval % 100 == 40 && interval / 100 > 0) {
			interval -= 40;
		}

		return --interval;
	}

	public void mousePressed(MouseEvent me) {
		if (me.getModifiers() == me.BUTTON3_MASK) {
			pMenu.show(this, me.getX(), me.getY());
		}
	}

	class MousePopupListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			checkPopup(e);
			;
		}

		public void mouseClicked(MouseEvent e) {
			checkPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			checkPopup(e);
		}

		private void checkPopup(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.isPopupTrigger()) {
				pMenu.show(Seat.this, e.getX(), e.getY());
			}

		}
	}

	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		if (me.getModifiers() == me.BUTTON3_MASK) {
			pMenu.show(this, me.getX(), me.getY());
		}
	}

	class PicPanel extends JPanel {

		private int w, h;

		public PicPanel(String fname) {

			// reads the image
			try {
				pic = ImageIO.read(new File(fname));
				w = pic.getWidth();
				h = pic.getHeight();

			} catch (IOException ioe) {
				System.out.println("Could not read in the pic");
				// System.exit(0);
			}

		}

		public Dimension getPreferredSize() {
			return new Dimension(w, h);
		}

		// this will draw the image
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(pic, 0, 0, 110, 118, this);
		}
	}
}
