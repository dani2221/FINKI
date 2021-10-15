package mk.ukim.finki.aud3.bank;

public class InterestCheckingAccount extends Account implements InterestBearingAccount{
    public static final double INTEREST_RATE = 0.03;

    public InterestCheckingAccount(String accountOwner, int number, double currentAmount) {
        super(accountOwner, number, currentAmount);
    }

    @Override
    public void addInterest() {
        addAmount(getCurrentAmount() * INTEREST_RATE);
    }
}
