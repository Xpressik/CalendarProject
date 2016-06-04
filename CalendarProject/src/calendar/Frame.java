package calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import com.toedter.calendar.JCalendar;

public class Frame implements ActionListener {
	
	private JCalendar calendar;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenu editMenu;
	private JMenuItem undo;
	private JMenuItem newFile;
	private JMenuItem exit;
	private JFrame frame;
	
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
		        System.out.println(c.get(Calendar.DAY_OF_MONTH));  
		        CreateEventWindow wnd = new CreateEventWindow();
		        wnd.init();
		    }
		});	
		
		menuItem = new JMenuItem("A text-only menu item", KeyEvent.VK_T);
		
		undo = new JMenuItem("Undo", KeyEvent.VK_U);
		
		newFile = new JMenuItem("New", KeyEvent.VK_N);
		newFile.addActionListener(this);

		exit = new JMenuItem("Exit", KeyEvent.VK_E);
		exit.addActionListener(this);
		
		editMenu = new JMenu("Edit");
		editMenu.add(undo);
		
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
	}
}
