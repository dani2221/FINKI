package mk.ukim.finki.aud3.bank;

public abstract class Account {
    private String accountOwner;
    private int number;
    private double currentAmount;

    public Account(String accountOwner, int number, double currentAmount) {
        this.accountOwner = accountOwner;
        this.number = number;
        this.currentAmount = currentAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void addAmount(double amount){
        this.currentAmount += amount;
    }

    public void withdraw(double amount){
        this.currentAmount -= amount;
    }
}
