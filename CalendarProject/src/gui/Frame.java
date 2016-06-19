package gui;

import data.*;
import logic.DBConnection;
import logic.DBData;
import logic.IncorrectPasswordException;
import logic.Listener;
import logic.PopClickListener;
import logic.Reminder;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JCalendar;
import java.awt.Point;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Frame implements ActionListener {
	
	private JCalendar calendar;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenu editMenu;
	private JMenuItem newEvent;
	private JMenuItem exit;
	private  JFrame frame;
	private JMenuItem filterByPlace;
	private JMenuItem filterByDescription;
	private JMenuItem filterByFrom;
	private JMenuItem filterByTo;
	private JMenuItem serializeToXML;
	private JMenuItem events;
	private JMenuItem DBconnect;
	private DBWindow dbWindow;
	
	private int currentMonth;
	private int currentYear;
	private JMenu settingsMenu;
	
	private Timer timer;

	
	public Frame(DBData dbData){
		init(dbData);
	}
	
	private void init(DBData dbData){
				
		calendar = new JCalendar();
		calendar.setBounds(0,0,350,350);
		Calendar c = calendar.getCalendar();
		currentMonth = c.get(Calendar.MONTH);
		currentYear = c.get(Calendar.YEAR);
		
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {

		    @Override
		    public void propertyChange(PropertyChangeEvent e) {
		    
		    	final Calendar c = (Calendar) e.getNewValue();    
		        if (currentMonth != c.get(Calendar.MONTH) || currentYear != c.get(Calendar.YEAR)){

		        }
		        else{
		        	//DayList.init(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR));
		        	LocalDate ld = LocalDate.now();	
		        	DayList.init(ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		        }
	        	currentMonth = c.get(Calendar.MONTH);
	        	currentYear = c.get(Calendar.YEAR);
				colorDayWithEvents(EventList.getEventListForSpecifiedMonth(Integer.toString(currentMonth+1)));
		    }
		});	
		
		events = new JMenuItem("Events",KeyEvent.VK_E);
		events.addActionListener(this);
		
		menuItem = new JMenuItem("Load events from XML file", KeyEvent.VK_L);
		menuItem.addActionListener(this);
		
		serializeToXML = new JMenuItem("Save all events to XML file", KeyEvent.VK_S);
		serializeToXML.addActionListener(this);
		
		filterByPlace = new JMenuItem("place");
		filterByPlace.addActionListener(this);
		filterByDescription = new JMenuItem("description");
		filterByDescription.addActionListener(this);
		filterByFrom = new JMenuItem("begin date");
		filterByFrom.addActionListener(this);
		filterByTo = new JMenuItem("ending date");
		filterByTo.addActionListener(this);
		
		DBconnect = new JMenuItem("Connect with DB", KeyEvent.VK_C);
		DBconnect.addActionListener(this);
		
		newEvent = new JMenuItem("New", KeyEvent.VK_N);
		newEvent.addActionListener(this);

		exit = new JMenuItem("Exit", KeyEvent.VK_E);
		exit.addActionListener(this);
		
		JMenu search = new JMenu("Filter by: ");
		search.add(filterByPlace);		
		search.add(filterByDescription);
		search.add(filterByFrom);		
		search.add(filterByTo);		
		
		editMenu = new JMenu("Edit");
		editMenu.add(search);
		
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		menu.add(newEvent);
		menu.add(events);
		menu.addSeparator();
		menu.add(DBconnect);
		menu.addSeparator();
		menu.add(menuItem);
		menu.add(serializeToXML);
		menu.addSeparator();
		menu.add(exit);
		
		menuBar = new JMenuBar();
		menuBar.add(menu);
		menuBar.add(editMenu);
		
		frame = new JFrame("Event Calendar");
		frame.setMinimumSize(new Dimension(355, 400));
		frame.setSize(new Dimension(355, 400));
		frame.setLocation(new Point(600, 300));
		frame.getContentPane().setLayout(null);
		frame.setJMenuBar(menuBar);
		
		settingsMenu = new JMenu("Settings");
		menuBar.add(settingsMenu);
		
		JMenuItem preferencesMenuItem = new JMenuItem("Preferences");
		preferencesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreferencesWindow.init();
				
				
			/*	JPanel panel= calendar.getDayChooser().getDayPanel();				
				Component [] components = panel.getComponents(); 
				for (int i= 8; i<49 ; i++ ){
					components[i].setBackground(PreferencesWindow.getBackgroundColor());
				} */
				/*for(int i= 13; i<49; i+= 7){
					components[i].setBackground(Color.RED);
				}*/
			}
		});
		settingsMenu.add(preferencesMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem mntmAboutProgram = new JMenuItem("About Program");
		helpMenu.add(mntmAboutProgram);
		mntmAboutProgram.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Event Calendar\nDawid Dziedziczak Micha³ Mackiewicz", "About", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		frame.getContentPane().add(calendar);
		frame.setVisible(true);
		
		frame.setResizable(false);		
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		        String ObjButtons[] = {"Yes","No"};
		        int promptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Question",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(promptResult ==JOptionPane.YES_OPTION)
		        {
		            DBConnection db;
					try {
						db = new DBConnection(dbData);
						db.saveData();
					} catch (IncorrectPasswordException | ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "There has been some problems with database.\nOr you have typed wrong data.", "Database failure", JOptionPane.WARNING_MESSAGE);
					}
		            
		        	System.exit(0);
		        }
		    }
		});
		
		
		System.out.println("Create timer");
 		timer = new javax.swing.Timer(1000, new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
				System.out.println("WORKING...");
 				String message = Reminder.toRemind();
 				if (message != null && !"".equals(message)) {
 					System.out.println("message " + message);
 					ReminderWindow reminderW = new ReminderWindow(message);
 				}
 			}
 		}){{setInitialDelay( 0 );}};
 		timer.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if (evt.getSource() == newEvent ){
			final Calendar c = calendar.getCalendar();
			CreateEventWindow.init(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR));
		}
		
		if (evt.getSource() == exit){
			int promptResult = JOptionPane.showConfirmDialog( null , "Do you really want to exit?", "Question", JOptionPane.YES_NO_OPTION);
			if(promptResult == JOptionPane.YES_OPTION){
				frame.dispose();
			}
		}
		
		if (evt.getSource() == events )
		{
			final Calendar c = calendar.getCalendar();	
		//	DayList.init(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR));
			LocalDate ld = LocalDate.now();	
			DayList.init(ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		if (evt.getSource() == menuItem){
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("XML file","xml"));
			int result = chooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION){
				File fi = chooser.getSelectedFile();
				try{
					XMLDecoder x = new XMLDecoder(new BufferedInputStream(new FileInputStream(fi.getPath())));
					Object obj = x.readObject();
					List<Event> events = (List<Event>) obj;
					for(Event e : events){
						EventList.addEvent(e);
					}
					x.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				JOptionPane.showMessageDialog(null, "Events imported properly.", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (evt.getSource() == serializeToXML){
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
					x.writeObject(EventList.getEvents());
					x.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
		if(evt.getSource() == filterByFrom){
			String from = JOptionPane.showInputDialog(null, "Type beging date (must be in HH:mm format");
			if ( from.isEmpty() )	
				JOptionPane.showMessageDialog(null, "Field can not be empty.", "Empty", JOptionPane.ERROR_MESSAGE);
		    else	
				SearchWindow.init(EventList.filterByFrom(from));
			
			
		}
		if(evt.getSource() == filterByTo){
			String to = JOptionPane.showInputDialog(null, "Type ending date (must be in HH:mm format");
			if ( to.isEmpty() )	
				JOptionPane.showMessageDialog(null, "Field can not be empty.", "Empty", JOptionPane.ERROR_MESSAGE);
		    else	
				SearchWindow.init(EventList.filterByTo(to));

		}
		if(evt.getSource() == filterByDescription){
			String desc = JOptionPane.showInputDialog(null, "Type description");
			if ( desc.isEmpty() )	
				JOptionPane.showMessageDialog(null, "Field can not be empty.", "Empty", JOptionPane.ERROR_MESSAGE);
		    else	
				SearchWindow.init(EventList.filterByDesc(desc));
		}
		if(evt.getSource() == filterByPlace){
			String place = JOptionPane.showInputDialog(null, "Type place");
			if ( place.isEmpty() )	
				JOptionPane.showMessageDialog(null, "Field can not be empty.", "Empty", JOptionPane.ERROR_MESSAGE);
		    else	
				SearchWindow.init(EventList.filterByPlace(place));
		}
		if (evt.getSource() == DBconnect){
			dbWindow = new DBWindow();			
		}
	
	}
	public void colorDayWithEvents(List<Event> eventList){
		JPanel panel= calendar.getDayChooser().getDayPanel();
		Component [] components = panel.getComponents(); 
		for(Event evt : eventList){
			int day = Integer.parseInt(evt.getDate().substring(0, 2).replaceAll("-", ""));
			components[day+8].setBackground(Color.green);

		}
	}
}