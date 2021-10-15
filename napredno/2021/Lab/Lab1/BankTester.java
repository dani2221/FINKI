import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;

//        Треба да се креира апликација за банка која ќе управуваа со сметките на повеќе корисниците и ќе врши трансакции помеѓу нив. Банката работи само со долари и притоа сите суми на пари се претставуваат како стрингови со знакот $ на крај, и точка помеѓу бројот на центи и бројот на долари без празни места. Бројот на центи треба да се состои од две цифри без разлика на износот.
//        За потребите на ваквата апликација треба да се напишат класите Account,Transaction и Bank. Класата Account претставува една сметка на еден корисник и треба да ги чува следните податоци:
//
//        Име на корисникот,
//        единствен идентификационен број (long)
//        тековното салдо на сметката.
//        Оваа класа исто така треба да ги имплементира и следниве методи
//
//        Account(String name, String balance) – конструктор со параметри (id-то треба да го генерирате сами со помош на класата java.util.Random)
//        getBalance():String
//        getName():String
//        getId():long
//        setBalance(String balance)
//        toString():String – враќа стринг во следниот формат, \n означува нов ред
//
//        Name:Andrej Gajduk\n Balance:20.00$\n
//
//        Класата Transaction претставува трансакција (префрлување пари од една на друга сметка), од страна на банката за што честопати се наплаќа провизија. За почеток треба да се напише класата Transaction со податочни членови за идентификационите броеви на две сметки, едната од која се одземаат парите и друга на која се додаваат парите, текстуален опис и износ на трансакцијата.
//
//        За оваа класа треба да ги имплементирате методите:
//
//        Transaction(long fromId, long toId, Stirng description, String amount) – конструктор со параметри
//        getAmount():String
//        getFromId():long
//        getToId():long
//        Оваа класа треба да е immutable, а можете и да ја направите и апстрактна бидејќи не е наменета директно да се користи туку само како основна класа за изведување на други класи.
//
//        Како што споменавме претходно банката наплаќа провизија за одредени трансакции. Има два типа на провизија, фискна сума и процент. Кај фиксна сума за било која трансакција без разлика на износот на трансакцијата се наплаќа исто провизија (пример 10$). Кај процент за секој еден долар од трансакцијата банката наплаќа одреден процент провизија (на пример 5%, или 5 центи на секој долар – процентите секогаш се целобројни и провизија се наплаќа само на цели долари).
//
//        За да се прави разлика меѓу различните типови на провизија, треба да напишете уште две класи кои ќе наследуваат од Transaction кои треба да ги именувате FlatAmountProvisionTransaction и FlatPercentProvisionTransaction.
//
//        Првата класа FlatAmountProvisionTransaction треба да содржи соодветен конструктор
//
//        FlatAmountProvisionTransaction(long fromId, long toId,String amount, String flatProvision) кој го иницијализира полето за опис на "FlatAmount" и соодветен get метод
//        getFlatAmount():String
//        Слично и класата FlatPercentProvisionTransaction треба да има соодветен конструктор
//
//        FlatPercentProvisionTransaction (long fromId, long toId, String amount, int centsPerDolar) кој го иницијализира полето за опис на "FlatPercent" и соодветен get метод
//        getPercent():int
//        Исто така треба да се преоптовари equals(Object o):boolean методот и за двете класи.
//
//        За крај треба да ја имплементирате класата Bank која ги чува сметките од своите корисници и дополнително врши трансакции. Класата освен сметките на своите корисници, треба да ги чува и сопственото име и вкупната сума на трансфери како и вкупната наплатена провизија од страна на банката за сите трансакции.
//
//        Класата Bank треба да ги нуди следните методи:
//
//        Bank(String name, Account accounts[]) – конструктор со соодветните параметри (направете сопствена копија на низата од сметки)
//        makeTransaction(Transaction t):boolean – врши проверка дали корисникот ги има потребните средства на сметка и дали и двете сметки на кои се однесува трансакцијата се нависитина во банката и ако и двата услови се исполнето ја извршува трансакцијата и враќа true, во спротивно враќа false
//        totalTransfers():String – ја дава вкупната сума на пари кои се префрлени во сите трансакции до сега
//        totalProvision():String – ја дава вкупната провизија наплатена од банката за сите извршени трансакции до сега
//        toString():String - го враќа името на банката во посебна линија во формат
//
//        Name:Banka na RM\n \n по што следат податоците за сите корисници.
//
//        Провизијата се наплаќа така што на основната сума на трансакцијата се додава вредноста не провизијата и таа сума се одзема од првата сметка.
//
//        За сите класи да се напишат соодветни equals и hashCode методи.
public class BankTester {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        String test_type = jin.nextLine();
        switch (test_type) {
            case "typical_usage":
                testTypicalUsage(jin);
                break;
            case "equals":
                testEquals();
                break;
        }
        jin.close();
    }

    private static void testEquals() {
        Account a1 = new Account("Andrej", "20.00$");
        Account a2 = new Account("Andrej", "20.00$");
        Account a3 = new Account("Andrej", "30.00$");
        Account a4 = new Account("Gajduk", "20.00$");
        List<Account> all = Arrays.asList(a1, a2, a3, a4);
        if (!(a1.equals(a1)&&!a1.equals(a2)&&!a2.equals(a1) && !a3.equals(a1)
                && !a4.equals(a1)
                && !a1.equals(null))) {
            System.out.println("Your account equals method does not work properly.");
            return;
        }
        Set<Long> ids = all.stream().map(Account::getId).collect(Collectors.toSet());
        if (ids.size() != all.size()) {
            System.out.println("Different accounts have the same IDS. This is not allowed");
            return;
        }
        FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
        FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(20, 20, "20.00$", "10.00$");
        FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, "20.00$", "10.00$");
        FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, "50.00$", "50.00$");
        FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, "20.00$", "10.00$");
        FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
        FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
        FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(10, 10, "20.00$", 10);
        FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, "50.00$", 10);
        FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 30);
        FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, "20.00$", 10);
        if (fa1.equals(fa1) &&
                !fa2.equals(null) &&
                fa2.equals(fa1) &&
                fa1.equals(fa2) &&
                fa1.equals(fa3) &&
                !fa1.equals(fa4) &&
                !fa1.equals(fa5) &&
                !fa1.equals(fp1) &&
                fp1.equals(fp1) &&
                !fp2.equals(null) &&
                fp2.equals(fp1) &&
                fp1.equals(fp2) &&
                fp1.equals(fp3) &&
                !fp1.equals(fp4) &&
                !fp1.equals(fp5) &&
                !fp1.equals(fp6)) {
            System.out.println("Your transactions equals methods do not work properly.");
            return;
        }
        Account accounts[] = new Account[]{a1, a2, a3, a4};
        Account accounts1[] = new Account[]{a2, a1, a3, a4};
        Account accounts2[] = new Account[]{a1, a2, a3};
        Account accounts3[] = new Account[]{a1, a2, a3, a4};

        Bank b1 = new Bank("Test", accounts);
        Bank b2 = new Bank("Test", accounts1);
        Bank b3 = new Bank("Test", accounts2);
        Bank b4 = new Bank("Sample", accounts);
        Bank b5 = new Bank("Test", accounts3);

        if (!(b1.equals(b1) &&
                !b1.equals(null) &&
                !b1.equals(b2) &&
                !b2.equals(b1) &&
                !b1.equals(b3) &&
                !b3.equals(b1) &&
                !b1.equals(b4) &&
                b1.equals(b5))) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        //accounts[2] = a1;
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        long from_id = a2.getId();
        long to_id = a3.getId();
        Transaction t = new FlatAmountProvisionTransaction(from_id, to_id, "3.00$", "3.00$");
        b1.makeTransaction(t);
        if (b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        b5.makeTransaction(t);
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        System.out.println("All your equals methods work properly.");
    }

    private static void testTypicalUsage(Scanner jin) {
        String bank_name = jin.nextLine();
        int num_accounts = jin.nextInt();
        jin.nextLine();
        Account accounts[] = new Account[num_accounts];
        for (int i = 0; i < num_accounts; ++i)
            accounts[i] = new Account(jin.nextLine(), jin.nextLine());
        Bank bank = new Bank(bank_name, accounts);
        while (true) {
            String line = jin.nextLine();
            switch (line) {
                case "stop":
                    return;
                case "transaction":
                    String descrption = jin.nextLine();
                    String amount = jin.nextLine();
                    String parameter = jin.nextLine();
                    int from_idx = jin.nextInt();
                    int to_idx = jin.nextInt();
                    jin.nextLine();
                    Transaction t = getTransaction(descrption, from_idx, to_idx, amount, parameter, bank);
                    System.out.println("Transaction amount: " + t.getAmount());
                    System.out.println("Transaction description: " + t.getDescription());
                    System.out.println("Transaction successful? " + bank.makeTransaction(t));
                    break;
                case "print":
                    System.out.println(bank.toString());
                    System.out.println("Total provisions: " + bank.totalProvision());
                    System.out.println("Total transfers: " + bank.totalTransfers());
                    System.out.println();
                    break;
            }
        }
    }

    private static Transaction getTransaction(String description, int from_idx, int to_idx, String amount, String o, Bank bank) {
        switch (description) {
            case "FlatAmount":
                return new FlatAmountProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, o);
            case "FlatPercent":
                return new FlatPercentProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, Integer.parseInt(o));
        }
        return null;
    }
}

class Account {
    private String name;
    private long id;
    private String balance;

    public Account(String name, String balance) {
        this.name = name;
        this.balance = balance;
        Random random = new Random();
        this.id = random.nextLong();
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + (int) id;
        hash = hash * prime + name.hashCode();
        hash = hash * prime + balance.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Account acc = (Account) obj;
        return this.id == acc.id && this.name.equals(acc.name)
                && this.balance.equals(acc.balance) && this.hashCode() == acc.hashCode();
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nBalance: " + balance + "\n";
    }
}

abstract class Transaction {
    private final long fromId;
    private final long toId;
    private final String description;
    private final String amount;

    public Transaction(long fromId, long toId, String description, String amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.description = description;
        this.amount = amount;
    }

    public long getFromId() {
        return fromId;
    }

    public long getToId() {
        return toId;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }

    public abstract double getProvision();

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + (int) fromId;
        hash = prime * hash + (int) toId;
        hash = prime * hash + description.hashCode();
        hash = prime * hash + amount.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FlatAmountProvisionTransaction) {
            FlatAmountProvisionTransaction tmp = (FlatAmountProvisionTransaction) obj;
            return this.fromId == tmp.getFromId() && this.toId == tmp.getToId()
                    && this.description.equals(tmp.getDescription()) && this.amount.equals(tmp.getAmount())
                    && this.hashCode() == tmp.hashCode();
        } else if (obj instanceof FlatPercentProvisionTransaction) {
            FlatPercentProvisionTransaction tmp = (FlatPercentProvisionTransaction) obj;
            return this.fromId == tmp.getFromId() && this.toId == tmp.getToId()
                    && this.description.equals(tmp.getDescription()) && this.amount.equals(tmp.getAmount())
                    && this.hashCode() == tmp.hashCode();
        } else
            return false;
    }
}

class FlatAmountProvisionTransaction extends Transaction implements Parser {
    private String flatProvision;

    public FlatAmountProvisionTransaction(long fromId, long toId, String amount, String flatProvision) {
        super(fromId, toId, "FlatAmount", amount);
        this.flatProvision = flatProvision;
    }

    public String getFlatProvision() {
        return flatProvision;
    }

    @Override
    public double getProvision() {
        return Parser.parseStringToDouble(flatProvision);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = super.hashCode();
        hash = hash * prime + flatProvision.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FlatAmountProvisionTransaction))
            return true;
        return super.equals(obj);
    }
}

class FlatPercentProvisionTransaction extends Transaction implements Parser {
    private int centsPerDollar;

    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int centsPerDollar) {
        super(fromId, toId, "FlatPercent", amount);
        this.centsPerDollar = centsPerDollar;
    }

    public int getCentsPerDollar() {
        return centsPerDollar;
    }

    @Override
    public double getProvision() {
        return (centsPerDollar / 100.0) * (int) Parser.parseStringToDouble(super.getAmount());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = super.hashCode();
        hash = prime * hash + centsPerDollar;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FlatPercentProvisionTransaction))
            return false;
        return super.equals(obj);
    }
}

class Bank implements Parser {
    private String name;
    private Account[] accounts;
    private double totalTransferSum;
    private double totalProvisionSum;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.totalTransferSum = 0;
        this.totalProvisionSum = 0;
        this.accounts = new Account[accounts.length];
        for (int i = 0; i < accounts.length; ++i)
            this.accounts[i] = accounts[i];
    }

    private int findId(long id) {
        int index = -1;
        for (int i = 0; i < accounts.length; ++i) {
            if (accounts[i].getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean makeTransaction(Transaction t) {
        int indexFromId = findId(t.getFromId());
        int indexToId = findId(t.getToId());

        // if ID's are not in the list of accounts
        if (indexFromId == -1 || indexToId == -1)
            return false;

        double balanceFromId = Parser.parseStringToDouble(accounts[indexFromId].getBalance());
        double balanceToId = Parser.parseStringToDouble(accounts[indexToId].getBalance());
        double transactionAmount = Parser.parseStringToDouble(t.getAmount());

        // if there is not enough money on the account balance
        if (balanceFromId < transactionAmount)
            return false;

        double provision = t.getProvision();
        totalTransferSum += transactionAmount;
        totalProvisionSum += provision;
        balanceFromId -= (transactionAmount + provision);
        if (indexFromId == indexToId)  // if someone makes a transaction to him/her self charge only the provision
            balanceToId -= provision;
        else
            balanceToId += transactionAmount;  // else add the transactionAmount
        accounts[indexFromId].setBalance(String.format("%.2f", balanceFromId) + "$");
        accounts[indexToId].setBalance(String.format("%.2f", balanceToId) + "$");
        return true;
    }

    public String totalTransfers() {
        return String.format("%.2f", totalTransferSum) + "$";
    }

    public String totalProvision() {
        return String.format("%.2f", totalProvisionSum) + "$";
    }

    public Account[] getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        sb.append("\n\n");
        for (Account account : accounts)
            sb.append(account);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + name.hashCode();
        hash = hash * prime + (int) totalTransferSum;
        hash = hash * prime + (int) totalProvisionSum;
        hash = hash * prime + Arrays.hashCode(accounts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Bank bank = (Bank) obj;
        return this.name.equals(bank.name) && Arrays.equals(this.accounts, bank.accounts)
                && this.totalTransferSum == bank.totalTransferSum && this.totalProvisionSum == bank.totalProvisionSum
                && this.hashCode() == bank.hashCode();
    }
}

interface Parser {
    static double parseStringToDouble(String num) {
        return Double.parseDouble(num.substring(0, num.length() - 1));
    }
}
