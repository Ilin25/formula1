package ru.ilin.formula1.domain;

import java.time.LocalDateTime;

public class Racer {

    private final String abbreviation;
    private final String name;
    private final String team;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private Racer(Builder builder) {
        this.abbreviation = builder.abbreviation;
        this.name = builder.name;
        this.team = builder.team;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getname() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public static class Builder {
        private  String abbreviation;
        private  String name;
        private  String team;
        private  LocalDateTime startTime;
        private  LocalDateTime endTime;

        private Builder() {
        }

        public Builder withAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
            return this;
        }

        public Builder withname(String name) {
            this.name = name;
            return this;
        }

        public Builder withTeam(String team) {
            this.team = team;
            return this;
        }

        public Builder withStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Racer build(){
            return new Racer(this);
        }
    }
}
