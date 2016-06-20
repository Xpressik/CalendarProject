package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.*;
import logic.DeleteButtonListener;
import logic.SaveXMLListener;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;

public class DayList extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	/**
	 * Launch the application.
	 * @return 
	 */
	public static void init(final String date, String formattedDate){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayList frame = new DayList(date, formattedDate);
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
	public DayList(final String date, String formattedDate) {
		
		this.date = date;

		final List<Event> eventList = EventList.getEventListForSpecifiedDate(date);

		
		setTitle("Events");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 300);
		getContentPane().setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 43, 46, 14);
		getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(66, 43, 46, 14);
		getContentPane().add(lblTo);
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setBounds(168, 43, 69, 14);
		getContentPane().add(lblDesc);
		
		JLabel lblNewLabel_3 = new JLabel("Place");
		lblNewLabel_3.setBounds(337, 43, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnCreateEvent = new JButton("Create Event");
		btnCreateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate ld = LocalDate.now();
				CreateEventWindow.init(date, ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				dispose();
			}
		});
		btnCreateEvent.setBounds(171, 11, 106, 23);
		getContentPane().add(btnCreateEvent);
		
		JLabel lblEventsFor = new JLabel("Events for :  " + date);
		lblEventsFor.setBounds(10, 18, 139, 14);
		getContentPane().add(lblEventsFor);
		
		JButton btnNewButton = new JButton("Save to XML");
		btnNewButton.addActionListener(new SaveXMLListener(eventList));
		btnNewButton.setBounds(306, 11, 106, 23);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 402, 182);
		getContentPane().add(scrollPane);
		
		final JList list = new JList();
		DayList.repaintList(list, eventList);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton_1 = new JButton("Delete"); 
		btnNewButton_1.addActionListener(new DeleteButtonListener(list, eventList));	
		btnNewButton_1.setBounds(412, 39, 89, 23);
		getContentPane().add(btnNewButton_1);		

	}
	private static String alignment(int length){
		
		String result = new String();		
		length = 40 - length;
		
		while(length > 0){
			result += "  ";
			length--;
		}
		return result;
	}
	
	public static void repaintList(JList list, List<Event> eventList){
		int size = eventList.size();
		String [] str = new String[size];
		for (int i= 0; i< size; i++){
			Event event = eventList.get(i);
			str[i] = (event.getFrom() + "    " + event.getTo() + "    " + event.getDescription() + alignment(event.getDescription().length()) + event.getPlace());
		}
		list.setListData(str);
	}
}