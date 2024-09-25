package com.javeriana.enums;

import java.util.List;

public enum ProjectStructure {

    //Model
    ARTIST("com.javeriana.model.Artist"),
    CUSTOMER("com.javeriana.model.Customer"),
    PLAYLIST("com.javeriana.model.Playlist"),
    SONG("com.javeriana.model.Song"),

    //Service
    ARTIST_SERVICE("com.javeriana.service.ArtistService"),
    CUSTOMER_ADMIN_SERVICE("com.javeriana.service.CustomerAdminService"),
    PLAYLIST_SERVICE("com.javeriana.service.PlaylistService"),
    SONG_SERVICE("com.javeriana.service.SongService"),

    //Controller
    ADMIN_CONTROLLER("com.javeriana.controller.AdminController");

    private final String className;

    ProjectStructure(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public String getSimpleName() {
        return className.substring(className.lastIndexOf('.') + 1);
    }

    public Class<?> getClassDefinition() throws ClassNotFoundException {
        return Class.forName(className);
    }

    public static List<ProjectStructure> getServices() {

        var allValues = List.of(ProjectStructure.values());

        return allValues.stream()
            .filter(value -> value.name().contains("SERVICE"))
            .toList();
    }
}
