package com.javeriana.classDefinition;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefinitionTester {

    private static final String GET = "get";
    public static final String SET = "set";
    public static final String PUBLIC_MODIFIER = Modifier.toString(Modifier.PUBLIC);
    public static final String PRIVATE_MODIFIER = Modifier.toString(Modifier.PRIVATE);
    public static final Map.Entry<Integer, String> DEFAULT_ENTRY = Map.entry(0, "");

    Class<?> testClass;

    public static final DefinitionTester create(String className) {
        try {
            return new DefinitionTester(Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new AssertionError("Class " + className + " not found.");
        }
    }

    private DefinitionTester(Class<?> testClass) {
        this.testClass = testClass;
    }

    public Map<String, Map.Entry<Integer,String>> getters() {
        var declaredMethods = testClass.getDeclaredMethods();

        return Arrays.stream(declaredMethods)
            .filter(method -> method.getName().startsWith(GET))
            .collect(
                Collectors.toMap(
                    method -> method.getName(),
                    method -> Map.entry(method.getModifiers(), getReturnType(method))
                )
            );
    }

    public Map<String, Map.Entry<Integer,String>> setters() {
        var declaredMethods = testClass.getDeclaredMethods();

        return Arrays.stream(declaredMethods)
            .filter(method -> method.getName().startsWith(SET))
            .collect(
                Collectors.toMap(
                    method -> method.getName(),
                    method -> Map.entry(method.getModifiers(), getReturnType(method))
                )
            );
    }

    public Map<String, Map.Entry<Integer,String>> attributes() {
        var declaredFields = testClass.getDeclaredFields();

        return Arrays.stream(declaredFields)
            .collect(
                Collectors.toMap(
                    field -> field.getName(),
                    field -> Map.entry(field.getModifiers(), getAttributeType(field))
                )
            );
    }

    public boolean hasConstructor(Class<?>... parameterTypes) {
        try {
            testClass.getDeclaredConstructor(parameterTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public boolean hasConstructor(String ... parameterTypes) {

        var parameterTypesClasses = new ArrayList<Class<?>>();

        for (String parameterType : parameterTypes) {
            try {
                parameterTypesClasses.add(Class.forName(parameterType));
            } catch (ClassNotFoundException e) {
                throw new AssertionError("Class " + parameterType + " not found.");
            }
        }

        var parameterTypesArray = parameterTypesClasses.toArray(new Class<?>[0]);

        return hasConstructor(parameterTypesArray);
    }


    private String getGetterName(String attributeName) {
        return GET + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
    }

    public void testGetters(List<AttributeData> expectedAttributes) {

        var getters = getters();

        assertAll(
            expectedAttributes.stream()
                .flatMap(attribute -> Stream.of(
                 () -> assertTrue(getters.containsKey(getGetterName(attribute.name())), getGetterName(attribute.name()) + " method does not exist in " + testClass.getSimpleName() + " class."),
                 () -> assertEquals(PUBLIC_MODIFIER, Modifier.toString(getters.getOrDefault(getGetterName(attribute.name()), DEFAULT_ENTRY).getKey()), getGetterName(attribute.name()) + " method is not public in " + testClass.getSimpleName() + " class."),
                 () -> assertEquals(attribute.type(), getters.getOrDefault(getGetterName(attribute.name()), DEFAULT_ENTRY).getValue(), getGetterName(attribute.name()) + " method in " + testClass.getSimpleName() + " class is not of type " + attribute.type() + ".")))

        );
    }

    private String getSetterName(String attributeName) {
        return SET + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
    }

    public void testSetters(List<AttributeData> expectedAttributes) {

        var setters = setters();

        var attributesToValidate = expectedAttributes.stream()
            .filter(attribute -> attribute.validateSetter())
            .collect(Collectors.toList());


        assertAll(
            attributesToValidate.stream()
                .flatMap(attribute -> Stream.of(
                 () -> assertTrue(setters.containsKey(getSetterName(attribute.name())), getSetterName(attribute.name()) + " method does not exist in " + testClass.getSimpleName() + " class."),
                 () -> assertEquals(PUBLIC_MODIFIER, Modifier.toString(setters.getOrDefault(getSetterName(attribute.name()), DEFAULT_ENTRY).getKey()), getSetterName(attribute.name()) + " method is not public in " + testClass.getSimpleName() + " class."),
                 () -> assertEquals("void", setters.getOrDefault(getSetterName(attribute.name()), DEFAULT_ENTRY).getValue(), getSetterName(attribute.name()) + " method in " + testClass.getSimpleName() + " class is not void.")))

        );

        if (setters.size() > expectedAttributes.size()) {
            var extraSetters = setters.keySet().stream()
                .filter(setter -> !expectedAttributes.stream()
                    .map(attribute -> getSetterName(attribute.name()))
                    .anyMatch(attribute -> attribute.equals(setter)))
                .toList();
            throw new AssertionError(
                "Extra setters found in " + testClass.getSimpleName() + " class: " + extraSetters);
        }
    }

    public void testAttributes(List<AttributeData> expectedAttributes) {
        var attributes = attributes();

        assertAll(
            expectedAttributes.stream()
                .flatMap(attribute -> Stream.of(
                    () -> assertTrue(attributes.containsKey(attribute.name()),
                        attribute.name() + " attribute does not exist in " + testClass.getSimpleName() + " class."),
                    () -> assertEquals(attribute.modifier(),
                        Modifier.toString(attributes.getOrDefault(attribute.name(), DEFAULT_ENTRY).getKey()),
                        attribute.name() + " attribute in " + testClass.getSimpleName() + " class is not " +
                            attribute.modifier() + "."),
                    () -> assertEquals(attribute.type(),
                        attributes.getOrDefault(attribute.name(), DEFAULT_ENTRY).getValue(),
                        attribute.name() + " attribute in " + testClass.getSimpleName() + " class is not of type " +
                            attribute.type() + ".")))

        );
    }

    public void testConstructor(Class<?>... parameterTypes) {

        Supplier<List<String>> getParameterTypesSimpleNames = () -> Arrays.stream(parameterTypes)
            .map(Class::getSimpleName)
            .toList();


        assertTrue(hasConstructor(parameterTypes),
            "Constructor with " + getParameterTypesSimpleNames.get() + " parameters does not exist in " +
                testClass.getSimpleName() + " class.");

    }

    public void testMethodDefinition(MethodData methodData) {

        try {
            Method method = testClass.getMethod(methodData.name(), methodData.parameterTypes());

            String returnType = getReturnType(method);

            assertAll(
                () -> assertEquals(methodData.modifier(), Modifier.toString(method.getModifiers()),
                    methodData.name() + " method in " + testClass.getSimpleName() + " class is not " +
                        methodData.modifier() + "."),
                () -> assertEquals(methodData.returnType(), returnType,
                    methodData.name() + " method in " + testClass.getSimpleName() + " class does not return " +
                        methodData.returnType() + ".")
            );
        } catch (NoSuchMethodException e) {
            throw new AssertionError(
                methodData.name() + " method does not exist in " + testClass.getSimpleName() + " class.");
        }
    }

    private String getReturnType(Method method) {

        var returnType = method.getReturnType().getSimpleName();

        if (method.getGenericReturnType() instanceof ParameterizedType parameterizedType) {
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            String genericTypes =  "<" + Arrays.stream(typeArguments)
                .map(type -> getTypeSimpleName(type))
                .collect(Collectors.joining(", ")) + ">";
            return returnType + genericTypes;
        }

        return getTypeNameWithGenerics(method.getGenericReturnType(), returnType);

    }

    private String getAttributeType(Field field) {

        var fieldType = field.getType().getSimpleName();

        return getTypeNameWithGenerics(field.getGenericType(), fieldType);
    }

    private String getTypeNameWithGenerics(Type genericType, String typeName) {
        if (genericType instanceof ParameterizedType parameterizedType) {
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            String genericTypes = "<" + Arrays.stream(typeArguments)
                .map(this::getTypeSimpleName)
                .collect(Collectors.joining(", ")) + ">";
            return typeName + genericTypes;
        }
        return typeName;
    }

    private String getTypeSimpleName(Type type) {

        if (type instanceof Class<?> clazz) {
            return clazz.getSimpleName();
        }

        if (type instanceof ParameterizedType parameterizedType) {
            return parameterizedType.getRawType().getTypeName();
        }

        return type.getTypeName();
    }


    public void testToStringMethod() {
        var toStringMethod = new MethodData("toString", "String", PUBLIC_MODIFIER);

        try {
            String classDefiningToString = testClass.getMethod(toStringMethod.name()).getDeclaringClass().getName();
            assertEquals(testClass.getName(), classDefiningToString,
                "toString method is not being overridden in " + testClass.getSimpleName() + " class.");
        } catch (NoSuchMethodException e) {
            throw new AssertionError(
                "toString method is not being overridden in " + testClass.getSimpleName() + " class.");
        }
        testMethodDefinition(toStringMethod);
    }
}
