# Void Command

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4c13a76aaeff4852ac651eddd0b56dfb)](https://app.codacy.com/manual/ianlibanio/VoidCommand?utm_source=github.com&utm_medium=referral&utm_content=ianlibanio/VoidCommand&utm_campaign=Badge_Grade_Dashboard)

> Void Command is a basic Spigot API used for easily creating commands and sub commands.

![Codacy coverage](https://img.shields.io/codacy/coverage/4c13a76aaeff4852ac651eddd0b56dfb?color=blue&label=CODE%20REVIEW&style=for-the-badge)

Void Command is an API using Spigot, for Minecraft that facilitates the creation and registration of new commands and sub commands. You can easily add it to your project, and follow the guide to create new commands.

## Installation

You can download the .jar into the [releases page](http://github.com/ianlibanio/VoidCommand/releases/), or use JitPack.

### Gradle

Add it at your root `build.gradle` at the end of repositories:
```gradle

repositories {
	...
	maven { url 'https://jitpack.io' }
}

```
Add the `dependency`:
```gradle
	dependencies {
		implementation 'com.github.ianlibanio:VoidCommand:0.1'
	}
```

### Maven
Add the JitPack repository to your `build` file:
```xml
<repositories>
	<repository>
		 <id>jitpack.io</id>
		 <url>https://jitpack.io</url>
	</repository>
</repositories>
```
Add the `dependency`:
```xml
<dependency>
	<groupId>com.github.User</groupId>
	<artifactId>Repo</artifactId>
	version>Tag</version>
</dependency>
```

Other options available at: <https://jitpack.io/#ianlibanio/VoidCommand>.

## Usage example

### Commands
Firstly, to create a command you must create an class and extend VoidCommand:

```java
public class Example extends VoidCommand {}
```

Then implement the command() method and annotate it with @Command and @Aliases (optional):

```java
public class TestCommand extends VoidCommand {

    @Command(name = "test", executor = Executor.PLAYER_ONLY, permission = "test.permission", invalid = "Invalid Sub Command!")
    @Aliases({"test1", "test2"})
    public void command(Context context) {
        context.message(ChatColor.RED + "Test!");
    }
}
```

Into @Command you can use the following arguments:
```java
name ('required') ["Your command name, any String is valid"]
executor ('optional', default: "Executor.BOTH") ["Who can use this command, values: PLAYER_ONLY, CONSOLE_ONLY, BOTH"]
permission ('optional', default: "") ["Permission required to execute this command, any String is valid"]
invalid ('optional', default: "You used an invalid sub command.") ["Which message will appear if the SubCommand is not valid"]
```

Into @Aliases you can use the following arguments:
```java
value ('required') ["Which aliases should your command have"]
```
Remember that this annotation is optional.

### Sub Commands

To create Sub Commands, you have to just create a void method with Context as the first param:
```java
@SubCommand(name = "hello", permission = "test.hello.permission")
public void help(Context context) {
    context.message(ChatColor.RED + "Hello!");
}
```

Into @SubCommand you can use the following arguments:
```java
name ('required') ["Your subcommand name, any String is valid. For multiple subcommands, like /test first second, you must separate with dots, in this case it would be first.second"]
permission ('optional', default: "") ["Permission required to execute this subcommand, any String is valid"]
```

Remember that you can use multiple SubCommands with the same start:

```java
@SubCommand(name = "use", permission = "test.use")
public void use(Context context) {
      context.message(ChatColor.GREEN + "You used a valid sub command!");
}

@SubCommand(name = "use.secret", permission = "test.use.secret")
public void useSecret(Context context) {
     context.message(ChatColor.YELLOW + "You found a secret!");
}
```

### Registration

To register commands is pretty simple, just add this to your plugin enable process:
```java
VoidRegister register = new VoidRegister(this); // "this" is the instance of your Main class.

register.add(new TestCommand(), new HelpCommand()); // Here you can register how many commands you want.
```

## Meta

Ian Libânio - Discord: Null#1001

Distributed under the MIT license. See ``LICENSE`` for more information.

[https://github.com/ianlibanio/VoidCommand](https://github.com/ianlibanio/)

## Contributing

 1. Fork it <https://github.com/ianlibanio/VoidCommand/fork>
 2. Create your feature branch (`git checkout -b feature/fooBar`)
 3. Commit your changes (`git commit -am 'Add some fooBar'`)
 4. Push to the branch (`git push origin feature/fooBar`)
 5. Create a new Pull Request
