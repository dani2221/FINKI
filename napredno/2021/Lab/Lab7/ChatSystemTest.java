import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;

//		Администрација на чет-систем Problem 3 (0 / 0)
//		Треба да се развие класа за администрација на чет‐систем(chat). Системот се состои од повеќе чет‐соби, објекти од класата ChatRoom. Во ChatRoom ги чуваме името на собата и имињата на корисниците кои тековно се наоѓаат во таа соба (за корисниците да се користи соодветен Set).
//
//		ChatRoom(String name) ‐ креира нова празна соба за чет (празна значи без корисници).
//		addUser(String username) - го додава корисникот со тоа име во собата.
//		removeUser(String username) - го отстранува корисникот со тоа име од собата доколку има таков, во спротивно не прави ништо.
//		toString():String - враќа стринг кои ги содржи името на собата и сите корисници кои се во собата секој одделен со нов ред. Корисниците се подредени алфабетски. Ако собата е празна се враќа името на собата во еден ред, а во вториот ред стрингот "EMPTY" (наводници само за појаснување).
//		hasUser(String username):boolean - враќа true ако постои корисник со тоа име во собата.
//		numUsers():int - го враќа бројот на корисници во собата.
//		Главната  класа ChatSystem  ги  содржи  сите  соби  и  сите  орисници. Корисниците може да се членови на една, повеќе или да не се членови на ниедна соба. За менаџмент на собите треба да ги понудите следните три методи:
//
//		addRoom(String roomName) - додава нова празна соба во листата на соби.
//		removeRoom(String roomName) - ја отстранува собата од листата.
//		getRoom(String roomName):ChatRoom - го враќа објектот кој ја претставува собата со име roomName. Фрлете NoSuchRoomException(roomName) доколку не постои соба со тоа име.
//		Забелешка: Собите чувајте ги во TreeMap за да бидат секогаш подредени по нивното име.
//
//		Дополнително во класата ChatSystem постојат следните методи за работа со корисниците:
//
//		ChatSystem() - default constructor
//		register(String userName) - го регистрира корисникот во системот. Го додава во собата со најмалку корисници. Доколку има повеќе такви соби тогаш го додава во првата соба по лексикоргафско подредување.
//		registerAndJoin(String userName, String roomName) - го регистрира корисникот во системот. Дополнително го додава во собата со име roomName.
//		joinRoom(String userName, String roomName) - го  додава  корисникот  во  собата  со соодветно  име  доколку  таа  постои,  во  спротивно  фрла  исклучок  од  типот NoSuchRoomExcеption(roomName). Ако не постои регистриран корисник со тоа име се фрла исклучок NoSuchUserException(userName).
//		leaveRoom(String username, String roomName) - го отстранува корисникот од собата со соодветно  име  доколку  таа  постои.  во  спротивно  фрла  исклучок  од  типот NoSuchRoomExcеption(roomName). Ако не постои регистриран корисник со тоа име се фрла исклучок NoSuchUserException(userName).
//		followFriend(String username, String friend_username) – корисникот со име username го приклучува во сите соби во кој е член корисникот со име friendUsername. Ако не постои регистриран корисник со тоа име се фрла исклучок NoSuchUserException(userName).

public class ChatSystemTest {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
		Scanner jin = new Scanner(System.in);
		int k = jin.nextInt();
		if ( k == 0 ) {
			ChatRoom cr = new ChatRoom(jin.next());
			int n = jin.nextInt();
			for ( int i = 0 ; i < n ; ++i ) {
				k = jin.nextInt();
				if ( k == 0 ) cr.addUser(jin.next());
				if ( k == 1 ) cr.removeUser(jin.next()); 
				if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));  
			}
			System.out.println("");
			System.out.println(cr.toString());
			n = jin.nextInt();
			if ( n == 0 ) return;
			ChatRoom cr2 = new ChatRoom(jin.next());
			for ( int i = 0 ; i < n ; ++i ) {
				k = jin.nextInt();
				if ( k == 0 ) cr2.addUser(jin.next());
				if ( k == 1 ) cr2.removeUser(jin.next()); 
				if ( k == 2 ) cr2.hasUser(jin.next());  
			}
            System.out.println(cr2.toString());
		}	
       if ( k == 1 ) {
			ChatSystem cs = new ChatSystem();
			Method mts[] = cs.getClass().getMethods();
			while ( true ) {
				String cmd = jin.next();
				if ( cmd.equals("stop") ) break;
				if ( cmd.equals("print") ) {
					System.out.println(cs.getRoom(jin.next())+"\n");continue;
				}
				for ( Method m : mts ) {
					if ( m.getName().equals(cmd) ) {
						String params[] = new String[m.getParameterTypes().length];
						for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
						m.invoke(cs,params);
					}
				}				
			}
		}
	}

}

class ChatRoom implements Comparable<ChatRoom>{
	private String name;
	private TreeSet<String> users;

	public ChatRoom(String name) {
		this.name = name;
		users = new TreeSet<String>();
	}

	public void addUser(String next) {
		users.add(next);
	}

	public void removeUser(String next) {
		users.remove(next);
	}

	public boolean hasUser(String next) {
		return users.contains(next);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s\n", name));
		if (users.isEmpty())
			sb.append("EMPTY\n");
		else
			users.forEach(user -> sb.append(String.format("%s\n", user)));
		return sb.toString();
	}

	@Override
	public int compareTo(ChatRoom o) {
		if(this.users.size()<o.users.size()) return -1;
		if(this.users.size()>o.users.size()) return 1;
		return this.name.compareTo(o.name);
	}

	public int numUsers() {
		return users.size();
	}
}

class ChatSystem {
	private HashSet<String> users;
	private TreeMap<String, ChatRoom> chatRooms;

	public ChatSystem() {
		this.chatRooms = new TreeMap<>();
		this.users = new HashSet<>();
	}

	public void addRoom(String roomName) {
		chatRooms.put(roomName, new ChatRoom(roomName));
	}

	public void removeRoom(String roomName) {
		chatRooms.remove(roomName);
	}

	public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
		if (chatRooms.containsKey(roomName))
			return chatRooms.get(roomName);
		throw new NoSuchRoomException(roomName);
	}

	private ChatRoom findMinUsersRoom() {
		if (chatRooms.isEmpty())
			return null;
		ChatRoom min = chatRooms.get(chatRooms.firstKey());
		for (String roomName : chatRooms.keySet()) {
			ChatRoom currentRoom = chatRooms.get(roomName);
			if (currentRoom.numUsers() < min.numUsers())
				min = currentRoom;
		}
		return min;
	}

	public void register(String userName) throws NoSuchRoomException, NoSuchUserException {
		users.add(userName);
		ChatRoom minUsersRoom = findMinUsersRoom();
		if (minUsersRoom == null)
			return;
		joinRoom(userName, minUsersRoom.getName());
	}

	public void registerAndJoin(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
		users.add(userName);
		joinRoom(userName, roomName);
	}

	private void checkIfExists(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
		if (!chatRooms.containsKey(roomName))
			throw new NoSuchRoomException(roomName);
		if (!users.contains(userName))
			throw new NoSuchUserException(userName);
	}

	public void joinRoom(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
		checkIfExists(userName, roomName);
		chatRooms.computeIfPresent(roomName, (rName, room) -> {
			room.addUser(userName);
			return room;
		});
	}

	public void leaveRoom(String userName, String roomName) throws NoSuchUserException, NoSuchRoomException {
		checkIfExists(userName, roomName);
		chatRooms.computeIfPresent(roomName, (rName, room) -> {
			room.removeUser(userName);
			return room;
		});
	}

	public void followFriend(String username, String friendUsername) throws NoSuchUserException {
		if (!users.contains(username))
			throw new NoSuchUserException(username);
		LinkedList<ChatRoom> rooms = new LinkedList<>(chatRooms.values());
		for (ChatRoom room : rooms)
			if (room.hasUser(friendUsername))
				room.addUser(username);
	}
}

class NoSuchRoomException extends Exception{
	public NoSuchRoomException(String message) {
		super(message);
	}
}
class NoSuchUserException extends Exception{
	public NoSuchUserException(String message) {
		super(message);
	}
}
