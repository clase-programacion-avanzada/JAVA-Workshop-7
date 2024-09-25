package com.javeriana.classDefinition;

public record AttributeData(
        String name,
        String type,
        String modifier,
        String value,
        String defaultValue,
        boolean validateSetter
) {
    public AttributeData(String name, String type, String modifier, String value) {
        this(name, type, modifier, value, "", true);
    }

    public AttributeData(String name, String type, String modifier) {
        this(name, type, modifier, "", "", true);
    }

    public AttributeData(String name, String type, String modifier, boolean validateSetter) {
        this(name, type, modifier, "", "", validateSetter);
    }

    public AttributeData(String name, String type) {
        this(name, type, "", "", "", true);
    }

    public AttributeData(String name) {
        this(name, "", "", "", "", true);
    }

}
