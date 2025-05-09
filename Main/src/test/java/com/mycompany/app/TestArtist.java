package com.mycompany.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mycompany.app.LibraryItem.Genre;
import com.mycompany.app.User.Origin;

public class TestArtist {
    @Test
    public void testCreateSong() {
        Artist artist = new Artist("Drake", Origin.AF, Genre.JAZZ);
        artist.createSong("NAME", 100, Genre.CLASSICAL);
        
        Boolean expected = true;
        Boolean result = artist.getSongs().contains(new Song("NAME", artist, 100, Genre.CLASSICAL));

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCreateAlbum() {
        Artist artist = new Artist("Drake", Origin.AF, Genre.JAZZ);
        artist.createAlbum("NAME");
        
        Boolean expected = true;
        Boolean result = artist.getAlbums().contains(new Album("NAME", artist));

        Assertions.assertEquals(expected, result);
    }
}
