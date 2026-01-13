package org.example.model;

public class RennenEreignis {
    private int id;
    private int fahrerId;
    private EreignisTyp typ;
    private int basePoints;
    private int lap;

    public RennenEreignis() {}

    public RennenEreignis(int id, int fahrerId, EreignisTyp typ, int basePoints, int lap) {
        this.id = id;
        this.fahrerId = fahrerId;
        this.typ = typ;
        this.basePoints = basePoints;
        this.lap = lap;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFahrerId() { return fahrerId; }
    public void setFahrerId(int fahrerId) { this.fahrerId = fahrerId; }

    public EreignisTyp getTyp() { return typ; }
    public void setTyp(EreignisTyp typ) { this.typ = typ; }

    public int getBasePoints() { return basePoints; }
    public void setBasePoints(int basePoints) { this.basePoints = basePoints; }

    public int getLap() { return lap; }
    public void setLap(int lap) { this.lap = lap; }
}

