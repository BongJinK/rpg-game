package day04_rpg;

import java.util.ArrayList;

public class Player {

	private static int money;
	private static Guild guild;
	private static Inventory inven;

	public Player() {
		money = 100000;
		guild = new Guild();
		guild.setGuild();
		inven = new Inventory();
	}

	public void guildMenu() {
		guild.guildMenu();
	}

	public void inventoryMenu() {
		inven.inventoryMenu();
	}

	public static ArrayList<Unit> getGuildList() {
		return guild.getGuildList();
	}

	public static void setGuildList(ArrayList<Unit> list) {
		guild.setGuildList(list);
	}

	public static ArrayList<Item> getItemList() {
		return inven.getItemList();
	}

	public static void setItemList(ArrayList<Item> list) {
		inven.setItemList(list);
	}

	public static Unit getGuildUnit(int num) {
		return guild.getGuildUnit(num);
	}

	public static int getGuildSize() {
		return guild.getGuildList().size();
	}

	public static int getItemSize() {
		return inven.getItemList().size();
	}

	public static Guild getGuild() {
		return guild;
	}

	public static int getMoney() {
		return money;
	}

	public static void setMoney(int money) {
		Player.money = money;
	}
}