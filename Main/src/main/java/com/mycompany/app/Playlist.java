package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Playlist extends LibraryItem {
    private List<Song> tracks;
    private int tracksQuantity;
    private boolean isVisible;

    public Playlist(String name, Listener creator) {
        super(name, creator);
        tracks = new ArrayList<>();
        tracksQuantity = 0;
        isVisible = false;
    }

    public Playlist(String name, Listener creator, List<Song> playlist) {
        super(name, creator);
        tracks = new ArrayList<>();
        tracksQuantity = playlist.size();
        isVisible = false;

        for (Song song : playlist) add(song);
    }

    public boolean makeVisible() {
        if (isVisible) return false;

        isVisible = true;
        return true;
    }

    public boolean makePrivate() {
        if (!isVisible) return false;

        isVisible = false;

        return true;
    }

    public boolean add(Song song) {
        if (song == null || tracks.contains(song)) return false;
        
        tracks.add(song);
        tracksQuantity++;
        setLength(getLength() + song.getLength());

        return true;
    }

    public void add(List<Song> songs) {
        if (songs == null) return;

        for (Song song : songs) System.out.println(add(song));
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
        return String.format("Playlist{%1s, %2s, %3f, %4b}", super.toString(), tracks.toString(), tracksQuantity, isVisible);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Playlist that = (Playlist) obj;

        return tracks.equals(that.getTracks());
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
