package com.javeriana.classDefinition;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public abstract class ClassDefinitionTest {

    protected abstract String getClassName();
    protected abstract List<AttributeData> getExpectedAttributes();
    protected abstract List<Class[]> getConstructorParameterTypes();
    protected List<MethodData> getExpectedMethods() {
        return List.of();
    }

    private DefinitionTester singletonDefinitionHelper;

    private DefinitionTester getDefinitionHelper(String className) {
        if (className == null || className.isBlank()) {
            throw new AssertionError("Provide a ClassName to test");
        }

        if (singletonDefinitionHelper == null) {
            singletonDefinitionHelper = DefinitionTester.create(className);
        }

        return singletonDefinitionHelper;
    }

    @Test
    @Order(1)
    @DisplayName("Class is defined in the models package")
    void modelsPackageContainsClass() {
        getDefinitionHelper(getClassName());
    }

    @Test
    @Order(2)
    @DisplayName("Class is defined with defined constructors")
    void classHasConstructorThatInitializesAllAttributesButId() {

        var definitionHelper = getDefinitionHelper(getClassName());

        List<Class[]> constructorParameterTypes = getConstructorParameterTypes();
        constructorParameterTypes.forEach(parameters -> {
            definitionHelper.testConstructor(parameters);
        });

    }

    @Test
    @Order(3)
    @DisplayName("Class is defined with correct attributes")
    void classHasCorrectAttributes() {
        var definitionHelper = getDefinitionHelper(getClassName());

        definitionHelper.testAttributes(getExpectedAttributes());

    }

    @Test
    @Order(4)
    @DisplayName("Class has getters for defined attributes")
    void classHasGettersForAllAttributes() {
        var definitionHelper = getDefinitionHelper(getClassName());

        definitionHelper.testGetters(getExpectedAttributes());

    }

    @Test
    @Order(5)
    @DisplayName("Class has setters for defined attributes")
    void classHasSettersForAllAttributes() {
        var definitionHelper = getDefinitionHelper(getClassName());

        definitionHelper.testSetters(getExpectedAttributes());

    }

    @Test
    @Order(6)
    @DisplayName("Class has toString method")
    protected void classHasToStringMethod() {

        var definitionHelper = getDefinitionHelper(getClassName());
        definitionHelper.testToStringMethod();

    }

    @Test
    @Order(7)
    @DisplayName("Class has methods defined in the class")
    protected void classHasMethodsDefinedInClass() {

        var definitionHelper = getDefinitionHelper(getClassName());
        Assertions.assertAll(
            getExpectedMethods().stream()
                .map(method -> () -> definitionHelper.testMethodDefinition(method))
        );

    }
}