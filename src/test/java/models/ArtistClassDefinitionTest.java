package models;

import helpers.AttributeData;
import helpers.ClassDefinitionHelper;
import helpers.ClassDefinitionTest;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ArtistClassDefinitionTest extends ClassDefinitionTest {

    @Override
    protected String getClassName() {
        return "com.javeriana.models.Artist";
    }

    @Override
    protected List<AttributeData> getExpectedAttributes() {
        return Arrays.asList(
            new AttributeData("id", "UUID", ClassDefinitionHelper.PRIVATE_MODIFIER),
            new AttributeData("name", "String", ClassDefinitionHelper.PRIVATE_MODIFIER)
        );
    }

    @Override
    protected List<Class[]> getConstructorParameterTypes() {
        return List.of(
            new Class[]{String.class},
            new Class[]{UUID.class, String.class});
    }
}