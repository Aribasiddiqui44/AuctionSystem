import java.util.ArrayList;
import java.util.List;

public class AuctionSystem implements InvariantCheck {
    private Double reservePrice;
    private Double currentBid;
    private String bidItem;
    private String startDate;
    private String endDate;
    private String auctionDate;
    private Double bidAmount;
    private List<String> stack;

    private void invTest() {
        if (!inv()) {
            throw new RuntimeException("Invariant violation!");
        }
    }

    // Constructor
    public AuctionSystem() {
        this.reservePrice = null;
        this.currentBid = null;
        this.bidItem = null;
        this.startDate = null;
        this.endDate = null;
        this.auctionDate = null;
        this.bidAmount = null;
        this.stack = new ArrayList<>();
        invTest();
    }

    // Function to check if price is in range
    private boolean inPriceRange(Double price) {
        return price >= MIN_PRICE;
    }

    // Operation to start the auction
    public void startAuction(Double initPrice, String bItem, String sDate, String eDate) {
        // Preconditions
        if ((reservePrice != null) && startDate != null && (endDate != null) && bidItem != null) {
            throw new IllegalStateException("Auction cannot start");
        }      

        // Postconditions
        reservePrice = initPrice;
        startDate = sDate;
        endDate = eDate;
        currentBid = reservePrice;
        bidItem = bItem;
        invTest();
    }

    // Operation to place a bid
    public void placeBid(Double amount, String date, String name) { 
        if (!(((amount > reservePrice) && (amount > currentBid)) &&  (startDate.compareTo(date) <= 0 && endDate.compareTo(date) >= 0))) {
            throw new IllegalArgumentException("Invalid bid");
        }

        // Postconditions
        bidAmount = amount;
        auctionDate = date;
        stack.add(name);
        currentBid = bidAmount;
        invTest();
    }

    // Operation to close the auction
    public void closeAuction(String date) {
        // Preconditions
        if (!(currentBid > reservePrice) && stack.isEmpty() || !date.equals(endDate)) {
            throw new IllegalStateException("Invalid auction closure");
        }
        
        // Postconditions
        if (!stack.isEmpty()) {
            String auctionWinner = stack.get(stack.size() - 1); // Get the last bidder
            Double winningBid = currentBid;

            System.out.println("Auction closed successfully");
            System.out.println("Winner: " + auctionWinner);
            System.out.println("Winning Bid: " + winningBid);
            invTest();

        } else {
            System.out.println("Auction closed with no winner"); // Handle the case with no bidders
        }
    }

    // Constants
    private static final Double MIN_PRICE = 1000.0;

    public boolean inv() {
        return  ( bidAmount == null || bidAmount != null) &&
                ( reservePrice == null || (inPriceRange(reservePrice)) ) &&
                (bidItem == null || bidItem != null) &&
                (currentBid == null || currentBid == reservePrice || currentBid == bidAmount) &&
                (startDate == null || startDate != null) &&
                (endDate == null || endDate != null && (endDate.compareTo(startDate) >=0)) &&
                (auctionDate == null || auctionDate != null) &&
                (stack.isEmpty() || !stack.isEmpty());
    }

    public static void main(String[] args) {
        AuctionSystem auctionSystem = new AuctionSystem();

        // Test StartAuction
        auctionSystem.startAuction(1000.0, "Item1", "2024-01-10", "2024-01-20");
        System.out.println("Auction started successfully");

        // Test PlaceBid
        auctionSystem.placeBid(1200.0, "2024-01-15", "Bidder1");
        System.out.println("Bid placed successfully");

        auctionSystem.placeBid(1500.0, "2024-01-17", "Bidder2");
        System.out.println("Bid placed successfully");

        // Test CloseAuction
        auctionSystem.closeAuction("2024-01-20");
        System.out.println("Auction closed successfully");
    }
}
