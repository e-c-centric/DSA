public class Song {
    private String title;
    private String artist;
    private int duration; // Duration in seconds
    private String album;
    private String genre;
    private int year;

    public Song(String title, String artist, int duration, String album, String genre, int year) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public void play() {
        System.out.println("Now playing: " + title + " by " + artist);
        System.out.println("Album: " + album);
        System.out.println("Duration: " + duration + " seconds");
        System.out.println("Genre: " + genre);
        System.out.println("Year: " + year);
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nArtist: " + artist + "\nAlbum: " + album +
               "\nDuration: " + duration + " seconds" + "\nGenre: " + genre + "\nYear: " + year;
    }
}
