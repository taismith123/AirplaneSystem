/*
 * Tai Smith
 * 
 * November 28, 2020
 *
 * Java Program: An airplane ticket reservation system for Test Company Airline.
 *
 *
*/
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TestCompany TC1 = new TestCompany("TC 200", 15);  //number of seats =15
        TestCompany TC2 = new TestCompany("TC 400", 15);

        System.out.println("---------TC Airplane Ticket Purchase System -------");
        while (true) { //red because of infinite loop
            //System.out.println("---------TC System Homepage -------");
            System.out.println("Press 1 to purchase a ticket");
            System.out.println("Press 2 to return a ticket");
            System.out.println("Press 3 to exit the system");
            int scan = scanner.nextInt();
            scanner.nextLine();
            switch (scan) {
                case 1:
                    System.out.println("-------------------------------------------------------"); 
                    System.out.println("Press 1 to book a ticket for airplane TC 200");
                    System.out.println("Press 2 to book a ticket for airplane TC 400");
                    int input = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Please Enter Your First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Please Enter Your Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Please Enter Travel Date (DD/MM/YYYY): ");
                    String date = scanner.nextLine();
 
                    switch (input) {
                        case 1:
                            try {
                                String seatNum = TC1.PurchaseTicket();
                                if (seatNum != null) {
                                    // Display bought ticket information
                                     //TC1.TC1Display();
                                    System.out.println("------------------Ticket Information-------------------");
                                    System.out.println("Name: " + firstName + " " + lastName);
                                    System.out.println("Flight Number: " + "TC 200");
                                    System.out.println("Flight Date: " + date);
                                    System.out.println("Flight Time: 10:00AM - 11:30AM (1h 30m)");
                                    System.out.println("Seat Number: " + seatNum);
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println("-------------------------------------------------------"); 
                                }
                            } catch (TCAirplaneFull ex) {
                                System.out.println("Could not book ticket");
                                break;
                            }
                            break;
                        case 2:
                            try {
                                String seatNum = TC2.PurchaseTicket();

                                if (seatNum != null) {
                                    // Display bought ticket information
                                    // TC2.TC2Display();
                                    System.out.println("------------------Ticket Information-------------------");
                                    System.out.println("Name: " + firstName + " " + lastName);
                                    System.out.println("Flight Number: " + "TC 400");
                                    System.out.println("Flight Date: " + date);
                                    System.out.println("Flight Time: 4:00PM - 5:30PM (1h 30m)");
                                    System.out.println("Seat Number: " + seatNum);
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println("-------------------------------------------------------");
                                }

                            } catch (TCAirplaneFull ex) {
                                System.out.println("Could not book ticket");
                                break;
                            }
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 2:
                    System.out.println("-------------------------------------------------------"); 
                    System.out.println("Press 1 to return a ticket from airplane TC 200");
                    System.out.println("Press 2 to return a ticket from airplane TC 400");
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Plese Enter Seat Number: ");
                    String seatNumber2 = scanner.nextLine();
                    switch (option) {
                        // Display returned ticket information
                        case 1:
                            if (TC1.returnTicket(seatNumber2))
                                System.out.println( "Ticket assigned to seat " + seatNumber2 + " has been successfully returned");
                            else
                                System.out.println("Sorry, the ticket assigned to seat " + seatNumber2 + " is not valid");
                            break;
                        case 2:
                            if (TC2.returnTicket(seatNumber2))
                                System.out.println( "Ticket assigned to seat " + seatNumber2 + " has been successfully returned");
                            else
                                System.out.println("Sorry, the ticket assigned to seat " + seatNumber2 + " is not valid ");
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}

final class TestCompany {
    List<String> seats;
    int numSeats;
    List<String> takenSeats;
    char[] seatsInRow = { 'A', 'B', 'C' };
    String flightName;
    
    public TestCompany(String flightName, int numSeats) { // A constructor to initialize variables
        
        seats = new ArrayList<String>();
        this.flightName = flightName;
        takenSeats = new ArrayList<String>();
        this.numSeats = 15; // assigned num of seats to 3, for testing purposes
        
        // add all seats possible and make them all available
        for (int seat = 0; seat < numSeats; seat++) {
            int row = seat / 3 + 1;
            char col = seatsInRow[seat % 3];
            String seatNum = row + "" + col;
            seats.add(seatNum);
        }
    }
                    
    public String PurchaseTicket() throws TCAirplaneFull {
        // This method returns an integer indicating the row number and character
        try {
            // If all seats are taken, plane is full
            if (seats.isEmpty()) {
                throw new TCAirplaneFull(); // If a customer tries to buy a ticket but all seats are taken,
                                            // exception will be thrown
            }
            // If space is available, give customer the first available seat
            // Remove this seat from available seats and add it to taken seats
            String seat = seats.remove(0);
            takenSeats.add(seat);
            return seat;

        } catch (TCAirplaneFull ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

   /*    public void TC1Display ()  {     //attempt at display functions 
        TestCompany TC1 = new TestCompany("TC 200", 2);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.print("Please Enter Your First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Please Enter Your Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Please Enter Date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        String seatNum = TC1.PurchaseTicket();

        System.out.println("------------------Ticket Information-------------------");
                                    System.out.println("Name: " + firstName + " " + lastName);
                                    System.out.println("Flight Date: " + date);
                                    System.out.println("Flight Time: 10:00AM - 11:30AM (1h 30m)");
                                    System.out.println("Seat Number: " + seatNum);
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println("-------------------------------------------------------");
      }

      public void TC2Display ()  {
        TestCompany TC2 = new TestCompany("TC 400", 2);
        Scanner scanner = new Scanner(System.in);
        String seatNum = TC2.PurchaseTicket();
        scanner.nextLine();
        System.out.print("Please Enter Your First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Please Enter Your Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Please Enter Date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        System.out.println("------------------Ticket Information-------------------");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Flight Date: " + date);
        System.out.println("Flight Time: 4:00PM - 5:30PM (1h 30m)");
        System.out.println("Seat Number: " + seatNum);
        System.out.println("-------------------------------------------------------");
        System.out.println("-------------------------------------------------------");
      } */
     
    public boolean returnTicket(String seatNum) { // If a customer returned a ticket the seat will be available

        if (takenSeats.remove(seatNum)) // when seatNum is successfully removed, it will be
        // returned and added back to available seats
        //*the returned seat will be avaliable only AFTER the remaining 1-15 a, b, or c have been traversed through* 
        {
            seats.add(seatNum);            
            return true;   
        } else
            return false;  
    }
}

final class TCAirplaneFull extends Exception {    // user defined exception class TCAirplanefull 
     //String errorMessage = "The airplane is full!";

    public TCAirplaneFull() {

        System.out.println("The airplane is full!"); 
    }
}
