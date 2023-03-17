package day04_rpg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileData {
	private FileWriter fout;
	private File file;
	private FileReader reader;
	private BufferedReader br;
	private String path;

	public FileData() {
		this.path = "gameData.txt";
	}

	public void save() throws IOException {
		this.fout = new FileWriter(this.path);
		ArrayList<Unit> temp = Player.getGuildList();
		String gameData = "";
		gameData += Player.money + "\r\n";
		gameData += temp.size() + "\r\n";

		// name, level, hp = maxhp, att, def, exp
		// Unit temp = new Unit("호랑이", 1, 100, 10, 5, 0);
		for (int i = 0; i < temp.size(); i++) {
			gameData += temp.get(i).getName() + "/";
			gameData += temp.get(i).getLevel() + "/";
			gameData += temp.get(i).getMaxHp() + "/";
			gameData += temp.get(i).getAtt() + "/";
			gameData += temp.get(i).getDef() + "/";
			gameData += temp.get(i).getExp() + "/";
			gameData += temp.get(i).isParty() + "/";
			gameData += "\r\n";

			if (temp.get(i).getWeapon() == null)
				gameData += temp.get(i).getWeapon();
			else {
				Item item = temp.get(i).getWeapon();
				String weaponData = "";
				weaponData += item.getKind() + ",";
				weaponData += item.getName() + ",";
				weaponData += item.getPower() + ",";
				weaponData += item.getPrice() + ",";
				gameData += weaponData;
			}

			gameData += "/";
			if (temp.get(i).getArmor() == null)
				gameData += temp.get(i).getArmor();
			else {
				Item item = temp.get(i).getArmor();
				String weaponData = "";
				weaponData += item.getKind() + ",";
				weaponData += item.getName() + ",";
				weaponData += item.getPower() + ",";
				weaponData += item.getPrice() + ",";
				gameData += weaponData;
			}

			gameData += "/";
			if (temp.get(i).getRing() == null)
				gameData += temp.get(i).getRing();
			else {
				Item item = temp.get(i).getRing();
				String weaponData = "";
				weaponData += item.getKind() + ",";
				weaponData += item.getName() + ",";
				weaponData += item.getPower() + ",";
				weaponData += item.getPrice() + ",";
				gameData += weaponData;
			}
			gameData += "\r\n";
		}

		// inventory
		gameData += Player.getItemSize();
		gameData += "\r\n";
		for (int i = 0; i < Player.getItemSize(); i++) {
			Item item = Player.getItemList().get(i);

			gameData += item.getKind() + "/";
			gameData += item.getName() + "/";
			;
			gameData += item.getPower() + "/";
			;
			gameData += item.getPrice() + "\r\n";
			;
		}

		System.out.println(gameData);
		this.fout.write(gameData, 0, gameData.length());
		this.fout.close();

	}

	public void loadData() throws IOException {
		this.file = new File(this.path);

		if (!this.file.exists()) {
			return;
		}

		this.reader = new FileReader(this.path);
		this.br = new BufferedReader(this.reader);

		String money = this.br.readLine();
		Player.money = Integer.parseInt(money);
		System.out.println(Player.money);

		String guildSize = this.br.readLine();
		int size = Integer.parseInt(guildSize);
		Player.guild.guildList.clear();
		System.out.println(size);

		for (int i = 0; i < size; i++) {
			String unitData = br.readLine();
			String[] unitArr = unitData.split("/");

			String name = unitArr[0];
			int level = Integer.parseInt(unitArr[1]);
			int maxhp = Integer.parseInt(unitArr[2]);
			int att = Integer.parseInt(unitArr[3]);
			int def = Integer.parseInt(unitArr[4]);
			int exp = Integer.parseInt(unitArr[5]);
			boolean party = Boolean.parseBoolean(unitArr[6]);

			Unit temp = new Unit(name, level, maxhp, att, def, exp, party);
			Player.guild.guildList.add(temp);

			// setting item
			String itemData = this.br.readLine();
			String itemArr[] = itemData.split("/");

			if (itemArr[0].equals("null"))
				Player.getGuildList().get(i).setWeapon(null);
			else {
				String[] weapon = itemArr[0].split(",");
				int itemKind = Integer.parseInt(weapon[0]);
				String itemName = weapon[1];
				int itemPower = Integer.parseInt(weapon[2]);
				int itemPrice = Integer.parseInt(weapon[3]);
				Item item = new Item();
				item.setItem(itemKind, itemName, itemPower, itemPrice);
				Player.getGuildList().get(i).setWeapon(item);
			}

			if (itemArr[1].equals("null"))
				Player.getGuildList().get(i).setArmor(null);
			else {
				String[] armor = itemArr[1].split(",");
				int itemKind = Integer.parseInt(armor[0]);
				String itemName = armor[1];
				int itemPower = Integer.parseInt(armor[2]);
				int itemPrice = Integer.parseInt(armor[3]);
				Item item = new Item();
				item.setItem(itemKind, itemName, itemPower, itemPrice);
				Player.getGuildList().get(i).setArmor(item);
				;
			}

			if (itemArr[2].equals("null"))
				Player.getGuildList().get(i).setRing(null);
			else {
				String[] ring = itemArr[2].split(",");
				int itemKind = Integer.parseInt(ring[0]);
				String itemName = ring[1];
				int itemPower = Integer.parseInt(ring[2]);
				int itemPrice = Integer.parseInt(ring[3]);
				Item item = new Item();
				item.setItem(itemKind, itemName, itemPower, itemPrice);
				Player.getGuildList().get(i).setRing(item);
			}
		}

		// inventory item
		String invenSize = this.br.readLine();
		System.out.println(invenSize);
		int inventorySize = Integer.parseInt(invenSize);

		Player.inven.itemList.clear();
		for (int i = 0; i < inventorySize; i++) {
			String invenData = this.br.readLine();
			String[] invenArr = invenData.split("/");

			int itemKind = Integer.parseInt(invenArr[0]);
			String itemName = invenArr[1];
			int itemPower = Integer.parseInt(invenArr[2]);
			int itemPrice = Integer.parseInt(invenArr[3]);

			Item item = new Item();
			item.setItem(itemKind, itemName, itemPower, itemPrice);
			Player.inven.itemList.add(item);
		}

		this.reader.close();
		this.br.close();
	}

}