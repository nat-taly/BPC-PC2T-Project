package cz.vut.pc2t.student;
import java.util.LinkedList;

public class HumStudent extends Student{
    public HumStudent(int id, String name, String surname, String birth_date,  LinkedList<Integer> marks) {
        super(id, name, surname, birth_date, "Humanitarian", marks);
    }

    public HumStudent(int id, String name, String surname, String birth_date) {
        super(id, name, surname, birth_date, "Humanitarian");
    }

    @Override
    public void skill() {
        System.out.println("My skill is Zodiac Sign...");
        System.out.println("I was born " + dayFromDate() + ". " + monthFromDate() + ". and I am: " + getZodiacSign());
    }

    public String getZodiacSign(){
        int birthMounth = super.monthFromDate();
        int birthDay = super.dayFromDate();
        return Student.ZodiacSign(birthMounth, birthDay);
    }
}
