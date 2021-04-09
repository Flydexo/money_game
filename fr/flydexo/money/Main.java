package fr.flydexo.money;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) throws IOException {
		Timer chrono = new Timer();
		Random r = new Random();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Vous mettez combien d'argent? ");
		String balanceStr = reader.readLine();
		reader.close();
		chrono.schedule(new TimerTask() {
			int balance = Integer.parseInt(balanceStr);
			final int oldBalance = balance;
			boolean firstTime = true;
			int win = 0;
			int time = Integer.parseInt(balanceStr) / 100;
			
			
			@Override
			public void run() {
				if (time < 1 && firstTime == true) {
					System.out.println("Désolé, mettez au minimum 100€");
					cancel();
				}else {
					if(firstTime == true) {
						System.out.println("Vous avez "+time+" tour(s), bonne chance !");
					}
					if(balance <= 0) {
						System.out.println("Vous n'avez plus d'argent");
						cancel();
					}
					balance -= 100;
					if(time == 0) {
						System.out.println("Vous aviez "+oldBalance+"€, vous avez maintenant "+balance+"€ !");
						cancel();
					}else {
						int number1 = r.nextInt(7-1) + 1;
						int number2 = r.nextInt(7-1) + 1;
						int number3 = r.nextInt(7-1) + 1;
						
						System.out.println(number1+" "+number2+" "+number3);
						if(number1 == number2 && number2 == number3) {
							if(number1 == 7) {
								int money = 10000;
								System.out.println("Bravo, vous avez le jackpot, vous gagnez "+money+"€ !");
								balance += money;
								win+= money;
							}else {
								int money = 2000;
								System.out.println("Bravo, vous gagnez "+money+"€ !");
								balance += money;
								win+= money;
							}
						}
						
						time--;
						firstTime = false;
					}
				}
				
			}
			
		}, 100, 100);
	}

}
