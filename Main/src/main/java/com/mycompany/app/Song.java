package com.mycompany.app;

import java.util.Comparator;

public class Song extends LibraryItem {
    private Genre genre;

    public Song(String name, Artist creator, double length, Genre genre) {
        super(name, creator, length);
        this.genre = genre;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public enum Genre {
        RAP, POP, CLASSICAL, JAZZ, ROCK
    }
}
