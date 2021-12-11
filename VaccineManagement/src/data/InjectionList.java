
package data;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import util.FileDAO;
import util.Inputter;
import static util.Inputter.daysBetween;

public class InjectionList implements List {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    StudentList studentList = new StudentList();
    VaccineList vaccineList = new VaccineList();
    ArrayList<Injection> injectionList = new ArrayList();

    public InjectionList() {
        readFile();
    }
    
    public void addInjection(){
        String injectionId, fPlace, fDate, sPlace =null, sDate=null;
        String sID, vID;
        int pos, pos1;
        int check, check2;
        do{
            do{
                injectionId = Inputter.getString("Enter the injection id: ", "Input invalid!");
                pos = searchID(injectionId);
                if(pos!=-1)
                    System.out.println(ANSI_RED+ "This injection id already exist!" + ANSI_RESET);
            }while(pos != -1);
            do{
                sID = studentList.menuStudent();
                pos1 = searchIDStudent(sID);
                if(pos1!=-1)
                    System.out.println(ANSI_RED + "This student already exist!" + ANSI_RESET);
            }while(pos1 != -1);
            fPlace = Inputter.getPlace("Enter the place of the first injection: ", "Input invalid!");
            fDate = Inputter.getDate("Enter the date of the first injection: ", "Input invalid!");
            checkDay(fDate);
            if(checkDay(fDate)){
                check2 = Inputter.getYesNo("This student was eligible for a 2nd injection (4 weeks to 12 weeks). Do you want to enter the information a second time?", "Please enter Yes/No or Y/N!");
                if(check2==1){
                    sPlace = Inputter.getPlace("Enter the place of the second injection: ", "Input invalid!");
                    sDate = Inputter.getDate1(fDate);
                }
            }
            vID = vaccineList.menuVaccine();
            injectionList.add(new Injection(injectionId, sID, fPlace, fDate, "null", "null", vID));
            System.out.println(ANSI_GREEN + "Add successfully!" + ANSI_RESET);
            check = Inputter.getYesNo("Do you want to continue adding another injection? (Yes/No) ", "Please enter Yes/No or Y/N!");
        }while(check==1);
    }
    public boolean checkDay(String str) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int n = daysBetween(str,sdf.format(date));
            if (n<27 || n >85){
                return false;
            }
            else
                return true;
    }
    public void update(){
        int pos;
        String id;
        if(injectionList.isEmpty()){
            System.out.println(ANSI_RED + "The injection list is empty. Nothing to update!" + ANSI_RESET);
            return;
        }
        id = Inputter.getString("Enter the injection id you want to update: ", "Input invalid!");
        pos = searchID(id);
        if(pos==-1){
            System.out.println(ANSI_RED + "Injection does not exist!" + ANSI_RESET);
            return;
        }
        if(search(id).getSecondDate().equals("null")&&search(id).getSecondPlace().equals("null")){
            System.out.println(ANSI_BLUE + "The injection you want to update: " + ANSI_RESET);
            infoAInjection(id);
            search(id).setSecondPlace(Inputter.getPlace("Enter the place of the second injection: ", "Input invalid!"));
            search(id).setSecondDate(Inputter.getDate1(search(id).getFirstDate()));
            System.out.println(ANSI_GREEN + "Update successfully!" + ANSI_RESET);               
        }
        else{
            System.out.println(ANSI_RED + "Student has completed 2 injections!" + ANSI_RESET);
        } 
    }
    
    public void infoAInjection(String id){
        search(id).output1();
        studentList.displayAStudent(search(id).getStudentID());
        search(id).output2();
        vaccineList.displayAVaccine(search(id).getVaccineID());
        System.out.println();
    }
    
    public void remove(){
        if(injectionList.isEmpty()){
            System.out.println(ANSI_RED + "The injection list is empty. Nothing to remove!" +ANSI_RESET);
            return;
        }
        int check;
        String id = Inputter.getString("Enter the injection id you want to remove: ", "Input invalid!");
        if(searchID(id)==-1)
            System.out.println(ANSI_RED + "Injection does not exist!" + ANSI_RESET);
        else{
            System.out.println(ANSI_BLUE + "The injection you want to remove: " + ANSI_RESET);
            infoAInjection(id);
            check = Inputter.getYesNo("Are you sure you want to delete it?(Yes/No)", "Please enter Yes/No or Y/N!");
            if(check == 1){
                injectionList.remove(search(id));
                System.out.println(ANSI_GREEN + "Remove successfully!" +ANSI_RESET);
            }
            else{
                System.out.println(ANSI_RED + "Remove failed!" + ANSI_RESET);
            }
        }
        
    }
    
    public void searchByStudentID(){
        String id = studentList.menuStudent();
        for (Injection injection : injectionList) {
            if(injection.getStudentID().equalsIgnoreCase(id)){
                infoAInjection(injection.getInjectionID());
                return;
            }
        }
        System.out.println(ANSI_RED + "Students have not been vaccinated. Not found in the list." + ANSI_RESET);
    }
    
    public Injection search(String id){
        for (Injection injection : injectionList) {
            if(injection.getInjectionID().equalsIgnoreCase(id))
                return injection;
        }
        return null;
    }
    
    public int searchID(String id){
        for (int i = 0; i < injectionList.size(); i++) {
            if(injectionList.get(i).getInjectionID().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }
    
    public int searchIDStudent(String id){
        for (int i = 0; i < injectionList.size(); i++) {
            if(injectionList.get(i).getStudentID().equalsIgnoreCase(id.replaceAll("\\s\\s+"," "))){
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void saveFile() {
        try {
            FileWriter fw = new FileWriter("injection.txt");
            fw.write(String.format("%-10s|%-10s|%-15s|%-10s|%-15s|%-10s|%-10s|\n", "ID","STUDENT_ID","1ST_PLACE", "1ST_DATE", "2ND_PLACE", "2ND_DATE", "VACCINE_ID"));
            for (Injection in : injectionList) {
                fw.write(in.toString());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean isEmpty(){
        return (studentList.isEmpty()||vaccineList.isEmpty());
    }
    
    public void addData(){
        if(studentList.isEmpty())
            studentList.add();
        if(vaccineList.isEmpty())
            vaccineList.add();
    }
    
    @Override
    public void display() {
        if(injectionList.isEmpty()){
            System.out.println(ANSI_RED + "The injection list is empty!" +ANSI_RESET);
            return;
        }
        int check = 0;
        for (Injection injection : injectionList) {
            if(studentList.search(injection.getStudentID())!=null){
                check++;
            }
        }
        if(check!=0){
            for (int i = 0; i < 125; i++) {
                System.out.print("-");
            }
            System.out.println();
            System.out.printf(ANSI_BLUE + "|%-10s|%-10s|%-20s|%-15s|%-10s|%-15s|%-10s|%-10s|%-15s|\n", "ID","STUDENT_ID", "STUDENT_NAME", "1ST_PLACE", "1ST_DATE", "2ND_PLACE", "2ND_DATE", "VACCINE_ID", "VACCINE_NAME" + ANSI_RESET);
            for (int i = 0; i < 125; i++) {
                System.out.print("-");
            }
            System.out.println();     
        }
        for (Injection injection : injectionList) {
            if(studentList.search(injection.getStudentID())!=null){
                injection.output1();
                studentList.displayAStudent(injection.getStudentID());
                injection.output2();
                vaccineList.displayAVaccine(injection.getVaccineID());
                System.out.println();
            }
        }
        if(check!=0){
            for (int i = 0; i < 125; i++) {
                System.out.print("-");
            }
            System.out.println();
        }
        else{
            System.out.println("The injection list is empty!");
            injectionList.clear();
        }
    }
    
    @Override
    public void readFile() {
        if(!isEmpty())
            injectionList = FileDAO.readFileInjection("injection.txt");
    }
    
}
