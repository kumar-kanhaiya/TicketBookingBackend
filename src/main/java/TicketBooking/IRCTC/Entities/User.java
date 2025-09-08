package TicketBooking.IRCTC.Entities;

import java.util.List;

public class User {

    private String name ;
    private String password ;
    private String hashedPassword ;
    private List<Ticket> ticketBooked;
    String userId;

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public User(String name , String password , String hashedPassword , List<Ticket> ticketBooked , String userId){
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketBooked = ticketBooked;
        this.userId = userId;
    }

    // default Constructor
    public User(){

    }

    public String getHashedPassword(){
        return hashedPassword;
    }

    public List<Ticket> getTicketBooked(){
        return ticketBooked;
    }

    public void printTicket(){
        for (int i = 0; i < ticketBooked.size() ; i++) {
            System.out.println(ticketBooked.get(i).getTicketInfo());
        }
    }

    public String getUserId(){
        return userId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setHashedPassword(String hashedPassword){
        this.hashedPassword = hashedPassword;
    }

    public void setTicketBooked(List<Ticket> ticketBooked){
        this.ticketBooked = ticketBooked;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
}
