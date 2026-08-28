package com.example.gopydiscord;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class GopYDiscord extends JavaPlugin {
    private JDA jda;
    private String channelId;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Lưu config mặc định
        saveDefaultConfig();
        config = getConfig();

        // Khởi tạo bot Discord
        String token = config.getString("discord.token");
        channelId = config.getString("discord.channel-id");

        try {
            jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build();
            jda.awaitReady();
            getLogger().info("Kết nối Discord thành công!");
        } catch (Exception e) {
            getLogger().severe("Không thể kết nối với Discord: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    @Override
    public void onDisable() {
        if (jda != null) {
            jda.shutdown();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gopy")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Lệnh này chỉ có thể được sử dụng bởi người chơi!");
                return true;
            }

            Player player = (Player) sender;

            if (!player.hasPermission("gopydiscord.use")) {
                String noPermMsg = config.getString("messages.no-permission", "§cBạn không có quyền sử dụng lệnh này!");
                player.sendMessage(noPermMsg != null ? noPermMsg : "§cBạn không có quyền sử dụng lệnh này!");
                return true;
            }

            if (args.length == 0) {
                player.sendMessage("§cSử dụng: /gopy <nội dung góp ý>");
                return true;
            }

            // Ghép các argument thành một chuỗi góp ý
            StringBuilder feedback = new StringBuilder();
            for (String arg : args) {
                feedback.append(arg).append(" ");
            }

            // Gửi góp ý lên Discord
            try {
                TextChannel channel = jda.getTextChannelById(channelId);
                if (channel != null) {
                    // Tạo embed để hiển thị góp ý
                    net.dv8tion.jda.api.EmbedBuilder embed = new net.dv8tion.jda.api.EmbedBuilder()
                        .setColor(0x00FF00) // Màu xanh lá
                        .setTitle("📝 GÓP Ý MỚI TỪ NGƯỜI CHƠI")
                        .addField(" Người gửi", "**" + player.getName() + "**", true)
                        .addField(" Thời gian", "**" + new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()) + "**", true)
                        .addField(" Nội dung", "```\n" + feedback.toString().trim() + "\n```", false);
                    
                    channel.sendMessageEmbeds(embed.build()).queue();
                    String successMsg = config.getString("messages.success", "§aGóp ý của bạn đã được gửi thành công!");
                    player.sendMessage(successMsg != null ? successMsg : "§aGóp ý của bạn đã được gửi thành công!");
                } else {
                    String errorMsg = config.getString("messages.error", "§cCó lỗi xảy ra khi gửi góp ý!");
                    player.sendMessage(errorMsg != null ? errorMsg : "§cCó lỗi xảy ra khi gửi góp ý!");
                    getLogger().warning(() -> String.format("Không tìm thấy channel với ID: %s", channelId));
                }
            } catch (Exception e) {
                String errorMsg = config.getString("messages.error", "§cCó lỗi xảy ra khi gửi góp ý!");
                player.sendMessage(errorMsg != null ? errorMsg : "§cCó lỗi xảy ra khi gửi góp ý!");
                getLogger().severe(() -> String.format("Lỗi khi gửi tin nhắn lên Discord: %s", e.getMessage()));
            }

            return true;
        }

        return false;
    }
}