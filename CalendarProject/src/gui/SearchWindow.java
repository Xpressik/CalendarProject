package gui;

import data.*;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SearchWindow extends JFrame {
	
	public static void init(final List<Event> evt){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchWindow frame = new SearchWindow(evt);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public SearchWindow(final List<Event> evt) {
		setTitle("Filtered Events");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 610, 400);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setBounds(10, 11, 69, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("From");
		lblNewLabel_1.setBounds(101, 11, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("To");
		lblNewLabel_2.setBounds(187, 11, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Description");
		lblNewLabel_3.setBounds(259, 11, 110, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Place");
		lblNewLabel_4.setBounds(450, 11, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel label = new JLabel("<html>");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setBounds(10, 36, 88, 314);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("<html>");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setBounds(101, 36, 46, 314);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("<html>");
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setBounds(187, 36, 46, 314);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("<html>");
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setBounds(258, 36, 182, 314);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("<html>");
		label_4.setVerticalAlignment(SwingConstants.TOP);
		label_4.setBounds(450, 36, 88, 314);
		getContentPane().add(label_4);
		
		for(Event e : evt){
			label.setText(label.getText() + e.getDate() + "<br>");
			label_1.setText(label_1.getText() + e.getFrom() + "<br>");
			label_2.setText(label_2.getText() + e.getTo() + "<br>");
			label_3.setText(label_3.getText() + e.getDescription() + "<br>");
			label_4.setText(label_4.getText() + e.getPlace() + "<br>");
		}
		label.setText(label.getText() + "</html>");
		label_1.setText(label_1.getText() + "</html>");
		label_2.setText(label_2.getText() + "</html>");
		label_3.setText(label_3.getText() + "</html>");
		label_4.setText(label_4.getText() + "</html>");		
	}
}
