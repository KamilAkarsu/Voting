import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;


public class main_window {

	private JFrame frmOylamaTabanlYazlm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_window window = new main_window();
					window.frmOylamaTabanlYazlm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main_window() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOylamaTabanlYazlm = new JFrame();
		frmOylamaTabanlYazlm.setTitle("OYLAMA TABANLI YAZILIM METR\u0130K SE\u00C7\u0130M\u0130 \u0130LE YAZILIM HATA TESP\u0130T BA\u015EARIMININ ARTTIRILMASI");
		frmOylamaTabanlYazlm.setResizable(false);
		frmOylamaTabanlYazlm.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frmOylamaTabanlYazlm.getContentPane().setBackground(Color.WHITE);
		frmOylamaTabanlYazlm.getContentPane().setLayout(null);
		
		JButton btnConverter = new JButton("Converter");
		btnConverter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConverter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameConverter frmConverter = new frameConverter();
				frmConverter.setVisible(true);
			}
		});
		btnConverter.setBounds(400, 49, 341, 54);
		frmOylamaTabanlYazlm.getContentPane().add(btnConverter);
		
		JButton btnAlgorithm = new JButton("Run Algorithm");
		btnAlgorithm.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAlgorithm frmAlgo = new frameAlgorithm();
				frmAlgo.setVisible(true);
			}
		});
		btnAlgorithm.setBounds(400, 132, 341, 54);
		frmOylamaTabanlYazlm.getContentPane().add(btnAlgorithm);
		
		JButton btnFSelection = new JButton("Feature Selection");
		btnFSelection.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnFSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameFSelection frmFSelection = new frameFSelection();
				frmFSelection.setVisible(true);
			}
		});
		btnFSelection.setBounds(400, 219, 341, 54);
		frmOylamaTabanlYazlm.getContentPane().add(btnFSelection);
		
		JButton btnVoting = new JButton("Voting");
		btnVoting.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVoting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameVoting frmVoting = new frameVoting();
				frmVoting.setVisible(true);
			}
		});
		btnVoting.setBounds(400, 298, 341, 54);
		frmOylamaTabanlYazlm.getContentPane().add(btnVoting);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/weka.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(32, 11, 324, 341);
		frmOylamaTabanlYazlm.getContentPane().add(lblNewLabel);
		
		frmOylamaTabanlYazlm.setBounds(100, 100, 831, 419);
		frmOylamaTabanlYazlm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
