import java.text.DecimalFormat;
import java.util.*;

//		Се со цел да се подобри комуникацијата на факултетот потребно е да се направи систем за чување на контакти за секој студент.
//
//		Да се креира класа Contact. За потребите на оваа класа да се дефинираат следниве методи:
//
//		Contact(String date) - конструктор каде што date е датумот кога е креиран контактот даден во следниов формат YYYY-MM-DD
//		isNewerThan(Contact c):boolean - метод кој враќа true доколку контактот е креиран подоцна од контактот c и обратно
//		getType():String - метод кој враќа вредност "Email" или "Phone" во зависност од типот на контактот
//		Од класата Contact не треба да може директно да се инстанцира објект.
//
//		Од оваа класа се изведуваат класите EmailContact и PhoneContact.
//
//		За класата EmailContact дополнително се чува e-маил кој што е од типот String. Да се дефинираат следниве методи:
//
//		EmailContact(String date, String email) - конструктор
//		getEmail():String - метод кој што го враќа е-маилот
//		getType():String- имплементација на методот од класата Contact
//		За класата PhoneContact дополнително се чува телефонски број кој што е од типот String и оператор кој што е енумерација и се дефинира на следниов начин enum Operator { VIP, ONE, TMOBILE }. За оваа класа да се дефинираат следниве методи:
//
//		PhoneContact(String date, String phone) - конструктор
//		getPhone():String - метод кој што го враќа телефонскиот број
//		getOperator():Operator - метод кој што го враќа операторот (070, 071, 072 – TMOBILE, 075,076 – ONE, 077, 078 – VIP)
//		getType():String- имплементација на методот од класата Contact
//		*Забелешка: Сите телефонски броеви се во формат 07X/YYY-ZZZ каде што X има вредност {0,1,2,5,6,7,8}
//
//		Потоа да се дефинира класата Student каде што се чува низа на контакти за секој студент
//
//		Student(String firstName, String lastName, String city, int age, long index) – конструктор
//		addEmailContact(String date, String email):void – метод што додава е-маил контакт во низата на контакти
//		addPhoneContact(String date, String phone):void – метод што додава телефонски контакт во низата на контакти
//		getEmailContacts():Contact[] – враќа низа на email контактите на студентот
//		getPhoneContacts():Contact[] – враќа низа на phone контактите на студентот
//		getCity():String - метод кој го враќа градот
//		getFullName():String - метод кој го враќа целосното име на студентот во формат IME PREZIME
//		getIndex():long - метод кој го враќа индексот на студентот
//		getLatestContact():Contact – го враќа најновиот контакт (според датум) од студентот
//		toString() – претставува JSON репрезентација на класата студент пр. {"ime":"Jovan", "prezime":"Jovanov", "vozrast":20, "grad":"Skopje", "indeks":101010, "telefonskiKontakti":["077/777-777", "078/888-888"], "emailKontakti":["jovan.jovanov@example.com", "jovanov@jovan.com", "jovan@jovanov.com"]}
//		*Забелешка: Во класата Student да се чува само една низа од контакти Contact[], а не две низи одделно (PhoneContact[] и EmailContact[])
//
//		*Напомена да не се користи instanceOf или getClass при имплементација на овие методи
//
//		Дополнително да се дефинира класа Faculty. За оваа класа да се дефинираат следниве методи:
//
//		Faculty(String name, Student [] students) – конструктор
//		countStudentsFromCity(String cityName):int – враќа колку студенти има од даден град
//		getStudent(long index):Student – метод кој го враќа студентот кој го има дадениот индекс
//		getAverageNumberOfContacts():double – враќа просечен број на контакти по студент
//		getStudentWithMostContacts():Student – метод кој го враќа студентот со најмногу контакти (доколку има повеќе студенти со ист број на контакти да го врати студентот со најголем индекс)
//		toString() – претставува JSON репрезентација на класата Faculty пример: {"fakultet":"FINKI", "studenti":[STUDENT1, STUDENT2, ...]} каде што треба да има целосни информации за секој студент.


public class ContactsTester {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int tests = scanner.nextInt();
		Faculty faculty = null;

		int rvalue = 0;
		long rindex = -1;

		DecimalFormat df = new DecimalFormat("0.00");

		for (int t = 0; t < tests; t++) {

			rvalue++;
			String operation = scanner.next();

			switch (operation) {
			case "CREATE_FACULTY": {
				String name = scanner.nextLine().trim();
				int N = scanner.nextInt();

				Student[] students = new Student[N];

				for (int i = 0; i < N; i++) {
					rvalue++;

					String firstName = scanner.next();
					String lastName = scanner.next();
					String city = scanner.next();
					int age = scanner.nextInt();
					long index = scanner.nextLong();

					if ((rindex == -1) || (rvalue % 13 == 0))
						rindex = index;

					Student student = new Student(firstName, lastName, city,
							age, index);
					students[i] = student;
				}

				faculty = new Faculty(name, students);
				break;
			}

			case "ADD_EMAIL_CONTACT": {
				long index = scanner.nextInt();
				String date = scanner.next();
				String email = scanner.next();

				rvalue++;

				if ((rindex == -1) || (rvalue % 3 == 0))
					rindex = index;

				faculty.getStudent(index).addEmailContact(date, email);
				break;
			}

			case "ADD_PHONE_CONTACT": {
				long index = scanner.nextInt();
				String date = scanner.next();
				String phone = scanner.next();

				rvalue++;

				if ((rindex == -1) || (rvalue % 3 == 0))
					rindex = index;

				faculty.getStudent(index).addPhoneContact(date, phone);
				break;
			}

			case "CHECK_SIMPLE": {
				System.out.println("Average number of contacts: "
						+ df.format(faculty.getAverageNumberOfContacts()));

				rvalue++;

				String city = faculty.getStudent(rindex).getCity();
				System.out.println("Number of students from " + city + ": "
						+ faculty.countStudentsFromCity(city));

				break;
			}

			case "CHECK_DATES": {

				rvalue++;

				System.out.print("Latest contact: ");
				Contact latestContact = faculty.getStudent(rindex)
						.getLatestContact();
				if (latestContact.getType().equals("Email"))
					System.out.println(((EmailContact) latestContact)
							.getEmail());
				if (latestContact.getType().equals("Phone"))
					System.out.println(((PhoneContact) latestContact)
							.getPhone()
							+ " ("
							+ ((PhoneContact) latestContact).getOperator()
									.toString() + ")");

				if (faculty.getStudent(rindex).getEmailContacts().length > 0
						&& faculty.getStudent(rindex).getPhoneContacts().length > 0) {
					System.out.print("Number of email and phone contacts: ");
					System.out
							.println(faculty.getStudent(rindex)
									.getEmailContacts().length
									+ " "
									+ faculty.getStudent(rindex)
											.getPhoneContacts().length);

					System.out.print("Comparing dates: ");
					int posEmail = rvalue
							% faculty.getStudent(rindex).getEmailContacts().length;
					int posPhone = rvalue
							% faculty.getStudent(rindex).getPhoneContacts().length;

					System.out.println(faculty.getStudent(rindex)
							.getEmailContacts()[posEmail].isNewerThan(faculty
							.getStudent(rindex).getPhoneContacts()[posPhone]));
				}

				break;
			}

			case "PRINT_FACULTY_METHODS": {
				System.out.println("Faculty: " + faculty.toString());
				System.out.println("Student with most contacts: "
						+ faculty.getStudentWithMostContacts().toString());
				break;
			}

			}

		}

		scanner.close();
	}
}
abstract class Contact {
	private Date date;

	public Contact(String date) {
		String[] splited = date.split("-");
		this.date = new Date(Integer.parseInt(splited[0]),Integer.parseInt(splited[1]),Integer.parseInt((splited[2])));
	}

	boolean isNewerThan(Contact c) {
		return this.date.after(c.date);
	}

	public abstract String getType();
}

class EmailContact extends Contact{
	private String email;

	public EmailContact(String date, String email) {
		super(date);
		this.email = email;
	}

	@Override
	public String getType() {
		return "Email";
	}

	public String getEmail() {
		return email;
	}
}
enum Operator { VIP, ONE, TMOBILE }

class PhoneContact extends Contact{
	private String phone;
	private Operator operator;

	public PhoneContact(String date, String phone) {
		super(date);
		int num = Integer.parseInt(""+phone.charAt(2));
		if(num==0 || num==1 || num==2) operator = Operator.TMOBILE;
		if(num==5 || num==6) operator = Operator.ONE;
		if(num==7 || num==8) operator = Operator.VIP;
		this.phone = phone;
	}

	@Override
	public String getType() {
		return "Phone";
	}

	public String getPhone() {
		return phone;
	}

	public Operator getOperator() {
		return operator;
	}
}

class Student {
	private String firstName;
	private String lastName;
	private String city;
	private int age;
	private long index;
	private List<Contact> contacts;

	public Student(String firstName, String lastName, String city, int age, long index) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.age = age;
		this.index = index;
		this.contacts = new ArrayList<Contact>();
	}

	public void addEmailContact(String date, String email) {
		contacts.add(new EmailContact(date,email));
	}
	public void addPhoneContact(String date, String phone) {
		contacts.add(new PhoneContact(date,phone));
	}

	public Contact[] getEmailContacts() {
		List<Contact> emailContacts = new ArrayList<Contact>();
		for(Contact c : contacts) {
			if(c.getType()=="Email") emailContacts.add(c);
		}
		return emailContacts.toArray(new Contact[emailContacts.size()]);
	}
	public Contact[] getPhoneContacts() {
		List<Contact> phoneContacts = new ArrayList<Contact>();
		for(Contact c : contacts) {
			if(c.getType()=="Phone") phoneContacts.add(c);
		}
		return phoneContacts.toArray(new Contact[phoneContacts.size()]);
	}

	public String getCity() {
		return city;
	}

	public long getIndex() {
		return index;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public Contact getLatestContact() {
		Contact newest = contacts.get(0);
		for(Contact c : contacts) {
			if(c.isNewerThan(newest)) newest = c;
		}
		return newest;
	}

	public int getContactLength() {
		return contacts.size();
	}

	@Override
	public String toString() {
		String str =  "{" +
				"\"ime\":\"" + firstName + "\", " +
				"\"prezime\":\"" + lastName + "\", " +
				"\"vozrast\":" + age + ", " +
				"\"grad\":\"" + city + "\", " +
				"\"indeks\":" + index + ", " +
				"\"telefonskiKontakti\":[";
		for(Contact c : this.getPhoneContacts()){
			PhoneContact pc = (PhoneContact) c;
			str = str.concat("\""+pc.getPhone()+"\"");
			str+=", ";
		}
		if(this.getPhoneContacts().length>0) str = str.substring(0,str.length()-2);
		str+="], \"emailKontakti\":[";
		for(Contact c : this.getEmailContacts()){
			EmailContact pc = (EmailContact) c;
			str = str.concat("\""+pc.getEmail()+"\"");
			str+=", ";
		}
		if(this.getEmailContacts().length>0)  str = str.substring(0,str.length()-2);
		str+="]}";
		return str;
	}
}

class Faculty {
	private String name;
	private Student[] students;

	public Faculty(String name, Student[] students) {
		this.name = name;
		this.students = students;
	}

	public int countStudentsFromCity(String cityName){
		int counter = 0;
		for(Student s : students) {
			if(s.getCity().compareTo(cityName)==0) counter++;
		}
		return counter;
	}

	public Student getStudent(long index){
		for(Student s : students) {
			if(s.getIndex()==index) return s;
		}
		return null;
	}

	public double getAverageNumberOfContacts(){
		double avg = 0.0;
		for(Student s : students) {
			avg+=s.getContactLength();
		}
		return avg/students.length;
	}

	public Student getStudentWithMostContacts() {
		Student maxStudent = students[0];
		for(Student s : students) {
			if(s.getContactLength()>maxStudent.getContactLength()) maxStudent = s;
			else if(s.getContactLength()==maxStudent.getContactLength()){
				if(s.getIndex()>maxStudent.getIndex()) maxStudent = s;
			}
		}
		return maxStudent;
	}

	@Override
	public String toString() {
		String str = "{" +
				"\"fakultet\":\"" + name + "\", " +
				"\"studenti\":[";
		for(Student s : students) {
			str+=s.toString()+", ";
		}
		if(students.length>0) str = str.substring(0,str.length()-2);
		str+="]}";
		return str;
	}
}
