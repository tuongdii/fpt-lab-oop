
package program;

import data.FoodManager;
import util.Inputter;
import util.Menu;

public class Program {
 public static void main(String[] args){
        FoodManager list = new FoodManager();
        list.data();
        int choice;
        Menu menu = new Menu("Welcome to Food Management - @ 2021 by SE150159 Võ Thị Tường Duy");
        menu.addNewOption("1. Add a new food.");
        menu.addNewOption("2. Search a food by name.");
        menu.addNewOption("3. Remove the food by ID.");
        menu.addNewOption("4. Print the food list in the descending order of expired date.");
        menu.addNewOption("5. Quit");
        do{ 
            Inputter.decorate();
            menu.printMenu();
            Inputter.decorate();
            choice = menu.getChoice();
            switch(choice){
                case 1:
                    Inputter.decorate();
                    list.addFood();
                    Inputter.decorate();
                    list.saveFile();
                    break;
                case 2:
                    Inputter.decorate();
                    list.searchByName();
                    Inputter.decorate();
                    break;
                case 3: 
                    Inputter.decorate();
                    list.removeByID();
                    Inputter.decorate();
                    list.saveFile();
                    break;
                case 4:
                    System.out.println("===========================================================================================");
                    list.sortByDate();
                    System.out.println("===========================================================================================");
                    break;
                case 5:
                    list.saveFile(); 
                    System.out.println("You have exited the program!");
                    break;
            }
        }while(choice<=4);
    }
}
