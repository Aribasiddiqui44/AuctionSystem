import java.util.Scanner;

public class AuctionSystemTester {
    public static void main(String[] args) {
        char choice;
        AuctionSystem auctionSystem = new AuctionSystem();
        try {
            do {
                System.out.println("\n\n");
                System.out.println("1. Start Auction");
                System.out.println("2. Place Bid");
                System.out.println("3. Close Auction");
                System.out.println("4. Quit");
                System.out.println("Enter choice 1-4");
                choice = new Scanner(System.in).next().charAt(0);
                System.out.println(); // blank line

                try {
                    switch (choice) {
                        case '1':
                            option1(auctionSystem);
                            break;
                        case '2':
                            option2(auctionSystem);
                            break;
                        case '3':
                            option3(auctionSystem);
                            break;
                        default:
                            break;
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            } while (choice != '4');
        } catch (RuntimeException e) {
            System.out.println("Initial object breaks invariant");
            System.out.println("\nPress Enter to quit");
            new Scanner(System.in).nextLine();
        }
    }

    private static void option1(AuctionSystem auctionSystem) {
        System.out.println("Enter initial price: ");
        double initPrice = new Scanner(System.in).nextDouble();
        System.out.println("Enter auction item: ");
        String item = new Scanner(System.in).next();
        System.out.println("Enter start date: ");
        String startDate = new Scanner(System.in).next();
        System.out.println("Enter end date: ");
        String endDate = new Scanner(System.in).next();

        auctionSystem.startAuction(initPrice, item, startDate, endDate);
        System.out.println("Auction started successfully");
    }

    private static void option2(AuctionSystem auctionSystem) {
        System.out.println("Enter bid amount: ");
        double amount = new Scanner(System.in).nextDouble();
        System.out.println("Enter bid date: ");
        String date = new Scanner(System.in).next();
        System.out.println("Enter bidder name: ");
        String name = new Scanner(System.in).next();

        auctionSystem.placeBid(amount, date, name);
        System.out.println("Bid placed successfully");
    }

    private static void option3(AuctionSystem auctionSystem) {
        System.out.println("Enter closing date: ");
        String closingDate = new Scanner(System.in).next();

        auctionSystem.closeAuction(closingDate);

        System.out.println("Auction closed successfully");
    }
}
