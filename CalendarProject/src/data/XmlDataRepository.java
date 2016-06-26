package data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import logic.IncorrectPasswordException;
/**
 * Klasa ktora zawiera rozszerzenie DataBase ktora posiada metody do obslugi plikow xml.<br> 
 * Zawiera takie informacje jak:<br>
 *  - nazwa pliku do ktorego zapisujemy dane lub z ktorego wczytujemy dane<br>
 * 
 */
public class XmlDataRepository extends DataRepository {
	private String filename;
	/**
	 * Domyslny konstruktor ktory przypisuje do zmiennej lokalnej filename parametr filename
	 * @param filename - obiekt typu String ktory okresla lokalizacje pliku
	 */
	public XmlDataRepository(String filename) {
		this.filename = filename;
	}
	/** 
	 * Pobiera wydarzenia z pliku xml i dodaje je do repozytorium podanego jako parametr
	 * @param repository - repozytorium z ktorego chcemy zapisac wydarzenia do pliku.xml
	 * @return DataRepository
	 * @throws Exception - wyjatek rzucany przez ta metode
	 */
	public DataRepository loadFromXmlFile(DataRepository repository) throws Exception {
		XMLDecoder xml = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		Object obj = xml.readObject();
		List<Event> events = (List<Event>) obj;
		for(Event e : events){
			repository.addEvent(e);
		}
		xml.close();
		return repository;
	}
	/** 
	 * Zapisuje eventy do pliku.xml
	 * @throws Exception - metoda moze wyrzucic wyjatek typu Exception
	 */
	public void saveToXmlFile() throws Exception {
		if (!filename.endsWith(".xml")){
			filename += ".xml";
		}
		XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		x.writeObject(getAllEvents());
		x.close();
	}
}