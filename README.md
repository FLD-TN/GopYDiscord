# GopYDiscord

## Description
GopYDiscord is a custom Minecraft server plugin (Spigot/Paper API 1.21+) that allows players to send feedback and suggestions directly to a Discord channel from within the game. It bridges the gap between your Minecraft server and your community Discord server, making it easier for players to report issues or suggest ideas.

## Features
- **In-game Feedback:** Players can use a simple command to submit feedback.
- **Discord Integration:** Sends the feedback directly to a configured Discord webhook/channel.
- **Lightweight:** Simple, efficient, and easy to configure.

## Installation
1. Download the latest compiled .jar file from the repository or build it from source.
2. Place the .jar file into your Minecraft server's plugins folder.
3. Restart your server.
4. Ensure you configure your Discord Webhook URL in the generated config.yml.
5. Run /reload or restart the server to apply your webhook settings.

## Usage & Commands
### Commands
- /gopy <message> - Submits your message/feedback to the server's Discord channel.

### Permissions
- gopydiscord.use - Allows a user to use the /gopy command (typically granted to all players by default, or you can manage it via a permissions plugin like LuckPerms).

## Building from Source
If you want to build this plugin yourself:
1. Ensure you have Java (appropriate version for 1.21 API) and Maven installed.
2. Clone this repository.
3. Run mvn clean package in the project root directory.
4. The compiled jar will be located in the 	arget/ directory.
