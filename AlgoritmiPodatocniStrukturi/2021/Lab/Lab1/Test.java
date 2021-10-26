import java.util.Scanner;
//		Да се моделира класата Patuvanje. Во неа се чуваат информации за името на агенцијата која го организира String и цената на патувањето int. За класата да се имплементира соодветниот конструктор и set() и get() методите. За секоје патување времетраењето на истото може да се пресмета со методата со потпис: int vratiVremeVoDenovi();.
//
//		Едно патување може да биде или празнично патување или годишен одмор. Да се моделираат класите PraznicnoPatuvanje и GodishenOdmor. Притоа, покрај името и цената за сакој годишен одмор се чува податок за времетраењето на патувањето во денови int, додека за секоје празнично патување се чуваат податоци за почетен датум int и месец int и краен датум int и месец int на патувањето врз база на кои може да се одреди бројот на денови (се зема дека сите месеци имаат 30 денови).
//
//		За класите PraznicnoPatuvanje и GodishenOdmor да се креираат и потребните конструктори и get() методи, како и да се имплементираат потребните методи. Дополнително, да се земе предвид дека вистинската цена на годишниот одмор е за 1000 денари поефтин. Овие 1000 денари ги плаќа државта. Исклучоци: Празничните патувања се изведуваат во иста година и притоа почетниот датум треба да му претходи календарски на крајниот датум од патувањето. Ако се направи обид за креирање на објект од класата PraznicnoPatuvanje каде тоа не е исполнето, потребно е да фрлите општ исклучок Exception. Истиот треба да се фати во конструкторот каде што е фрлен и да се справите со истиот така што ќе ги замените вредностите за денот и месецот кога патувањето почнува со денот и месецот кога тоа завршува. Притоа ако исклучокот е фатен да се отпечати во нов ред порака на екранот: Iskluchok.
//
//		Во main методата на класата Test креирана е низа од n променливи од типPatuvanje (nizaPatuvanje). Од стандарден влез се внесуваат информациите за елементите од низата. Ваша задача е во определеното место во методата да ги исполните следните барања:
//
//		(Барање 1) На стандарден излез да ги отпечатите сите имиња на агенции кои нудат празнични патувања кои почнуваат во месец Јуни т.е. 6тиот месец од годината (во еден ред и одвоени со празно место).
//		(Барање 2) На стандарден излез да го отпечатите просечното времетраење на сите патувања изразено во денови.
//		(Барање 3) Од стандарден влез да ги прочитате информациите за еден годишен одмор (ime, cena, vremetraenje) . Да се креира променлива odmor од тип GodishenOdmor која референцира објект од тип (GodishenOdmor) креиран со информациите од влезот.
//		(Барање 4) На стандарден излез отпечатете минимална цена на она патување меѓу оние патувања кои се подолги од вчитаниот годишен одмор (odmor) . Притоа за таа цел да се искористи метода со потпис int vratiMinCena(Patuvanje [] niza, int n, Patuvanje zaSporedba); Оваа метода е статичка во класата Patuvanje. Таа ја враќа минималната цена на патувањето од низата niza кое е подолго од патувањето zaSporedba . Ако нема такво патување функцијата треба да врати 0.

abstract class Patuvanje {
	private String ime;
	private int cena;
	public abstract int vratiVremeVoDenovi();

	public Patuvanje(String ime, int cena) {
		this.ime = ime;
		this.cena = cena;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}
}
class GodishenOdmor extends Patuvanje{
	private int vremetraenje;

	public GodishenOdmor(String ime, int cena, int vremetraenje) {
		super(ime, cena-1000);
		this.vremetraenje = vremetraenje;
	}

	public int getVremetraenje() {
		return vremetraenje;
	}

	@Override
	public int vratiVremeVoDenovi() {
		return this.vremetraenje;
	}
}
class PraznicnoPatuvanje extends Patuvanje{
	private int startDen;
	private int krajDen;
	private int startMesec;
	private int krajMesec;

	public PraznicnoPatuvanje(String ime, int cena, int startDen, int startMesec,int krajDen, int krajMesec) {
		super(ime, cena);
		try {
			if(krajMesec<startMesec || (krajMesec==startMesec && krajDen<startDen) ) throw new Exception("Iskluchok");
			this.startDen = startDen;
			this.krajDen = krajDen;
			this.startMesec = startMesec;
			this.krajMesec = krajMesec;
		}catch (Exception e){
			System.out.println(e.getMessage());
			this.startDen = krajDen;
			this.krajDen = startDen;
			this.startMesec = krajMesec;
			this.krajMesec = startMesec;
		}
	}

	public int getStartDen() {
		return startDen;
	}

	public int getKrajDen() {
		return krajDen;
	}

	public int getStartMesec() {
		return startMesec;
	}

	public int getKrajMesec() {
		return krajMesec;
	}

	@Override
	public int vratiVremeVoDenovi() {
		return (krajMesec-startMesec)*30+(krajDen-startDen);
	}
}

public class Test {

	
	public static void main(String[] args) {
		
		
		int n;
		Scanner in=new Scanner(System.in);
		n=in.nextInt();
		
		Patuvanje nizaPatuvanje[]=new Patuvanje[n];
		
		for (int i=0;i<n;i++){
			int tip=in.nextInt();
			if (tip==0){
				String ime=in.next();
				int cena =in.nextInt();
				int vreme=in.nextInt();
				nizaPatuvanje[i]=new GodishenOdmor(ime,cena,vreme);
			}
			else {
				String ime=in.next();
				int cena =in.nextInt();
				int pocD=in.nextInt();
                int pocM=in.nextInt();
				int krajD=in.nextInt();
				int krajM=in.nextInt();
				nizaPatuvanje[i]=new PraznicnoPatuvanje(ime,cena,pocD,pocM, krajD,krajM);
				
			}
		}
		
		//решение на барање 1
		for(Patuvanje p : nizaPatuvanje) {
			if(p instanceof PraznicnoPatuvanje && ((PraznicnoPatuvanje) p).getStartMesec()==6){
				System.out.print(p.getIme()+" ");
			}
		}
		System.out.println();
		
        //решение на барање 2
		double vremetraenje = 0.0;
		for(Patuvanje p : nizaPatuvanje) {
			vremetraenje+=p.vratiVremeVoDenovi();
		}

		System.out.println(vremetraenje/nizaPatuvanje.length);
		
        //решение на барање 3
		String ime=in.next();
		int cena =in.nextInt();
		int vreme=in.nextInt();
		GodishenOdmor odmor = new GodishenOdmor(ime,cena,vreme);
  		
		//решение на барање 4
		System.out.println(vratiMinCena(nizaPatuvanje,nizaPatuvanje.length,odmor));
		

	}
	public static int vratiMinCena(Patuvanje [] niza, int n, Patuvanje zaSporedba){
		int minCena = Integer.MAX_VALUE;
		for(Patuvanje p : niza){
			if(p.vratiVremeVoDenovi()>zaSporedba.vratiVremeVoDenovi() && p.getCena()<minCena){
				minCena = p.getCena();
			}
		}
		if(minCena!=Integer.MAX_VALUE) return minCena;
		return 0;
	}

}
