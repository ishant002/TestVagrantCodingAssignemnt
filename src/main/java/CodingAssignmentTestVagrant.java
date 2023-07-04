import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CodingAssignmentTestVagrant {
    private int capacity;
    private final Map<String, LinkedList<String>> songUserMap;

    public CodingAssignmentTestVagrant(int capacity) {
        this.capacity = capacity;
        this.songUserMap = new HashMap<>();
    }

    public void playSong(String user, String song) {
        if (!songUserMap.containsKey(user)) {
            songUserMap.put(user, new LinkedList<>());
        }

        LinkedList<String> songs = songUserMap.get(user);

        // Remove the song if it's already present in the user's playlist
        songs.remove(song);

        // Add the song at the end of the user's playlist
        songs.addLast(song);

        // Remove the least recently played song if the playlist exceeds the capacity
        if (songs.size() > capacity) {
            songs.removeFirst();
        }
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        return songUserMap.getOrDefault(user, new LinkedList<>());
    }
}
