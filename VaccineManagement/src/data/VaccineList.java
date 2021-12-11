
package data;

import java.io.FileWriter;
import java.util.ArrayList;
import util.FileDAO;
import util.Inputter;
import util.Menu;


public class VaccineList implements List{
    ArrayList<Vaccine> list = new ArrayList();
    
    public VaccineList(){
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
        n = Inputter.getPostiveAnInteger("How many types of vaccines do you want to import: ", "Input Invalid!");
        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i+1) +"/"+ n);
            do{
                id = Inputter.getString("Enter the vaccine ID: ", "Input Invalid!");
                pos = searchID(id);
                if(pos!=-1)
                    System.out.println("The vaccine already exits!");
            }while(pos != -1);
            name = Inputter.getString("Enter the vaccine name: ", "Input Invalid!"); 
            list.add(new Vaccine(id, name));
        }
        saveFile();
    }
    public void saveFile() {
        if (list.isEmpty()) {
            System.out.println("Save FAILED. Empty list.");
            return;
        }
        try {
            FileWriter fw = new FileWriter("vaccine.txt");
            for (Vaccine vc : list) {
                fw.write(vc.toString());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int searchID(String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getvID().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    public String menuVaccine(){
        while (true) {
            Menu menu = new Menu("Choice the vaccine id:");
            for (int i = 0; i < list.size(); i++) {
                menu.addNewOption((i+1) +". " + list.get(i).toString2());
            }
            menu.printMenu();
            int choice = menu.getChoice();
            for (int i = 0; i < list.size(); i++) {
                if((choice-1)==i)
                    return list.get(i).getvID();
            }               
        }
    }
    
    public void displayAVaccine(String id){
        for (Vaccine vaccine : list) {
            if(vaccine.getvID().equalsIgnoreCase(id))
                vaccine.output();
        }
    }
    @Override
    public void display() {
        if(list.isEmpty()){
            System.out.println("The vaccine list is empty!");
            return;
        }
        for (int i = 0; i < 33; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("|%-10s|%-20s|\n", "VACCINE ID", "VACCINE NAME");
        for (Vaccine vaccine : list) {
            vaccine.output();
            System.out.println();
        }
        for (int i = 0; i < 33; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    @Override
    public void readFile() {
        list = FileDAO.readFileVaccine("vaccine.txt");
    }

    
}
