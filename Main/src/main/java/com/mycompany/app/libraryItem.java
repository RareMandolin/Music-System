package com.mycompany.app;

import java.time.LocalDate;

public class libraryItem {
    private String name;
    private User creator;
    private double length;
    private LocalDate creationDate;

    public libraryItem(String name, User creator, double length) {
        this.name = name;
        this.creator = creator;
        this.length = length;
        creationDate = LocalDate.now();
    }

    public libraryItem(String name, User creator, double length, LocalDate creationDate) {
        this.name = name;
        this.creator = creator;
        this.length = length;
        this.creationDate = creationDate;
    }

    public String toString() {
        return String.format("LibraryItem{%1s, %2s, %3d, %4s}", name, creator.toString(), length, creationDate);
    }

    public String getName() {
        return name;
    }

    public User getCreator() {
        return creator;
    }
    
    public double getLength() {
        return length;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User Creator) {
        this.Creator = Creator;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
