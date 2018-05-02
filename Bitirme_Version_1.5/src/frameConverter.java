import java.awt.EventQueue;
import java.awt.Desktop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.Panel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;


public class frameConverter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameConverter frame = new frameConverter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frameConverter() {
		setResizable(false);
		setTitle("File Converter");
		setBounds(100, 100, 605, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 564, 282);
		contentPane.add(tabbedPane);
		
		Panel panel = new Panel();
		panel.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Arff to Csv", null, panel, null);
		panel.setLayout(null);
		
		JButton btnLoadArffFile = new JButton("Load Arff File");
		btnLoadArffFile.setToolTipText("Only Arff  file can be selected");
		btnLoadArffFile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLoadArffFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameConverter_ArfftoCsv frmCon_1 = new frameConverter_ArfftoCsv();
				String result_1 = frmCon_1.ArfftoCsv();
				if(result_1!= "")
					comboBox_1.addItem(result_1);
			}
		});
		btnLoadArffFile.setBounds(95, 67, 200, 52);
		panel.add(btnLoadArffFile);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"~~TRANSACTION~~"}));
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((comboBox_1.getSelectedItem()!="") && (comboBox_1.getSelectedItem() != "~~TRANSACTION~~" ))
				{
					comboBox_1.removeItem("~~TRANSACTION~~");
					Desktop desktop_1 = Desktop.getDesktop();
					File file_1 = new File((String) comboBox_1.getSelectedItem());
					try {
						desktop_1.open(file_1.getParentFile());
					} catch (IOException e1) {
					}
				}
			}
		});
		comboBox_1.setBounds(10, 172, 527, 20);
		
		panel.add(comboBox_1);
		Panel panel_1 = new Panel();
		panel_1.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Csv to Arff", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnLoadCsvFile = new JButton("Load Csv File");
		btnLoadCsvFile.setToolTipText("Only Csv file can be selected");
		btnLoadCsvFile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLoadCsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameConverter_CsvtoArff frmCon_2 = new frameConverter_CsvtoArff();
				String result_2 = frmCon_2.CsvtoArff();
				if(result_2!= "")
					comboBox_2.addItem(result_2);
			}
		});
		btnLoadCsvFile.setBounds(95, 67, 200, 52);
		panel_1.add(btnLoadCsvFile);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((comboBox_2.getSelectedItem()!="") && (comboBox_2.getSelectedItem() != "~~TRANSACTION~~" ))
				{
					comboBox_2.removeItem("~~TRANSACTION~~");
					Desktop desktop_2 = Desktop.getDesktop();
					File file_2 = new File((String) comboBox_2.getSelectedItem());
					try {
						desktop_2.open(file_2.getParentFile());
					} catch (Exception e2) {
					}
				}
			}
		});
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"~~TRANSACTION~~"}));
		comboBox_2.setBounds(10, 172, 527, 20);
		panel_1.add(comboBox_2);
		
		
	}
}
