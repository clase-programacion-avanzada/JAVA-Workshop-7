package com.javeriana.definition.model;

import static com.javeriana.enums.ProjectStructure.PLAYLIST;

import com.javeriana.classDefinition.AttributeData;
import com.javeriana.classDefinition.DefinitionTester;
import com.javeriana.classDefinition.ClassDefinitionTest;
import com.javeriana.classDefinition.MethodData;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PlayListClassDefinitionTest extends ClassDefinitionTest {

    @Override
    protected String getClassName() {
        return PLAYLIST.getClassName();
    }

    @Override
    protected List<AttributeData> getExpectedAttributes() {
        return Arrays.asList(
            new AttributeData("id", "UUID", DefinitionTester.PRIVATE_MODIFIER, false),
            new AttributeData("name", "String", DefinitionTester.PRIVATE_MODIFIER),
            new AttributeData("songs", "List<Song>", DefinitionTester.PRIVATE_MODIFIER, false)
        );
    }

    @Override
    protected List<Class[]> getConstructorParameterTypes() {
        return List.of(
            new Class[]{String.class},
            new Class[]{UUID.class, String.class, List.class});
    }

    @Override
    protected List<MethodData> getExpectedMethods() {
        return List.of();
    }
}
