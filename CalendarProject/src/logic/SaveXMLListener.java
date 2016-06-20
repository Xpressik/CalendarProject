

/**
 * Pakiet zawieraj�cy klasy odpowiedzialne za logik� aplikacji<br>Warstwa logiki aplikacji
 */
package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Event;
/**
 * Klasa nas�uchuj�ca. Implementuje odpowiednie zachowanie po klikni�ciu na przycisk Save to XML .<br>
 * Odpowiada za serializacj� obiektu klasy Event (wydarzenia) do pliku XML.
 * @author Dawid
 *
 */
public class SaveXMLListener implements ActionListener {

	/**
	 * Lista zdarze�, z kt�rych wybrane ma zosta� zserializowane
	 */
	private List<Event> eventList;
	
	/**
	 * Konstruktor tworzy na stercie instancj� klasy SaveXMLListener.<br>
	 *  Ustawia otrzyman� przez parametr list� jako swoje pole List w celu dalszego korzystania z niej.
	 * @param lst - lista wydarzen ktore maja zostac zserializowane
	 */
	public SaveXMLListener(List<Event> lst){
		this.eventList = lst;
	}
	/**
	 * Metoda nas�uchuj�ca. Implementuje serializacj� wybranych wydarze� do formatu XML.
	 */
	@Override
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

}
