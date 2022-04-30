package cz.vut.pc2t.student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ListOfStudents {
    //creating hashmap - student ID is the key
    private HashMap<Integer, Student> list_of_students = new HashMap<>();
    private int ids = 0;

    public void addStudent(int id, Student student){
        list_of_students.put(id, student);
        ids++;
    }

    public HashMap<Integer, Student> getAllStudents(){
        return list_of_students;
    }

    public double getStudenTypeAverage(String student_type){
        double average;
        int countMarks = 0;
        double totalMarks = 0;

        for (Student student: list_of_students.values()){
            // if state for counting average of marks - if student is Tech and Hum (in both departments)
            if(student.getClass().getName().equals(student_type) || student.getClass().getName().equals("cz.vut.pc2t.student.TechHumStudent")){
                if (student.getMarkAverage() == 0){
                    continue;
                }
                countMarks +=1;
                totalMarks += student.getMarkAverage();
            }
        }
        try{
            average = (double) totalMarks/countMarks;
        }
        catch (ArithmeticException e){
            average = 0;
        }
        return average;
    }

    public int getNextId(){
        return this.ids + 1;
    }

    public Student getStudentById(int id){
        return list_of_students.get(id);
    }

    public int getCountOfStudents(String student_type){
        int count = 0;
        for (Student student: list_of_students.values()){
            if(student.getClass().getName().equals(student_type) || student.getClass().getName().equals("cz.vut.pc2t.student.TechHumStudent")){
                count++;
            }
        }
        return count;
    }

    public void getStudentsSortBySurname(String student_type){
        ArrayList<Student> students = new ArrayList<>();
        for (Student student: list_of_students.values()){
            if(student.getClass().getName().equals(student_type) || student.getClass().getName().equals("cz.vut.pc2t.student.TechHumStudent")){
                students.add(student);
            }
        }
        students.sort((o1, o2) -> o1.getSurname().compareTo(o2.getSurname()));
        for (Student student: students){
            System.out.println(student);
        }
    }

    public void deleteStudent(int id){
        list_of_students.remove(id);
    }

    public void saveToFile(String filename){
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(filename);
            for (Student student: list_of_students.values()){
                myWriter.write(student.toFile() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename){
        HashMap<Integer, Student> students = new HashMap<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Student student = strToStudent(data);
                students.put(student.getId(), student);
            }
            myReader.close();
            list_of_students = students;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private Student strToStudent(String str){
        String[] arr =  str.split(", ");
        String student_type = arr[4];
        if (student_type.equals("Technical")){
            return new TechStudent(Integer.parseInt(arr[0]), arr[1], arr[2],arr[3], strToMarks(str));
        }
        else if (student_type.equals("Humanitarian")){
            return new HumStudent(Integer.parseInt(arr[0]), arr[1], arr[2],arr[3], strToMarks(str));
        }
        else if (student_type.equals("Tech&Hum")){
            return new TechHumStudent(Integer.parseInt(arr[0]), arr[1], arr[2],arr[3], strToMarks(str));
        }
        return null;
    }

    private LinkedList<Integer> strToMarks(String str){
        str = removeNFromString(str, 5);
        int len = str.length();
        str = str.substring(2,len-2);
        String[] arr = str.split(", ");
        LinkedList<Integer> marks = new LinkedList<>();
        for (String str_mark: arr){
            marks.add(Integer.parseInt(str_mark));
        }
        return marks;
    }
    
    private String removeNFromString(String str, int n){
        String[] arr =  str.split(", ");
        List<String> list1 = new ArrayList<String>();

        Collections.addAll(list1, arr);

        for (int i = 0; i < n; i++) {
            list1.remove(0);
        }
        return list1.toString();
    }

    public int totalIntype(String student_type){
        int count = 0;
        for (Student student: list_of_students.values()){
            if(student.getClass().getName().equals(student_type) || student.getClass().getName().equals("cz.vut.pc2t.student.TechHumStudent")){
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return list_of_students.toString();
    }
}
