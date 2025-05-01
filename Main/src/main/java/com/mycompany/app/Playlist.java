package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Playlist extends LibraryItem {
    private List<Song> tracks;
    private int tracksQuantity;
    private boolean isVisible;

    public Playlist(String name, User creator) {
        super(name, creator, 0);
        tracks = new ArrayList<>();
        tracksQuantity = 0;
        isVisible = false;
    }

    public Playlist(String name, User creator, Playlist playlist) {
        super(name, creator, playlist.getLength());
        tracks = new ArrayList<>(playlist.getTracks());
        tracksQuantity = playlist.getTracksQuantity();
        isVisible = false;
    }

    public boolean makeVisible() {
        if (isVisible) return false;

        isVisible = true;
        return true;
    }

    public boolean add(Song song) {
        if (song == null || tracks.contains(song)) return false;
        tracks.add(song);
        tracksQuantity++;

        return true;
    }

    public boolean add(List<Song> songs) {
        if (songs == null) return false;

        boolean successful = false;
        for (Song song : songs) successful = add(song);

        return successful;
    }

    public void export() {
        final String PATH = "C:\\Users\\Admin\\Desktop\\MusicSystem\\Main\\src\\resources\\export.csv";
        export(PATH);
    }

    public void export(String path) {
        if (path == null) return;

        final String PATH = path;
        //TODO
    }

    public String toString() {
        return String.format("Playlist{%1s, %2s, %3d, %4b}", super.toString(), tracks.toString(), tracksQuantity, isVisible);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
       
        Playlist that = (Playlist) obj;

        return tracks.equals(that.getTracks())
            && tracksQuantity == that.getTracksQuantity()
            && isVisible == that.isVisible();
    }

    public List<Song> getTracks() {
        return tracks;
    }

    public int getTracksQuantity() {
        return tracksQuantity;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
