package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chatting extends JFrame implements ActionListener {

	private JPanel panCenter, panBottom;
	private JTextArea area;
	private JTextField tf;
	private JButton btn;

	public Chatting(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);

		setLayout(new BorderLayout());
		setLocation(1000, 300);
		// setLocationRelativeTo(this);

		setPanCenter();
		setPanBottom();
		add(panCenter, BorderLayout.CENTER);
		add(panBottom, BorderLayout.SOUTH);
		setVisible(true);

	}

	private void setPanBottom() {
		// TODO Auto-generated method stub
		panBottom = new JPanel();

		tf = new JTextField(18);
		tf.addActionListener(this);
		btn = new JButton("입력");
		btn.addActionListener(this);
		panBottom.add(tf);
		panBottom.add(btn);
	}

	private void setPanCenter() {
		// TODO Auto-generated method stub
		panCenter = new JPanel();
		panCenter.setLayout(new BorderLayout());

		area = new JTextArea(7, 20);
		area.setEditable(false);
		JScrollPane sp = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panCenter.add(sp);
	}

	public static void main(String[] args) {
       new Chatting("하이",300,300);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj == btn || obj == tf) {
			String str = tf.getText();
			if (!str.equals("")) {
				area.append(" 관리자메세지: " + tf.getText() + "\n");
				tf.setText("");
			}
		}

	}

}
