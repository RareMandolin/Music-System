package com.mycompany.app;

public class Song extends LibraryItem {
    private Genre genre;

    public Song(String name, User creator, double length, Genre genre) {
        super(name, creator, length);
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public enum Genre {
        RAP, POP, CLASSICAL, JAZZ, ROCK
    }
}
