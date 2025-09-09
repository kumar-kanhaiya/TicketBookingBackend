package TicketBooking.IRCTC.Services;

import TicketBooking.IRCTC.Entities.Train;

import java.util.List;

public class TrainService {

    private List<Train> trainList;

    public searchTrains(String source , String dest){
        return trainList.stream()filter(train -> validTrain(train , source , dest)).coll;
    }
}
