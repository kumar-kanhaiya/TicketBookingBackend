package TicketBooking.IRCTC;

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
                    userBookingService.signUp(userToSignUp.getName() , passwordToSignUp);
                    break;

                case 2:
                    System.out.println("Enter the username to signIn");
                    String nameToSignIn = scanner.next();

            }


        }

	}

}
