package com.javeriana.definition.model;

import static com.javeriana.enums.ProjectStructure.ARTIST;

import com.javeriana.classDefinition.AttributeData;
import com.javeriana.classDefinition.DefinitionTester;
import com.javeriana.classDefinition.ClassDefinitionTest;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ArtistClassDefinitionTest extends ClassDefinitionTest {

    @Override
    protected String getClassName() {
        return ARTIST.getClassName();
    }

    @Override
    protected List<AttributeData> getExpectedAttributes() {
        return Arrays.asList(
            new AttributeData("id", "UUID", DefinitionTester.PRIVATE_MODIFIER, false),
            new AttributeData("name", "String", DefinitionTester.PRIVATE_MODIFIER)
        );
    }

    @Override
    protected List<Class[]> getConstructorParameterTypes() {
        return List.of(
            new Class[]{String.class},
            new Class[]{UUID.class, String.class});
    }

}