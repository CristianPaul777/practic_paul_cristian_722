package org.example.controller;

import org.example.model.Fahrer;
import org.example.model.RennenEreignis;
import org.example.model.Strafe;
import org.example.repo.EventRepository;
import org.example.repo.FahrerRepository;
import org.example.repo.PenaltyRepository;
import org.example.service.RaceService;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {

    private final FahrerRepository fahrerRepo;
    private final EventRepository eventRepo;
    private final PenaltyRepository penaltyRepo;
    private final RaceService service;

    public ConsoleController(FahrerRepository fahrerRepo, EventRepository eventRepo, PenaltyRepository penaltyRepo, RaceService service) {
        this.fahrerRepo = fahrerRepo;
        this.eventRepo = eventRepo;
        this.penaltyRepo = penaltyRepo;
        this.service = service;
    }

    public void run() throws Exception {
        Path driversPath = Paths.get("drivers.json");
        Path driversMixedPath = Paths.get("drivers_v2_mixed.json");
        Path eventsPath = Paths.get("events.json");
        Path penaltiesPath = Paths.get("penalties.json");

        List<Fahrer> drivers = fahrerRepo.readAll(driversPath);
        List<RennenEreignis> events = eventRepo.readAll(eventsPath);
        List<Strafe> penalties = penaltyRepo.readAll(penaltiesPath);

        System.out.println("Drivers loaded: " + drivers.size());
        System.out.println("Events loaded: " + events.size());
        System.out.println("Penalties loaded: " + penalties.size());

        List<Fahrer> mixedDrivers = fahrerRepo.readAll(driversMixedPath);
        for (Fahrer d : mixedDrivers) System.out.println(d);

        Scanner sc = new Scanner(System.in);
        System.out.print("Input team: ");
        String team = sc.nextLine().trim();

        List<Fahrer> filtered = service.filterActiveByTeam(drivers, team);
        for (Fahrer d : filtered) System.out.println(d);

        List<Fahrer> sorted = service.sortDrivers(drivers);
        for (Fahrer d : sorted) System.out.println(d);

        Files.write(Paths.get("drivers_sorted.txt"), toLines(sorted), StandardCharsets.UTF_8);

        int limit = Math.min(5, events.size());
        for (int i = 0; i < limit; i++) {
            RennenEreignis e = events.get(i);
            int cp = service.computedPoints(e);
            System.out.println("Event " + e.getId() + " -> raw=" + e.getBasePoints() + " -> computed=" + cp);
        }
    }

    private List<String> toLines(List<Fahrer> drivers) {
        List<String> lines = new ArrayList<String>();
        for (Fahrer d : drivers) lines.add(d.toString());
        return lines;
    }
}

