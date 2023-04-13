import java.util.Scanner;

public class ShipBattle {

    public static void setShips(String player, int[][] desk, Scanner scan){
        System.out.println(player + " please, set your ships: ");

        for(int i = 0; i < desk.length; i++) {

            System.out.print("Coordinates: ");
            int x = scan.nextInt();
            int y = scan.nextInt();

            if( (x < 1 || x > desk.length) || (y < 1 || y > desk.length) ) {
                System.out.println("Your coordinates are incorrect!, please rearrange them: ");
                i--;
            } else if (desk[x-1][y-1] == 1){
                System.out.println("The coordinates are already filled with a ship, please rearrange them: ");
                i--;
            } else {
                desk[x-1][y-1] = 1;
            }
        }

        printDesk(player, desk);

    }

    public static void printDesk( String player, int[][] desk) {

        System.out.println(player + " here is your desk: ");

        for(int i =0; i < desk.length; i++){
            for(int j =0; j < desk[i].length; j++){
                System.out.print(desk[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

    }


    public static void attack(String player, int[][] desk, Scanner scan){
        System.out.print("Set your coordinates to attack: ");

        int x = scan.nextInt();
        int y = scan.nextInt();

        if((x < 1 || x > desk.length) || (y<1 || y> desk.length)) {
            System.out.println("Your coordinates are incorrect!, please rearrange them: ");
            attack(player, desk, scan);
        } else if (desk[x-1][y-1] == 1) {
            System.out.println("You hit a ship!");
            desk[x-1][y-1] = 0;
        } else {
            System.out.println("Oops, you missed");
        }
    }

    public static boolean checkDesk (String player, int[][] desk){

        for (int i =0; i < desk.length; i++) {
            for(int j =0; j < desk[i].length; j++) {
                if(desk[i][j] == 1 ) {
                    return false;
                }
            }
        }

        System.out.println(player + "'s ships are all destroyed! Game over");
        return true;
    }


    public static void main(String[] args){
        System.out.print("Player1, please enter your name: ");
        Scanner scan = new Scanner(System.in);
        String player1 = scan.next();
        System.out.print("Player2, please enter your name: ");
        String player2 = scan.next();
        System.out.print("Please, set the size of the desk (nxn): ");
        int deskSize = scan.nextInt();

        int[][] player1Desk = new int [deskSize][deskSize];
        int[][] player2Desk = new int [deskSize][deskSize];

        setShips(player1, player1Desk, scan);
        setShips(player2, player2Desk, scan);

        while(true) {
            System.out.println(player1 + " please, make an attack");

            attack(player1, player2Desk, scan);

            if (checkDesk(player2, player2Desk)) {
                break;
            }

            System.out.println(player2 + " please, make an attack");

            attack(player2, player1Desk, scan);

            if (checkDesk(player1, player1Desk)) {
                break;
            }
        }

    }
}
