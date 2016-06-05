package calendar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JButton;

public class DayEvents extends JFrame {
	private final Calendar calendar;
	/**
	 * Launch the application.
	 * @return 
	 */
	public static void init(final Calendar calendar){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayEvents frame = new DayEvents(calendar);
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
	public DayEvents(final Calendar calendar) {
		
		this.calendar = calendar;
		setTitle("Events");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 43, 46, 14);
		getContentPane().add(lblFrom);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 61, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(66, 43, 46, 14);
		getContentPane().add(lblTo);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(66, 61, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setBounds(122, 43, 69, 14);
		getContentPane().add(lblDesc);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(122, 61, 132, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Place");
		lblNewLabel_3.setBounds(264, 43, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(264, 61, 160, 14);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnCreateEvent = new JButton("Create Event");
		btnCreateEvent.setBounds(171, 11, 117, 23);
		getContentPane().add(btnCreateEvent);
		
		JLabel lblEventsFor = new JLabel("Events for :  " + calendar.get(Calendar.DAY_OF_MONTH) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.YEAR));
		lblEventsFor.setBounds(10, 18, 139, 14);
		getContentPane().add(lblEventsFor);
	}
}
