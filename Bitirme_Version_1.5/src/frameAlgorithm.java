import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class frameAlgorithm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private File file;
	private JComboBox<String> comboBox_Result;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameAlgorithm frame = new frameAlgorithm();
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
	public frameAlgorithm() {
		setResizable(false);
		setTitle("Run Algorithm");
		setBounds(100, 100, 884, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadFile = new JButton("Load Arff File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAlgorithm_LoadFile load_file = new frameAlgorithm_LoadFile();
				file=null;
				try {
					file=load_file.getFile();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "LoadFile ERROR");
				}
			}
		});
		btnLoadFile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLoadFile.setBounds(106, 75, 196, 44);
		contentPane.add(btnLoadFile);
		
		JLabel lbl_1 = new JLabel("Cross Validation");
		lbl_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_1.setBounds(357, 36, 132, 27);
		contentPane.add(lbl_1);
		
		JLabel lbl_2 = new JLabel("Algorithm Name");
		lbl_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_2.setBounds(570, 36, 132, 27);
		contentPane.add(lbl_2);
		
		final JComboBox<Integer> comboBox_CrossValidation = new JComboBox<Integer>();
		comboBox_CrossValidation.setMaximumRowCount(9);
		comboBox_CrossValidation.setBounds(357, 75, 132, 44);
		contentPane.add(comboBox_CrossValidation);
		comboBox_CrossValidation.addItem(2);
		comboBox_CrossValidation.addItem(3);
		comboBox_CrossValidation.addItem(4);
		comboBox_CrossValidation.addItem(5);
		comboBox_CrossValidation.addItem(6);
		comboBox_CrossValidation.addItem(7);
		comboBox_CrossValidation.addItem(8);
		comboBox_CrossValidation.addItem(9);
		comboBox_CrossValidation.addItem(10);
		
		comboBox_Result = new JComboBox<String>();
		comboBox_Result.addItem("");
		comboBox_Result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_Result.getSelectedItem()!="")
				{
					comboBox_Result.removeItem("");
					Desktop desktop_1 = Desktop.getDesktop();
					File file_1 = new File((String) comboBox_Result.getSelectedItem());
					try {
						desktop_1.open(file_1.getParentFile());
					} catch (IOException e1) {
					}
				
				}
			}
		});
		comboBox_Result.setBounds(179, 289, 679, 27);
		contentPane.add(comboBox_Result);
		
		final JComboBox<String> comboBox_AlgorithmName = new JComboBox<String>();
		comboBox_AlgorithmName.setBounds(570, 75, 132, 44);
		contentPane.add(comboBox_AlgorithmName);
		comboBox_AlgorithmName.addItem("Naive Bayes");
		comboBox_AlgorithmName.addItem("IBk");
		comboBox_AlgorithmName.addItem("J48");
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAlgorithm_Run run = new frameAlgorithm_Run();
				if(file==null)
					JOptionPane.showMessageDialog(null, "Dosya Seçmek Zorundasýnýz");
				else
				{
					int com1 = (Integer)comboBox_CrossValidation.getSelectedItem();
					String com2 = (String) comboBox_AlgorithmName.getSelectedItem();
					String result = run.RunFile(file, com1, com2);
					
					String file_name = file.getName().toString();
					String file_name_without_extension = file_name.substring(0, file_name.lastIndexOf('.'));
					String file_name_parent = file.getParent();
					String new_file_path_and_name = file_name_parent+"\\"+file_name_without_extension+"_"+com2+"_crossVal_"+com1+".doc";
					
					File last_file = new File(new_file_path_and_name);
					try {
						FileWriter fileWriter_2 = new FileWriter(last_file);
						BufferedWriter bWriter = new BufferedWriter(fileWriter_2);
						String result_last = "\nCross Validation:"+com1+"\nFeature Selection Algorithm:"+com2+"\nFilePath:"+file.getAbsolutePath()+"\n\n\n"+result;
						bWriter.write(result_last);
						bWriter.close();
						
						comboBox_Result.addItem(new_file_path_and_name);
						
					} catch (IOException e1) {
						//DO NOTHING
					}
				}
			}
		});
		btnRun.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRun.setBounds(106, 169, 597, 44);
		contentPane.add(btnRun);
		
		JLabel lblNewLabel = new JLabel("Output Information :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 284, 159, 33);
		contentPane.add(lblNewLabel);
		
		
	}
}
