package com.javeriana.definition.service;

import static com.javeriana.enums.ProjectStructure.SONG;

import com.javeriana.classDefinition.AttributeData;
import com.javeriana.classDefinition.ClassDefinitionTest;
import com.javeriana.classDefinition.DefinitionTester;
import com.javeriana.classDefinition.MethodData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SongServiceClassDefinitionTest extends ClassDefinitionTest {

    @Override
    protected String getClassName() {
        return "com.javeriana.service.SongService";
    }

    @Override
    protected List<AttributeData> getExpectedAttributes() {
        return Arrays.asList(
            new AttributeData("songRepository", "SongRepository", DefinitionTester.PRIVATE_MODIFIER)
        );
    }

    @Override
    protected List<Class[]> getConstructorParameterTypes() {
        return new ArrayList<>(Collections.singleton(new Class[]{}));
    }

    @Override
    protected List<MethodData> getExpectedMethods() {
        var songSimpleName = SONG.getSimpleName();

        var listOfSongs = "List<" + songSimpleName + ">";

        return List.of(
            new MethodData("getSongs", listOfSongs, DefinitionTester.PUBLIC_MODIFIER),
            new MethodData("addSong", songSimpleName, DefinitionTester.PUBLIC_MODIFIER, new Class[]{String.class, String.class, int.class, String.class, List.class, String.class, List.class}),
            new MethodData("removeSong", "boolean", DefinitionTester.PUBLIC_MODIFIER, new Class[]{UUID.class}),
            new MethodData("getSongById", songSimpleName, DefinitionTester.PUBLIC_MODIFIER, new Class[]{UUID.class}),
            new MethodData("getSongsToString", "List<String>", DefinitionTester.PUBLIC_MODIFIER),
            new MethodData("removeSongsOfArtist", "boolean", DefinitionTester.PUBLIC_MODIFIER, new Class[]{UUID.class})
        );
    }
}
