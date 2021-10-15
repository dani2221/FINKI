package mk.ukim.finki.aud3.bank;

import java.util.Arrays;

public class Bank {
    private Account[] accounts;
    private int totalAccounts;
    private int maxAccounts;

    public Bank(int maxAccounts) {
        this.maxAccounts = maxAccounts;
        this.accounts = new Account[maxAccounts];
        this.totalAccounts = 0;
    }

    public void addAccount(Account account){
        if(totalAccounts == maxAccounts){
            accounts = Arrays.copyOf(accounts, maxAccounts * 2);
        }
        accounts[totalAccounts++] = account;
    }

    public double totalAssets() {
        double sum = 0;
        for (Account account : accounts){
            sum += account.getCurrentAmount();
        }
        return sum;
    }

    public void addInterestToAllAccounts() {
        for (Account account : accounts){
            if(account instanceof InterestBearingAccount){
                InterestBearingAccount interestBearingAccount = (InterestBearingAccount) account;
                interestBearingAccount.addInterest();
            }
        }
    }

}
