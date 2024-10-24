import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaylistManagementSystem {

    class Song {
        private String title;

        public Song(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    class Playlist {
        private List<Song> songs = new ArrayList<>();

        public void addSong(Song song) {
            songs.add(song);
        }

        public Iterator getIterator(String type) {
            switch (type) {
                case "SEQUENTIAL":
                    return new SequentialIterator();
                case "SHUFFLE":
                    return new ShuffleIterator();
                case "GENRE_FILTER":
                    return new GenreFilterIterator("Rock"); // Example filter
                default:
                    return null;
            }
        }

        private class SequentialIterator implements Iterator {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < songs.size();
            }

            @Override
            public Song next() {
                return songs.get(index++);
            }
        }

        private class ShuffleIterator implements Iterator {
            private List<Song> shuffledSongs;
            private int index = 0;

            public ShuffleIterator() {
                shuffledSongs = new ArrayList<>(songs);
                Random rand = new Random();
                for (int i = 0; i < shuffledSongs.size(); i++) {
                    int randomIndexToSwap = rand.nextInt(shuffledSongs.size());
                    Song temp = shuffledSongs.get(i);
                    shuffledSongs.set(i, shuffledSongs.get(randomIndexToSwap));
                    shuffledSongs.set(randomIndexToSwap, temp);
                }
            }

            @Override
            public boolean hasNext() {
                return index < shuffledSongs.size();
            }

            @Override
            public Song next() {
                return shuffledSongs.get(index++);
            }
        }

        private class GenreFilterIterator implements Iterator {
            private int index = 0;
            private List<Song> filteredSongs = new ArrayList<>();

            public GenreFilterIterator(String genre) {
                // In a real scenario, you would filter based on the genre attribute of songs
                // Here we just simulate with an example where all songs are "Rock" genre
                for (Song song : songs) {
                    filteredSongs.add(song);
                }
            }

            @Override
            public boolean hasNext() {
                return index < filteredSongs.size();
            }

            @Override
            public Song next() {
                return filteredSongs.get(index++);
            }
        }
    }

    interface Iterator {
        boolean hasNext();
        Song next();
    }

    public static void main(String[] args) {
        PlaylistManagementSystem system = new PlaylistManagementSystem();
        Playlist playlist = system.new Playlist();

        playlist.addSong(system.new Song("Song 1"));
        playlist.addSong(system.new Song("Song 2"));
        playlist.addSong(system.new Song("Song 3"));
        playlist.addSong(system.new Song("Song 4"));

        System.out.println("Sequential Playlist:");
        Iterator sequentialIterator = playlist.getIterator("SEQUENTIAL");
        while (sequentialIterator.hasNext()) {
            System.out.println(sequentialIterator.next().getTitle());
        }

        System.out.println("\nShuffled Playlist:");
        Iterator shuffleIterator = playlist.getIterator("SHUFFLE");
        while (shuffleIterator.hasNext()) {
            System.out.println(shuffleIterator.next().getTitle());
        }

        System.out.println("\nGenre Filtered Playlist:");
        Iterator genreFilterIterator = playlist.getIterator("GENRE_FILTER");
        while (genreFilterIterator.hasNext()) {
            System.out.println(genreFilterIterator.next().getTitle());
        }
    }
}
