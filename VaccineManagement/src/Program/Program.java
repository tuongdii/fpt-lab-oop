
package Program;

import data.InjectionList;
import util.Inputter;
import util.Menu;

public class Program {
 public static void main(String[] args){
        InjectionList list = new InjectionList();
        int choice;
        int choice1 = 0;
        Menu menu = new Menu("Welcome to Vaccine Management - @ 2021 by SE150159 Võ Thị Tường Duy");
        menu.addNewOption("1. Show information all students have been injected.");
        menu.addNewOption("2. Add student's vaccine injection information.");
        menu.addNewOption("3. Updating information of students' vaccine injection.");
        menu.addNewOption("4. Delete student vaccine injection information.");
        menu.addNewOption("5. Search for injection information by studentID.");
        menu.addNewOption("6. Quit");
        if(list.isEmpty()){
            System.out.println("Student data and vaccines are empty. You need to enter it before administering the injection.");
            Menu menu1 = new Menu("Choice of the data input method:");
            menu1.addNewOption("1. Import in here");
            menu1.addNewOption("2. Import in the file");
            menu1.printMenu();
            choice1 = menu1.getChoice();
        }
        if(choice1==1||choice1==0){
            do{ 
                list.addData();
                menu.printMenu();
                choice = menu.getChoice();
                switch(choice){
                    case 1:
                        list.display();
                        list.saveFile();
                        break;
                    case 2:
                        list.addInjection();
                        list.saveFile();
                        break;
                    case 3:
                        list.update();
                        list.saveFile();
                        break;
                    case 4:
                        list.remove();
                        list.saveFile();
                        break;
                    case 5:
                        list.searchByStudentID();
                        break;
                    case 6:
                        list.saveFile(); 
                        System.out.println("You have exited the program!");
                        break;
                    }
                }while(choice<=5);
            }
            else{
                    System.out.println("You have exited the program!");
                    System.out.println("Go back to the file to import the data.");
            }
    }
}
