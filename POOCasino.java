import ntu.r09922114.gambling.Player;
import ntu.r09922114.gambling.Computer;
import java.io.*;
import java.util.Scanner;


public class POOCasino {
    public static void main(String[] argv) {
        Scanner sc = new Scanner(System.in);
        System.out.println("POOCasino Jacks or better, written by r09922114 Tai-Yu Su");
        System.out.print("Please enter your name: ");
        Player p = new Player(sc.nextLine(), 1000);
        Computer c = new Computer();

        System.out.println("Welcome, " + p.getName() + ".");
        int round = 0, bet, payoff;

        while (true) {
            round++;
            System.out.println("You have " + p.getAsset() + " P-dollars now.");
            System.out.print("Please enter your P-dollar bet for round " + round + " (1-5 or 0 for quitting the game): ");
            bet = p.placeAbet();
            if (bet == 0)
                break;
            c.startNewRound();
            p.drawCards(c);
            System.out.print("Your cards are");
            p.showCards();
            System.out.print("Which cards do you want to keep? ");
            p.replaceCards(sc.nextLine(), c);
            System.out.print("Your new cards are");
            p.showCards();
            String hand = c.classify(p.getCards());
            payoff = c.pays(hand, bet);
            p.getPayoff(payoff);
            System.out.println("You get a " + hand + " hand. "  + "The payoff is " + payoff + ".");
        }
        System.out.print("Good bye, " + p.getName() + ". ");
        System.out.println("You played for " + (round - 1) + " rounds and have " + p.getAsset() + " dollars now.");
    }
}
