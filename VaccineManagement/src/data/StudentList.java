/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.FileWriter;
import java.util.ArrayList;
import util.FileDAO;
import util.Inputter;
import util.Menu;

/**
 *
 * @author DELL
 */
public class StudentList implements List{
    ArrayList<Student> list = new ArrayList();
    
    public StudentList(){
        this.readFile();
    }   
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public int getSize(){
        return list.size();
    }
    public void add(){
        String id, name;
        int pos, n;
        n = Inputter.getPostiveAnInteger("How many students do you want to import: ", "Input Invalid!");
        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i+1) +"/"+ n);
            do{
                id = Inputter.getString("Enter the student ID: ", "Input Invalid!");
                pos = searchID(id);
                if(pos!=-1)
                    System.out.println("The student already exits!");
            }while(pos != -1);
            name = Inputter.getString("Enter the student name: ", "Input Invalid!"); 
            list.add(new Student(id, name));
        }
        saveFile();
    }
    @Override
    public void saveFile() {
        if (list.isEmpty()) {
            System.out.println("Save FAILED. Empty list.");
            return;
        }
        try {
            FileWriter fw = new FileWriter("student.txt");
            for (Student st : list) {
                fw.write(st.toString());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int searchID(String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getsID().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    public Student search(String id){
        for (Student student : list) {
            if(student.getsID().equalsIgnoreCase(id)){
                return student;
            }
        }
        return null;
    }
    public String menuStudent(){
        while (true) {
            Menu menu = new Menu("Choice the student id to be injected:");
            for (int i = 0; i < list.size(); i++) {
                menu.addNewOption((i+1) +". " + list.get(i).toString2());
            }
            menu.printMenu();
            int choice = menu.getChoice();
            for (int i = 0; i < list.size(); i++) {
                if((choice-1)==i)
                    return list.get(i).getsID();
            }               
        }
    }
    public void displayAStudent(String id){
        for (Student student : list) {
            if(student.getsID().equalsIgnoreCase(id))
                student.output();
        }
    }
    @Override
    public void display() {
        if(list.isEmpty()){
            System.out.println("The student list is empty!");
            return;
        }
        for (int i = 0; i < 33; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("|%-10s|%-20s|\n", "STUDENT ID", "STUDENT NAME");
        for (Student student : list) {
            student.output();
            System.out.println();
        }
        for (int i = 0; i < 33; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    @Override
    public void readFile() {
        list = FileDAO.readFileStudent("student.txt");
    }   
}
