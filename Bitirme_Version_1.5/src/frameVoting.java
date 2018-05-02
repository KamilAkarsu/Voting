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
import java.io.File;
import java.io.IOException;


public class frameVoting extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox_VotingCount;
	private JComboBox<String> comboBox_OutputInf;
	private File[] files;
	private boolean sonuc;
	private JLabel lblVotingCount,lbl_Result ;
	private JButton btnRun;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameVoting frame = new frameVoting();
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
	public frameVoting() {
		setResizable(false);
		setTitle("Voting");
		setBounds(100, 100, 891, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadFile = new JButton("Load Csv File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameVoting_LoadFile load_file = new  frameVoting_LoadFile();
				frameVoting_AttributeControl attr_cont = new  frameVoting_AttributeControl();
				
				try {
					files = load_file.getFile();
					sonuc = attr_cont.IsAttr_Equal(files);
					
					if(files.length==4)
					{
						if(sonuc==true)
						{
							lblVotingCount.setEnabled(true);
							comboBox_VotingCount.setEnabled(true);
							btnRun.setEnabled(true);	
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Veri Setlerinin Attribute Sayýlarý Ayný Olmak Zorundadýr");
							lblVotingCount.setEnabled(false);
							comboBox_VotingCount.setEnabled(false);
							btnRun.setEnabled(false);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "4 Veri Seti Zorunludur");
						lblVotingCount.setEnabled(false);
						comboBox_VotingCount.setEnabled(false);
						btnRun.setEnabled(false);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Dosya Yükleme Hatasý");
					lblVotingCount.setEnabled(false);
					comboBox_VotingCount.setEnabled(false);
					btnRun.setEnabled(false);
				}
			}
		});
		btnLoadFile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLoadFile.setBounds(10, 40, 200, 50);
		contentPane.add(btnLoadFile);
		
		lblVotingCount = new JLabel("Voting Count");
		lblVotingCount.setEnabled(false);
		lblVotingCount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVotingCount.setBounds(310, 28, 106, 19);
		contentPane.add(lblVotingCount);
		
		comboBox_VotingCount = new JComboBox<String>();
		comboBox_VotingCount.setEnabled(false);
		comboBox_VotingCount.setBounds(309, 53, 107, 37);
		contentPane.add(comboBox_VotingCount);
		comboBox_VotingCount.addItem("2/4");
		comboBox_VotingCount.addItem("3/4");
		comboBox_VotingCount.addItem("4/4");
		
		btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int voting_count = 0;
				String voting_co = (String) comboBox_VotingCount.getSelectedItem();
				if (voting_co=="2/4")
					voting_count=2;
				else if (voting_co=="3/4")
					voting_count=3;
				else if (voting_co=="4/4")
					voting_count=4;
				
				frameVotin_RunVoting run_voting = new frameVotin_RunVoting();
				try {
					String result = run_voting.RunVoting(files,voting_count);
					comboBox_OutputInf.addItem(result);

				} catch (IOException e1) {
					System.out.println("hata");
				}
					
			}
		});
		btnRun.setEnabled(false);
		btnRun.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRun.setBounds(10, 165, 796, 37);
		contentPane.add(btnRun);
		
		lbl_Result = new JLabel("Output Information :");
		lbl_Result.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_Result.setBounds(10, 265, 159, 37);
		contentPane.add(lbl_Result);
		
		comboBox_OutputInf = new JComboBox<String>();
		comboBox_OutputInf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_OutputInf.getSelectedItem()!="")
				{
					comboBox_OutputInf.removeItem("");
					Desktop desktop_1 = Desktop.getDesktop();
					File file_1 = new File((String) comboBox_OutputInf.getSelectedItem());
					try {
						desktop_1.open(file_1.getParentFile());
					} catch (IOException e1) {
					}
				
				}
			}
		});
		comboBox_OutputInf.setBounds(179, 265, 629, 37);
		contentPane.add(comboBox_OutputInf);
		comboBox_OutputInf.addItem("");
	}

}
