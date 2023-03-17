package day04_rpg;
 
public class Unit {
	private String name;
	private int level;
	private int hp;
	private int maxHp;
	private int att;
	private int def;
	private int exp;
	private boolean party;
	private Item weapon;
	private Item armor;
	private Item ring;

	public Unit(String n, int l, int h, int a, int d, int e) {
		this.name = n;
		this.level = l;
		this.maxHp = h;
		this.att = a;
		this.def = d;
		this.exp = e;
		this.hp = this.maxHp;
		this.party = false;
		this.weapon = null;
		this.armor = null;
		this.ring = null;
	}

	public Unit(String n, int l, int h, int a, int d, int e, boolean p) {
		this.name = n;
		this.level = l;
		this.maxHp = h;
		this.att = a;
		this.def = d;
		this.exp = e;
		this.hp = this.maxHp;
		this.party = p;
		this.weapon = null;
		this.armor = null;
		this.ring = null;
	}

	public void setItem(Item w, Item a, Item r) {
		this.weapon = w;
		this.armor = a;
		this.ring = r;
	}

	public void printStatus() {
		System.out.printf("[이름 : %s]\n", this.name);
		System.out.printf("[레벨 : %s]\n", this.level);

		if (this.ring == null)
			System.out.printf("[체력 : %s", this.hp);
		else {
			int hp = this.hp + this.ring.getPower();
			System.out.printf("[체력 : %s", hp);
		}

		if (this.ring == null)
			System.out.printf(" / %d(%d+0)]\n", this.maxHp, this.maxHp);
		else {
			int maxhp = this.maxHp + this.ring.getPower();
			System.out.printf(" / %d", maxhp);
			System.out.printf("(%d+%d)]\n", this.maxHp, this.ring.getPower());
		}

		if (this.weapon == null)
			System.out.printf("[공격력 : %d(%d+0)]\n", this.att, this.att);
		else {
			int att = this.att + this.ring.getPower();
			System.out.printf("[공격력 : %d", att);
			System.out.printf("(%d+%d)]\n", this.att, this.weapon.getPower());
		}

		if (this.armor == null)
			System.out.printf("[방어력 : %d(%d+0)]\n", this.def, this.def);
		else {
			int def = this.def = this.armor.getPower();
			System.out.printf("[방어력 : %d", def);
			System.out.printf("(%d+%d)]\n", this.def, this.armor.getPower());
		}

		System.out.printf("[파티중 : %s]\n", this.party);
	}

	public void printEquippedItem() {
		if (this.weapon == null)
			System.out.println("[무기 : 없음 ]");
		else
			System.out.printf("[무기 : %s]\n", this.weapon.getName());

		if (this.armor == null)
			System.out.println("[방어구 : 없음 ]");
		else
			System.out.printf("[방어구 : %s]\n", this.armor.getName());

		if (this.ring == null)
			System.out.println("[반지 : 없음 ]");
		else
			System.out.printf("[반지 : %s]\n", this.ring.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public boolean isParty() {
		return party;
	}

	public void setParty(boolean party) {
		this.party = party;
	}

	public Item getWeapon() {
		return this.weapon;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

	public Item getArmor() {
		return this.armor;
	}

	public void setArmor(Item armor) {
		this.armor = armor;
	}

	public Item getRing() {
		return this.ring;
	}

	public void setRing(Item ring) {
		this.ring = ring;
	}
}