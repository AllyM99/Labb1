package hig.se.spike;


import hig.se.service.ServiceRunner;
import hig.se.service.person.DeleteAllPersonService;

public class DeleteAllPerson {

    public static void main(String[] args){
        ServiceRunner<Void> serviceRunner = new ServiceRunner<>(new DeleteAllPersonService());
        serviceRunner.execute();
    }
}
