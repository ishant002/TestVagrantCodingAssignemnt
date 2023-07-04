import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

public class SongsPlaylistTests {

    @Test
    public void testPlaySongAndGetRecentlyPlayedSongs() {
        SongsPlaylist store = new SongsPlaylist(3);

        // Test for a single user
        store.playSong("User1", "S1");
        store.playSong("User1", "S2");
        store.playSong("User1", "S3");
        List<String> user1Songs = store.getRecentlyPlayedSongs("User1");
        Assert.assertEquals(3, user1Songs.size());
        Assert.assertEquals("S3", user1Songs.get(2));
        Assert.assertEquals("S2", user1Songs.get(1));
        Assert.assertEquals("S1", user1Songs.get(0));

        // Test for another user
        store.playSong("User2", "S4");
        List<String> user2Songs = store.getRecentlyPlayedSongs("User2");
        Assert.assertEquals(1, user2Songs.size());
        Assert.assertEquals("S4", user2Songs.get(0));

        // Test playing a song already in the playlist
        store.playSong("User1", "S2");
        user1Songs = store.getRecentlyPlayedSongs("User1");
        Assert.assertEquals(3, user1Songs.size());
        Assert.assertEquals("S2", user1Songs.get(2));
        Assert.assertEquals("S3", user1Songs.get(1));
        Assert.assertEquals("S1", user1Songs.get(0));

        // Test playing a song exceeding the capacity
        store.playSong("User1", "S5");
        user1Songs = store.getRecentlyPlayedSongs("User1");
        Assert.assertEquals(3, user1Songs.size());
        Assert.assertEquals("S5", user1Songs.get(2));
        Assert.assertEquals("S2", user1Songs.get(1));
        Assert.assertEquals("S3", user1Songs.get(0));

        // Test for a user without any played songs
        List<String> user3Songs = store.getRecentlyPlayedSongs("User3");
        Assert.assertEquals(0, user3Songs.size());
    }

    @Test
    public void testCapacityZero() {
        SongsPlaylist store = new SongsPlaylist(0);

        store.playSong("User1", "S1");
        List<String> user1Songs = store.getRecentlyPlayedSongs("User1");
        Assert.assertEquals(0, user1Songs.size());
    }

    @Test
    public void testNegativeCapacity() {
        SongsPlaylist store = new SongsPlaylist(-1);

        store.playSong("User1", "S1");
        List<String> user1Songs = store.getRecentlyPlayedSongs("User1");
        Assert.assertEquals(0, user1Songs.size());
    }
}
