# StaroBotApi
 An API for making Telegram bots easily on java.

[![CodeFactor](https://www.codefactor.io/repository/github/starobot/starobotapi/badge)](https://www.codefactor.io/repository/github/starobot/starobotapi)
[![](https://tokei.rs/b1/github/starobot/starobotapi)](https://github.com/starobot/starobotapi)

 This project is still quite raw and is only published due to me editing it quite rarely. Made it for myself to make personal bots more easily. 

 ### Implementation to gradle:
```
maven {
         url 'https://jitpack.io'
}

dependencies {
	        implementation 'com.github.starobot:StaroBotAPI:2.0-beta'
}
```
### implementation to maven:
```
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

<dependency>
	    <groupId>com.github.starobot</groupId>
	    <artifactId>StaroBotAPI</artifactId>
	    <version>2.0-beta</version>
	</dependency>
```

Current TODO list:
1. <del> Rework the Inline handling withing commands
2. <del> Make a factory for commands.
3. <del> Speed up the event handling system
4. Provide a complete documentation for the API with an example of a bot.
5. <del> Fix permissions for commands
6. <del> Investigate event system
