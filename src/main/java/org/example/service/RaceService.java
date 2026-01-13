package org.example.service;

import org.example.model.EreignisTyp;
import org.example.model.Fahrer;
import org.example.model.FahrerStatus;
import org.example.model.RennenEreignis;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RaceService {

    public List<Fahrer> filterActiveByTeam(List<Fahrer> drivers, String team) {
        return drivers.stream()
                .filter(d -> d.getTeam() != null && d.getTeam().equals(team))
                .filter(d -> d.getStatus() == FahrerStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    public List<Fahrer> sortDrivers(List<Fahrer> drivers) {
        return drivers.stream()
                .sorted(Comparator.comparingInt(Fahrer::getSkillLevel).reversed()
                        .thenComparing(Fahrer::getName))
                .collect(Collectors.toList());
    }

    public int computedPoints(RennenEreignis e) {
        EreignisTyp t = e.getTyp();
        int base = e.getBasePoints();
        int lap = e.getLap();

        if (t == EreignisTyp.OVERTAKE) return base + 1;
        if (t == EreignisTyp.FASTEST_LAP) return base + 2 * (lap % 3);
        if (t == EreignisTyp.TRACK_LIMITS) return base - 5;
        if (t == EreignisTyp.COLLISION) return base - 10 - lap;
        if (t == EreignisTyp.PIT_STOP) return (lap <= 10) ? (base + 2) : base;

        return base;
    }
}

