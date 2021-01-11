package ca.shubbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 *
 * @author Mustafa Shubbar <codingbox@outlook.com>
 */
public class MusicMain {

    private static ArrayList<Album> albums = new ArrayList<Album>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Album album = new Album("Album1", "Artist1");
        album.addSong("a1Song1", 2.5);
        album.addSong("a1Song2", 4.67);
        album.addSong("a1Song3", 3.56);
        album.addSong("a1Song4", 2.34);
        album.addSong("a1Song5", 7.3);
        album.addSong("a1Song6", 3.56);
        album.addSong("a1Song7", 8.5);
        album.addSong("a1Song8", 2.0);

        albums.add(album);

        album = new Album("Album2", "Artist2");
        album.addSong("a2Song1", 2.5);
        album.addSong("a2Song2", 4.67);
        album.addSong("a2Song3", 3.56);
        album.addSong("a2Song4", 2.34);
        album.addSong("a2Song5", 7.3);
        album.addSong("a2Song6", 3.56);
        album.addSong("a2Song7", 8.5);
        album.addSong("a2Song8", 2.0);

        albums.add(album);

        LinkedList<Song> playlist = new LinkedList<Song>();

        albums.get(0).addToPlaylist("a1Song4", playlist);
        albums.get(0).addToPlaylist("a1Song2", playlist);
        albums.get(0).addToPlaylist("a1Song7", playlist);
        albums.get(0).addToPlaylist("a1Song1", playlist);

        albums.get(0).addToPlaylist("a1Song9", playlist); // Not exists

        albums.get(0).addToPlaylist(7, playlist);
        albums.get(1).addToPlaylist(5, playlist);
        albums.get(1).addToPlaylist(3, playlist);
        albums.get(1).addToPlaylist(1, playlist);

        albums.get(1).addToPlaylist(55, playlist); // Not exists

        play(playlist);

    }

    private static void play(LinkedList<Song> playlist) {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator i = playlist.listIterator();
        if (playlist.size() == 0) {
            System.out.println("Playlist empty.");
            return;
        } else {
            System.out.println("Now playing " + i.next().toString());
            printMenu();
        }

        while (!quit) {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    System.out.println("Quitting...");
                    quit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (i.hasNext()) {
                            i.next();
                        }
                        forward = true;
                    }

                    if (i.hasNext()) {
                        System.out.println("Now playing: " + i.next().toString());
                    } else {
                        System.out.println("End of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (i.hasPrevious()) {
                            i.previous();
                        }
                        forward = false;
                    }

                    if (i.hasPrevious()) {
                        System.out.println("Playing: " + i.previous().toString());
                    } else {
                        System.out.println("Start of the playlist.");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (i.hasPrevious()) {
                            System.out.println("Now playing: "
                                    + i.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("Start of the list.");
                        }
                    } else {
                        if (i.hasNext()) {
                            System.out.println("Now playing: "
                                    + i.next().toString());
                            forward = true;
                        } else {
                            System.out.println("End of the list.");
                        }
                    }
                    break;
                case 4:
                    printList(playlist);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if (playlist.size() > 0) {
                        i.remove();
                        // better to try catch, but we will try to prevent
                        // expected error by calling next or prev.
                        if (i.hasNext()) {
                            if (i.hasNext()) {
                                System.out.println("Playing: " + i.next());
                            } else if (i.hasPrevious()) {
                                System.out.println("Playing: " + i.previous());
                            }
                        }
                    }
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Options:\n"
                + "1- Play nex song\n"
                + "2- Play previous song\n"
                + "3- Replay current song\n"
                + "4- List songs in the playlist\n"
                + "5- Print available options\n"
                + "6- Remove current song from playlist");
    }

    private static void printList(LinkedList<Song> playlist) {
        Iterator<Song> i = playlist.iterator();
        System.out.println("################");
        while (i.hasNext()) {
            System.out.println(i.next());
        }
        System.out.println("################");

    }

}
