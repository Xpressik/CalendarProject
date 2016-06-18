package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.xml.internal.ws.api.ComponentsFeature;

import data.*;
import logic.PopClickListener;

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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;

public class DayList extends JFrame {
	
	private String date;
	/**
	 * Launch the application.
	 * @return 
	 */
	public static void init(final String date){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayList frame = new DayList(date);
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
	public DayList(final String date) {
		
		this.date = date;

		List<Event> eventList = EventList.getEventListForSpecifiedDate(date);

		
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
		lblDesc.setBounds(122, 43, 69, 14);
		getContentPane().add(lblDesc);
		
		JLabel lblNewLabel_3 = new JLabel("Place");
		lblNewLabel_3.setBounds(264, 43, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 402, 182);
		getContentPane().add(scrollPane);
		
		JList list = new JList();
		DayList.repaintList(list, eventList);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton_1 = new JButton("Delete"); // uniemozliwic usuwanie jesli nic nie jest klikniete
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedEventIndex = list.getSelectedIndex();
				try{
					EventList.deleteEvent(eventList.get(selectedEventIndex));
				
				eventList.remove(selectedEventIndex);
				DayList.repaintList(list, eventList);
				}
				catch(Exception e){
					
				}
			}
		});
		
		//Component cmp = list.getComponents(); // DODAÆ DO KA¯DEGO ELEMENTU LISTY LISTENERA DO POPMENU
		
		btnNewButton_1.setBounds(412, 39, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnEdit.setBounds(412, 88, 89, 23);
		getContentPane().add(btnEdit);
		
		
		list.addMouseListener(new PopClickListener());
		

	}
	public static void repaintList(JList list, List<Event> eventList){
		int size = eventList.size();
		String [] str = new String[size];
		for (int i= 0; i< size; i++){
			Event event = eventList.get(i);
			str[i] = (event.getFrom() + " " + event.getTo() + " " + event.getDescription() + " "  + event.getPlace());
		}
		list.setListData(str);
	}
}