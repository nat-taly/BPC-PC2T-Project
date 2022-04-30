package cz.vut.pc2t.student;
import java.util.LinkedList;

public class TechHumStudent extends Student{
    public TechHumStudent(int id, String name, String surname, String birth_date, LinkedList<Integer> marks) {
        super(id, name, surname, birth_date, "Tech&Hum", marks);
    }

    public TechHumStudent(int id, String name, String surname, String birth_date) {
        super(id, name, surname, birth_date, "Tech&Hum");
    }

    @Override
    public void skill() {
        System.out.println("My skill is Zodiac Sign and leap year...");
        System.out.println("I was born " + super.yearFromDate() + " Was leap year: " + isLeapYear());
        System.out.println("I was born " + dayFromDate() + ". " + monthFromDate() + ". and I am: " + getZodiacSign());
    }

    public String getZodiacSign(){
        int birthMounth = super.monthFromDate();
        int birthDay = super.dayFromDate();
        return Student.ZodiacSign(birthMounth, birthDay);
    }

    public boolean isLeapYear(){
        int birthYear = super.yearFromDate();
        return Student.LeapYear(birthYear);
    }
}
