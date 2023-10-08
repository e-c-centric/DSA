import java.util.Random;

/**
 * The <code>Song</code> class represents a song with a title, artist, duration,
 * and album.
 * It has a <code>play()</code> method that prints out the song title and
 * artist.
 * It also has a <code>toString()</code> method that returns the song title and
 * artist.
 *
 * @author Elikem Asudo Gale-Zoyiku
 **/
class Song {
    private String title;
    private String artist;
    private int duration;
    private String album;

    /**
     * The <code>Song</code> constructor takes in a song title, artist, duration,
     * and album.
     * It initializes the instance variables with the given parameters.
     * 
     * @param title    The song title.
     * 
     * @param artist   The song artist.
     * 
     * @param duration The song duration.
     * 
     * @param album    The song album.
     * 
     * @return A new <code>Song</code> object.
     **/
    public Song(String title, String artist, int duration, String album) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
    }

    /**
     * The <code>getTitle</code> method returns the song title.
     * 
     * @param None
     * @return The song title.
     **/
    public String getTitle() {
        return title;
    }

    /**
     * The <code>getArtist</code> method returns the song artist.
     * 
     * @param None
     * @return The song artist.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * The <code>getDuration</code> method returns the song duration.
     * 
     * @param None
     * @return The song duration.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * The <code>getAlbum</code> method returns the song album.
     * 
     * @param None
     * @return The song album.
     */
    public String getAlbum() {
        return album;
    }

    /**
     * The <code>play</code> method prints out the song title and artist.
     * 
     * @param None
     * @return None
     */
    public void play() {
        System.out.println("Now playing: " + title + " by " + artist);
    }

    /**
     * The <code>toString</code> method returns the song title and artist.
     * 
     * @param None
     * @return The song title and artist.
     * 
     * @Override The <code>toString</code> method overrides the
     *           <code>toString</code> method in the <code>Object</code> class.
     */
    @Override
    public String toString() {
        return title + " by " + artist;
    }
}

/**
 * The <code>Node</code> class represents a node in a doubly linked list.
 * It has a <code>data</code> field that stores the data of the node.
 * It also has a <code>next</code> field that stores the reference to the next
 * node.
 * It also has a <code>prev</code> field that stores the reference to the
 * previous node.
 * It has a <code>toString()</code> method that returns the data of the node.
 * 
 * @param <T> The type of data stored in the node.
 * @author Elikem Asudo Gale-Zoyiku
 */
class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    /**
     * The <code>Node</code> constructor takes in data and initializes the
     * <code>data</code> field with the given data.
     * 
     * @param data The data to be stored in the node.
     * @return A new <code>Node</code> object.
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * The <code>toString</code> method returns the data of the node.
     * This method invokes the <code>toString</code> method of the type of data,
     * that is <code>T.toString()</code>.
     * 
     * @param None
     * @return The data of the node.
     */
    public String toString() {
        return data.toString();
    }
}

/**
 * The <code>DoublyLinkedList</code> class represents a doubly linked list.
 * It has a <code>size</code> field that stores the size of the list.
 * It also has a <code>head</code> field that stores the reference to the head
 * node.
 * It also has a <code>tail</code> field that stores the reference to the tail
 * node.
 * It has a <code>insert()</code> method that inserts a node at a given
 * position.
 * It has a <code>delete()</code> method that deletes a node at a given
 * position.
 * 
 * @param <T> The type of data stored in the nodes of the list..
 * @see <code>Node</code> class
 * @author Elikem Asudo Gale-Zoyiku
 */
class DoublyLinkedList<T extends Node<?>> {
    int size = 0;
    private Node<T> head;
    private Node<T> tail;

    /**
     * The <code>DoublyLinkedList</code> constructor initializes the
     * <code>head</code> and <code>tail</code> fields to null.
     * 
     * @param None
     * @return A new <code>DoublyLinkedList</code> object.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * The <code>insert</code> method inserts a node at a given position.
     * 
     * @param data The song to be stored in the node.
     * @param pos  The position at which the node is to be inserted.
     * @return None
     */
    public void insert(T data, int pos) {
        Node<T> newNode = new Node<T>(data);
        if (pos == 1) {
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                head.prev = newNode;
                newNode.next = head;
                newNode.prev = null;
                head = newNode;
            }
            size++;
        } else if (pos == size + 1) {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
            newNode.next = null;
            this.tail = newNode;
            size++;
        } else {
            Node<T> current = head;
            int count = 1;
            while (count < (pos - 1)) {
                current = current.next;
                count++;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
            size++;
        }
    }

    /**
     * The <code>delete</code> method deletes a song at a given position.
     * 
     * @param pos The position of the node to be deleted.
     * @return None
     */
    public void delete(int pos) {
        if (head == null) {
            return;
        } else if (pos == 1) {
            head = head.next;
            head.prev = null;
            size--;
        } else if (pos == size + 1) {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.prev.next = null;
            current.prev = current.prev.prev;
            size--;
        } else {
            Node<T> current = head;
            int count = 1;
            while (count < (pos - 1)) {
                current = current.next;
                count++;
            }
            current.next = current.next.next;
            current.next.prev = current;
            size--;
        }
    }

    /**
     * The <code>getNode</code> method returns the node at a given position.
     * 
     * @param pos The position of the node to be returned.
     * @return The node at the given position.
     */
    public Node<T> getNode(int pos) {
        Node<T> current = head;
        int count = 1;
        while (count < pos) {
            current = current.next;
            count++;
        }
        return current;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }
}

/**
 * The <code>MusicPlayer</code> class represents a music player.
 * It has a <code>playlist</code> field that stores the playlist.
 * It also has a <code>currentSong</code> field that stores the current song.
 * It has an <code>addSong()</code> method that adds a song to the playlist.
 * It has a <code>removeSong()</code> method that removes a song from the
 * playlist.
 * It has a <code>playNext()</code> method that plays the next song in the
 * playlist.
 * It has a <code>playPrevious()</code> method that plays the previous song in
 * the playlist.
 * It has a <code>shuffle()</code> method that shuffles the playlist.
 * It has a <code>play()</code> method that starts playing the music.
 * 
 * @see <code>Song</code> class
 * @see <code>DoublyLinkedList</code> class
 * @see <code>Node</code> class
 * @param None
 * @author Elikem Asudo Gale-Zoyiku
 * 
 */
public class MusicPlayer {

    DoublyLinkedList<Node<Song>> playlist;
    Node<Song> currentSong;

    /**
     * The <code>MusicPlayer</code> constructor initializes the playlist and
     * currentSong fields to null.
     * 
     * @param None
     * @return A new <code>MusicPlayer</code> object.
     */
    public MusicPlayer() {
        this.playlist = new DoublyLinkedList<Node<Song>>();
        this.currentSong = null;
    }

    /**
     * The <code>addSong</code> method adds a song to the playlist.
     * It invokes the <code>insert</code> method of the
     * <code>DoublyLinkedList</code> class.
     * 
     * @see <code>DoublyLinkedList</code> class *
     * @param song The song to be added to the playlist.
     * @return None
     */

    public void addSong(Song song) {
        Node<Song> newNode = new Node<Song>(song);
        playlist.insert(newNode, playlist.size + 1);
    }

    /**
     * The <code>removeSong</code> method removes a song from the playlist.
     * 
     * @param song The song to be removed from the playlist.
     * @return None
     */
    public void removeSong(Song song) {
        Node<Song> current = head;
        int count = 1;
        while (current != null) {
            if (current.data.equals(song)) {
                playlist.delete(count);
                break;
            }
            current = current.next;
            count++;
        }
    }

    /**
     * The <code>playNext</code> method plays the next song in the playlist.
     * 
     * @param None
     * @return None
     */
    public void playNext() {
        if (currentSong == null) {
            currentSong = playlist.head;
        } else if (currentSong.next != null) {
            currentSong = currentSong.next;
        }
        System.out.println("Now playing: " + currentSong.data.getTitle());
    }

    /**
     * The <code>playPrevious</code> method plays the previous song in the playlist.
     * 
     * @param None
     * @return None
     */
    public void playPrevious() {
        if (currentSong == null) {
            currentSong = playlist.tail;
        } else if (currentSong.prev != null) {
            currentSong = currentSong.prev;
        }
        System.out.println("Now playing: " + currentSong.data.getTitle());
    }

    /**
     * The <code>shuffle</code> method shuffles the playlist.
     * 
     * @param None
     * @return None
     */
    public void shuffle() {
        Random rand = new Random();
        for (int i = playlist.size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Node<Song> node1 = playlist.getNode(j);
            Node<Song> node2 = playlist.getNode(i);
            Song temp = node1.data;
            node1.data = node2.data;
            node2.data = temp;
        }
    }

    /**
     * The <code>play</code> method starts playing the music.
     * 
     * @param None
     * @return None
     */
    public void play() {
        if (playlist.head == null) {
            System.out.println("Playlist is empty.");
        } else {
            currentSong = playlist.head;
            System.out.println("Now playing: " + currentSong.data.getTitle());
        }
    }
}
