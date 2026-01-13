package org.example;

import org.example.controller.ConsoleController;
import org.example.repo.EventRepository;
import org.example.repo.FahrerRepository;
import org.example.repo.PenaltyRepository;
import org.example.service.RaceService;

public class App {
    public static void main(String[] args) throws Exception {
        FahrerRepository fr = new FahrerRepository();
        EventRepository er = new EventRepository();
        PenaltyRepository pr = new PenaltyRepository();
        RaceService service = new RaceService();

        ConsoleController controller = new ConsoleController(fr, er, pr, service);
        controller.run();
    }
}
