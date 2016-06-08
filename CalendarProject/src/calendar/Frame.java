package calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JCalendar;
import java.awt.Point;
import java.awt.Dimension;

public class Frame implements ActionListener {
	
	private JCalendar calendar;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenu editMenu;
	private JMenuItem newEvent;
	private JMenuItem exit;
	private JFrame frame;
	private JMenuItem filterByPlace;
	private JMenuItem filterByDescription;
	private JMenuItem filterByFrom;
	private JMenuItem filterByTo;
	private JMenuItem serializeToXML;
	
	private int currentMonth;
	private int currentYear;
	public Frame(){
		init();
	}
	
	private void init(){
		
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
		        	DayEvents.init(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR));
		        	//CreateEventWindow.init(c);
		        }
	        	currentMonth = c.get(Calendar.MONTH);
	        	currentYear = c.get(Calendar.YEAR);
		    }
		});	
		
		
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
		frame.getContentPane().add(calendar);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if (evt.getSource() == newEvent ){
			final Calendar c = calendar.getCalendar();
	        CreateEventWindow.init(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR));
		}
		
		if (evt.getSource() == exit){
			int odp = JOptionPane.showConfirmDialog( null , "Do you really want to exit?", "Question", JOptionPane.YES_NO_OPTION);
			if(odp == JOptionPane.YES_OPTION){
				frame.dispose();
			}
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
			SearchWindow.init(EventList.filterByFrom(from));

		}
		if(evt.getSource() == filterByTo){
			String to = JOptionPane.showInputDialog(null, "Type ending date (must be in HH:mm format");
			SearchWindow.init(EventList.fiterByTo(to));

		}
		if(evt.getSource() == filterByDescription){
			String desc = JOptionPane.showInputDialog(null, "Type description");
			SearchWindow.init(EventList.filterByDesc(desc));
		}
		if(evt.getSource() == filterByPlace){
			String place = JOptionPane.showInputDialog(null, "Type place");
			SearchWindow.init(EventList.fiterByPlace(place));
		}
	}
}