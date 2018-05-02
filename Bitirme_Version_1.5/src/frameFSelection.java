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
import javax.swing.JTextField;

import weka.core.Instances;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;


public class frameFSelection extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private File file;
	private JComboBox<String> comboBox_FSAlgorithm ;
	private JComboBox<String> comboBox_Result;
	private JLabel lbl_AttrCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameFSelection frame = new frameFSelection();
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
	public frameFSelection() {
		setResizable(false);
		setTitle("Feature Selection ");
		setBounds(100, 100, 851, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Feature Count :");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 124, 145, 26);
		contentPane.add(lblNewLabel);
		
		
		JButton btnLoadFile = new JButton("Load Arff File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameFSelection_LoadFile load_file = new frameFSelection_LoadFile();
				textField.setText("");
				file=null;
				try {
					file=load_file.getFile();
					BufferedReader f_breader = new BufferedReader(new FileReader(file));
					Instances train = new Instances(f_breader);
					lbl_AttrCount.setText(Integer.toString(train.numAttributes()-1)+" + Class Attribute");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "LOAD FÝLE ERROR");
				}
			}
		});
		btnLoadFile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLoadFile.setBounds(10, 58, 159, 36);
		contentPane.add(btnLoadFile);
		
		JLabel lblFS = new JLabel("Feature Selection Algorithm");
		lblFS.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFS.setBounds(297, 22, 234, 26);
		contentPane.add(lblFS);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(file==null )
					JOptionPane.showMessageDialog(null, "Dosya Seçmek Zorundasýnýz");
				else if (textField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Ranker Alaný Boþ");
				else
				{
					String file_path = file.getParent();
					//Verisetinin bulunduðu klasör dizini
					
					int ranker = Integer.parseInt(textField.getText().toString());
					//TextField'a girilen ranker deðeri
					
					String fs_name = (String)comboBox_FSAlgorithm.getSelectedItem();
					//Combobox'dan seçilen Algoritma adý
					
					String dataSet_name_withExtension = file.getName();
					String dataSet_name = dataSet_name_withExtension.substring(0, dataSet_name_withExtension.lastIndexOf('.'));
					//   Data set adýný uzantýdan baðýmsýz elde ediliyor
					
					BufferedReader breader = null ;

					try {
						breader = new BufferedReader(new FileReader(file));
						Instances train = new Instances(breader);
						
						if (train.classIndex() == -1)
							train.setClassIndex(train.numAttributes() - 1);
						
						if((train.numAttributes()-2)>=ranker && ranker >=2)
							//Girilen ranker sayýsý var olan attribute den büyük veya ayný sayýysa iþlem yapma hata ver
						{
							if(fs_name=="InfoGain")
							{
								frameFSelection_InfoGainFeatureSelection obj = new frameFSelection_InfoGainFeatureSelection();
								obj.Info_FS_Run(train, file_path, ranker,dataSet_name);
								comboBox_Result.addItem(file.getParent()+"\\"+fs_name+"_FS_"+dataSet_name+"_to_"+ranker+"_attr.arff");
							}
							else if (fs_name=="Correlation")
							{
								frameFSelection_CorrelationFeatureSelection obj = new frameFSelection_CorrelationFeatureSelection();
								obj.Corre_FS_Run(train, file_path, ranker,dataSet_name);
								comboBox_Result.addItem(file.getParent()+"\\"+fs_name+"_FS_"+dataSet_name+"_to_"+ranker+"_attr.arff");
							}
							else if (fs_name=="Relief")
							{
								frameFSelection_ReliefFeatureSelection obj = new frameFSelection_ReliefFeatureSelection();
								obj.Relief_FS_Run(train, file_path, ranker,dataSet_name);
								comboBox_Result.addItem(file.getParent()+"\\"+fs_name+"_FS_"+dataSet_name+"_to_"+ranker+"_attr.arff");
							}
							else if (fs_name=="Symmetrical")
							{
								frameFSelection_SymmetricalFeatureSelection obj = new frameFSelection_SymmetricalFeatureSelection();
								obj.Symmetrical_FS_Run(train, file_path, ranker,dataSet_name);
								comboBox_Result.addItem(file.getParent()+"\\"+fs_name+"_FS_"+dataSet_name+"_to_"+ranker+"_attr.arff");
							}
						}
						else
							JOptionPane.showMessageDialog(null, "Ranker Deðerini Kontrol Edip Tekrar Ediniz");
						
						} 
					catch ( Exception e1) {
							JOptionPane.showMessageDialog(null, "Bozuk veya Eksik Arff Dosyasý");
						} 
				}
			}
		});
		btnRun.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRun.setBounds(10, 187, 815, 36);
		contentPane.add(btnRun);
		
		JLabel lblResult = new JLabel("Output Information :");
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResult.setBounds(10, 278, 159, 36);
		contentPane.add(lblResult);
		
		JLabel lblRankerFold = new JLabel("Ranker Fold");
		lblRankerFold.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRankerFold.setBounds(607, 22, 145, 26);
		contentPane.add(lblRankerFold);
		
		comboBox_FSAlgorithm = new JComboBox<String>();
		comboBox_FSAlgorithm.setBounds(332, 60, 146, 36);
		contentPane.add(comboBox_FSAlgorithm);
		comboBox_FSAlgorithm.addItem("InfoGain");
		comboBox_FSAlgorithm.addItem("Correlation");
		comboBox_FSAlgorithm.addItem("Relief");
		comboBox_FSAlgorithm.addItem("Symmetrical");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char character = e.getKeyChar();
				if (((character < '0') || (character > '9'))
                        && (character != '\b')) {
                    e.consume();
                }
				if (textField.getText().length() >= 6 ) 
		            e.consume(); 
			}
		});
		textField.setBounds(598, 58, 114, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		comboBox_Result = new JComboBox<String>();
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
		comboBox_Result.setBounds(179, 288, 646, 26);
		contentPane.add(comboBox_Result);
		
		
		
		lbl_AttrCount = new JLabel("");
		lbl_AttrCount.setForeground(Color.RED);
		lbl_AttrCount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_AttrCount.setBounds(151, 124, 380, 26);
		contentPane.add(lbl_AttrCount);
		comboBox_Result.addItem("");
	}
}