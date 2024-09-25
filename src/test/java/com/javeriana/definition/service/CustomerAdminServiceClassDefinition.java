package com.javeriana.definition.service;

import static com.javeriana.enums.ProjectStructure.CUSTOMER_ADMIN_SERVICE;

import com.javeriana.classDefinition.AttributeData;
import com.javeriana.classDefinition.ClassDefinitionTest;
import com.javeriana.classDefinition.DefinitionTester;
import com.javeriana.classDefinition.MethodData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CustomerAdminServiceClassDefinition extends ClassDefinitionTest {

    @Override
    protected String getClassName() {
        return CUSTOMER_ADMIN_SERVICE.getClassName();
    }

    @Override
    protected List<AttributeData> getExpectedAttributes() {
        return Arrays.asList(
            new AttributeData("customerRepository", "CustomerRepository", DefinitionTester.PRIVATE_MODIFIER)
        );
    }

    @Override
    protected List<Class[]> getConstructorParameterTypes() {
        return new ArrayList<>(Collections.singleton(new Class[]{}));
    }

    @Override
    protected List<MethodData> getExpectedMethods() {
        return List.of(
            new MethodData("getCustomers", "List<Customer>", DefinitionTester.PUBLIC_MODIFIER),
            new MethodData("addCustomer", "Customer", DefinitionTester.PUBLIC_MODIFIER,
                new Class[] {String.class, String.class, String.class, String.class, int.class}),
            new MethodData("removeCustomer", "boolean", DefinitionTester.PUBLIC_MODIFIER, new Class[] {UUID.class}),
            new MethodData("getCustomerById", "Customer", DefinitionTester.PUBLIC_MODIFIER, new Class[] {UUID.class}),
            new MethodData("getCustomersToString", "List<String>", DefinitionTester.PUBLIC_MODIFIER),
            new MethodData("removePlaylistOfCustomers", "boolean", DefinitionTester.PUBLIC_MODIFIER, new Class[] {UUID.class}),
            new MethodData("removeArtistOfCustomers", "boolean", DefinitionTester.PUBLIC_MODIFIER, new Class[] {UUID.class})
        );
    }
}
