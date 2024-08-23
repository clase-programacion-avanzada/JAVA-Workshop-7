package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

public abstract class ClassDefinitionTest {

    protected abstract String getClassName();
    protected abstract List<AttributeData> getExpectedAttributes();
    protected abstract List<Class[]> getConstructorParameterTypes();

    @Test
    @Order(1)
    @DisplayName("Class is defined in the models package")
    void modelsPackageContainsClass() {
        try {
            Class.forName(getClassName());
        } catch (ClassNotFoundException e) {
            throw new AssertionError("The models package should contain a class named " + getClassName());
        }
    }

    @Test
    @Order(2)
    @DisplayName("Class is defined with defined constructors")
    void classHasConstructorThatInitializesAllAttributesButId() {
        try {
            Class<?> classFromName = Class.forName(getClassName());
            ClassDefinitionHelper definitionHelper = new ClassDefinitionHelper(classFromName);
            List<Class[]> constructorParameterTypes = getConstructorParameterTypes();
            constructorParameterTypes.forEach(parameters -> {
                definitionHelper.testConstructor(parameters);
            });

        } catch (ClassNotFoundException e) {
            throw new AssertionError("The models package should contain a class named " + getClassName());
        }
    }

    @Test
    @Order(3)
    @DisplayName("Class is defined with correct attributes")
    void classHasCorrectAttributes() {
        try {
            Class<?> classFromName = Class.forName(getClassName());
            ClassDefinitionHelper definitionHelper = new ClassDefinitionHelper(classFromName);
            definitionHelper.testAttributes(getExpectedAttributes());
        } catch (ClassNotFoundException e) {
            throw new AssertionError("The models package should contain a class named " + getClassName());
        }
    }

    @Test
    @Order(4)
    @DisplayName("Class has getters for defined attributes")
    void classHasGettersForAllAttributes() {
        try {
            Class<?> classFromName = Class.forName(getClassName());
            ClassDefinitionHelper definitionHelper = new ClassDefinitionHelper(classFromName);
            definitionHelper.testGetters(getExpectedAttributes());
        } catch (ClassNotFoundException e) {
            throw new AssertionError("The models package should contain a class named " + getClassName());
        }
    }

    @Test
    @Order(5)
    @DisplayName("Class has setters for defined attributes")
    void classHasSettersForAllAttributes() {
        try {
            Class<?> classFromName = Class.forName(getClassName());
            ClassDefinitionHelper definitionHelper = new ClassDefinitionHelper(classFromName);
            definitionHelper.testSetters(getExpectedAttributes().subList(1, getExpectedAttributes().size()));
        } catch (ClassNotFoundException e) {
            throw new AssertionError("The models package should contain a class named " + getClassName());
        }
    }

    @Test
    @Order(6)
    @DisplayName("Class has toString method")
    void classHasToStringMethod() {
        try {
            Class<?> classFromName = Class.forName(getClassName());
            ClassDefinitionHelper definitionHelper = new ClassDefinitionHelper(classFromName);
            definitionHelper.testToStringMethod();
        } catch (ClassNotFoundException e) {
            throw new AssertionError("The models package should contain a class named " + getClassName());
        }
    }
}