package TicketBooking.IRCTC;

import TicketBooking.IRCTC.Entities.Train;
import TicketBooking.IRCTC.Entities.User;
import TicketBooking.IRCTC.Services.UserBookingService;
import TicketBooking.IRCTC.util.UserServiceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class IrctcApplication {

	public static void main(String[] args) {
//		SpringApplication.run(IrctcApplication.class, args);
//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
//        List<Integer> l1 = list.stream().filter(i -> i%2==0).collect(Collectors.toList());
//        System.out.println(l1);

        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        Train trainSelectedForBooking = null;
        UserBookingService userBookingService ;
        try{
            userBookingService = new UserBookingService();
        }
        catch (IOException exception){
            System.out.println("There is something Wrong !!");
            return;
        }

        while(option != 7){
            System.out.println("Choice Option: ");
            System.out.println("1, Sign Up");
            System.out.println("2, Login");
            System.out.println("3, Fetch Booking");
            System.out.println("4, Search Train");
            System.out.println("5, Book a Seat");
            System.out.println("6, Cancel my Booking");
            System.out.println("7, Exit the App");
            option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter the username to signup");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = scanner.next();
                    User userToSignUp = new User(nameToSignUp , passwordToSignUp ,
                            UserServiceUtil.hashPassword(passwordToSignUp) , new ArrayList<>() ,
                            UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignUp);
                    break;

                case 2:
                    System.out.println("Enter the username to signIn");
                    String nameToSignIn = scanner.next();
                    System.out.println("Enter your signIn password");
                    String passwordToSignIn = scanner.next();
                    User userToLogin = new User(nameToSignIn , passwordToSignIn ,
                            UserServiceUtil.hashPassword(passwordToSignIn), new ArrayList<>(),
                            UUID.randomUUID().toString());

                    try{
                        userBookingService = new UserBookingService(userToLogin);
                    }
                    catch(IOException exception){
                        return;
                    }
                    break;

                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;

                case 4:
                    System.out.println("Type your source station");
                    String source = scanner.next();
                    System.out.println("Type your destination station");
                    String dest = scanner.next();
                    List<Train> trains = userBookingService.getTrains(source, dest);
                    int index = 1;
                    for (Train t: trains){
                        System.out.println(index+" Train id : "+t.getTrainId());
                        for (Map.Entry<String, String> entry: t.getStationTime().entrySet()){
                            System.out.println("station "+entry.getKey()+" time: "+entry.getValue());
                        }
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    trainSelectedForBooking = trains.get(scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                    for (List<Integer> row: seats){
                        for (Integer val: row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = scanner.nextInt();
                    System.out.println("Enter the column");
                    int col = scanner.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked! Enjoy your journey");
                    }else{
                        System.out.println("Can't book this seat");
                    }
                    break;
                default:
                    break;

            }


        }

	}

}
