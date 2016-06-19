package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import data.Event;
import data.EventList;
import gui.DayList;

public class CreateButtonListener implements ActionListener{
	
	public void actionPerformed(ActionEvent arg0) { 
		
		/*int fromHour = 0, fromMinutes = 0, toHour = 0, toMinutes = 0;
		try{
			String[] from = formattedTextField.getText().split(":");
			 fromHour = Integer.parseInt(from[0]);
			 fromMinutes = Integer.parseInt(from[1]);
			String[] to = formattedTextField_1.getText().split(":");
			 toHour = Integer.parseInt(to[0]);
			 toMinutes = Integer.parseInt(to[1]);
			Object reminder = comboBoxReminder.getSelectedItem();
			if ( reminder != null ){
				
			}
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "You have to type hours.", "Wrong hours", JOptionPane.OK_OPTION);
			return;
		}
		
		if( fromHour > toHour || (fromHour == toHour && fromMinutes > toMinutes)){
			JOptionPane.showMessageDialog(null, "Event end before it starts.", "Wrong hours", JOptionPane.OK_OPTION);
		}
		else{
			EventList.addEvent(new Event(textField.getText(), textField_1.getText(), formattedTextField.getText(), formattedTextField_1.getText(), date)); 
			JOptionPane.showMessageDialog(null, "Event created propertly.", "Success", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			DayList.init(date);
			//DayEvents.init(date);
		}*/
	}
}
