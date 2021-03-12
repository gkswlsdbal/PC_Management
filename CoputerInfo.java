package Project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CoputerInfo extends JFrame {
	private JLabel lblmoney, lblmember, lblmon, lblcheck;

	public CoputerInfo(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);
		setLayout(null);

		setLocation(1000, 300);
		// setLocationRelativeTo(this);

		lblmoney = new JLabel("결제한 금액: ");
		lblmoney.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblmoney.setForeground(Color.DARK_GRAY);
		lblmoney.setBounds(20, 50, 100, 20);
		add(lblmoney);

		lblmember = new JLabel("사용중인 회원: ");
		lblmember.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblmember.setForeground(Color.DARK_GRAY);
		lblmember.setBounds(20, 20, 100, 20);
		add(lblmember);

		lblcheck = new JLabel("");
		lblcheck.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblcheck.setForeground(Color.DARK_GRAY);
		lblcheck.setBounds(20, 80, 250, 20);
		add(lblcheck);

		setVisible(true);

	}

	public void setMoney(String money) {
		lblmon = new JLabel(money + "원 입니다");
		lblmon.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblmon.setForeground(Color.DARK_GRAY);
		lblmon.setBounds(110, 50, 100, 20);

		add(lblmon);
	}

	public void setCheck(boolean flag) {
		if (flag) {

			lblcheck.setText("현재 컴터 이용중입니다.");

		} else {

			lblcheck.setText("현재 컴터전원이 꺼져있습니다.");
		}

	}

	public static void main(String[] args) {
       
	}

}
