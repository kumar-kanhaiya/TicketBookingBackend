package TicketBooking.IRCTC.util;

import TicketBooking.IRCTC.Entities.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil extends User {

    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword , BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword , String hashedPassword){
        return BCrypt.checkpw(plainPassword , hashedPassword);
    }

}
