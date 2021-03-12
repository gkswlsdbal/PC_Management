package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Sales extends JFrame implements ActionListener {

	private JTable table;
	private JButton btn, btncel;
	private JPanel panel;
	private DefaultTableModel model;

	public Sales(String title, int w, int h) {

		setTitle(title);
		setSize(w, h);
		setLayout(new BorderLayout());

		setLocation(1000, 300);
		// setLocationRelativeTo(this);
		JLabel lbl = new JLabel("PC방 매상");
		lbl.setFont(new Font("SansSerif", Font.BOLD, 20));

		lbl.setForeground(new Color(204, 051, 205));

		btn = new JButton("조회");
		btn.addActionListener(this);

		btncel = new JButton("초기화");
		btncel.addActionListener(this);

		String header[] = { "컴퓨터", "충전한 가격", "정산 과정", "총 금액" };
		String[][] contents = new String[50][4];

		model = new DefaultTableModel(contents, header);
		table = new JTable(model);
		table.getColumn("총 금액").setPreferredWidth(20);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(400, 400));

		panel = new JPanel();
		panel.setLayout(new FlowLayout());

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // ����Ʈ���̺������� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // �������� ���������� ���ͷ� �����Ѵ�.

		TableColumnModel tcm = table.getColumnModel(); // ������ ���̺��� �÷����� ������w
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		panel.add(lbl);
		panel.add(btn);
		panel.add(btncel);
		add(scrollPane, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
		pack();

		setVisible(true);
	}

	public static void main(String[] args) {
		SaleDB.init();
		new Sales("매상", 400, 400);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj == btn) {
			model.setNumRows(50);
			String sql = "SELECT * FROM SALE";
			ResultSet rs = SaleDB.getResultSet(sql);

			int count = 0;
			int sum = 0;
			String str = null;
			try {
				while (rs.next()) {

					str = rs.getString(2);
					sum += Integer.parseInt(str);
					str = String.valueOf(sum);
					table.setValueAt(Integer.parseInt(rs.getString(1))+1+ "번 컴퓨터", count, 0);
					table.setValueAt(rs.getString(2) + "원", count, 1);
					table.setValueAt(str + "원", count, 2);
					count++;
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (str == null) {
				table.setValueAt("", 0, 3);
			} else {
				table.setValueAt(str + "원", 0, 3);
			}

		 } else if (obj == btncel) {
			String sql = "DELETE FROM SALE";
			ResultSet rs = SaleDB.getResultSet(sql);
			model.setNumRows(0);

		}
	}

}
