package com.mycompany.app;

public class Song extends LibraryItem implements Playable {
    private Genre genre;

    public Song(String name, Artist creator, double length, Genre genre) {
        super(name, creator);
        this.genre = genre;
        setLength(length);
        creator.getSongs().add(this);
    }

    /**
     * Plays the song for a given listener
     * @param listener the listener for which the song will be played
     */
    public void play(Listener listener) {
        listener.startPlayback(this);
    }

    public String toString() {
        return String.format("Song{%1s, %2s}", super.toString(), genre);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Song that = (Song) obj;

        return genre == that.getGenre();
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
