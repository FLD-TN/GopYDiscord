# GopYDiscord

## Description
GopYDiscord is a custom Minecraft server plugin (Spigot/Paper API 1.21+) that allows players to send feedback and suggestions directly to a Discord channel from within the game. It bridges the gap between your Minecraft server and your community Discord server, making it easier for players to report issues or suggest ideas.

## Features
- **In-game Feedback:** Players can use a simple command to submit feedback.
- **Discord Bot Integration:** Sends the feedback directly to your Discord server via a Bot.
- **Lightweight:** Simple, efficient, and easy to configure.

## Installation & Setup
To get this plugin working, you need to set up a Discord Bot and configure the plugin.

### 1. Discord Bot Setup
1. Go to the [Discord Developer Portal](https://discord.com/developers/applications).
2. Click **New Application** and give it a name (e.g., "Server Feedback Bot").
3. Navigate to the **Bot** tab on the left menu.
4. Click **Reset Token** and copy your **Bot Token**. Keep this token secret!
5. Go to the **OAuth2 > URL Generator** tab. Select the ot scope and give it Send Messages permissions.
6. Copy the generated URL, paste it into your browser, and invite the bot to your Discord server.
7. In your Discord app, enable **Developer Mode** (User Settings > Advanced > Developer Mode). Right-click on the channel where you want the bot to send feedback and click **Copy Channel ID**.

### 2. Plugin Installation
1. Download the compiled GopYDiscord-1.0-SNAPSHOT.jar file or build it from source.
2. Place the .jar file into your Minecraft server's plugins folder.
3. Start or restart your server to generate the configuration folder.
4. Open the plugins/GopYDiscord/config.yml file.
5. Paste your Bot Token and Channel ID into the configuration:
   `yaml
   discord:
     token: "YOUR_BOT_TOKEN_HERE"
     channel-id: "YOUR_CHANNEL_ID_HERE"
   `
6. You can also customize the messages in this file!
7. Run /reload confirm or restart your server to apply the changes.

## Usage & Commands
### Commands
- /gopy <message> - Submits your message/feedback to the configured Discord channel.

### Permissions
- gopydiscord.use - Allows a user to use the /gopy command (you can manage it via a permissions plugin like LuckPerms).

## Building from Source
If you want to build this plugin yourself:
1. Ensure you have Java (appropriate version for 1.21 API) and Maven installed.
2. Clone this repository.
3. Run mvn clean package in the project root directory.
4. The compiled jar will be located in the 	arget/ directory.
