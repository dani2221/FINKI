package mk.ukim.finki.aud3.bank;

public class PlatinumCheckingAccount extends InterestCheckingAccount{
    public PlatinumCheckingAccount(String accountOwner, int number, double currentAmount) {
        super(accountOwner, number, currentAmount);
    }

    @Override
    public void addInterest() {
        addAmount(getCurrentAmount() * InterestCheckingAccount.INTEREST_RATE * 2);
    }
}
