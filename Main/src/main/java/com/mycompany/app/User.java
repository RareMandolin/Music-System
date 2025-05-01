package com.mycompany.app;

import java.time.LocalDate;

public abstract class User {
    protected String name;
    protected Origin origin;
    protected LocalDate creationDate;

    public User(String name, Origin origin) {
        this.name = name;
        this.origin = origin;
        creationDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
    
        User that = (User) obj;
        return name.equals(that.getName())
            && origin.equals(that.getOrigin())
            && creationDate.equals(that.getCreationDate());
    }

    @Override
    public String toString() {
        return String.format("User{%1s, %2s, %3s}", name, origin, creationDate);
    }

    public String getName() {
        return name;
    }

    public Origin getOrigin() {
        return origin;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public enum Origin {
        NA, SA, EU, AS, OC, AF, AN
    }
}
