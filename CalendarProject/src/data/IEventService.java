package data;

import java.util.Date;
import java.util.List;

/**
 * Intefejs ktory zawiera deklaracje metod do obslugi repozytorium.<br> 
 * 
 */
public interface IEventService {
	/**
	 * Ustawia dataRepository na ktorym bedzie odbywal sie serwis 
	 * @param dataRepository - repozytorium do ktorego dodajemy obsluge
	 */
	public void setDataRepository(DataRepository dataRepository);
	/**
	 * Dodanie eventu do repozytorium
	 * @param event - element ktory dodajemy do repozytorium
	 */
	public void addEvent(Event event);
	/**
	 * Usuniecie eventu z repozytorium
	 * @param  event - element ktory chcemy usunac z repozytorium
	 */
	public void removeEvent(Event event);
	/**
	 * Zwraca z repozytorium liste eventow ktore odbywaja sie w podanej dacie 
	 *
	 * @param date - data dla ktorej zwracamy liste eventow z repozytorium
	 * @return lista zdarzen
	 */
	public List<Event> getEventsFromSpecifiedDay(String date);
	/**
	 * Zwraca liste eventow w danym miesiacu
	 *
	 * @param month - miesiac dla ktorego zwracamy liste eventow
	 * @return lista zdarzen
	 */
	public List<Event> getEventsFromSpecifiedMonth(String month);	/**
	 * Zwraca liste evnetow z podana deskrypcja
	 *
	 * @param desc - string ktory podaje fragment opisu wydarzenia. Opis wszystkich wydarzen bedzie porownywany z tym parametrem w poszukiwaniu zgodnosci
	 * @return lista zdarzen
	 */
	public List<Event> filterWithDescription(String desc);
	/**
	 * Zwraca liste eventow ktore odbywaja sie w danym miejscu
	 *
	 * @param place - string ktory opisuje miejsce wydarzenia. Miejsce odbywania kazdego wydarzenia bedzie porownywany z tym parametrem w poszukiwaniu zgodnosci
	 * @return lista eventow ktorych opis pasuje do podanego opisu w przekazanym przez parametr Stringu
	 */
	public List<Event> filterWithPlace(String place);
	/**
	 * Zwraca liste eventow ktore rozpoczynaja sie o danej godzinie
	 *
	 * @param from - string ktory okresla godzine rozpoczecia wydarzenia. Godzina rozpoczecia kazdego wydarzenia bedzie porownywana z tym parametrem w poszukiwaniu zgodnosci
	 * @return lista zdarzen
	 */
	public List<Event> filterWithStartHour(String from);
	/**
	 * Zwraca liste eventow ktore koncza sie o danej godzinie
	 *
	 * @param to - string ktory okresla godzine zakonczenia wydarzenia. Godzina rozpoczecia kazdego wydarzenia bedzie porownywana z tym parametrem w poszukiwaniu zgodnosci
	 * @return lista zdarzen
	 */
	public List<Event> filterWithEndHour(String to);
	/**
	 * Zwraca liste ktora przechowuje wszystkie eventy w repozytorium
	 * @return zwraca liste Eventow ktorych godzina zakonczenia pasuje do godziny zakonczenia przekazanej przez parametr Stringu 
	 */
	public List<Event> getAllEvents();
	/**
	 * Tworzy obiekt Remindera ktory bedzie informowal o zblizajacym sie wydarzeniu wedlug okreslonego czasu przed rozpoczeciem wydarzenia 
	 *
	 * @param eventDate - Data wydarzenia do przypomnienia
	 * @param fromHour - Godzina rozpoczecia wydarzenia
	 * @param remindIndex - index ktory mowi o tym ktory reminder zostal wybrany(ile czasu przed wydarzeniem ma zostac wystosowane przypomnienie)
	 * @return data uruchomienia przypomnienia
	 */
	public Date createReminder(String eventDate, String fromHour, Integer remindIndex);
	/**
	 * Usuwa obiekt wg podanego indeksu wydarzenia danego dnia
	 *
	 * @param eventDate - data konkretnego dnia w ktorym chcemy usunac wydarzenie
	 * @param idx - indeks wydarzenia do usuniecia
	 * @return lista zdarzen po usunieciu wskazanego przez index
	 */
	public List<Event> removeEventByIdx(String eventDate, Integer idx);
	/**
	 * Wczytuje eventy z pliku.xml oraz zwraca liste z tymi eventami
	 *
	 * @param filename - nazwa ktora okresla lokalizacje pliku z ktorego chcemy wczytac eventy
	 */
	public void loadEventsFromXmlFile(String filename);
	/**
	 * Zapisuje eventy do pliku.txt
	 *
	 * @param filename - nazwa ktora okresla lokalizacje pliku do ktorego chcemy zapisac eventy
	 */
	public void saveEventsToXmlFile(String filename);
	/**
	 * Wczytuje eventy z bazy danych
	 */
	public void loadEventsFromDatabase();
	/**
	 * Zapisuje eventy do bazdy danych
	 */
	public void saveEventsToDatabase();
	/**
	 * Sprawdza czy Zdefiniowane zostalo xmlDataRepository
	 * @return boolean
	 */
	public boolean hasXmlDataRepository();
	/**
	 * Sprawdza czy ZDefiniowane zostalo DbDataRepository
	 * @return boolean
	 */
	public boolean hasDbDataRepository();
}