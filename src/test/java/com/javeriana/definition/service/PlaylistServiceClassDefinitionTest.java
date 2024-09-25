package com.javeriana.definition.service;

import static com.javeriana.enums.ProjectStructure.PLAYLIST_SERVICE;

import com.javeriana.classDefinition.AttributeData;
import com.javeriana.classDefinition.ClassDefinitionTest;
import com.javeriana.classDefinition.DefinitionTester;
import com.javeriana.classDefinition.MethodData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PlaylistServiceClassDefinitionTest extends ClassDefinitionTest {

    @Override
    protected String getClassName() {
        return PLAYLIST_SERVICE.getClassName();
    }

    @Override
    protected List<AttributeData> getExpectedAttributes() {
        return Arrays.asList(
            new AttributeData("playlistRepository", "PlaylistRepository", DefinitionTester.PRIVATE_MODIFIER)
        );
    }

    @Override
    protected List<Class[]> getConstructorParameterTypes() {
        return new ArrayList<>(Collections.singleton(new Class[]{}));
    }

    @Override
    protected List<MethodData> getExpectedMethods() {
        return List.of(
            new MethodData("getPlaylists", "List<Playlist>", DefinitionTester.PUBLIC_MODIFIER),
            new MethodData("addPlaylist", "Playlist", DefinitionTester.PUBLIC_MODIFIER, new Class[]{String.class}),
            new MethodData("removePlaylist", "boolean", DefinitionTester.PUBLIC_MODIFIER, new Class[]{UUID.class}),
            new MethodData("getPlaylistById", "Playlist", DefinitionTester.PUBLIC_MODIFIER, new Class[]{UUID.class}),
            new MethodData("getPlaylistsToString", "List<String>", DefinitionTester.PUBLIC_MODIFIER)
        );
    }
}
