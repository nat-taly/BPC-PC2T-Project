package cz.vut;

public class MarkException extends java.lang.Exception{
    public MarkException(int number){
        super("Number " + number + " is out of the range 0 - 5");
    }
}
