package calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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

public class Frame implements ActionListener {
	
	private JCalendar calendar;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenu editMenu;
	private JMenuItem newFile;
	private JMenuItem exit;
	private JFrame frame;
	private JMenuItem filterByPlace;
	private JMenuItem filterByDescription;
	private JMenuItem filterByFrom;
	private JMenuItem filterByTo;
	public Frame(){
		init();
	}
	
	private void init(){
		
		calendar = new JCalendar();
		calendar.setBounds(0,0,350,350);
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {

		    @Override
		    public void propertyChange(PropertyChangeEvent e) { // Zmiana miesiaca i roku powoduje otwarcie okna ..
		    	
		    	final Calendar c = (Calendar) e.getNewValue();    
		        DayEvents.init(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR));
		        //CreateEventWindow.init(c);
		        
		    }
		});	
		
		
		menuItem = new JMenuItem("Deserialize events from XML file", KeyEvent.VK_D);
		menuItem.addActionListener(this);
		
		filterByPlace = new JMenuItem("place");
		filterByPlace.addActionListener(this);
		filterByDescription = new JMenuItem("description");
		filterByDescription.addActionListener(this);
		filterByFrom = new JMenuItem("begin date");
		filterByFrom.addActionListener(this);
		filterByTo = new JMenuItem("ending date");
		filterByTo.addActionListener(this);
		
		newFile = new JMenuItem("New", KeyEvent.VK_N);
		newFile.addActionListener(this);

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
		menu.add(newFile);
		menu.add(menuItem);
		menu.add(exit);
		
		menuBar = new JMenuBar();
		menuBar.add(menu);
		menuBar.add(editMenu);
		
		frame = new JFrame("Event Calendar");
		frame.setLayout(null);
		frame.setJMenuBar(menuBar);
		frame.add(calendar);
		frame.setSize(365, 410);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
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
			}
		}
		if(evt.getSource() == filterByFrom){
			JOptionPane.showInputDialog(null, "Type beging date (must be in HH:mm format");
		}
		if(evt.getSource() == filterByTo){
			JOptionPane.showInputDialog(null, "Type ending date (must be in HH:mm format");
		}
		if(evt.getSource() == filterByDescription){
			JOptionPane.showInputDialog(null, "Type description");
		}
		if(evt.getSource() == filterByPlace){
			JOptionPane.showInputDialog(null, "Type place");
		}
	}
}