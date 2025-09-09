package TicketBooking.IRCTC.Services;

import TicketBooking.IRCTC.Entities.Ticket;
import TicketBooking.IRCTC.Entities.User;
import TicketBooking.IRCTC.util.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private static final String USERS_PATH= "app/src/main/java/TicketBooking/IRCTC/LocalDB/user.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>(){});

    }

    public boolean loginUser(){
        Optional<User> foundUser = userList.stream()
                .filter(user1 -> user1.getName().equals(user.getName()) &&
                        UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword()))
                .findFirst();

        return foundUser.isPresent();

    }

    public boolean signUp(User user){
        try{
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        }
        catch (IOException exception){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile , userList);
    }

    // Json -> Object (user) --> DeSerialize
    // Object (user) -> Json --> Serialize

    public void fetchBooking(){
        user.printTicket();
    }

    public boolean cancelBooking(String ticketId) {
        List<Ticket> tickets = user.getTicketBooked();

        Optional<Ticket> ticketToCancel = tickets.stream()
                .filter(ticket -> ticket.getTicketId().equals(ticketId))
                .findFirst();

        if (ticketToCancel.isPresent()) {
            tickets.remove(ticketToCancel.get());

            try {
                saveUserListToFile();
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        return false;
    }


}
