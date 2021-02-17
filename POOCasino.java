import ntu.r09922114.gambling.Player;
import ntu.r09922114.gambling.Computer;
import java.io.*;
import java.util.Scanner;


public class POOCasino {
    public static void main(String[] argv) {
        // Create the Scanner
        Scanner sc = new Scanner(System.in);
        
        // Show the developer Info
        System.out.println("POOCasino Jacks or better, written by r09922114 Tai-Yu Su");
        
        // Request player's name
        System.out.print("Please enter your name: ");
        
        // Create a Player object with 1000 P-dallars initially
        Player p = new Player(sc.nextLine(), 1000);
        
        // Create a Computer object
        Computer c = new Computer();

        // Show the welcome Info
        System.out.println("Welcome, " + p.getName() + ".");
        
        // Initialize variables
        int round = 0, bet, payoff;

        while (true) {
            round++;
            System.out.println("You have " + p.getAsset() + " P-dollars now.");
            System.out.print("Please enter your P-dollar bet for round " + round + " (1-5 or 0 for quitting the game): ");
            
            // Place a bet
            bet = sc.nextInt();
            
            // Exit if the play enter 0
            if (bet == 0)
                break;
                
            // Place a bet again until the bet is valid
            while (true) {
                if (bet < 0 || bet > 5)
                    System.out.print("Your bet is not between 1-5, please enter your P-dollar bet again: ");
                else if (bet > p.getAsset())
                    System.out.print("Your asset is insufficient, please enter your P-dollar bet again: ");
                else
                    break;
                bet = sc.nextInt();
            }
            
            // Clear the buffer
            sc.nextLine();
            
            // Deduct the bet from the player's asset
            p.placeAbet(bet);
            
            // The computer shuffles the cards
            c.startNewRound();
            
            // The player draws 5 cards from the dack
            p.drawCards(c);
            
            // Show the cards in the player's hand
            System.out.print("Your cards are");
            p.showCards();
            
            // Player select the cards he wants to keep
            System.out.print("Which cards do you want to keep? ");
            p.replaceCards(sc.nextLine(), c);
            
            // Show the cards in the player's hand again
            System.out.print("Your new cards are");
            p.showCards();
            
            // Computer Classify the cards in player's hand
            String hand = c.classify(p.getCards());
            
            // Computer pays the player according to his hand
            payoff = c.pays(hand, bet);
            p.getPayoff(payoff);
            System.out.println("You get a " + hand + " hand. "  + "The payoff is " + payoff + ".");
        }
        
        // Show the quit message
        System.out.print("Good bye, " + p.getName() + ". ");
        System.out.println("You played for " + (round - 1) + " rounds and have " + p.getAsset() + " dollars now.");
    }
}
