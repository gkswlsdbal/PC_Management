
package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Project extends JFrame implements ActionListener {
	private JPanel p1, p2, back;
	private JLabel id, password;
	private JTextField idtext;
	private JPasswordField passtext;
	private JButton login, member;
	private ImageIcon background;
	private JScrollPane scrollPane;

	public Project(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(1000, 300);

		// setLocationRelativeTo(this);


		background = new ImageIcon("images/back.png");

		back = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(background.getImage(), 0, 0, 1400, 770, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};

		back.setLayout(null);
		scrollPane = new JScrollPane(back);
		setContentPane(scrollPane);

	
		login = new JButton("로그인");
		login.setSize(80, 30);
		login.setLocation(130, 220);
		login.addActionListener(this);
		member = new JButton("회원가입");
		member.setSize(100, 30);
		member.setLocation(220, 220);
		member.addActionListener(this);

		
		id = new JLabel("ID:");
		id.setFont(new Font("SansSerif", Font.BOLD, 20));
		id.setSize(150, 100);
		id.setForeground(new Color(36, 205, 198));
		id.setLocation(95, 115);
		idtext = new JTextField("");
		idtext.setSize(130, 20);
		idtext.setLocation(200, 155);
		
		password = new JLabel("password:");
		password.setSize(150, 100);
		password.setFont(new Font("SansSerif", Font.BOLD, 20));
		password.setForeground(new Color(36, 205, 198));
		password.setLocation(95, 140);
		passtext = new JPasswordField("");
		passtext.setSize(130, 20);
		passtext.setLocation(200, 180);
		passtext.selectAll();
		passtext.setEchoChar('*');

		JLabel pc = new JLabel("PC방 관리");
		pc.setFont(new Font("SansSerif", Font.BOLD, 30));
		pc.setSize(200, 50);
		pc.setLocation(290, 290);

		JLabel pro = new JLabel("Program");
		pro.setFont(new Font("SansSerif", Font.BOLD, 50));
		pro.setForeground(new Color(36, 205, 198));
		pro.setSize(250, 60);
		pro.setLocation(115, 50);


		back.add(login);
		back.add(id);
		back.add(password);
		back.add(idtext);
		back.add(passtext);
		back.add(member);
		back.add(pc);
		back.add(pro);

		setVisible(true);

		//

	}

	public static void main(String[] args) {
		PCDB.init();
		SaleDB.init();
		new Project("Login", 460, 380);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj == member) {
			new Member("회원가입", 450, 500);
		} else if (obj == login) {
			String id = idtext.getText();
			String strpass = passtext.getText();
			boolean check = checkIDPW(id, strpass);

			if (check) {
				Runnable pc = new PC("PC방 관리 프로그램", 1400, 800);
				Thread t1 = new Thread(pc);
				t1.start();
				dispose();

			} else {
				JOptionPane.showMessageDialog(null, "일치하는 회원이 없습니다.", "Message", JOptionPane.WARNING_MESSAGE);
				idtext.setText("");
				passtext.setText("");
				idtext.requestFocus();
			}

		}
	}

	boolean checkIDPW(String id, String strpass) {
		// TODO Auto-generated method stub
		boolean check = false;
		String sql = "SELECT * FROM PC WHERE ID='" + id + "' AND PW='" + strpass + "'";
		System.out.println(sql);
		ResultSet rs = PCDB.getResultSet(sql);
		try {
			if (rs.next()) {
				check = true;
			} else {
				check = false;
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return check;
	}

}
