package Project;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.PaintEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import db.login.DB;

public class Member extends JFrame implements ItemListener, ActionListener {

	private JTextField idtext, name, number, email1, email2, phoneNumber;

	private JCheckBox chkemail;

	private JButton idtest, search, member, cancel, btnpass;

	private JPasswordField behind, pstest, password;;

	private Choice chmail, chphone;

	private JPanel panel;

	private JScrollPane scrollPane;
	private ImageIcon icon;
	static boolean check = true;

	public Member(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(1000, 300);
		setLayout(null);
		// setLocationRelativeTo(this);

		icon = new ImageIcon("images/back.png");

		panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, 1400, 770, null);

				setOpaque(false);
				super.paintComponent(g);
			}

		};

		panel.setLayout(null);
		scrollPane = new JScrollPane(panel);
		setContentPane(scrollPane);

		// ���̵�
		JLabel laid = new JLabel("아이디:");
		laid.setBounds(75, 90, 80, 20);
		laid.setForeground(new Color(36, 205, 198));
		panel.add(laid);
		idtext = new JTextField();
		idtext.setBounds(125, 90, 150, 20);
		panel.add(idtext);
		idtest = new JButton("아이디 검사");
		idtest.setForeground(Color.BLACK);
		idtest.setBounds(285, 90, 110, 20);
		idtest.addActionListener(this);
		panel.add(idtest);
		JLabel idex = new JLabel("*10글자이내로 입력");
		idex.setForeground(Color.RED);
		idex.setBounds(125, 110, 250, 18);
		panel.add(idex);

		// ��й�ȣ
		JLabel laps = new JLabel("패스워드:");
		laps.setForeground(new Color(36, 205, 198));
		laps.setBounds(62, 140, 80, 20);
		panel.add(laps);
		password = new JPasswordField(10);
		password.setBounds(125, 140, 90, 20);
		password.setEchoChar('*');
		panel.add(password);
		JLabel lapsex = new JLabel("*10글자 이내로 입력");
		lapsex.setForeground(Color.RED);
		lapsex.setBounds(225, 140, 150, 20);
		panel.add(lapsex);

		// ��й�ȣ Ȯ��
		JLabel lapstx = new JLabel("패스워드 확인:");
		lapstx.setBounds(32, 170, 110, 20);
		lapstx.setForeground(new Color(36, 205, 198));
		panel.add(lapstx);
		pstest = new JPasswordField(10);
		pstest.setBounds(125, 170, 90, 20);
		pstest.setEchoChar('*');
		panel.add(pstest);
		btnpass = new JButton("비밀번호 확인");
		btnpass.setForeground(Color.BLACK);
		btnpass.setBounds(225, 170, 120, 20);
		btnpass.addActionListener(this);
		panel.add(btnpass);

		// �̸�
		JLabel laname = new JLabel("이름:");
		laname.setForeground(new Color(36, 205, 198));
		laname.setBounds(88, 200, 80, 20);
		panel.add(laname);
		name = new JTextField();
		name.setBounds(125, 200, 90, 20);
		panel.add(name);

		// �ֹι�ȣ
		JLabel lanumber = new JLabel("주민번호:");
		lanumber.setForeground(new Color(36, 205, 198));
		lanumber.setBounds(62, 230, 90, 20);
		panel.add(lanumber);
		number = new JTextField();
		number.setBounds(125, 230, 70, 20);
		panel.add(number);
		JLabel sep = new JLabel("ㅡ");
		sep.setForeground(new Color(36, 205, 198));
		sep.setBounds(195, 230, 20, 20);
		panel.add(sep);
		behind = new JPasswordField();
		behind.select(1, 1);
		behind.setEchoChar('*');
		behind.setBounds(210, 230, 90, 20);
		panel.add(behind);

		// �̸���
		JLabel laemail = new JLabel("이메일:");
		laemail.setForeground(new Color(36, 205, 198));
		laemail.setBounds(75, 260, 70, 20);
		panel.add(laemail);
		email1 = new JTextField();
		email2 = new JTextField();
		email1.setBounds(125, 260, 70, 20);
		email2.setBounds(212, 260, 90, 20);
		panel.add(email1);
		panel.add(email2);
		JLabel mail = new JLabel("@");
		mail.setForeground(new Color(36, 205, 198));
		mail.setBounds(197, 260, 20, 20);
		panel.add(mail);
		chkemail = new JCheckBox("메일 수신 동의");
		chkemail.setForeground(Color.RED);
		chkemail.setBounds(121, 280, 150, 20);
		chkemail.setBorderPainted(false);
		chkemail.setContentAreaFilled(false);
		chkemail.setFocusPainted(false);
		panel.add(chkemail);
		chmail = new Choice();
		chmail.setBounds(307, 260, 70, 20);

		chmail.add("naver.com");
		chmail.add("daum.net");
		chmail.add("yahoo.co.kr");
		chmail.add("gmail.com");
		chmail.add("hanmail.net");
		chmail.add("nate.com");
		chmail.addItemListener(this);
		panel.add(chmail);

		// �ڵ�����ȣ
		JLabel laphone = new JLabel("핸드폰:");
		laphone.setBounds(75, 310, 50, 20);
		laphone.setForeground(new Color(36, 205, 198));
		panel.add(laphone);
		JTextField phone1 = new JTextField();
		phone1.setBounds(197, 310, 50, 20);
		panel.add(phone1);
		JTextField phone2 = new JTextField();
		phone2.setBounds(270, 310, 50, 20);
		panel.add(phone2);
		JLabel la1 = new JLabel("ㅡ");
		la1.setForeground(new Color(36, 205, 198));
		JLabel la2 = new JLabel("ㅡ");
		la2.setForeground(new Color(36, 205, 198));
		la1.setBounds(180, 310, 30, 20);
		panel.add(la1);
		la2.setBounds(250, 310, 30, 20);
		panel.add(la2);
		chphone = new Choice();
		chphone.setBounds(125, 310, 50, 20);
		chphone.add("010");
		chphone.add("011");
		chphone.add("012");
		chphone.add("013");
		chphone.add("014");
		chphone.add("015");
		chphone.add("016");
		chphone.add("017");
		chphone.add("018");
		chphone.add("019");
		panel.add(chphone);

		// ��
		member = new JButton("회원 가입");
		member.setBounds(115, 350, 100, 30);
		member.addActionListener(this);
		member.setForeground(Color.BLACK);
		panel.add(member);

		cancel = new JButton("가입취소");
		cancel.setBounds(225, 350, 100, 30);
		cancel.setForeground(Color.BLACK);
		panel.add(cancel);
		cancel.addActionListener(this);

		setVisible(true);

	}

	public static void main(String[] args) {
		PCDB.init();
		new Member("회원 가입", 450, 500);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (chmail.getSelectedIndex() != 0) {
			email2.setText(chmail.getSelectedItem());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj == cancel) {
			dispose();
		} else if (obj == btnpass) {
			if (password.getText().length() >= 10) {
				JOptionPane.showMessageDialog(null, "10자리가 넘어갔습니다\n다시  입력해주세요", "Message", JOptionPane.WARNING_MESSAGE);
				check = false;
			} else if (password.getText().equals(pstest.getText())) {
				JOptionPane.showMessageDialog(null, "비밀번호 일치");
				check = true;
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호 불일치", "Message", JOptionPane.ERROR_MESSAGE);
				check = false;

			}
		} else if (obj == member) {
			String id = idtext.getText();
			String strpass = password.getText();
			String sql = "INSERT INTO PC(ID,PW) " + "VALUES('" + id + "','" + strpass + "')";

			System.out.println(sql);
			ResultSet rs = PCDB.getResultSet(sql);

			if (check) {
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "제대로 다시 입력해주세요", "Message", JOptionPane.ERROR_MESSAGE);

			}
		}

		else if (obj == idtest) {

			String id = idtext.getText();
			String sql = "SELECT * FROM PC WHERE ID='" + id + "'";
			System.out.println(sql);
			ResultSet rs = PCDB.getResultSet(sql);
			try {
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "아이디 생성불가", "Message", JOptionPane.WARNING_MESSAGE);
					check = false;
				} else {
					JOptionPane.showMessageDialog(null, "아이디 생성 가능");
					check = true;
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
}
