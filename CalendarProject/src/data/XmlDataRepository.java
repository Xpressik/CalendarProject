package data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class XmlDataRepository extends DataRepository {
	private String filename;
	
	public XmlDataRepository(String filename) {
		this.filename = filename;
	}
	
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

	public void saveToXmlFile() throws Exception {
		if (!filename.endsWith(".xml")){
			filename += ".xml";
		}
		XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		x.writeObject(getAllEvents());
		x.close();
	}
}