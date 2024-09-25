package com.javeriana.definition.model;

import static com.javeriana.enums.ProjectStructure.CUSTOMER;

import com.javeriana.classDefinition.AttributeData;
import com.javeriana.classDefinition.DefinitionTester;
import com.javeriana.classDefinition.ClassDefinitionTest;
import com.javeriana.classDefinition.MethodData;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CustomerClassDefinitionTest extends ClassDefinitionTest {

    @Override
    protected String getClassName() {
        return CUSTOMER.getClassName();
    }

    @Override
    protected List<AttributeData> getExpectedAttributes() {
        return Arrays.asList(
            new AttributeData("id", "UUID", DefinitionTester.PRIVATE_MODIFIER, false),
            new AttributeData("username", "String", DefinitionTester.PRIVATE_MODIFIER),
            new AttributeData("password", "String", DefinitionTester.PRIVATE_MODIFIER),
            new AttributeData("name", "String", DefinitionTester.PRIVATE_MODIFIER),
            new AttributeData("lastName", "String", DefinitionTester.PRIVATE_MODIFIER),
            new AttributeData("age", "int", DefinitionTester.PRIVATE_MODIFIER),
            new AttributeData("followedArtists", "List<Artist>", DefinitionTester.PRIVATE_MODIFIER, false),
            new AttributeData("playlists", "List<Playlist>", DefinitionTester.PRIVATE_MODIFIER, false)
        );
    }

    @Override
    protected List<Class[]> getConstructorParameterTypes() {
        return List.of(new Class[]{String.class, String.class, String.class, String.class, int.class},
                       new Class[]{UUID.class, String.class, String.class, String.class, String.class, int.class, List.class, List.class});
    }

    @Override
    protected List<MethodData> getExpectedMethods() {
        return List.of();
    }
}