package cz.vut.pc2t.student;

import cz.vut.db.crud.Select;
import java.util.LinkedList;

public abstract class Student implements Comparable<Student>{
    private int id;
    private String name;
    private String surname;
    private String birth_date;
    private String student_type;
    private LinkedList<Integer> marks = new LinkedList<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getStudent_type() {
        return student_type;
    }


    public Student(int id,String name, String surname, String birth_date, String student_type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.student_type = student_type;
    }

    public Student(int id, String name, String surname, String birth_date, String student_type, LinkedList<Integer> marks) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.student_type = student_type;
        this.marks = marks;
    }

    public void setMarks(LinkedList<Integer> marks) {
        this.marks = marks;
    }

    public LinkedList<Integer> getMarks() {
        return marks;
    }

    public void addMark(int mark){
        this.marks.add(mark);
    }

    public double getMarkAverage(){
        double average;
        try{
            int countMarks = marks.size();
            int totalMarks = 0;
            for (int i = 0; i < countMarks; i++) {
                totalMarks += marks.get(i);
            }
            average = (double) totalMarks / countMarks;
        }
        catch (ArithmeticException e){
            average = 0;
            if (marks.size() == 0){
                average = 0;
            }
        }
        catch (NullPointerException e){
            average =0;
        }
        return average;
    }

    protected int yearFromDate(){
        String[] arrDate = birth_date.split("-");
        String strYear  = arrDate[0];
        return Integer.parseInt(strYear);
    }

    protected int monthFromDate(){
        String[] arrDate = birth_date.split("-");
        String strMonth  = arrDate[1];
        return Integer.parseInt(strMonth);
    }

    protected int dayFromDate(){
        String[] arrDate = birth_date.split("-");
        String strDay  = arrDate[2];
        return Integer.parseInt(strDay);
    }
    public abstract void skill();

    @Override
    public int compareTo(Student o) {
        return surname.compareTo(o.getSurname());
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", name: " + name +
                ", surname: " + surname  +
                ", birth_date: " + birth_date +
                ", student_type: " + student_type +
                ", average: " + getMarkAverage();
    }

    public String toFile(){
        return id +
                ", " + name +
                ", " + surname  +
                ", " + birth_date +
                ", " + student_type +
                ", " + marks.toString();
    }

    protected static boolean LeapYear (int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    protected static String ZodiacSign(int month, int day){
        if (month == 12){
            if (day < 22)
                return ZodiacSign.sagittarius.toString();
            else
                return ZodiacSign.capricorn.toString();
        }

        else if (month == 1){
            if (day < 20)
                return ZodiacSign.capricorn.toString();
            else
                return ZodiacSign.aquarius.toString();
        }

        else if (month == 2){
            if (day < 19)
                return ZodiacSign.aquarius.toString();
            else
                return ZodiacSign.pisces.toString();
        }

        else if(month == 3){
            if (day < 21)
                return ZodiacSign.pisces.toString();
            else
                return ZodiacSign.aries.toString();
        }
        else if (month == 4){
            if (day < 20)
                return ZodiacSign.aries.toString();
            else
                return ZodiacSign.taurus.toString();
        }

        else if (month == 5){
            if (day < 21)
                return ZodiacSign.taurus.toString();
            else
                return ZodiacSign.gemini.toString();
        }

        else if( month == 6){
            if (day < 21)
                return ZodiacSign.gemini.toString();
            else
                return ZodiacSign.cancer.toString();
        }

        else if (month == 7){
            if (day < 23)
                return ZodiacSign.cancer.toString();
            else
                return ZodiacSign.leo.toString();
        }

        else if( month == 8){
            if (day < 23)
                return ZodiacSign.leo.toString();
            else
                return ZodiacSign.virgo.toString();
        }

        else if (month == 9){
            if (day < 23)
                return ZodiacSign.virgo.toString();
            else
                return ZodiacSign.libra.toString();
        }

        else if (month == 10){
            if (day < 23)
                return ZodiacSign.libra.toString();
            else
                return ZodiacSign.scorpio.toString();
        }

        else if (month == 11){
            if (day < 22)
                return ZodiacSign.scorpio.toString();
            else
                return ZodiacSign.sagittarius.toString();
        }
        return null;
    }
}
