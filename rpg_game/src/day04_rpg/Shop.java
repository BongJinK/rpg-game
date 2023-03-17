package day04_rpg;

import java.util.ArrayList;

public class Shop {
	ArrayList<Item> itemList = new ArrayList<>();
 
	public Shop() {
		Item temp = new Item();
		temp.setKind(Item.WEAPON);
		temp.setName("목검");
		temp.setPower(3);
		temp.setPrice(1000);
		;
		this.itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.WEAPON);
		temp.setName("철검");
		temp.setPower(5);
		temp.setPrice(2000);
		;
		itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.WEAPON);
		temp.setName("레이피어");
		temp.setPower(7);
		temp.setPrice(2500);
		;
		itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.ARMOR);
		temp.setName("티셔츠");
		temp.setPower(1);
		temp.setPrice(300);
		;
		itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.ARMOR);
		temp.setName("가죽갑옷");
		temp.setPower(4);
		temp.setPrice(800);
		;
		itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.ARMOR);
		temp.setName("강철갑옷");
		temp.setPower(7);
		temp.setPrice(1500);
		;
		itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.RING);
		temp.setName("은반지");
		temp.setPower(7);
		temp.setPrice(3000);
		itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.RING);
		temp.setName("은반지");
		temp.setPower(17);
		temp.setPrice(7000);
		itemList.add(temp);

		temp = new Item();
		temp.setKind(Item.RING);
		temp.setName("다이아반지");
		temp.setPower(35);
		temp.setPrice(20000);
		itemList.add(temp);
	}

	public void shopMng() {
		while (true) {
			System.out.println("=============== [상점] ===============");
			System.out.println("[1.무기] [2.갑옷] [3.반지] [0.뒤로가기]");
			int selKind = MainGame.scan.nextInt();
			if (selKind == 0)
				return;

			while (true) {
				if (selKind == Item.WEAPON)
					System.out.println("=========== [무기] ============");
				else if (selKind == Item.ARMOR)
					System.out.println("=========== [방어구] ============");
				else if (selKind == Item.RING)
					System.out.println("=========== [반지] ============");
				printItems(selKind);

				System.out.printf("[골드 : %dGold]\n", Player.money);
				System.out.println("구입할 아이템 번호를 입력하세요 [0.뒤로가기]");
				int selNum = MainGame.scan.nextInt();
				if (selNum == 0)
					break;

				int count = 0;
				for (int i = 0; i < itemList.size(); i++) {
					if (itemList.get(i).getKind() == selKind) {
						count += 1;
						if (count == selNum) {
							Player.inven.addItem(itemList.get(i));
							Player.money -= itemList.get(i).getPrice();
							System.out.printf("[%s]을(를) 구입했습니다.\n", itemList.get(i).getName());
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
		}
	}

	public void printItems(int kind) {
		int count = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getKind() != kind)
				continue;
			System.out.printf("[%d번]\n" + (count + 1) + "번]");
			System.out.printf("[이름 : %s]\n", itemList.get(i).getName());
			System.out.printf("[능력 : +%d]\n", itemList.get(i).getPower());
			System.out.printf("[가격 : %d]\n", itemList.get(i).getPrice());
			System.out.println("");
			count += 1;
		}
	}

}