package com.mycompany.app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Album extends LibraryItem {
    private Set<Song> tracks;
    private Set<Artist> artists;
    private int tracksQuantity;
    private boolean isReleased;

    public Album(String name, Artist creator, boolean isReleased) {
        super(name, creator, 0);
        tracks = new HashSet<>();
        artists = new HashSet<>();
        tracksQuantity = 0;
        this.isReleased = isReleased;
    }

    public boolean release() {
        if (isReleased) return false;

        isReleased = true;
        return true;
    }

    public boolean add(Song song) {
        if (tracks.contains(song)) return false;
        tracks.add(song);
        tracksQuantity++;

        if (!artists.contains(song.getCreator())) artists.add((Artist) song.getCreator());
        return true;
    }

    public boolean add(List<Song> songs) {
        boolean successful = false;
        for (Song song : songs) successful = add(song);

        return successful;
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
}
