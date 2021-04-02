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

public class MemberInfo extends JFrame {
	private JTable table;
	private JButton btn, btncel;
	private JPanel panel;
	private DefaultTableModel model;

	public MemberInfo(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);

		setLocation(1000, 300);
		// setLocationRelativeTo(this);
		JLabel lbl = new JLabel("현재 회원");
		lbl.setFont(new Font("SansSerif", Font.BOLD, 20));

		lbl.setForeground(new Color(204, 051, 205));

		String header[] = { "가입한 회원 아이디" };
		String[][] contents = new String[20][1];

		model = new DefaultTableModel(contents, header);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(200, 300));

		panel = new JPanel();
		panel.setLayout(new FlowLayout());

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); 
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); 

		TableColumnModel tcm = table.getColumnModel(); 
		tcm.getColumn(0).setCellRenderer(dtcr);
		panel.add(lbl);
		add(scrollPane, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);

		String sql = "SELECT * FROM PC";
		ResultSet rs = PCDB.getResultSet(sql);
		String str = null;
		int count = 0;
		try {
			while (rs.next()) {
				table.setValueAt(rs.getString(1) + "님 회원", count, 0);
				count++;
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		pack();
		setVisible(true);

	}

	public static void main(String[] args) {
		PCDB.init();
		new MemberInfo("", 0, 0);
	}

}
