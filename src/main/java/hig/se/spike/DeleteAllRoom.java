package hig.se.spike;

import hig.se.service.ServiceRunner;
import hig.se.service.room.DeleteAllRoomService;

public class DeleteAllRoom {
    public static void main(String[] args){
        ServiceRunner<Void> serviceRunner = new ServiceRunner<>(new DeleteAllRoomService());
        serviceRunner.execute();
    }
}
