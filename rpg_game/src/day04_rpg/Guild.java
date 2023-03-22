package day04_rpg;

import java.util.ArrayList;

public class Guild {
	public final int PARTY_SIZE = 6;
	private ArrayList<Unit> guildList = new ArrayList<>();
	private ArrayList<Unit> partyList = new ArrayList<>();

	public void setGuild() {
		// name, level, hp = maxhp, att, def, exp
		Unit standatdUnit = new Unit("이성계", 1, 200, 25, 10, 0);
		this.guildList.add(standatdUnit);
	}

	private void printGuildMenu() {
		System.out.println("======== [길드관리] ========");
		System.out.println("[1.길드목록]\n[2.길드원추가]\n[3.길드원삭제]");
		System.out.println("[4.파티]\n[5.정렬]\n[0.뒤로가기]");
	}

	public void printAllUnitStaus() {
		System.out.println("=========================================");
		System.out.printf("[골드 : %d Gold]\n", Player.getMoney());
		System.out.println("================ [길드원] ================");
		for (int i = 0; i < this.guildList.size(); i++) {
			System.out.printf("[%d 번]\n", i + 1);
			System.out.printf("[이름 : %s] ", this.guildList.get(i).getName());
			System.out.printf("[레벨 : %d] ", this.guildList.get(i).getLevel());
			System.out.printf("[체력 : %d", this.guildList.get(i).getHp());
			System.out.printf(" / %d]\n", this.guildList.get(i).getMaxHp());
			System.out.printf("[공격력 : %d]", this.guildList.get(i).getAtt());
			System.out.printf("[방어력 : %d]", this.guildList.get(i).getDef());
			System.out.printf("[파티중 : %s]\n", this.guildList.get(i).isParty());
		}
		System.out.println("=========================================");
	}

	private void buyUnit() {
		if (Player.getMoney() < 5000)
			return;
		String[] n1 = { "이", "김", "김", "최", "장", "김", "척" };
		String[] n2 = { "순", "시", "유", "무", "보", "좌", "준" };
		String[] n3 = { "신", "민", "신", "선", "고", "진", "경" };

		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n1.length)];
		name += n3[MainGame.ran.nextInt(n1.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 20;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, 1, hp, att, def, 0);

		System.out.println("=====================================");
		System.out.printf("[이름 : %s]\n", name);
		System.out.printf(" [레벨 : %d]\n", 1);
		System.out.printf(" [체력 : %d", hp);
		System.out.printf(" / %d]\n", hp);
		System.out.printf(" [공격력 : %d]\n", att);
		System.out.printf(" [방어력 : %d]\n", def);
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.guildList.add(temp);
		for (Unit i : this.guildList)
			System.out.println(i.getName());
		Player.setGuildList(this.guildList);
		Player.setMoney(Player.getMoney() - 5000);
	}

	private void removeUnit() {
		printAllUnitStaus();
		int sel = MainGame.selectNumber("삭제할 번호를 입력하세요.");

		if (this.guildList.get(sel - 1).isParty()) {
			System.out.println("파티중인 멤버는 삭제할수 없습니다.");
			return;
		}

		Unit unit = this.guildList.get(sel - 1);
		System.out.println("=================================");
		System.out.printf("[이름 : %s]\n", unit.getName());
		System.out.println("길드원을 삭제합니다.");
		System.out.println("=================================");
		this.guildList.remove(sel - 1);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void printPartyList() {
		System.out.println("================ [파티원] ===============");
		for (int i = 0; i < this.partyList.size(); i++) {
			System.out.printf("[%d번]", i + 1);

			Unit unit = this.partyList.get(i);
			System.out.printf("[이름 : %s]\n", unit.getName());
			System.out.printf("[레벨 : %d]\n", unit.getLevel());
			System.out.printf("[체력 : %d", unit.getHp());
			System.out.printf(" / %d]\n", unit.getMaxHp());
			System.out.printf("[공격력 : %d]", unit.getAtt());
			System.out.printf("[방어력 : %d]", unit.getDef());
			System.out.printf("[파티중 : %s]", unit.isParty());
			System.out.println("");
		}
		System.out.println("=====================================");
	}

	private void party() {
		while (true) {
			System.out.println("1. 파티 목록\n2. 파티원 추가");
			System.out.println("3. 파티원 추방\n0. 뒤로가기");

			int select = MainGame.selectNumber("메뉴");

			if (select == MainGame.EXIT)
				break;

			if (select == 1)
				printPartyList();
			else if (select == 2)
				partyParticipation();
			else if (select == 3)
				partyExpulsion();
		}
	}

	private void partyParticipation() {

		if (this.partyList.size() >= this.PARTY_SIZE) {
			System.err.println("더 이상 파티 초대가 불가능합니다.");
			return;
		}
		printAllUnitStaus();
		int guildNum = changeMemberNumber("파티에 추가할 길드원 번호");
		if (this.guildList.get(guildNum - 1).isParty()) {
			System.out.println("이미 파티에 가입되어 있습니다.");
			return;
		}

		this.guildList.get(guildNum - 1).setParty(true);

		this.partyList.add(this.guildList.get(guildNum - 1));
	}

	private void partyExpulsion() {
		if (this.partyList.size() == 0) {
			System.err.println("추방할 파티원이 존재하지 않습니다.");
			return;
		}
		printPartyList();
		int guildNum = changeMemberNumber("추방할 길드원 번호");

		this.guildList.get(guildNum - 1).setParty(false);
		System.out.println(this.guildList.get(guildNum - 1).isParty());

		this.partyList.remove(this.guildList.get(guildNum - 1));
	}

	private int changeMemberNumber(String message) {
		while (true) {
			int number = MainGame.selectNumber(message);
			if (number > this.guildList.size() || number < 1)
				continue;
			return number;
		}
	}

	private void sortRun() {
		while (true) {
			System.out.println("1. 길드정렬\n2. 파티정렬\n0. 뒤로가기");
			int select = MainGame.selectNumber("메뉴");

			if (select == MainGame.EXIT)
				break;

			if (select == 1)
				sortGuildList();
			else if (select == 2)
				sortPartyList();
		}
	}

	private void sortGuildList() {
		while (true) {
			System.out.println("1. 이름순\n2. 레벨순\n3. 공격력순");
			System.out.println("4. 파티가입 유무\n0. 뒤로가기");
			int select = MainGame.selectNumber("메뉴");

			if (select == MainGame.EXIT)
				break;

			if (select == 1)
				sortByName(this.guildList);
			else if (select == 2)
				sortByLevel(this.guildList);
			else if (select == 3)
				sortByAttackForce(this.guildList);
			else if (select == 4)
				sortByParty(this.guildList);
		}
	}

	private void sort(ArrayList<Unit> list, int i, int index) {
		if (index != -1) {
			Unit unit = list.get(i);
			list.set(i, list.get(index));
			list.set(index, unit);
		}
	}

	private void sortByName(ArrayList<Unit> list) {
		if (list.size() < 2)
			return;

		for (int i = 0; i < list.size(); i++) {
			String standardName = list.get(i).getName();
			int diffrence = 0;
			int index = -1;

			for (int j = i + 1; j < list.size(); j++) {
				String compareName = list.get(j).getName();
				if (standardName.compareTo(compareName) > diffrence) {
					diffrence = standardName.compareTo(compareName);
					index = j;
				}
			}

			sort(list, i, index);
		}
	}

	private void sortByLevel(ArrayList<Unit> list) {
		if (list.size() < 2)
			return;

		for (int i = 0; i < list.size(); i++) {
			int standardLevel = list.get(i).getLevel();
			int diffrence = 0;
			int index = -1;

			for (int j = i + 1; j < list.size(); j++) {
				int compareLevel = list.get(j).getLevel();
				if (standardLevel - compareLevel < diffrence) {
					diffrence = standardLevel - compareLevel;
					index = j;
				}
			}

			sort(list, i, index);
		}
	}

	private void sortByAttackForce(ArrayList<Unit> list) {
		if (list.size() < 2)
			return;

		for (int i = 0; i < list.size(); i++) {
			int standardAtt = list.get(i).getAtt();
			int diffrence = 0;
			int index = -1;

			for (int j = i + 1; j < list.size(); j++) {
				int compareAtt = list.get(j).getAtt();
				if (standardAtt - compareAtt < diffrence) {
					diffrence = standardAtt - compareAtt;
					index = j;
				}
			}

			sort(list, i, index);
		}
	}

	private void sortByParty(ArrayList<Unit> list) {
		if (list.size() < 2)
			return;

		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).isParty()) {
				int index = -1;
				for (int j = i + 1; j < list.size(); j++) {
					if (list.get(j).isParty()) {
						index = j;
						break;
					}
				}
				sort(list, i, index);
			}
		}
	}

	private void sortPartyList() {
		while (true) {
			System.out.println("1. 이름순\n2. 레벨순\n3. 공격력순");
			System.out.println("0. 뒤로가기");
			int select = MainGame.selectNumber("메뉴");

			if (select == MainGame.EXIT)
				break;

			if (select == 1)
				sortByName(this.partyList);
			else if (select == 2)
				sortByLevel(this.partyList);
			else if (select == 3)
				sortByAttackForce(this.partyList);
		}
	}

	public void guildMenu() {
		while (true) {
			printGuildMenu();
			int sel = MainGame.scan.nextInt();

			if (sel == MainGame.EXIT)
				break;

			if (sel == 1)
				printAllUnitStaus();
			else if (sel == 2)
				buyUnit();
			else if (sel == 3)
				removeUnit();
			else if (sel == 4)
				party();
			else if (sel == 5)
				sortRun();
		}
	}

	public Unit getGuildUnit(int num) {
		return guildList.get(num);
	}

	public void printUnitStaus(int num) {
		this.guildList.get(num).printStatus();
	}

	public void printUnitItem(int num) {
		this.guildList.get(num).printEquippedItem();
	}

	public ArrayList<Unit> getGuildList() {
		return this.guildList;
	}

	public void setGuildList(ArrayList<Unit> list) {
		this.guildList = list;
	}
}