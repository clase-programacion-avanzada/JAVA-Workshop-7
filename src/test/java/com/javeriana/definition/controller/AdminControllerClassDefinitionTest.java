package com.javeriana.definition.controller;

import static com.javeriana.enums.ProjectStructure.ADMIN_CONTROLLER;
import static com.javeriana.enums.ProjectStructure.getServices;

import com.javeriana.classDefinition.AttributeData;
import com.javeriana.classDefinition.ClassDefinitionTest;
import com.javeriana.classDefinition.DefinitionTester;
import com.javeriana.classDefinition.MethodData;
import com.javeriana.enums.ProjectStructure;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdminControllerClassDefinitionTest extends ClassDefinitionTest {

    @Override
    protected String getClassName() {
        return ADMIN_CONTROLLER.getClassName();
    }

    @Override
    protected List<AttributeData> getExpectedAttributes() {
        return Arrays.asList(
            new AttributeData("artistService", "ArtistService", DefinitionTester.PRIVATE_MODIFIER, false),
            new AttributeData("customerAdminService", "CustomerAdminService", DefinitionTester.PRIVATE_MODIFIER, false),
            new AttributeData("playlistService", "PlaylistService", DefinitionTester.PRIVATE_MODIFIER, false),
            new AttributeData("songService", "SongService", DefinitionTester.PRIVATE_MODIFIER, false)
        );
    }

    @Override
    protected List<Class[]> getConstructorParameterTypes() {

        var servicesClasses = getServicesClasses();


        return new ArrayList<>(Collections.singleton(servicesClasses));
    }

    private Class<?>[] getServicesClasses() {

        var services = getServices();
        var serviceClasses = new ArrayList<Class<?>> ();
        for (ProjectStructure projectStructure : services) {
            try {
                Class<?> serviceClass = projectStructure.getClassDefinition();
                serviceClasses.add(serviceClass);
            } catch (ClassNotFoundException e) {
                throw new AssertionError("");
            }
        }
        
        return serviceClasses.toArray(new Class<?>[0]);
    }

    @Override
    protected List<MethodData> getExpectedMethods() {
        return List.of();

    }

}
