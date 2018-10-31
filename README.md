# TelegramBot
JTelegramBot is a Java library with a Telegram Bot API.
Weather data provided by OpenWeatherMap.
 
Dependencies
	•	Java 8
	•	commons-logging
	•	jackson-core
	•	jackson-databind
	•	jackson-annotations
	•	com.mashape.unirest
	•	log4j
  
How to use Telegram Bot?
The name of the bot @WeatherFindBot. You can find it in Telegram. Just write the command /start and follow instructions.

How Telegram Bot works?
Telegram apps will have interface shortcuts for these commands.
		/start - begins interaction with the user, e.g., by sending a greeting message. This command can also be used to pass additional parameters to the bot (see Deep linking)
		/help - returns a help message. It can be a short text about what your bot can do and a list of commands.
		/settings - (if applicable) returns the bot's settings for this user and suggests commands to edit these settings.
For more see https://core.telegram.org/bots/api

Implementation
	1	Firstly talk to BotFather and follow a few simple steps. Once you've created a bot and received your authorization token, head down to the Bot API manual to see what you can teach your bot to do.
	2	Start writing Java code by using long polling mechanism with the help of getUpdates method. Use Spring Boot Task Scheduling to implement polling. 
	3	After that, install the project with Maven.
  
Exceptions Handling
Most of the methods in this library throws 2 types of exceptions:
	•	IOException: if an I/O exception occurs.
	•	NegativeResponseException: if 4xx-5xx HTTP response is received from Telegram server.
  
Copyright and Licensing Information
This project is licensed.
