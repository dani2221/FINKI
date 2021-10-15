package mk.ukim.finki.aud3.calculator;

public class UnknownOperatorException extends Exception{
    public UnknownOperatorException(char operator) {
        super(String.format("%c is an unknown operation", operator));
    }
}
