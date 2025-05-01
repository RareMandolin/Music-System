package com.mycompany.app;

import java.util.Comparator;

public class Song extends LibraryItem {
    private Genre genre;

    public Song(String name, Artist creator, double length, Genre genre) {
        super(name, creator);
        this.genre = genre;
        setLength(length);
        //AUTO ADD SONG TO ARTIST TRACK LIST??
    }

    public class SongComparator implements Comparator<Song> {
        private final Criteria criteria;

        public SongComparator(Criteria criteria) {
            this.criteria = criteria;
        }

        @Override
        public int compare(Song s1, Song s2) {
            switch (criteria) {
                case DATECREATED : return s1.getCreationDate().compareTo(s2.getCreationDate());
                case LENGTH : return Double.compare(s1.getLength(), s2.getLength());
                default: return Integer.compare(s1.getId(), s2.getId());
            }
        }

        public enum Criteria {
            ID, DATECREATED, LENGTH
        }
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
