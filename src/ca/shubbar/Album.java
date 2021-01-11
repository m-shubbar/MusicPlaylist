package ca.shubbar;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Mustafa Shubbar <codingbox@outlook.com>
 */
public class Album {

    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
    }

    public boolean addSong(String title, double duration) {
        if (findSong(title) == null) {
            this.songs.add(new Song(title, duration));
            return true;
        }
        return false;
    }

    private Song findSong(String title) {
        for (Song s : this.songs) {
            if (s.getTitle().equals(title)) {
                return s;
            }
        }
        return null;
    }
    
    public boolean addToPlaylist(int trackNumber, LinkedList<Song> playlist) {
        int index = trackNumber - 1;
        if((index >= 0) && (index <= playlist.size())) {
            playlist.add(this.songs.get(index));
            return true;          
        } else {
            System.out.println("This album does not have a track " + trackNumber);
            return false;
        }
    }
    
    public boolean addToPlaylist(String title, LinkedList<Song> playlist) {
        Song song = findSong(title);
        if(song != null) {
            playlist.add(song);
            return true;
        }
        System.out.println("Song: " + title + " is not in this album");
        return false;
    }

}
