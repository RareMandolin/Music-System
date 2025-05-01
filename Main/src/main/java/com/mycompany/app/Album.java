package com.mycompany.app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Album extends LibraryItem {
    private Set<Song> tracks;
    private Set<Artist> artists;
    private int tracksQuantity;
    private boolean isReleased;
    private Genre genre;

    public Album(String name, Artist creator) {
        super(name, creator);
        tracks = new HashSet<>();
        artists = new HashSet<>();
        tracksQuantity = 0;
        isReleased = false;
    }

    public boolean release() {
        if (isReleased) return false;

        isReleased = true;
        return true;
    }

    public boolean add(Song song) {
        if (song == null
        || tracks.contains(song)
        || !song.getCreator().equals(getCreator())) return false;

        tracks.add(song);
        tracksQuantity++;
        setLength(getLength() + song.getLength());

        if (!artists.contains(song.getCreator())) artists.add((Artist) song.getCreator());
        return true;
    }

    public void add(List<Song> songs) {
        if (songs == null) return;

        for (Song song : songs) add(song);
    }

    public String toString() {
        return String.format("Album{%1s, %2s, %3s, %4d, %5b, %6s}", super.toString(), tracks.toString(), artists.toString(), tracksQuantity, isReleased, genre);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Album that = (Album) obj;

        return tracks.equals(that.getTracks())
            && artists.equals(that.getArtists())
            && tracksQuantity == that.getTracksQuantity()
            && isReleased == that.isReleased()
            && genre == that.getGenre();
    }

    public Set<Song> getTracks() {
        return tracks;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public int getTracksQuantity() {
        return tracksQuantity;
    }

    public boolean isReleased() {
        return isReleased;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
