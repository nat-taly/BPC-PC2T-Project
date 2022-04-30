import cz.vut.Numbers;
import cz.vut.db.crud.Insert;
import cz.vut.db.crud.Select;
import cz.vut.db.init.Create;
import cz.vut.pc2t.student.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        int choice;
       
        Scanner sc = new Scanner(System.in);
        System.out.println("Databáze studentů");

        ListOfStudents students = new ListOfStudents();

        while (run){
            System.out.println();
            System.out.println("1) Vložit nového studenta");
            System.out.println("2) Vložit známku");
            System.out.println("3) Smazat studenta");
            System.out.println("4) Najít studenta podle ID");
            System.out.println("5) Dovednost podle ID");
            System.out.println("6) Vypis studentu");
            System.out.println("7) Vypis studijnich prumeru");
            System.out.println("8) Vyúis poctu studentu v oborech");
            System.out.println("9) Ulozit do datbaze");
            System.out.println("10) Nacteci z datbaze");
            System.out.println("11) Ulozit do souboru");
            System.out.println("12) Nacteci ze souboru");
            System.out.println("13) Konec");
            System.out.println("\n");

            choice = Numbers.pouzeCelaCisla(sc);
            switch (choice){
                case 1:
                    System.out.println("Zadej jméno: ");
                    String name = sc.next();
                    System.out.println("Zadej příjmení: ");
                    String surname = sc.next();
                    System.out.println("Zadej datum narozerní (ve formátu YYYY-MM-DD): ");
                    String birth_date = sc.next();

                    System.out.println("Výběr oboru: ");
                    System.out.println("1 - Technický");
                    System.out.println("2 - Humanitní");
                    System.out.println("3 - Kombinovaný");
                    choice = Numbers.pouzeCelaCisla(sc);
                    switch (choice){
                        case 1:
                            students.addStudent(students.getNextId(), new TechStudent(students.getNextId(), name, surname, birth_date));
                            break;
                        case 2:
                            students.addStudent(students.getNextId(), new HumStudent(students.getNextId(), name, surname, birth_date));
                            break;
                        case 3:
                            students.addStudent(students.getNextId(), new TechHumStudent(students.getNextId(), name, surname, birth_date));
                    }
                    break;

                case 2:
                    System.out.println("Zadej ID: ");
                    try {
                        int id = Numbers.pouzeCelaCisla(sc);
                        Student our_student = students.getStudentById(id);
                        System.out.println("Zadej známku: ");
                        int mark = Numbers.pouzeZnamku(sc);
                        our_student.addMark(mark);
                    }
                    catch (NullPointerException e){
                        System.out.println("ID není v databázi známka nepřidána");
                    }
                    break;

                case 3:
                    System.out.println("Zadej ID: ");
                    try{
                        int id = Numbers.pouzeCelaCisla(sc);
                        students.deleteStudent(id);
                    }catch (NullPointerException e){
                        System.out.println("ID není v databázi, student neexistuje");
                    }
                    break;

                case 4:
                    System.out.println("Zadej ID: ");
                    try{
                        int id = Numbers.pouzeCelaCisla(sc);
                        System.out.println(students.getStudentById(id));
                    }catch (NullPointerException e){
                        System.out.println("ID není v databázi, student neexistuje");
                    }
                    break;

                case 5:
                    System.out.println("Zadej ID: ");
                    try{
                        int id = Numbers.pouzeCelaCisla(sc);
                        Student own_student = students.getStudentById(id);
                        own_student.skill();
                    }catch (NullPointerException e){
                        System.out.println("ID není v databázi, student neexistuje");
                    }
                    break;

                case 6:
                    System.out.println("Technický obor: ");
                    students.getStudentsSortBySurname("cz.vut.pc2t.student.TechStudent");
                    System.out.println("Humanitní obor: ");
                    students.getStudentsSortBySurname("cz.vut.pc2t.student.HumStudent");
                    break;

                case 7:
                    System.out.println("Průměr technických oborů: " + String.format( "%.2f",students.getStudenTypeAverage("cz.vut.pc2t.student.TechStudent")));
                    System.out.println("Průměr humaitních oborů: " + String.format( "%.2f", students.getStudenTypeAverage("cz.vut.pc2t.student.HumStudent")));
                    break;

                case 8:
                    System.out.println("Studenů v technických oborech: " + students.getCountOfStudents("cz.vut.pc2t.student.TechStudent"));
                    System.out.println("Studenů v humanitních oborech: " + students.getCountOfStudents("cz.vut.pc2t.student.HumStudent"));
                    System.out.println("Z toho je kombinovaných: " + students.getCountOfStudents(""));
                    break;

                case 9:
                    Create.init_tables();
                    Insert.rewriteDatabse(students);
                    break;
                    
                case 10:
                    students = Select.allStudents();
                    break;

                case 11:
                    students.saveToFile("students.txt");
                    break;

                case 12:
                    students.loadFromFile("students.txt");
                    break;

                case 13:
                    run = false;
                    break;
            }
        }
    }
}
