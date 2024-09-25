package com.javeriana.service;

import com.javeriana.model.Artist;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ArtistService {

    private List<Artist> artists;

    public ArtistService() {
        this.artists = new ArrayList<>();
    }

    public List<Artist> getArtists() {
        return new ArrayList<>(this.artists);
    }

    public Artist addArtist(String name) {
        Artist artist = new Artist(name);
        this.artists.add(artist);
        return artist;

    }

    private Artist getArtistById(UUID id) {

        for (Artist artist : this.artists) {
            if (artist.getId().equals(id)) {
                return artist;
            }
        }

        return null;
    }

    public boolean removeArtist(UUID id) {

        Artist artist = this.getArtistById(id);

        if (artist != null) {
            return false;
        }

        return this.artists.remove(artist);

    }

    public List<String> getArtistsToString() {
        return this.artists.stream()
            .map(Artist::toString)
            .collect(Collectors.toList());
    }

    public List<Artist> getArtistsByIds(List<UUID> artists) {
        List<Artist> artistsList = new ArrayList<>();
        for (UUID artistId : artists) {
            Artist artist = this.getArtistById(artistId);
            if (artist != null) {
                artistsList.add(artist);
            }
        }
        return artistsList;
    }
}
