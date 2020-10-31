package com.ianlibanio.voidcommand.annotation.command;

import com.ianlibanio.voidcommand.settings.Executor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    String name();
    String description() default "";
    String usage() default "";
    String permission() default "";
    String[] invalid() default {"You used an invalid sub command."};

    Executor executor() default Executor.BOTH;

}
