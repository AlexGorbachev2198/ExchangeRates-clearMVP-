package com.bpc.modulesdk.modulity.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Masloed on 20.11.2015.
 * <p>
 * Annotation for module? that will be registered in application and used in it.
 */
@Inherited
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AppModule {

    /*String IMPLEMENTATION_STANDART = "STANDART";

    ModuleType moduleType() default ModuleType.OTHER;

    String moduleImplementation() default IMPLEMENTATION_STANDART;*/
    String moduleName();
}

