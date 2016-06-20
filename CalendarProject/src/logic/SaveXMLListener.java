

/**
 * Pakiet zawierajacy klasy odpowiedzialne za logike aplikacji<br>Warstwa logiki aplikacji
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
 * Klasa nasluchujaca. Implementuje odpowiednie zachowanie po kliknieciu na przycisk Save to XML .<br>
 * Odpowiada za serializacje obiektu klasy Event (wydarzenia) do pliku XML.
 * @author Dawid
 *
 */
public class SaveXMLListener implements ActionListener {

	/**
	 * Lista zdarzen, z ktorych wybrane ma zostac zserializowane
	 */
	private List<Event> eventList;
	
	/**
	 * Konstruktor tworzy na stercie instancje klasy SaveXMLListener.<br>
	 *  Ustawia otrzymana przez parametr liste jako swoje pole List w celu dalszego korzystania z niej.
	 * @param lst - lista wydarzen ktore maja zostac zserializowane
	 */
	public SaveXMLListener(List<Event> lst){
		this.eventList = lst;
	}
	/**
	 * Metoda nasluchujaca. Implementuje serializacje wybranych wydarzen do formatu XML.
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
