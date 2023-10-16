package MusicPlayer;

import java.util.Scanner;

/**
 * The Song class represents a single song in a music player application. Each
 * song has a title, artist, duration, and album.
 * This class provides methods to get and set each attribute, play the song,
 * edit the song's attributes, and compare two songs for equality.
 */
public class Song {

    /**
     * The title of the song.
     */
    private String title;

    /**
     * The artist who performed the song.
     */
    private String artist;

    /**
     * The duration of the song in seconds.
     */
    private int duration;

    /**
     * The album that the song belongs to.
     */
    private String album;

    /**
     * Constructs a new Song object with the given title, artist, duration, and
     * album.
     * 
     * @param title    the title of the song
     * @param artist   the artist who performed the song
     * @param duration the duration of the song in seconds
     * @param album    the album that the song belongs to
     */
    public Song(String title, String artist, int duration, String album) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
    }

    /**
     * Returns the title of the song.
     * 
     * @return the title of the song
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the song to the given value.
     * 
     * @param title the new title of the song
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the artist who performed the song.
     * 
     * @return the artist who performed the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the artist who performed the song to the given value.
     * 
     * @param artist the new artist who performed the song
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Returns the duration of the song in seconds.
     * 
     * @return the duration of the song in seconds
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the song to the given value.
     * 
     * @param duration the new duration of the song in seconds
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns the album that the song belongs to.
     * 
     * @return the album that the song belongs to
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Sets the album that the song belongs to to the given value.
     * 
     * @param album the new album that the song belongs to
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Plays the song by printing a message to the console.
     */
    public void play() {
        System.out.println("Playing: " + title + " by " + artist);
    }

    /**
     * Returns a string representation of the song in the format "title by artist
     * (duration seconds)".
     * 
     * @return a string representation of the song
     */
    public String toString() {
        return title + " by " + artist + " (" + duration + " seconds)";
    }

    /**
     * Allows the user to edit the song's attributes by prompting for input and
     * setting the appropriate attribute.
     */
    public void edit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What attribute would you like to edit?");
        System.out.println("1. Title");
        System.out.println("2. Artist");
        System.out.println("3. Duration");
        System.out.println("4. Album");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter new title:");
                String newTitle = scanner.nextLine();
                setTitle(newTitle);
                break;
            case 2:
                System.out.println("Enter new artist:");
                String newArtist = scanner.nextLine();
                setArtist(newArtist);
                break;
            case 3:
                System.out.println("Enter new duration (in seconds):");
                int newDuration = scanner.nextInt();
                setDuration(newDuration);
                break;
            case 4:
                System.out.println("Enter new album:");
                String newAlbum = scanner.nextLine();
                setAlbum(newAlbum);
                break;
            default:
                System.out.println("Invalid choice.");

                scanner.close();
        }
    }

    /**
     * Compares this song to another song for equality.
     * 
     * @param other the other song to compare to
     * @return true if the two songs are equal (i.e. have the same title, artist,
     *         duration, and album), false otherwise
     */
    public boolean equals(Song other) {
        if (this.title.equals(other.title) &&
                this.artist.equals(other.artist) &&
                this.duration == other.duration &&
                this.album.equals(other.album)) {
            return true;
        } else {
            return false;
        }
    }
}