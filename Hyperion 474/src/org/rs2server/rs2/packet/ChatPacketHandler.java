package org.rs2server.rs2.packet;

import java.io.IOException;
import java.io.File;
import java.util.List;

import org.rs2server.rs2.model.ChatMessage;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.net.Packet;
import org.rs2server.rs2.util.TextUtils;
import org.rs2server.rs2.util.NameUtils;
import org.rs2server.util.XMLController;

/**
 * Handles public chat messages.
 * @author Graham Edgecombe
 *
 */
public class ChatPacketHandler implements PacketHandler {

	private static final int CHAT_QUEUE_SIZE = 4;
	
	@Override
	public void handle(Player player, Packet packet) {
		int effects = packet.getByte() & 0xFF;
		int colour = packet.getByte() & 0xFF;
		int size = packet.getLength() - 2;
		byte[] chatData = new byte[size];
		packet.get(chatData);
		if(player.getChatMessageQueue().size() >= CHAT_QUEUE_SIZE) {
			return;
		}
		String unpacked = TextUtils.textUnpack(chatData, size);
		byte[] packed = new byte[size];
		
		try {
		List<String> mutedUsers = XMLController.readXML(new File("data/mutedUsers.xml"));
		for(String lol : mutedUsers) {
			if(lol.equalsIgnoreCase(NameUtils.formatName(player.getName()))) {
				return;
			}
			}
		} catch(IOException ex) {
	
	}

		/**
		*
		* If the player tries to advertise their server..
		*
		**/
		
		if(unpacked.startsWith("/")) {
			if(player.getInterfaceState().getClan().length() > 0) {
				TextUtils.textPack(packed, unpacked.substring(1));
				World.getWorld().getPrivateChat().get(player.getInterfaceState().getClan()).sendMessage(player, packed);
				return;
			}
		}
		TextUtils.textPack(packed, unpacked);
		player.getChatMessageQueue().add(new ChatMessage(effects, colour, packed));
		
	}

}