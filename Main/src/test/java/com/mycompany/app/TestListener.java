package com.mycompany.app;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mycompany.app.LibraryItem.Genre;
import com.mycompany.app.User.Origin;

public class TestListener {
    @Test
    public void testCreatePlaylist() {
        Listener listener = new Listener("DRAKE", Origin.AF, Genre.CLASSICAL);

        listener.createPlaylist("P1");
        Playlist p1 = new Playlist("P1", listener);

        Boolean result = listener.getLibrary().contains(p1);
        Boolean expected = true;

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCreatePlaylistWithSongs() {
        Listener listener = new Listener("DRAKE", Origin.AF, Genre.CLASSICAL);
        Song song = new Song("Song", new Artist("dRAKE", Origin.AF, Genre.CLASSICAL), 0, Genre.CLASSICAL);
        listener.createPlaylist("P1", Arrays.asList(song));
    
        Boolean result = listener.getLibrary().contains(new Playlist("P1", listener, Arrays.asList(song)));
        

        Assertions.assertTrue(result); 
    }

    @Test
    public void testSaveSong() {
        Listener listener = new Listener();
        Song song = new Song("Song", new Artist(), 0, null);

        listener.saveSong(song);

        Boolean result = listener.getLibrary().contains(song);

        Assertions.assertTrue(result);
    }

    @Test
    public void testUnsaveSong() {
        Listener listener = new Listener();
        Song song = new Song("Song", new Artist(), 0, null);

        listener.saveSong(song);
        Boolean result = listener.getLibrary().contains(song);
        Assertions.assertTrue(result);

        listener.unsaveSong(song);
        result = listener.getLibrary().contains(song);
        Assertions.assertFalse(result);
    }

    @Test
    public void testAddToQueue() {
        Listener listener = new Listener();
        Song song = new Song("Song", new Artist(), 0, null);
        listener.addToQueue(song);

        Assertions.assertTrue(listener.getQueue().poll().equals(song));
    }
    
    @Test
    public void testAddToQueueMultipleSongs() {
        Listener listener = new Listener();
        Song song = new Song("Song", new Artist(), 0, null);
        Song song2 = new Song("Song2", new Artist(), 10, null);
        listener.addToQueue(Arrays.asList(song, song2));

        Assertions.assertTrue(listener.getQueue().poll().equals(song));
        Assertions.assertTrue(listener.getQueue().poll().equals(song2));
    }

    @Test
    public void testClearQueue() {
        Listener listener = new Listener();
        Song song = new Song("Song", new Artist(), 0, null);
        listener.addToQueue(song);

        Assertions.assertTrue(listener.getQueue().peek().equals(song));
        listener.clearQueue();
        Assertions.assertTrue(listener.getQueue().isEmpty());
    }

    @Test
    public void testStartPlayback() {
        Listener listener = new Listener();
        Song song = new Song("Song", new Artist(), 0, null);
        listener.addToQueue(song);

        listener.startPlayback();

        Assertions.assertTrue(listener.getCurrentlyPlaying().equals(song));
    }

    @Test
    public void testSkip() {
        Listener listener = new Listener();
        Song song = new Song("Song", new Artist(), 0, null);
        Song song2 = new Song("Song2", new Artist(), 10, null);
        listener.addToQueue(Arrays.asList(song, song2));

        listener.startPlayback();
        Assertions.assertTrue(listener.getCurrentlyPlaying().equals(song));
        listener.skip();
        Assertions.assertTrue(listener.getCurrentlyPlaying().equals(song2));
    }
}
