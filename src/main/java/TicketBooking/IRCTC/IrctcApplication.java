package TicketBooking.IRCTC;

import TicketBooking.IRCTC.Services.UserBookingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
            System.out.println("Choice Option :");

        }

	}

}
