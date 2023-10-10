package MusicPlayer;

import java.util.Scanner;

public class Song {
    private String title;
    private String artist;
    private int duration;
    private String album;

    public Song(String title, String artist, int duration, String album) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void play() {
        System.out.println("Playing: " + title + " by " + artist);
    }

    public String toString() {
        return title + " by " + artist + " (" + duration + " seconds)";
    }

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