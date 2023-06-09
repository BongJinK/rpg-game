package day04_rpg;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> itemList = new ArrayList<>();

	public ArrayList<Item> getItemList() {
		return this.itemList;
	}

	public void setItemList(ArrayList<Item> list) {
		this.itemList = list;
	}

	public void inventoryMenu() {
		while (true) {
			System.out.println("============ [인벤메뉴] =============");
			System.out.println("[1.착용] [2.판매] [0.뒤로가기]");
			int sel = MainGame.selectNumber("메뉴");
			if (sel == 0)
				break;
			if (sel == 1)
				equipMenu();
			if (sel == 2)
				sellMenu();
			// 장비 착용 해제
		}
	}

	public void equipMenu() {
		Player.getGuild().printAllUnitStaus();

		int selUnit = MainGame.selectNumber("아이템 착용할 길드원을 선택하세요 ");

		if (selUnit < 1 || selUnit > Player.getGuildList().size()) {
			System.err.println("올바르지 않은 선택입니다.");
			return;
		}

		while (true) {
			System.out.println("point 1");

			Player.getGuild().printUnitStaus(selUnit - 1);
			Player.getGuild().printUnitItem(selUnit - 1);

			System.out.println("point 2");

			printItemList();
			String message = "착용할 아이템 번호를 입력하세요.\n[0.뒤로가기]";
			int selEquip = MainGame.selectNumber(message);

			if (selEquip > this.itemList.size()) {
				System.out.println("착용할 아이템이 없습니다.\n");
				break;
			}
			if (selEquip == 0)
				break;

			selEquip -= 1;
			Item newItem = this.itemList.get(selEquip);
			int itemCode = this.itemList.get(selEquip).getKind();
			Unit unit = Player.getGuildUnit(selUnit - 1);

			if (itemCode == Item.WEAPON) {
				if (unit.getWeapon() != null) {
					this.itemList.add(unit.getWeapon());
//					int att = unit.getAtt() - unit.getWeapon().getPower();
//					Player.getGuildList().get(selUnit - 1).setAtt(att);
				}
				unit.setWeapon(newItem);
//				int att = unit.getAtt() + unit.getWeapon().getPower();
//				Player.getGuildList().get(selUnit - 1).setAtt(att);

			} else if (itemCode == Item.ARMOR) {
				if (unit.getArmor() != null) {
					this.itemList.add(unit.getArmor());
				}
				unit.setArmor(newItem);
//				int def = unit.getDef() + unit.getArmor().getPower();
//				Player.getGuildList().get(selUnit - 1).setDef(def);

			} else if (itemCode == Item.RING) {
				if (unit.getRing() != null) {
					this.itemList.add(unit.getRing());
				}
				unit.setRing(newItem);
//				int att = unit.getAtt() + unit.getRing().getPower();
//				Player.getGuildList().get(selUnit - 1).setAtt(att);

			}
			this.itemList.remove(selEquip);
		}
	}

	public void printItemList() {
		System.out.println("============ [아이템리스트] ==============");
		for (int i = 0; i < this.itemList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			Item item = this.itemList.get(i);
			System.out.print("[이름 : " + item.getName() + "]");
			if (item.getKind() == 1)
				System.out.print("[공격력 : " + item.getPower() + "]");
			else if (item.getKind() == 2)
				System.out.print("[방어력 : " + item.getPower() + "]");
			else if (item.getKind() == 3)
				System.out.print("[체력 : " + item.getPower() + "]");
			System.out.print("[가격 : " + item.getPrice() + "]");
			System.out.println("");
		}
	}

	public void sellMenu() {
		while (true) {
			printItemList();
			System.out.printf("[골드 : %d]\n", Player.getMoney());

			String message = "판매할 아이템 번호를 입력하세요. (50% 수수료)\n[0.뒤로가기]";
			int selItemNum = MainGame.selectNumber(message);

			if (selItemNum > this.itemList.size()) {
				System.out.println("판매할 아이템이 없습니다.\n");
				break;
			}
			if (selItemNum == 0)
				break;

			String sellItemName = this.itemList.get(selItemNum - 1).getName();
			System.out.printf("%s을 판매합니다.\n", sellItemName);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int cost = this.itemList.get(selItemNum - 1).getPrice();
			int saleProceeds = cost / 2;
			Player.setMoney(Player.getMoney() + saleProceeds);
			this.itemList.remove(selItemNum - 1);
		}
	}

	public void addItem(Item item) {
		this.itemList.add(item);
	}

	// 아래 메소드 사용 유무 확인
//	public void deleteItem(Item deleteItem) {
//		int index = IndexOf(deleteItem);
//		if (index != -1)
//			this.itemList.remove(index);
//	}
//
//	public int IndexOf(Item deleteItem) {
//		int index = -1;
//		for (int i = 0; i < this.itemList.size(); i++) {
//			Item item = this.itemList.get(i);
//			if (item.getName().equals(deleteItem.getName()))
//				return i;
//		}
//		return index;
//	}
}