package com.darthpiotr.swintegration.commands;

import java.util.ArrayList;
import java.util.List;

import com.darthpiotr.swintegration.config.ConfigurationHandler;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandRender implements ICommand {

	private List aliases;

	public CommandRender() {
		aliases = new ArrayList();
		aliases.add("render");
		aliases.add("r");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "render";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "render [r|t] [r|x|y|z] <value>";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
//		if (args.length < 1 || args.length > 3 || args.length == 2)
//			sender.addChatMessage(new ChatComponentText("Invalid arguments"));
//
//		if (args.length == 1) {
//			if (args[0] == "r") {
//				sender.addChatMessage(new ChatComponentText("Rotation: rot:" + ConfigurationHandler.rot + " x:" + ConfigurationHandler.rx + " y: " + ConfigurationHandler.ry + " z:" + ConfigurationHandler.rz));
//			}
//			if (args[0] == "t") {
//				sender.addChatMessage(new ChatComponentText("Transform: x:" + ConfigurationHandler.tx + " y: " + ConfigurationHandler.ty + " z:" + ConfigurationHandler.tz));
//			}
//		}
//		if (args.length == 3) {
//			if (args[0] == "r") {
//				double r = Double.valueOf(args[3]);
//
//				if (args[1] == "r") {
//					ConfigurationHandler.rot = r;
//				}
//				if (args[1] == "x") {
//					ConfigurationHandler.rx = r;
//				}
//				if (args[1] == "y") {
//					ConfigurationHandler.ry = r;
//				}
//				if (args[1] == "z") {
//					ConfigurationHandler.rz = r;
//				}
//				
//				sender.addChatMessage(new ChatComponentText("Rotation: rot:" + ConfigurationHandler.rot + " x:" + ConfigurationHandler.rx + " y: " + ConfigurationHandler.ry + " z:" + ConfigurationHandler.rz));
//				
//			}
//			if (args[0] == "t") {
//				double t = Double.valueOf(args[3]);
//
//				if (args[1] == "x") {
//					ConfigurationHandler.tx = t;
//				}
//				if (args[1] == "y") {
//					ConfigurationHandler.ty = t;
//				}
//				if (args[1] == "z") {
//					ConfigurationHandler.tz = t;
//				}
//				
//				sender.addChatMessage(new ChatComponentText("Transform: x:" + ConfigurationHandler.tx + " y: " + ConfigurationHandler.ty + " z:" + ConfigurationHandler.tz));
//				
//			}
//		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}

}
