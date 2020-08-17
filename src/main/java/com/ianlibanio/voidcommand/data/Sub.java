package com.ianlibanio.voidcommand.data;

import com.ianlibanio.voidcommand.annotation.subcommand.SubCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
@AllArgsConstructor
public class Sub {

    private final SubCommand subCommand;
    private final Method method;

}
