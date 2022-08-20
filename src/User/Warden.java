import java.io.*;
import java.util.*;

public class Warden{
    static Scanner s = new Scanner(System.in);

    public static void wardenLog() throws FileNotFoundException{
        boolean hasId = false;
        System.out.println("Enter the Username: ");
        String usId = s.nextLine();
        File wardenFile = new File("WardenPass.csv"); 
        Scanner rf = new Scanner(wardenFile);
        Scanner rf1 = new Scanner(wardenFile);
        String det[];
        while(rf.hasNextLine()){
            String line = rf.nextLine();
            if(line.contains(usId)){
                hasId = true;
                break;
            }
        }
        if(hasId == true){
        System.out.println("Enter the Password: ");
        String pass = s.nextLine();
        while(rf1.hasNextLine()){
            String line = rf1.nextLine();
            if(line.contains(usId)){
                det = line.split(",");
                if(det[1].equals(pass)){
                    System.out.println("Succesful Login");
                    DisplayMenu();
                }
                else{
                System.out.println("Wrong Password!");
                break;
                }
                
            }
        }
    }
    else{
        System.out.println("ID does not exist!");
    }

    }

    public static void DisplayMenu(){
        Scanner s = new Scanner(System.in);
        DoubleLinkedlist students = new DoubleLinkedlist("data.csv");
        int choice,locationStartingFromZero;
        String NAME,DEP;
        long ID;
        double Roomnumber;
        System.out.println("================================================================");
        System.out.println("                     Warden Page                ");
        System.out.println("================================================================");
//Manage student 
        System.out.println("1. Add a student: ");
        System.out.println("2. Search for a student:  ");
        System.out.println("3. Delete a student: ");
        System.out.println("4. Update a student info: ");
        System.out.println("5. Display the students info: ");
        System.out.println("6. Give token to the Student: ");
        System.out.println("7. Remove token ");   
        System.out.println("8. Attandence Portal");
        System.out.println("9. Manage room ");
        System.out.println("10. View Stock in the hostel ");
        System.out.println("11. View complaints raised by students :");
//System.out.println("7 Dequeue: ");
        System.out.println("-1 To return to menu: ");
        System.out.print("Enter your choice: ");
        choice = s.nextInt();
        switch(choice){
            case 1:
            System.out.print("Enter the student's name: ");
            NAME = s.next();
            System.out.print("Enter the student's Id: ");
            ID = s.nextLong();
            System.out.print("Enter the student's Room Number: ");
            Roomnumber = s.nextDouble();
            System.out.print("Enter the student's Department: ");
            DEP = s.next();
            System.out.print("Enter the location Starting from zero to insert the student at: ");
            locationStartingFromZero = s.nextInt();
            students.insertNode(NAME, ID, Roomnumber, DEP, locationStartingFromZero);
            System.out.println("================================================================");
            DisplayMenu();

                break;
            case 2:
                
            System.out.print("Enter the student's Id: ");
            ID = s.nextLong();
            students.searchnode(ID);
            System.out.println("================================================================");
            DisplayMenu();
                break;
            case 3:
                System.out.print("Delete by id or name[i/n]: ");
                char c = s.next().charAt(0);
                if(c == 'i' || c == 'I'){
                    System.out.print("Enter the student's Id: ");
                    ID = s.nextLong();
                    students.DeleteById(ID);
                    System.out.println("================================================================");
                    DisplayMenu();
                    break;
                }else if(c == 'n' || c =='N'){
                    System.out.print("Enter the student's name: ");
                    NAME = s.next();
                    System.out.println(NAME);
                    students.DeleteByName(NAME);
                    System.out.println("================================================================");
                    DisplayMenu();
                    break;
                }else{
                    System.out.println("Please Enter a Valid Symbol!!! ");

                    System.out.println("================================================================");
                    DisplayMenu();
                }
                break;
            case 4:
                System.out.print("Enter the student's Id: ");
                ID = s.nextLong();
                students.updateNode(ID);
                DisplayMenu();
                break;

            case 5:
                students.displayList();
                System.out.println("================================================================");
                DisplayMenu();
                break;
            
            case 6 :
                int id;
                System.out.print("Enter the student's Id: ");
                id = s.nextInt();
                System.out.print("Enter the student name: ");
                String name = s.next();
                Token_queue q = new Token_queue();
                q.enqueue(id, name);
            break;

            case 7 :
            Token_queue dq = new Token_queue();
            dq.dequeue();
            break;
             case 8:
             Attandence at = new Attandence();
             at.main_attandence();
            break;
            case 9 :
            Manage_room mg= new Manage_room();
            mg.main();
            break;

            case 10:
            Admin ad= new Admin();
            ad.disp_stock();
            break;

            case 11:
                ManageComplaints mc = new ManageComplaints();
                mc.viewComplaint();
                break;

            case -1:
                System.out.println("Have a Good Day <3");
                break;
            default:
                System.out.println("Please Enter a Valid Symbol!!! ");
                System.out.println("================================================================");

                DisplayMenu();
                break;
        }
    }
}