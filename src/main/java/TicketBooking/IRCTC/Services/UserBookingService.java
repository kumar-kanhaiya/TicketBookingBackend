package TicketBooking.IRCTC.Services;

import TicketBooking.IRCTC.Entities.Ticket;
import TicketBooking.IRCTC.Entities.Train;
import TicketBooking.IRCTC.Entities.User;
import TicketBooking.IRCTC.util.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private static final String USERS_PATH= "app/src/main/java/TicketBooking/IRCTC/LocalDB/user.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUser();

    }
    // default constructor
    public UserBookingService() throws IOException{
        loadUser();
    }

    public List<User> loadUser() throws IOException{
        File users = new File(USERS_PATH);

        return userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
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

    public void fetchBookings(){
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        if(userFetched.isPresent()){
            userFetched.get().printTicket();
        }
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

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }


}
