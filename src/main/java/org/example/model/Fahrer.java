package org.example.model;

public class Fahrer {
    private int id;
    private String name;
    private String team;
    private FahrerStatus status;
    private int skillLevel;

    public Fahrer() {}

    public Fahrer(int id, String name, String team, FahrerStatus status, int skillLevel) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.status = status;
        this.skillLevel = skillLevel;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public FahrerStatus getStatus() { return status; }
    public void setStatus(FahrerStatus status) { this.status = status; }

    public int getSkillLevel() { return skillLevel; }
    public void setSkillLevel(int skillLevel) { this.skillLevel = skillLevel; }

    @Override
    public String toString() {
        return "[#" + id + "] " + name + " (" + team + ") - " + status + ", skill=" + skillLevel;
    }
}

