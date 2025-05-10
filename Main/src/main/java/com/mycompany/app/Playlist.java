package com.mycompany.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist extends LibraryItem implements Playable {
    private List<Song> tracks;
    private int tracksQuantity;
    private boolean isVisible;

    public Playlist(String name, Listener creator) {
        super(name, creator);
        tracks = new ArrayList<>();
        tracksQuantity = 0;
        isVisible = false;
        creator.getLibrary().add(this);
    }

    public Playlist(String name, Listener creator, List<Song> playlist) {
        super(name, creator);
        tracks = new ArrayList<>();
        tracksQuantity = playlist.size();
        isVisible = false;
        creator.getLibrary().add(this);

        for (Song song : playlist) add(song);
    }

    /**
     * Plays the playlist for a given user
     * @param listener the listener for which the playlist will be played for
     */
    public void play(Listener listener) {
        listener.clearQueue();
        listener.addToQueue(tracks);
        listener.startPlayback();
    }

    /**
     * Sorts the playlist tracks based on a given criteria
     * @param criteria the criteria to sort the playlist based on
     */
    public void sort(SongComparator.Criteria criteria) {
        if (criteria == null) return;

        Collections.sort(tracks, new SongComparator(criteria));
    }

    /**
     * Makes the playlist visible
     * @return whether the playlist was successfully made visible
     */
    public boolean makeVisible() {
        if (isVisible) return false;

        isVisible = true;
        return true;
    }

    /**
     * Makes the playlist private
     * @return whether the playlist was successfully made private
     */
    public boolean makePrivate() {
        if (!isVisible) return false;

        isVisible = false;

        return true;
    }

    /**
     * Adds a given song to the playlist
     * @param song the song to be added
     * @return whether the song was successfully added to the playlist
     */
    public boolean add(Song song) {
        if (song == null || tracks.contains(song)) return false;
        
        tracks.add(song);
        tracksQuantity++;
        setLength(getLength() + song.getLength());

        return true;
    }

    /**
     * Adds multiple songs to the playlist
     * @param songs the list of songs to be added
     */
    public void add(List<Song> songs) {
        if (songs == null) return;

        for (Song song : songs) add(song);
    }

    /**
     * Exports the information of each song in the playlist
     */
    public void export() {
        final String PATH = "C:\\Users\\Admin\\Desktop\\MusicSystem\\Main\\src\\resources\\export.csv";
        export(PATH);
    }

    /**
     * Exports the information of each song in the playlist to a given location
     * @param PATH the location to which the song information will be exported
     */
    public void export(final String PATH) {
        if (PATH == null) return;
        if (!PATH.substring(PATH.length() - 4, PATH.length()).equals(".csv")) 
        throw new RuntimeException("File type must be CSV");

        try (FileWriter fw = new FileWriter(PATH)) {
            for (int i = 0; i < tracks.size(); i++) {
                fw.write((i + 1) + ". ");
                fw.write(tracks.get(i).getName() + ", ");
                fw.write(tracks.get(i).getCreator().getName() + ", ");
                fw.write(tracks.get(i).getLength() + ", ");
                fw.write(tracks.get(i).getGenre() + "\n");
            }
        } catch (IOException e) {e.printStackTrace();}
    }

    public String toString() {
        return String.format("Playlist{%1s, %2s, %3d, %4b}", super.toString(), tracks.toString(), tracksQuantity, isVisible);
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
