package com.mycompany.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mycompany.app.LibraryItem.Genre;
import com.mycompany.app.User.Origin;

public class TestPlaylist {
    
    @Test
    public void testMakeVisible() {
        Playlist playlist = new Playlist("Main", new Listener("name", Origin.AF, Genre.JAZZ));
        boolean expected = false;
        boolean result = playlist.isVisible();

        Assertions.assertEquals(expected, result);

        playlist.makeVisible();
        expected = true;
        result = playlist.isVisible();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testAdd() {
        Playlist playlist = new Playlist("Main", new Listener("name", Origin.AF, Genre.JAZZ));
        Song song = new Song("SongName", new Artist("drake", Origin.NA, Genre.RAP), 20, Genre.CLASSICAL);
        playlist.add(song);

        List<Song> expected = new ArrayList<Song>(Arrays.asList(song));
        List<Song> result = playlist.getTracks();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testAddMultipleSongs() {
        Playlist playlist = new Playlist("Main", new Listener("name", Origin.AF, Genre.JAZZ));
        Song song1 = new Song("Song1Name", new Artist("drake", Origin.NA, Genre.RAP), 20, Genre.CLASSICAL);
        Song song2 = new Song("Song2Name", new Artist("drake", Origin.NA, Genre.RAP), 20, Genre.CLASSICAL);
        playlist.add(Arrays.asList(song1, song2));

        List<Song> expected = new ArrayList<Song>(Arrays.asList(song1, song2));
        List<Song> result = playlist.getTracks();

        Assertions.assertEquals(expected, result);
    }
}
