package com.mycompany.app;

import com.mycompany.app.LibraryItem.Genre;

import java.util.HashSet;
import java.util.Set;

public class Artist extends User {
    private LibraryItem.Genre genre;
    private Set<Song> songs;
    private Set<Album> albums;

    public Artist(String name, Origin origin, Genre genre) {
        super(name, origin);
        this.genre = genre;
        songs = new HashSet<>();
        albums = new HashSet<>();
    }

    public boolean createSong() {
        //TODO
        return false;
    }

    public boolean createAlbum() {
        //TODO
        return false;
    }

    @Override
    public String toString() {
        return String.format("User{%1s, %2s, %3s, %4s}", super.toString(), genre, songs.toString(), albums.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Artist that = (Artist) obj;
        
        return genre == that.getGenre()
            && songs.equals(that.getSongs())
            && albums.equals(that.getAlbums());
    }

    public Genre getGenre() {
        return genre;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
