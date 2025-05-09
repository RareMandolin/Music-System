package com.mycompany.app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Album extends LibraryItem implements Playable {
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
        creator.getAlbums().add(this);
    }

    /**
     * Releases the album 
     * @return whether the album was successfully released or not
     */
    public boolean release() {
        if (isReleased) return false;

        isReleased = true;
        return true;
    }

    /**
     * Adds a song to the album tracklist
     * @param song A song to be added to the album
     * @return whether or not the song was successfully added
     */
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

    /**
     * Adds multiple songs to the album
     * @param songs the list of songs to be added
     */
    public void add(List<Song> songs) {
        if (songs == null) return;

        for (Song song : songs) add(song);
    }

    /**
     * Plays the album for a given user
     * @param listener the user for which the album will be played for
     */
    public void play(Listener listener) {
        listener.clearQueue();

        for (Song song : tracks) if (song != null) listener.addToQueue(song);

        listener.startPlayback();
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
