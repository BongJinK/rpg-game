package day04_rpg;

import java.util.ArrayList;

public class Shop {
	private ArrayList<Item> itemList = new ArrayList<>();

	public Shop() {
		Item temp = new Item();

		temp.setKind(Item.WEAPON);
		temp.setName("목검");
		temp.setPower(5);
		temp.setPrice(1000);
		;
		this.itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.WEAPON);
		temp.setName("철검");
		temp.setPower(10);
		temp.setPrice(2000);
		;
		this.itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.WEAPON);
		temp.setName("레이피어");
		temp.setPower(17);
		temp.setPrice(2500);
		;
		this.itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.ARMOR);
		temp.setName("티셔츠");
		temp.setPower(3);
		temp.setPrice(300);
		;
		this.itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.ARMOR);
		temp.setName("가죽갑옷");
		temp.setPower(7);
		temp.setPrice(800);
		;
		this.itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.ARMOR);
		temp.setName("강철갑옷");
		temp.setPower(10);
		temp.setPrice(1500);
		;
		this.itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.RING);
		temp.setName("은반지");
		temp.setPower(7);
		temp.setPrice(3000);
		this.itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.RING);
		temp.setName("금반지");
		temp.setPower(17);
		temp.setPrice(7000);
		this.itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.RING);
		temp.setName("다이아반지");
		temp.setPower(35);
		temp.setPrice(20000);
		this.itemList.add(temp);
	}

	public void shopMng() {
		while (true) {
			System.out.println("=============== [상점] ===============");
			System.out.println("[1.무기] [2.갑옷] [3.반지] [0.뒤로가기]");
			int selKind = MainGame.scan.nextInt();
			if (selKind == 0)
				return;

			purchaseItem(selKind);
		}
	}

	private void purchaseItem(int selectItemOfKind) {
		while (true) {
			if (selectItemOfKind == Item.WEAPON)
				System.out.println("=========== [무기] ============");
			else if (selectItemOfKind == Item.ARMOR)
				System.out.println("============ [방어구] ============");
			else if (selectItemOfKind == Item.RING)
				System.out.println("=========== [반지] ============");

			printItems(selectItemOfKind);

			System.out.printf("[골드 : %dGold]\n", Player.getMoney());
			System.out.println("구입할 아이템 번호를 입력하세요 [0.뒤로가기]");
			int selNum = MainGame.scan.nextInt();
			if (selNum == 0)
				break;

			int count = 0;
			for (int i = 0; i < this.itemList.size(); i++) {
				Item item = this.itemList.get(i);
				if (item.getKind() != selectItemOfKind)
					continue;

				count += 1;
				if (count == selNum) {
					ArrayList<Item> inventory = Player.getItemList();
					inventory.add(item);
					Player.setItemList(inventory);

					int itemPrice = item.getPrice();
					Player.setMoney(Player.getMoney() - itemPrice);

					System.out.printf("[%s]을(를) 구입했습니다.\n", item.getName());

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					break;
				}
			}
		}
	}

	private void printItems(int selectItemOfKind) {
		int count = 0;
		for (int i = 0; i < this.itemList.size(); i++) {
			Item item = this.itemList.get(i);
			if (item.getKind() != selectItemOfKind)
				continue;
			System.out.printf("[%d번]\n", count + 1);
			System.out.printf("[이름 : %s]\n", item.getName());
			if (item.getKind() == 1)
				System.out.print("[공격력 : " + item.getPower() + "]");
			else if (item.getKind() == 2)
				System.out.print("[방어력 : " + item.getPower() + "]");
			else if (item.getKind() == 3)
				System.out.print("[체력 : " + item.getPower() + "]");
			System.out.printf(" [가격 : %d]\n", item.getPrice());
			System.out.println("");
			count += 1;
		}
	}

}