package day04_rpg;

import java.util.ArrayList;

public class Player {

	private int money;
	private Guild guild;
	private Inventory inven;

//	static Guild guild = new Guild();
//	static Inventory inven = new Inventory();

	public Player() {
		this.money = 100000;
		this.guild = new Guild();
		this.inven = new Inventory();
	}

	public void guildMenu() {
		this.guild.guildMenu();
	}

	public void inventoryMenu() {
		this.inven.inventoryMenu();
	}

	public ArrayList<Unit> getGuildList() {
		return this.guild.getGuildList();
	}
	
	public void setGuildList(ArrayList<Unit> list) {
		this.guild.setGuildList(list);
	}
	
	public ArrayList<Item> getItemList() {
		return this.inven.getItemList();
	}

	public void setItemList(ArrayList<Item> list) {
		this.inven.setItemList(list);
	}

	public Unit getGuildUnit(int num) {
		return this.guild.getGuildUnit(num);
	}

	public int getGuildSize() {
		return this.guild.getGuildList().size();
	}

	public int getItemSize() {
		return this.inven.getItemList().size();
//		return this.inven.itemList.size();
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

//	public void guildMenu() {
//	guild.guildMenu();
//}
//
//public void inventoryMenu() {
//	inven.inventoryMenu();
//}
//
//static public ArrayList<Unit> getGuildList() {
//	return guild.guildList;
//}
//
//static public ArrayList<Item> getItemList() {
//	return inven.itemList;
//}
//
//static public Unit getGuildUnit(int num) {
//	return guild.getGuildUnit(num);
//}
//
//static public int getGuildSize() {
//	return guild.guildList.size();
//}
//
//static public int getItemSize() {
//	return inven.itemList.size();
//}

}