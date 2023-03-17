package day04_rpg;

import java.util.ArrayList;

public class Guild {
	final int PARTY_SIZE = 4;
	ArrayList<Unit> guildList = new ArrayList<>();
	Unit[] partyList;

	public void printGuildMenu() {
		System.out.println("=============== [길드관리] ================");
		System.out.println("[1.길드목록]\n[2.길드원추가]\n[3.길드원삭제]");
		System.out.println("[4.파티원교체]\n[5.정렬]\n[0.뒤로가기]");
		// 파티원 추가, 삭제
	}

	// name, level, hp = maxhp, att, def, exp
	public void setGuild() {
		Unit temp = new Unit("호랑이", 1, 100, 10, 5, 0);
		this.guildList.add(temp);
		temp = new Unit("강아지", 1, 80, 7, 3, 0);
		this.guildList.add(temp);
		temp = new Unit("사슴", 1, 50, 3, 1, 0);
		this.guildList.add(temp);
		temp = new Unit("두더지", 1, 70, 5, 2, 0);
		this.guildList.add(temp);
		temp = new Unit("돼지", 1, 200, 4, 8, 0);
		this.guildList.add(temp);
		temp = new Unit("사자", 1, 120, 11, 7, 0);
		this.guildList.add(temp);
		for (int i = 0; i < 4; i++) {
			this.guildList.get(i).setParty(true);
		}
		this.partyList = new Unit[this.PARTY_SIZE];
		int n = 0;
		for (int i = 0; i < this.guildList.size(); i++) {
			if (this.guildList.get(i).isParty() == true) {
				this.partyList[n] = this.guildList.get(i);
				n++;
			}
		}
	}

	public Unit getGuildUnit(int num) {
		return guildList.get(num);
	}

	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[골드 : " + Player.money + "]");
		System.out.println("============= [길드원] =================");
		for (int i = 0; i < this.guildList.size(); i++) {
			System.out.printf("[%d 번]\n", i + 1);
			System.out.printf(" [이름 : %s]\n", this.guildList.get(i).getName());
			System.out.printf(" [레벨 : %d]\n", this.guildList.get(i).getLevel());
			System.out.printf(" [체력 : %d", this.guildList.get(i).getHp());
			System.out.printf(" / %d]\n", this.guildList.get(i).getMaxHp());
			System.out.printf("[공격력 : %d]\n", this.guildList.get(i).getAtt());
			System.out.printf("[방어력 : %d]\n", this.guildList.get(i).getDef());
			System.out.printf("[파티중 : %s]\n", this.guildList.get(i).isParty());
			System.out.println("");
		}
		System.out.println("=================================");
	}

	public void printUnitStaus(int num) {
		this.guildList.get(num).printStatus();
	}

	public void printUnitItem(int num) {
		this.guildList.get(num).printEquippedItem();
	}

	public void buyUnit() {
		if (Player.money < 5000)
			return;
		String[] n1 = { "박", "이", "김", "최", "유", "지", "오" };
		String[] n2 = { "명", "기", "종", "민", "재", "석", "광" };
		String[] n3 = { "수", "자", "민", "수", "석", "민", "철" };

		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n1.length)];
		name += n3[MainGame.ran.nextInt(n1.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 11;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, 1, hp, att, def, 0);

		System.out.println("=====================================");
		System.out.printf("[이름 : %s]\n", name);
		System.out.printf(" [레벨 : %d]\n", 1);
		System.out.printf(" [체력 : %d", hp);
		System.out.printf(" / %d]\n", hp);
		System.out.printf("[공격력 : %d]\n", att);
		System.out.printf(" [방어력 : %d]\n", def);
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");
 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.guildList.add(temp);
		Player.money -= 5000;
	}

	public void removeUnit() {
		printAllUnitStaus();
		System.out.println("삭제할 번호를 입력하세요 ");
		int sel = MainGame.scan.nextInt();

		if (this.guildList.get(sel - 1).isParty()) {
			System.out.println("파티중인 멤버는 삭제할수 없습니다.");
			return;
		}

		System.out.println("=================================");
		System.out.printf("[이름 : %s]\n", guildList.get(sel - 1).getName());
		System.out.println("길드원을 삭제합니다.");
		System.out.println("=================================");
		this.guildList.remove(sel - 1);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void printParty() {
		System.out.println("================ [파티원] ===============");
		for (int i = 0; i < PARTY_SIZE; i++) {
			System.out.printf("[%d번]", i + 1);
			System.out.printf("[이름 : %s]\n", this.partyList[i].getName());
			System.out.printf("[레벨 : %d]\n", this.partyList[i].getLevel());
			System.out.printf("[체력 : %d", this.partyList[i].getHp());
			System.out.printf(" / %d]\n", this.partyList[i].getMaxHp());
			System.out.printf("[공격력 : %d]", this.partyList[i].getAtt());
			System.out.printf("[방어력 : %d]", this.partyList[i].getDef());
			System.out.printf("[파티중 : %s]", this.guildList.get(i).isParty());
			System.out.println("");
		}
		System.out.println("=====================================");
	}

	public void partyChange() {
		printParty();
		System.out.println("교체할 번호를 입력하세요 ");
		int partyNum = MainGame.scan.nextInt();
		printAllUnitStaus();
		System.out.println("참가할 번호를 입력하세요 ");
		int guildNum = MainGame.scan.nextInt();

		this.partyList[partyNum - 1].setParty(false);
		this.guildList.get(guildNum - 1).setParty(true);
		;

		System.out.println("====================================");
		System.out.printf("[이름 : %s]", this.partyList[partyNum - 1].getName());
		System.out.print("에서 ");
		System.out.printf("[이름 : %s]", this.guildList.get(guildNum - 1).getName());
		System.out.println("으로 교체 합니다. ");
		System.out.println("====================================");

		int n = 0;
		for (int i = 0; i < this.guildList.size(); i++) {
			if (this.guildList.get(i).isParty()) {
				this.partyList[n] = this.guildList.get(i);
				n++;
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void guildMenu() {
		while (true) {

			int sel = MainGame.scan.nextInt();
			if (sel == 1) {
				printAllUnitStaus();
			} else if (sel == 2) {
				buyUnit();
			} else if (sel == 3) {
				removeUnit();
			} else if (sel == 4) {
				partyChange();
			} else if (sel == 0) {
				break;
			}
		}
	}

}