package com.mycompany.app;

import java.util.Comparator;

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