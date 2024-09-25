package com.javeriana.definition.service;

import static com.javeriana.enums.ProjectStructure.ARTIST;
import static com.javeriana.enums.ProjectStructure.ARTIST_SERVICE;

import com.javeriana.classDefinition.AttributeData;
import com.javeriana.classDefinition.ClassDefinitionTest;
import com.javeriana.classDefinition.DefinitionTester;
import com.javeriana.classDefinition.MethodData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ArtistServiceClassDefinitionTest extends ClassDefinitionTest {


    @Override
    protected String getClassName() {
        return ARTIST_SERVICE.getClassName();
    }

    @Override
    protected List<AttributeData> getExpectedAttributes() {
        return Arrays.asList(
            new AttributeData("artists", "List", DefinitionTester.PRIVATE_MODIFIER, false)
        );
    }

    @Override
    protected List<Class[]> getConstructorParameterTypes() {
        return  new ArrayList<>(Collections.singleton(new Class[]{}));
    }



    @Override
    protected List<MethodData> getExpectedMethods() {
        var artistSimpleName = ARTIST.getSimpleName();
        var listOfArtist = "List<" + artistSimpleName + ">";

        return List.of(
            new MethodData("getArtists", listOfArtist, DefinitionTester.PUBLIC_MODIFIER),
            new MethodData("addArtist", artistSimpleName, DefinitionTester.PUBLIC_MODIFIER, new Class[]{String.class}),
            new MethodData("getArtistById", "String", DefinitionTester.PUBLIC_MODIFIER, new Class[]{UUID.class}),
            new MethodData("removeArtist", artistSimpleName, DefinitionTester.PUBLIC_MODIFIER, new Class[]{UUID.class}),
            new MethodData("getArtistsToString", "List<String>", DefinitionTester.PUBLIC_MODIFIER, new Class[]{})
        );
    }

    @Override
    protected void classHasToStringMethod() {
        assert true;
    }

}
