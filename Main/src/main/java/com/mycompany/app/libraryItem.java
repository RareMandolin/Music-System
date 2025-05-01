package com.mycompany.app;

import java.time.LocalDate;

public class LibraryItem {
    private int id;
    private String name;
    private User creator;
    private double length;
    private LocalDate creationDate;

    private static int nextId = 1;

    public LibraryItem(String name, User creator) {
        id = nextId++;
        this.name = name;
        this.creator = creator;
        length = 0;
        creationDate = LocalDate.now();
    }

    public LibraryItem(String name, User creator, LocalDate creationDate) {
        id = nextId++;
        this.name = name;
        this.creator = creator;
        length = 0;
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        
        LibraryItem that = (LibraryItem) obj;

        return name.equals(that.getName())
            && creator.equals(that.getCreator())
            && length == that.getLength()
            && creationDate.equals(that.getCreationDate());
    }

    public String toString() {
        return String.format("LibraryItem{%1s, %2s, %3f, %4s}", name, creator.toString(), length, creationDate);
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

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public enum Genre {
        RAP, POP, CLASSICAL, JAZZ, ROCK
    }
}
