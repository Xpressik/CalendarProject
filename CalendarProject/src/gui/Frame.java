package gui;

import data.*;
import logic.Reminder;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JCalendar;
import java.awt.Point;
import java.awt.Dimension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Klasa odpowiedzialna za wyswietlanie glownego okna aplikacji wraz z kalendarzem i calym menu. <br>
 * Umozliwia uzytkownikowi wybor dnia dla ktorego chce obejrzec badz stworzyc wydarzenia. <br>
 * Poprzez menu uzytkownik moze zmieniac ustawienia aplikacji, tworzyc oraz ogladac wydarzenia a takze je filtrowac.
 * @author Dawid
 *
 */
public class Frame implements ActionListener {
	
	/**
	 * Komponent reprezenujacy kalendarz, ktory wyswietlamy w glownym oknie aplikacji
	 */
	private JCalendar calendar;
	/**
	 * Element menu
	 */
	private JMenuBar menuBar;
	/**
	 * Element menu
	 */
	private JMenu menu;
	/**
	 * Element menu
	 */
	private JMenuItem menuItem;
	/**
	 * Element menu
	 */
	private JMenu editMenu;
	/**
	 * Element menu
	 */
	private JMenuItem newEvent;
	/**
	 * Element menu
	 */
	private JMenuItem exit;
	/**
	 * Element menu
	 */
	private JFrame frame;
	/**
	 * Element menu
	 */
	private JMenuItem filterByPlace;
	/**
	 * Element menu
	 */
	private JMenuItem filterByDescription;
	/**
	 * Element menu
	 */
	private JMenuItem filterByFrom;
	/**
	 * Element menu
	 */
	private JMenuItem filterByTo;
	/**
	 * Element menu
	 */
	private JMenuItem serializeToXML;
	/**
	 * Element menu
	 */
	private JMenuItem events;
	/**
	 * Zmienna przechowujaca obecny miesiac
	 */
	private int currentMonth;
	/**
	 * Zmienna przechowujaca obecny rok
	 */
	private int currentYear;
	/**
	 * Obiekt Menu, do ktorego przypiety jest przycisk Prefereces
	 */
	private JMenu settingsMenu;
	/**
	 * 
	 */
	private Timer timer;
	
	private EventService eventService;

	/**
	 * Konstruktor tworzacy na stercie instancje klasy Frame.<br>
	 * Wywoluje metode init z paramterem dbData
	 * @param eventService - obiekt ktory umozliwia dzialanie na repozytorium z pozycji Frame
	 */
	public Frame(EventService eventService){
		init(eventService);
	}
	
	/**
	 * Metoda odpowiedzialna za stworzenie glownego okna aplikacji i umozliwienie uzytkownikowi interakcje z programem. Poprzez wybor dni, tworzenie, przegladanie, filtrowanie wydarzen. Umozliwia dobor ustawien oraz wyswietlenie informacji.
	 * @param eventService
	 */
	private void init(final EventService eventService){
				
		calendar = new JCalendar();
		calendar.setBounds(0,0,350,350);
		Calendar c = calendar.getCalendar();
		currentMonth = c.get(Calendar.MONTH);
		currentYear = c.get(Calendar.YEAR);
		
		this.eventService = eventService;
		
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {

		    @Override
		    public void propertyChange(PropertyChangeEvent e) {
		    
		    	final Calendar c = (Calendar) e.getNewValue();    
		        if (currentMonth != c.get(Calendar.MONTH) || currentYear != c.get(Calendar.YEAR)){

		        }
		        else{
		        	LocalDate ld = LocalDate.now();	
		        	DayList.init(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR), ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), eventService);
		        }
	        	currentMonth = c.get(Calendar.MONTH);
	        	currentYear = c.get(Calendar.YEAR);
		    }
		});	
		
		events = new JMenuItem("Events",KeyEvent.VK_E);
		events.addActionListener(this);
		
		menuItem = new JMenuItem("Load events from XML file", KeyEvent.VK_L);
		menuItem.addActionListener(this);
		
		serializeToXML = new JMenuItem("Save all events to XML file", 'S');
		serializeToXML.addActionListener(this);
		
		filterByPlace = new JMenuItem("place");
		filterByPlace.addActionListener(this);
		filterByDescription = new JMenuItem("description");
		filterByDescription.addActionListener(this);
		filterByFrom = new JMenuItem("begin date");
		filterByFrom.addActionListener(this);
		filterByTo = new JMenuItem("ending date");
		filterByTo.addActionListener(this);
				
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
			}
		});
		settingsMenu.add(preferencesMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem mntmAboutProgram = new JMenuItem("About Program");
		helpMenu.add(mntmAboutProgram);
		mntmAboutProgram.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Event Calendar 1.0\nAllows to:\ncreate events with set reminders,\nfilter existing events with specified filters,\ndelete chosen events,"
					+ "\nload and save events from\\to XML files,\nconnect calendar with data bases. \n\n\nCreated by Dawid Dziedziczak and Michal Mackiewicz", "About", JOptionPane.INFORMATION_MESSAGE);
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
		        	if (eventService.hasDbDataRepository()) {
		        			eventService.saveEventsToDatabase();
		        	} else if (eventService.hasXmlDataRepository()) {
		        		eventService.saveEventsToXmlFile(null);
		        	}
		          System.exit(0);
		        }
		    }
		});
		
		
 		timer = new javax.swing.Timer(1000, new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				
 				Reminder reminderObject = new Reminder(eventService);
 				String message = reminderObject.toRemind();
 				if (message != null && !"".equals(message)) {
 					ReminderWindow reminderW = new ReminderWindow(message);
 				}
 			}
 		}){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{setInitialDelay( 0 );}};
 		timer.start();
		
	}

	/**
	 * Metoda nasluchujaca umozliwia tworzenie oraz przegladanie juz istniejacych wydarzen, odpowienie zamykanie aplikacji, wczytywanie wydarzen z plikow XML (deserializacja) oraz filtorwanie wydarzen.
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if (evt.getSource() == newEvent ){
			final Calendar c = calendar.getCalendar();
        	LocalDate ld = LocalDate.now();	
			CreateEventWindow.init(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR), ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), eventService);
		}
		
		if (evt.getSource() == exit){
			int promptResult = JOptionPane.showConfirmDialog( null , "Do you really want to exit?", "Question", JOptionPane.YES_NO_OPTION);
			if(promptResult == JOptionPane.YES_OPTION){
				if (eventService.hasDbDataRepository()) {
					eventService.saveEventsToDatabase();
				} else if (eventService.hasXmlDataRepository()) {
					eventService.saveEventsToXmlFile(null);
				}
				System.exit(0);
			}
		}
		
		if (evt.getSource() == events )
		{
			final Calendar c = calendar.getCalendar();	
			LocalDate ld = LocalDate.now();	
			DayList.init(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR), ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), eventService);
		}
		if (evt.getSource() == menuItem){
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("XML file","xml"));
			int result = chooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION){
				String fi = chooser.getSelectedFile().toString();
				eventService.loadEventsFromXmlFile(fi);
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
					eventService.saveEventsToXmlFile(path);
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
				SearchWindow.init(eventService.filterWithStartHour(from));
			
			
		}
		if(evt.getSource() == filterByTo){
			String to = JOptionPane.showInputDialog(null, "Type ending date (must be in HH:mm format");
			if ( to.isEmpty() )	
				JOptionPane.showMessageDialog(null, "Field can not be empty.", "Empty", JOptionPane.ERROR_MESSAGE);
		    else	
				SearchWindow.init(eventService.filterWithEndHour(to));

		}
		if(evt.getSource() == filterByDescription){
			String desc = JOptionPane.showInputDialog(null, "Type description");
			if ( desc.isEmpty() )	
				JOptionPane.showMessageDialog(null, "Field can not be empty.", "Empty", JOptionPane.ERROR_MESSAGE);
		    else	
				SearchWindow.init(eventService.filterWithDescription(desc));
		}
		if(evt.getSource() == filterByPlace){
			String place = JOptionPane.showInputDialog(null, "Type place");
			if ( place.isEmpty() )	
				JOptionPane.showMessageDialog(null, "Field can not be empty.", "Empty", JOptionPane.ERROR_MESSAGE);
		    else	
				SearchWindow.init(eventService.filterWithPlace(place));
		}
	
	}
	
}