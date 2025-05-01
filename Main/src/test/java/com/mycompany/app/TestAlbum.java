package com.mycompany.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mycompany.app.LibraryItem.Genre;
import com.mycompany.app.User.Origin;

public class TestAlbum {
    @Test
    public void testRelease() {
        Album album = new Album("Main", new Artist("Drake", Origin.EU, Genre.POP));
        boolean expected = false;
        boolean result = album.isReleased();

        Assertions.assertEquals(expected, result);

        album.release();
        expected = true;
        result = album.isReleased();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testAdd() {
        Album album = new Album("Main", new Artist("Drake", Origin.EU, Genre.POP));
        Song song = new Song("SongName", new Artist("Drake", Origin.EU, Genre.POP), 20, Genre.CLASSICAL);
        album.add(song);

        Set<Song> expected = new HashSet<Song>(Arrays.asList(song));
        Set<Song> result = album.getTracks();

        Assertions.assertEquals(album.getTracksQuantity(), 1);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testAddMultipleSongs() {
        Album album = new Album("Main", new Artist("Drake", Origin.EU, Genre.POP));
        Song song1 = new Song("Song1Name", new Artist("Drake", Origin.EU, Genre.POP), 20, Genre.CLASSICAL);
        Song song2 = new Song("Song2Name", new Artist("Drake", Origin.EU, Genre.POP), 20, Genre.CLASSICAL);
        album.add(Arrays.asList(song1, song2));

        Set<Song> expected = new HashSet<Song>(Arrays.asList(song1, song2));
        Set<Song> result = album.getTracks();

        Assertions.assertEquals(album.getTracksQuantity(), 2);
        Assertions.assertEquals(expected, result);
    }
}
