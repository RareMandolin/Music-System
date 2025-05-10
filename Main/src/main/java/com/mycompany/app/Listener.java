package com.mycompany.app;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.mycompany.app.LibraryItem.Genre;

public class Listener extends User {
    private boolean isVisble;
    private Genre favGenre;
    private Set<LibraryItem> library;
    private Queue<Song> queue;
    private Song currentlyPlaying;

    public Listener() {
        super();
        isVisble = false;
        library = new HashSet<>();
        queue = new LinkedList<>();
        favGenre = null;
        currentlyPlaying = null;
    }

    public Listener(String name, Origin origin, Genre favGenre) {
        super(name, origin);
        isVisble = true;
        library = new HashSet<>();
        queue = new LinkedList<>();
        this.favGenre = favGenre;
        currentlyPlaying = null;
    }

    /**
     * Creates a new empty playlist and adds it to the listener's library
     * @param name the name of the created playlist
     * @return whether or not the playlist was successfully created or not
     */
    public boolean createPlaylist(String name) {
        if (name == null) return false;

        for (LibraryItem item : library) {
            if (item instanceof Playlist playlist) if (playlist.getName() == name) return false;
        }

        library.add(new Playlist(name, this));
        return true;
    }

    /**
     * Creates a new playlist containing a given list of songs and adds it to the listener's library
     * @param name the name of the new playlist
     * @param songs the list of songs to be used in creation
     * @return whether the creation was successful
     */
    public boolean createPlaylist(String name, List<Song> songs) {
        if (name == null || songs == null) return false;

        for (LibraryItem item : library) {
            if (item instanceof Playlist playlist) if (playlist.getName() == name) return false;
        }

        library.add(new Playlist(name, this, songs));

        return true;
    }

    /**
     * Adds a given song to the listener's library
     * @param song the song to be saved
     * @return whether the song was saved successfully
     */
    public boolean saveSong(Song song) {
        if (song == null || library.contains(song)) return false;

        library.add(song);

        return true;
    }

    /**
     * Removes a song from the listener's library
     * @param song the song to be removed
     * @return whether the song was removed successfully
     */
    public boolean unsaveSong(Song song) {
        if (song == null || !library.contains(song)) return false;

        library.remove(song);

        return true;
    }

    /**
     * Adds a given song to the queue
     * @param song the song to be added
     */
    public void addToQueue(Song song) {
        if (song == null) return;

        queue.offer(song);
    }

    /**
     * Adds a list of songs to the queue
     * @param songs the list of songs to be added
     */
    public void addToQueue(List<Song> songs) {
        if (songs == null) return;

        for (Song song : songs) if (song != null) queue.offer(song);
    }

    /**
     * Clears the listener's queue
     */
    public void clearQueue() {
        queue.clear();
    }

    /**
     * Plays the next song in the queue if playback is currently stopped
     * @return whether or not playback was successfully started
     */
    public boolean startPlayback() {
        if (queue.isEmpty() || currentlyPlaying != null) return false;

        currentlyPlaying = queue.poll();

        return true;
    }

    /**
     * Sets playback to a given song
     * @param song the song to be played
     * @return whether playback was successfully started
     */
    public boolean startPlayback(Song song) {
        if (song == null) return false;

        currentlyPlaying = song;

        return true;
    }

    /**
     * Skips the currently playing song
     * @return whether the song was successfully skipped
     */
    public boolean skip() {
        if (currentlyPlaying == null && queue.isEmpty()) return false;

        if (queue.isEmpty()) {
            currentlyPlaying = null;
            return true;
        }

        currentlyPlaying = queue.poll();

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Listener that = (Listener) obj;
        
        return favGenre == that.getFavGenre()
            && library.equals(that.getLibrary())
            && queue.equals(that.getQueue())
            && currentlyPlaying.equals(currentlyPlaying);
    }

    @Override
    public String toString() {
        return String.format("Listener{%1s, %2b, %3s, %4s, %5s, %6s}", super.toString(), isVisble, 
        favGenre, library, queue, currentlyPlaying);
    }

    public boolean getIsVisible() {
        return isVisble;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisble = isVisible;
    }

    public Genre getFavGenre() {
        return favGenre;
    }

    public void setFavGenre(Genre favGenre) {
        this.favGenre = favGenre;
    }

    public Set<LibraryItem> getLibrary() {
        return library;
    }

    public Queue<Song> getQueue() {
        return queue;
    }

    public Song getCurrentlyPlaying() {
        return currentlyPlaying;
    }
}
