package day04_rpg;
  
import java.util.ArrayList;

public class Inventory {
	ArrayList<Item> itemList = new ArrayList<>();

	public void inventoryMenu() {
		while (true) {
			System.out.println("============ [인벤메뉴] =============");
			System.out.println("[1.착용] [2.판매] [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 0)
				break;
			if (sel == 1) {
				equipMenu();
			}
			if (sel == 2) {
				sellMenu();
			}
		}
	}

	public void equipMenu() {
		Player.guild.printAllUnitStaus();
		System.out.println("아이템 착용할 길드원을 선택하세요 ");
		int selUnit = MainGame.scan.nextInt();
		while (true) {
			Player.guild.printUnitStaus(selUnit - 1);
			Player.guild.printUnitItem(selUnit - 1);
			printItemList();
			System.out.println("착용할 아이템 번호를 입력하세요 [0.뒤로가기]");
			int selEquip = MainGame.scan.nextInt();
			if (selEquip == 0)
				break;

			selEquip -= 1;
			Item newItem = this.itemList.get(selEquip);
			int itemCode = this.itemList.get(selEquip).getKind();
			Unit unit = Player.getGuildUnit(selUnit - 1);

			if (itemCode == Item.WEAPON) {
				if (unit.getWeapon() != null) {
					this.itemList.add(unit.getWeapon());
				}
				unit.setWeapon(newItem);
				int att = unit.getAtt() + unit.getWeapon().getPower();
				Player.guild.guildList.get(selUnit - 1).setAtt(att);

			} else if (itemCode == Item.ARMOR) {
				if (unit.getArmor() != null) {
					this.itemList.add(unit.getArmor());
				}
				unit.setArmor(newItem);

			} else if (itemCode == Item.RING) {
				if (unit.getRing() != null) {
					this.itemList.add(unit.getRing());
				}
				unit.setRing(newItem);
				;

			}
			this.itemList.remove(selEquip);
		}
	}

	public void printItemList() {
		System.out.println("============ [아이템리스트] ==============");
		for (int i = 0; i < itemList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print("[이름 : " + this.itemList.get(i).getName() + "]");
			System.out.print("[능력 : " + this.itemList.get(i).getPower() + "]");
			System.out.print("[가격 : " + this.itemList.get(i).getPrice() + "]");
			System.out.println("");
		}
	}

	public void sellMenu() {
		while (true) {
			printItemList();
			System.out.println("[골드 : " + Player.money + "]");
			System.out.println("판매할 아이템 번호를 입력하세요. (50% 수수료)\n[0.뒤로가기]");
			int selSell = MainGame.scan.nextInt();

			System.out.println(this.itemList.get(selSell - 1).getName() + "을 판매합니다.");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Player.money += (this.itemList.get(selSell - 1).getPrice() / 2);
			this.itemList.remove(selSell - 1);
		}
	}

	public void addItem(Item item) {
		this.itemList.add(item);
	}

}