import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Admin {
public  void read(){
    try {

      File file = new File("instruction_table.txt");
      Scanner sc = new Scanner(file);

      System.out.println("INSTRUCTIONS :");
     int i=1;
      while(sc.hasNextLine()) {
        System.out.println("\t"+"\t"+ i+ ") "+sc.nextLine());
        i++;
      }
    
      sc.close();
    } catch (Exception e) {
      e.getStackTrace();
    }
  }

  public static void write_instruction(){
    Scanner input = new Scanner(System.in);
    	
         
    System.out.println("Enter number  of instruction to be added");
      Scanner in = new Scanner(System.in);  
      
      int size=in.nextInt();
      int[] myList=new int[size];
      
     
     int i;

     for (i = 0; i < myList.length; i++) {
    System.out.println("Enter the new instruction : ");
    String inp= input.next();
         try{
             File file =new File("instruction_table.txt");
           if(!file.exists()){
            file.createNewFile();
           }
           FileWriter fw = new FileWriter(file,true);
           BufferedWriter bw = new BufferedWriter(fw);
           PrintWriter pn = new PrintWriter(bw);
     
           pn.println( inp);
           pn.close();
       }
       catch(IOException ia){
            System.out.println("Exception occurred:");
            ia.printStackTrace();
         }

     }   
     
     

  }
  static void add_stock(){
    Scanner sc = new Scanner(System.in);
    int flag = 1;

    try{
        File f = new File("requirements.csv");

        if(!f.exists()){
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        while (flag == 1){
            System.out.print("Enter item: ");
            String stname = sc.next();
            System.out.print("Enter item quanity: ");
            int stquan = sc.nextInt();
            System.out.print("Enter item price: ");
            int st = sc.nextInt();
            pw.println(stname + "," + stquan+","+st);
            System.out.print("Do you want to continue (y/n): ");
            String con = sc.next();

            if (con.equals("n")){
                flag = 0;
            }
        }

        pw.close();
    }

    catch (IOException e){
        System.out.println("Something went wrong...");
    }
}

static void disp_stock(){
    
  try{
      File f = new File("requirements.csv");
      Scanner r = new Scanner(f);
      System.out.println("----------------------------------------");
      System.out.println("                 Stock");
      System.out.println("----------------------------------------");
      int c = 1;

      while (r.hasNextLine()){
          String data = r.nextLine();
          String[] line = data.split(",");
          System.out.println(c + ". " + "item name : "+line[0] + "\n\tQuantity:- " + line[1] +"\n\tPrice:- "+line[2]+ "\n");
          c++;
      }

      r.close();
  }

  catch (FileNotFoundException e){
      System.out.println("Something went wrong...");
  }
}

static Scanner s = new Scanner(System.in);

public static void AdminLog() throws FileNotFoundException{
    boolean hasId = false;
    System.out.println("Enter the Username: ");
    String usId = s.nextLine();
    File wardenFile = new File("AdminPass.csv"); 
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
                main();
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
static void enter_st_user(){

    try{
        Scanner s= new Scanner(System.in);
        System.out.println("Enter the UsertID: ");
        String usId = s.nextLine();
        ArrayList<String[]> f = utils.getData("StudentPass.csv");

        System.out.println("Enter the Password: ");
        String pass = s.nextLine();

        FileWriter fw = new FileWriter("StudentPass.csv",true);
        fw.write(usId+","+pass+"\n");
        fw.close();
        
    }
    catch(IOException ioe){
        System.err.println("IOException: " + ioe.getMessage());
    }

}
static void manage_student(){
    
        Scanner s = new Scanner(System.in);
        DoubleLinkedlist students = new DoubleLinkedlist("data.csv");
        int choice,locationStartingFromZero;
        String NAME,DEP;
        long ID;
        double Roomnumber;
        System.out.println("================================================================");
        System.out.println("                     Manage Student page for Admin                 ");
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
            System.out.print("Enter the location to insert the student at: ");
            locationStartingFromZero = s.nextInt();
            students.insertNode(NAME, ID, Roomnumber, DEP, locationStartingFromZero);
            System.out.println("================================================================");
            main();

                break;
            case 2:
                
            System.out.print("Enter the student's Id: ");
            ID = s.nextLong();
            students.searchnode(ID);
            System.out.println("================================================================");
            main();
                break;
            case 3:
                System.out.print("Delete by id or name[i/n]: ");
                char c = s.next().charAt(0);
                if(c == 'i' || c == 'I'){
                    System.out.print("Enter the student's Id: ");
                    ID = s.nextLong();
                    students.DeleteById(ID);
                    System.out.println("================================================================");
                    main();
                    break;
                }else if(c == 'n' || c =='N'){
                    System.out.print("Enter the student's name: ");
                    NAME = s.next();
                    System.out.println(NAME);
                    students.DeleteByName(NAME);
                    System.out.println("================================================================");
                    main();
                    break;
                }else{
                    System.out.println("Please Enter a Valid Symbol!!! ");

                    System.out.println("================================================================");
                    main();
                }
                break;
            case 4:
                System.out.print("Enter the student's Id: ");
                ID = s.nextLong();
                students.updateNode(ID);
                main();
                break;

            case 5:
                students.displayList();
                System.out.println("================================================================");
                main();
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


            case -1:
                System.out.println("Have a Good Day <3");
                break;
            default:
                System.out.println("Please Enter a Valid Symbol!!! ");
                System.out.println("================================================================");

                break;
        }
    }

static void main(){
  
    Scanner s = new Scanner(System.in);

    System.out.println("\nEnter your choice :\n1.Manage students \n2.Manage Room \n3.Add new instruction in the notice board \n4.Display the stock \n5.Add Stock\n6. Add new student user  \n-1.Exit\n");
        int choice;
        choice = s.nextInt();
        switch(choice){

            case 1:
          manage_student();
                break;
                
            case 2:
            Manage_room w = new Manage_room();
            w.main();
                break;
            
            case 3:
            write_instruction();
               break;

            case 4 :
            disp_stock();
            break;

            case 5:
            add_stock();
            break;

            case 6:
            enter_st_user();
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



