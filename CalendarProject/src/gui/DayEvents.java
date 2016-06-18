package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.*;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DayEvents extends JFrame {
	
	private String date;
	/**
	 * Launch the application.
	 * @return 
	 */
	public static void init(final String date){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayEvents frame = new DayEvents(date);
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
	public DayEvents(final String date) {
		
		this.date = date;

		final List<Event> eventList = EventList.getEventListForSpecifiedDate(date);

		
		setTitle("Events");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 43, 46, 14);
		getContentPane().add(lblFrom);
		
		JLabel lblNewLabel = new JLabel("<html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 61, 46, 189);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(66, 43, 46, 14);
		getContentPane().add(lblTo);
		
		JLabel lblNewLabel_1 = new JLabel("<html>");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(66, 61, 46, 189);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setBounds(122, 43, 69, 14);
		getContentPane().add(lblDesc);
		
		JLabel lblNewLabel_2 = new JLabel("<html>");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setBounds(122, 61, 132, 189);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Place");
		lblNewLabel_3.setBounds(264, 43, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("<html>");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setBounds(264, 61, 160, 189);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnCreateEvent = new JButton("Create Event");
		btnCreateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateEventWindow.init(date);
				dispose();
			}
		});
		btnCreateEvent.setBounds(171, 11, 106, 23);
		getContentPane().add(btnCreateEvent);
		
		JLabel lblEventsFor = new JLabel("Events for :  " + date);
		lblEventsFor.setBounds(10, 18, 139, 14);
		getContentPane().add(lblEventsFor);
		
		for(Event evt : eventList){
			lblNewLabel.setText(lblNewLabel.getText() + evt.getFrom() + "<br>");
			lblNewLabel_1.setText(lblNewLabel_1.getText() + evt.getTo() + "<br>");
			lblNewLabel_2.setText(lblNewLabel_2.getText() + evt.getDescription() + "<br>");
			lblNewLabel_4.setText(lblNewLabel_4.getText() + evt.getPlace() + "<br>");
		}
		lblNewLabel.setText(lblNewLabel.getText() + "</html>");
		lblNewLabel_1.setText(lblNewLabel_1.getText() + "</html>");
		lblNewLabel_2.setText(lblNewLabel_2.getText() + "</html>");
		lblNewLabel_4.setText(lblNewLabel_4.getText() + "</html>");
		
		JButton btnNewButton = new JButton("Save to XML");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("XML file","xml"));
				int result = chooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION){
					File fi = chooser.getSelectedFile();
					try{
						String path = fi.getPath();
						if (!path.endsWith(".xml")){
							path += ".xml";
						}
						XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
						x.writeObject(eventList);
						x.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				
			}
		});
		btnNewButton.setBounds(306, 11, 106, 23);
		getContentPane().add(btnNewButton);

	}
}