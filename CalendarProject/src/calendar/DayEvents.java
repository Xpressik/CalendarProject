package calendar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

public class DayEvents extends JFrame {

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void init(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayEvents frame = new DayEvents();
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
	public DayEvents() {
		setTitle("Events");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 11, 46, 14);
		getContentPane().add(lblFrom);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 29, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(66, 11, 46, 14);
		getContentPane().add(lblTo);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(66, 29, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblDesc = new JLabel("Desc");
		lblDesc.setBounds(122, 11, 46, 14);
		getContentPane().add(lblDesc);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(122, 29, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Place");
		lblNewLabel_3.setBounds(227, 11, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(227, 29, 46, 14);
		getContentPane().add(lblNewLabel_4);
	}
}
