package TicketBooking.IRCTC.Services;

import TicketBooking.IRCTC.Entities.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
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
        Optional<User> foundUser = userList.stream().filter(user ->{
            return user.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),)
        })
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
}
