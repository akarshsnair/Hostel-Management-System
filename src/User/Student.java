import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    public static void login() throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("1. Register ");
        System.out.println("2. login ");
        choice = sc.nextInt();
        switch(choice){
            case 1:
            register();
                break;
            case 2:
            StudentLog();
            break;
              
        }
    }
    public static void register(){
        try{
            Scanner s= new Scanner(System.in);
            System.out.println("Enter the UsertID: ");
            String usId = s.nextLine();
            ArrayList<String[]> f = utils.getData("StudentPass.csv");
            for (String[] data : f) {
                if(data[0].equals(usId)){
                    register();
                }
                
            }
            System.out.println("Enter the Password: ");
            String pass = s.nextLine();

            FileWriter fw = new FileWriter("StudentPass.csv",true);
            fw.write(usId+","+pass+"\n");
            fw.close();
            StudentLog();
        }
        catch(IOException ioe){
            System.err.println("IOException: " + ioe.getMessage());
        }

    }
    public static void StudentLog() throws FileNotFoundException{
        Scanner s= new Scanner(System.in);
        boolean hasId = false;
        System.out.println("Enter the UsertID: ");
        String usId = s.nextLine();
        File wardenFile = new File("StudentPass.csv"); 
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
        DoubleLinkedlist d = new DoubleLinkedlist("data.csv");
        Manage_room mg = new Manage_room();
        System.out.println("================================================================");
        System.out.println("                            Student Page ");
        System.out.println("================================================================");
        //Manage student 
        System.out.println("1 Add details: ");
        System.out.println("2 Update a student info: ");
        System.out.println("3 Take token for food : ");
        System.out.println("4 See the features of the room : ");
        System.out.println("5 See room availability :");
        System.out.println("6 Add the complaint  :");

        System.out.println("-1 To return to menu: ");
        System.out.print("Enter your choice: ");

            int choice;
            String name,depart;
            long id;
            double Roomnumber;

            choice = s.nextInt();
            int locationStartingFromZero;
            Object i;
            switch(choice){

                case 1:
                    System.out.print("Enter the student's name: ");
                    name = s.next();
                    System.out.print("Enter the student's id: ");
                    id = s.nextLong();
                    System.out.print("Enter the student's Room Number: ");
                    Roomnumber = s.nextDouble();
                    System.out.print("Enter the student's departartment: ");
                    depart = s.next();
                    System.out.print("Enter the location Starting from zero to insert the student at: ");
                    locationStartingFromZero = s.nextInt();
                    d.insertNode(name, id, Roomnumber, depart, locationStartingFromZero);
                    DisplayMenu();
                    break;
                
                case 2:
                    System.out.print("Enter the student's id: ");
                    id = s.nextLong();
                    d.updateNode(id);
                    DisplayMenu();
                    break;

                
                case 3 :
                System.out.print("Enter the student's name: ");
                    String n = s.next();
                    System.out.print("Enter the student's id: ");
                    int id_ = s.nextInt();
                    Token_queue q= new Token_queue();
                    q.enqueue(id_, n);

                    DisplayMenu();
                    break;
                case 4:
                System.out.println("\nChoose room type :\n1.Double Room_1 \n2.Double Room_2 \n3.Single Room_1 \n4.Single Room_2\n");
                    Hotel.features();
                    break;
                case 5:
                System.out.println("\nChoose room type :\n1.Double Room_1 \n2.Double Room_2 \n3.Single Room_1\n4.Single Room_2\n");
                     Hotel.availability();
                break;
                case 6:
                 ManageComplaints m = new ManageComplaints();
                    Scanner in = new Scanner(System.in);
                    System.out.println("Enter the priority : ");
                    int priority = in.nextInt();
                    System.out.println("Enter the complaint :");
                    String complaint = in.next();
                    m.addComplaint(complaint,priority);
                break;  
                case -1:
                    System.out.println("Have a Good Day <3");
                    break;
                default:
                    System.out.println("Please Enter a Valid Symbol!!! ");
                    System.out.println("================================================================");

                    break;
            }
        }
    }

    

    
