package TicketBooking.IRCTC.Entities;

import java.util.List;

public class User {

    private String name ;
    private String password ;
    private String hashedPassword ;
    private List<Ticket> ticketBooked;
    String userId;
}
