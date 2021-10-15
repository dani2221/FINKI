package mk.ukim.finki.aud2;

public class CombinationLock {

    private int combination;
    private boolean isOpen;

    public CombinationLock(int combination) {
        this.combination = combination;
    }

    //try to open the combination lock
    public boolean isOpen(int combination){
        this.isOpen = (this.combination == combination);
        return this.isOpen;
    }

    //try to change the combination
    public boolean changeCombo(int oldCombination, int newCombination){
        if(this.combination == oldCombination){
            this.combination = newCombination;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CombinationLock combinationLock = new CombinationLock(123);
        System.out.println(combinationLock.isOpen(123));
        System.out.println(combinationLock.changeCombo(123, 456));
        System.out.println(combinationLock.isOpen(456));
    }
}
