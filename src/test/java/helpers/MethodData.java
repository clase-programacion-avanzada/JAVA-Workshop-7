package helpers;

public record MethodData(
    String name,
    String returnType,
    String modifier,

    Class[] parameterTypes,
    String body
) {
    public MethodData(String name, String returnType, String modifier, Class[] parameterTypes) {
        this(name, returnType, modifier, parameterTypes, "");
    }

    public MethodData(String name, String returnType, String modifier) {
        this(name, returnType, modifier, new Class[0], "");
    }

    public MethodData(String name, String returnType) {
        this(name, returnType, "", new Class[0], "");
    }

    public MethodData(String name) {
        this(name, "void", "", new Class[0], "");
    }

}
