package MusicPlayer;

import java.util.Random;
import java.util.Scanner;

/**
 * The MusicPlayer class represents a simple music player that manages a
 * playlist of songs.
 * It allows users to add and remove songs, play the playlist, play the next and
 * previous songs,
 * shuffle the playlist, and edit the properties of the current song.
 * 
 * @see Song
 * @see DoublyLinkedList
 * @see Node
 * @author Elikem Asudo Gale-Zoyiku
 */
public class MusicPlayer {
    private DoublyLinkedList<Song> playlist;
    private Node<Song> currentSong;

    /**
     * Constructs a new MusicPlayer with an empty playlist.
     */

    public MusicPlayer() {
        this.playlist = new DoublyLinkedList<>();
        this.currentSong = null;
    }
/**
 * The <code>addSong</code> method adds a song to the playlist.
 * 
 * @param song The song to be added to the playlist.
 * @return None
 * @see DoublyLinkedList#insertAtPosition(Node, int)
 */
    public void addSong(Node<Song> song) {
        playlist.insertAtPosition(song, playlist.getSize());
    }
/**
 * The <code>removeSong</code> method removes a song from the playlist.
 * 
 * @param song The song to be removed from the playlist.
 * @return None
 * @see DoublyLinkedList#deleteByElement(Node)
 */
    public void removeSong(Song song) {
        playlist.deleteByElement(song);
    }
/**
 * The <code>play</code> method plays the current song.
 * 
 * @see Song#play()
 */
    public void play() {
        if (playlist.getSize() > 0) {
            currentSong = playlist.get(0);
            currentSong.data.play();
        } else {
            System.out.println("Playlist is empty.");
        }
    }
/**
 * The <code>playNext</code> method plays the next song in the playlist.
 * 
 * @see Song#play()
 * @see Node#next
 */
    public void playNext() {
        if (currentSong != null && currentSong.next != null) {
            currentSong = currentSong.next;
            currentSong.data.play();
        }
    }

    /**
     * The <code>playPrevious</code> method plays the previous song in the
     * playlist.
     * 
     * @see Song#play()
     * @see Node#prev
     */

    public void playPrevious() {
        if (currentSong != null && currentSong.prev != null) {
            currentSong = currentSong.prev;
            currentSong.data.play();
        }
    }

    /**
     * The <code>shuffle</code> method shuffles the playlist.
     * 
     * @see DoublyLinkedList#insertAtPosition(Node, int)
     * @see DoublyLinkedList#deleteByPosition(int)
     */

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
        System.out.println("Welcome to the Music Player!");

        MusicPlayer player = new MusicPlayer();
        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Create a new playlist");
            System.out.println("2. Add a song to the current playlist");
            System.out.println("3. Remove a song from the current playlist");
            System.out.println("4. Play the current playlist");
            System.out.println("5. Play the next song");
            System.out.println("6. Play the previous song");
            System.out.println("7. Shuffle the current playlist");
            System.out.println("8. Edit the current song's properties");
            System.out.println("9. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    player = new MusicPlayer();
                    System.out.println("New playlist created.");
                    break;
                case 2:
                    System.out.println("Please enter the song title:");
                    String title = scanner.nextLine();
                    System.out.println("Please enter the song artist:");
                    String artist = scanner.nextLine();
                    System.out.println("Please enter the song duration (in seconds):");
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter the song album:");
                    String album = scanner.nextLine();
                    Song song = new Song(title, artist, duration, album);
                    player.addSong(new Node<>(song));
                    System.out.println("Song added to the playlist.");
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Song added to the playlist.");
                    break;
                case 3:
                    System.out.println("Please enter the song title:");
                    title = scanner.nextLine();
                    System.out.println("Please enter the song artist:");
                    artist = scanner.nextLine();
                    System.out.println("Please enter the song duration (in seconds):");
                    duration = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Please enter the song album:");
                    String albu = scanner.nextLine();
                    song = new Song(title, artist, duration, albu);
                    player.removeSong(song);
                    System.out.println("Song removed from the playlist.");
                    break;
                case 4:
                    player.play();
                    break;
                case 5:
                    player.playNext();
                    break;
                case 6:
                    player.playPrevious();
                    break;
                case 7:
                    player.shuffle();
                    System.out.println("Playlist shuffled.");
                    break;
                case 8:
                    if (player.currentSong != null) {
                        player.currentSong.data.edit();
                    } else {
                        System.out.println("No song is currently playing.");
                    }
                    break;
                case 9:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Music Player!");
    }
}