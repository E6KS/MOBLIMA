import controller.DBController;
import controller.SerializedDB;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import static view.LandingPageView.*;
import static view.TicketPriceView.ticketPriceView;

public class MoblimaApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        DBController dbController = DBController.getInstance();
        try {
            dbController.load();
        }catch(Exception e) {
            runApp();
            return;
        }
        runApp();
    }

    public static void runApp(){
        int choice = 0;
        boolean stillRunning = true;
        DBController dbController = DBController.getInstance();
        while (stillRunning) {
            System.out.println("Welcome to MOvie Booking and LIsting Management Application (MOBLIMA)\n");
            System.out.println("Enter your options: ");
            System.out.println("1. Login as admin");
            System.out.println("2. Login as customer");
            System.out.println("3. Create admin account");
            System.out.println("4. Create customer account");
            System.out.println("5. Quit");
            System.out.print("Option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    adminLogin();
                    try {
                        ticketPriceView();
                    }catch(ParseException e){
                        System.out.println("[System: Error in loading Ticket Price Menu]");
                    }
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    createAdminAccount();
                    break;
                case 4:
                    createCustomerAccount();
                    break;
                case 5:
                    stillRunning = false;
                    System.out.println("bye");
                    try {
                        dbController.save();
                    }catch(IOException e){
                        try {
                            dbController.createDB("SerializedDB");
                            dbController.save();
                        }catch (IOException i){

                        }
                }
                    break;

            }
        }
    }
}