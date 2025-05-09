package com.mycompany.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mycompany.app.LibraryItem.Genre;
import com.mycompany.app.User.Origin;

public class TestSong {
    @Test
    public void testPlay() {
        Listener listener = new Listener("NAME1", Origin.AF, Genre.CLASSICAL);
        Song song = new Song("Name", new Artist("NAME", Origin.NA, Genre.ROCK), 0, Genre.POP);
        song.play(listener);

        Song expected = song;
        Song result = listener.getCurrentlyPlaying();

        Assertions.assertEquals(expected, result);
    }
}
