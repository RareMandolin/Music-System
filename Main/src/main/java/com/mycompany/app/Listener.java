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

    public Listener(String name, Origin origin, Genre favGenre) {
        super(name, origin);
        isVisble = true;
        library = new HashSet<>();
        queue = new LinkedList<>();
        this.favGenre = favGenre;
        currentlyPlaying = null;
    }

    public boolean createPlaylist(String name) {
        if (name == null) return false;

        library.add(new Playlist(name, this));
        return true;
    }

    public boolean createPlaylist(String name, List<Song> songs) {
        if (name == null || songs == null) return false;

        library.add(new Playlist(name, this, songs));

        return true;
    }

    public boolean saveSong(Song song) {
        if (song == null || library.contains(song)) return false;

        library.add(song);

        return true;
    }

    public boolean unsaveSong(Song song) {
        if (song == null || !library.contains(song)) return false;

        library.remove(song);

        return true;
    }

    public boolean addToQueue(Song song) {
        if (song == null) return false;

        queue.offer(song);

        return true;
    }

    public boolean addToQueue(Album album) {
        if (album == null) return false;

        for (Song song : album.getTracks()) queue.offer(song);

        return true;
    }

    public void clearQueue() {
        queue.clear();
    }

    public boolean play() {
        if (queue.isEmpty() || currentlyPlaying != null) return false;

        currentlyPlaying = queue.poll();

        return true;
    }

    public boolean play(Song song) {
        if (song == null) return false;

        currentlyPlaying = song;

        return true;
    }

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
