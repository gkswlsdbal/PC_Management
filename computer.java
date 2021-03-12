package Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import menu.MyFrame;

public class computer extends JFrame {
	private JPanel panel;
	private ImageIcon icon;
	private JTable table;

	public computer(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);
		setLayout(new BorderLayout());
		setLocation(1000, 300);
		// setLocationRelativeTo(this);

		icon = new ImageIcon("images/com.png");
		panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, 450, 300, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setPreferredSize(new Dimension(300, 300));

		String header[] = { "품목", "제품명" };
		String[][] contents = new String[10][2];
		table = new JTable(contents, header);
		table.getColumn("품목").setPreferredWidth(5);
		table.getColumn("제품명").setPreferredWidth(120);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(450, 250));
		add(panel, BorderLayout.NORTH);
		add(scrollPane);

		table.setValueAt("                  CPU", 0, 0);
		table.setValueAt("                Intel i9-10900K 피씨 디렉트", 0, 1);
		table.setValueAt("              메인보드", 1, 0);
		table.setValueAt("  GIGABYTE Z490 AORUS XTREME WATER", 1, 1);
		table.setValueAt("           그래픽카드", 2, 0);
		table.setValueAt("        AORUS Geforce RTX 2080 Ti 11G", 2, 1);
		table.setValueAt("                  SSD", 3, 0);
		table.setValueAt("        AORUS RGB AIC NVMe SSD 512GB", 3, 1);
		table.setValueAt("                 메모리", 4, 0);
		table.setValueAt("           G.SKILL DDR4 64G PC4-25600", 4, 1);
		table.setValueAt("              수납부품", 5, 0);
		table.setValueAt("                Thermaltake 수로플레이트", 5, 1);
		table.setValueAt("                팬쿨러", 6, 0);
		table.setValueAt("            Thermaltake Pacific C-PRO 피딩", 6, 1);
		table.setValueAt("                케이스", 7, 0);
		table.setValueAt("             Thermaltake AH T600 BLACK", 7, 1);
		table.setValueAt("             사운드카드", 8, 0);
		table.setValueAt("            SoundBLASTTER AE-9 피씨디렉트", 8, 1);
		table.setValueAt("                  파워", 9, 0);
		table.setValueAt("   Enermax Platimax D.F. 1050W EPF1050EWT", 9, 1);

		pack();
		setVisible(true);

	}

	public static void main(String[] args) {
		new computer("컴퓨터 정보", 400, 400);
	}

}
