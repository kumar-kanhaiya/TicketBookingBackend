package TicketBooking.IRCTC.Services;

import TicketBooking.IRCTC.Entities.Train;

import java.util.List;
import java.util.stream.Collectors;

public class TrainService {

    private List<Train> trainList;

    public searchTrains(String source , String dest){
        return trainList.stream()filter(train -> validTrain(train , source , dest)).collect(Collectors.toList());
    }

    private boolean validTrain(Train train , String source , String destination){
        List<String> stationOrder = train.getStations();

        int sourceIndex = stationOrder.indexOf(destination.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }
}
