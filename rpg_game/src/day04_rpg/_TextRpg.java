package day04_rpg;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class MainGame {

	public static final int EXIT = 0;

	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();

	private void printMenu() {
		System.out.println("==== [메인메뉴] ====");
		System.out.println("[1.길드관리]\n[2.상점]\n[3.인벤토리]");
		System.out.println("[4.저장]\n[5.로드]\n[0.종료]");

		// 4. 던전 >> 고블린, 오크, 등등 (사냥터)
	}

	public static int selectNumber(String message) {
		int select = -1;
		while (true) {
			try {
				System.out.printf("%s : ", message);
				select = MainGame.scan.nextInt();
			} catch (InputMismatchException e) {
				e.printStackTrace();
				MainGame.scan.nextLine();
			}
			if (select < 0)
				continue;
			return select;
		}
	}

	public MainGame() {
		Player player = new Player();
		Shop shop = new Shop();
		FileData fileData = new FileData();

		while (true) {
			printMenu();
			int sel = selectNumber("메뉴");

			if (sel == EXIT) {
				System.out.println("게임을 종료 합니다.");
				break;
			}

			if (sel == 1)
				player.guildMenu();
			else if (sel == 2)
				shop.shopMng();
			else if (sel == 3)
				player.inventoryMenu();
			else if (sel == 4) {
				try {
					fileData.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (sel == 5) {
				try {
					fileData.loadData();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		MainGame.scan.close();
	}
}

public class _TextRpg {
	public static void main(String[] args) {
		new MainGame();
	}
}