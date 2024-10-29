# StaroBotApi
 An API for making Telegram bots easily on java.

 This project is still quite raw and is only published due to me editing it quite rarely. Made it for myself to make personal bots more easily. 

 ### Implementation to gradle:
```
maven {
         url 'https://jitpack.io'
}

dependencies {
	        implementation 'com.github.starobot:StaroBotAPI:2.0-SNAPSHOT'
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
	    <version>2.0-SNAPSHOT</version>
	</dependency>
```

Current TODO list:
1. ~~ Rework the Inline handling withing commands
2. ~~ Make a factory for commands.
3. Speed up the event handling system
4. Provide a complete documentation for the API with an example of a bot.
