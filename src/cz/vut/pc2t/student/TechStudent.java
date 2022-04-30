package cz.vut.pc2t.student;
import java.util.LinkedList;

public class TechStudent extends Student{
    public TechStudent(int id, String name, String surname, String birth_date, LinkedList<Integer> marks) {
        super(id, name, surname, birth_date, "Technical", marks);
    }

    public TechStudent(int id, String name, String surname, String birth_date) {
        super(id, name, surname, birth_date, "Technical");
    }

    @Override
    public void skill() {
        System.out.println("My skill is leap year... ");
        System.out.println("I was born " + super.yearFromDate() + " Was leap year: " + isLeapYear());
    }

    public boolean isLeapYear(){
        int birthYear = super.yearFromDate();
        return Student.LeapYear(birthYear);
    }
}
