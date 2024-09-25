package com.javeriana.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Song {

    private UUID id;
    private String name;
    private String genre;
    private int durationInSeconds;
    private String album;

    private List<Artist> artists;

    public Song(String name, String genre, int durationInSeconds, String album, List<Artist> artists) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.genre = genre;
        this.durationInSeconds = durationInSeconds;
        this.album = album;

        this.artists = artists;
    }

    public Song(UUID id, String name, String genre, int durationInSeconds, String album, List<Artist> artists) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.durationInSeconds = durationInSeconds;
        this.album = album;

        this.artists = artists;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public boolean hasArtist(UUID artistId) {
        for (Artist artist : artists) {
            if (artist.getId().equals(artistId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        //toString format: Attribute1: value1 - Attribute2: value2 - ... - AttributeN: valueN
        return "Id: " + this.id
            + " - Name: " + this.name
            + " - Genre: " + this.genre
            + " - DurationInSeconds: " + this.durationInSeconds
            + " - Album: " + this.album;
    }
}
