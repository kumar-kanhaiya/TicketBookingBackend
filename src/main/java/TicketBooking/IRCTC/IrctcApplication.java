package TicketBooking.IRCTC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class IrctcApplication {

	public static void main(String[] args) {
//		SpringApplication.run(IrctcApplication.class, args);
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> l1 = list.stream().filter(i -> i%2==0).collect(Collectors.toList());
        System.out.println(l1);

	}

}
