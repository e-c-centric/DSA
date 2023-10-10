package MusicPlayer;
import java.util.Random;

public class MusicPlayer{
    private DoublyLinkedList<Song> playlist;
    private Node<Song> currentSong;

    public MusicPlayer() {
        this.playlist = new DoublyLinkedList<>();
        this.currentSong = null;
    }

    public void addSong(Node<Song> song) {
        playlist.insertAtPosition(song, playlist.getSize());
    }

    public void removeSong(Song song) {
        playlist.deleteByElement(song);
    }

    public void play() {
        if (playlist.getSize() > 0) {
            currentSong = playlist.get(0);
            currentSong.data.play();
        } else {
            System.out.println("Playlist is empty.");
        }
    }

    public void playNext() {
        if (currentSong != null && currentSong.next != null) {
            currentSong = currentSong.next;
            currentSong.data.play();
        } else {
            System.out.println("No next song in the playlist.");
        }
    }

    public void playPrevious() {
        if (currentSong != null && currentSong.prev != null) {
            currentSong = currentSong.prev;
            currentSong.data.play();
        } else {
            System.out.println("No previous song in the playlist.");
        }
    }

    public void shuffle() {
        int playlistSize = playlist.getSize();
        if (playlistSize <= 1) {
            return; // Nothing to shuffle
        }

        // Create an array of song indices and shuffle it
        Random random = new Random();
        int[] songIndices = new int[playlistSize];
        for (int i = 0; i < playlistSize; i++) {
            songIndices[i] = i;
        }

        for (int i = playlistSize - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = songIndices[i];
            songIndices[i] = songIndices[j];
            songIndices[j] = temp;
        }

        // Reorder the playlist according to shuffled indices
        DoublyLinkedList<Song> shuffledPlaylist = new DoublyLinkedList<>();
        for (int i = 0; i < playlistSize; i++) {
            Node<Song> song = playlist.get(songIndices[i]);
            shuffledPlaylist.insertAtPosition(song, i);
        }

        playlist = shuffledPlaylist;
        currentSong = playlist.get(0);
    }

    public static void main(String[] args) {
        System.out.println("Creating a new playlist...");
    }
}
