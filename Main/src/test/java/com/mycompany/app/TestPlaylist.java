package com.mycompany.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mycompany.app.LibraryItem.Genre;
import com.mycompany.app.SongComparator.Criteria;
import com.mycompany.app.User.Origin;

public class TestPlaylist {
    @Test
    public void testPlay() {
        Listener listener = new Listener("name", Origin.AF, Genre.JAZZ);
        Playlist playlist = new Playlist("Main", listener);

        playlist.add(Arrays.asList(new Song("NAME1", new Artist("DRAKE", Origin.AF, Genre.CLASSICAL), 0, Genre.CLASSICAL),
                                    new Song("NAME2", new Artist("DRAKE", Origin.AF, Genre.CLASSICAL), 0, Genre.CLASSICAL)));
        playlist.play(listener);

        Song result1 = listener.getCurrentlyPlaying();
        Song expected1 = playlist.getTracks().get(0);
        Assertions.assertEquals(result1, expected1);

        Queue<Song> result2 = listener.getQueue();
        List<Song> expected2 = playlist.getTracks().subList(1, playlist.getTracks().size());
        Assertions.assertEquals(result2, expected2);
    }

    @Test
    public void testSort() {
        Playlist playlist = new Playlist("Main", new Listener("name", Origin.AF, Genre.JAZZ));
        Song song1 = new Song("S1", new Artist(), 5, Genre.CLASSICAL);
        Song song2 = new Song("S2", new Artist(), 3, Genre.JAZZ);
        Song song3 = new Song("S3", new Artist(), 2, Genre.RAP);
        playlist.add(song1);
        playlist.add(song2);
        playlist.add(song3);
        
        playlist.sort(Criteria.LENGTH);
        
        List<Song> expected = new ArrayList<>(Arrays.asList(song3, song2, song1));
        List<Song> result = playlist.getTracks();

        Assertions.assertEquals(expected, result);
    }

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
    public void testMakePrivate() {
        Playlist playlist = new Playlist("Main", new Listener("name", Origin.AF, Genre.JAZZ));
        playlist.makeVisible();
        boolean expected = true;
        boolean result = playlist.isVisible();

        Assertions.assertEquals(expected, result);

        playlist.makePrivate();
        expected = false;
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

    @Test
    public void testExport() {
        Playlist playlist = new Playlist("Main", new Listener("name", Origin.AF, Genre.JAZZ));
        Song song1 = new Song("Song1Name", new Artist("drake", Origin.NA, Genre.RAP), 20, Genre.CLASSICAL);
        Song song2 = new Song("Song2Name", new Artist("drake", Origin.NA, Genre.RAP), 20, Genre.CLASSICAL);
        playlist.add(Arrays.asList(song1, song2));

        String expected = "1. Song1Name, drake, 20.0, CLASSICAL\n2. Song2Name, drake, 20.0, CLASSICAL\n";
        String result = null;

        playlist.export();
        try {
            result = Files.readString(Paths.get("C:\\Users\\Admin\\Desktop\\MusicSystem\\Main\\src\\resources\\export.csv"));
        } catch (IOException e) {e.printStackTrace();}
        
        Assertions.assertEquals(result, expected);
    }
}
