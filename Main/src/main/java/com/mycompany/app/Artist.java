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

    /**
     * Creates a song for the artist
     * @param name the name of the song
     * @param length the length of the song
     * @param genre the genre of the song
     * @return whether the song was successfully created
     */
    public boolean createSong(String name, double length, Genre genre) {
        if (name == null || genre == null) return false;

        for (Song song : songs) {
            if (song.getName().equals(name)
            && song.getLength() == length
            && song.getGenre().equals(genre)) return false;
        }

        songs.add(new Song(name, this, length, genre));

        return true;
    }

    /**
     * Creates an album for the artist
     * @param name the name of the album
     * @return whether the album was succcessfully created
     */
    public boolean createAlbum(String name) {
        if (name == null) return false;

        for (Album album : albums) {
            if (album.name.equals(name)) return false;
        }

        albums.add(new Album(name, this));

        return true;
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
