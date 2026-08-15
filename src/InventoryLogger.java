public class InventoryLogger implements Observer {
    @Override
    public void update(String eventType, String message) {
        System.out.println("Transaction recorded: " + eventType + " : " + message);
    }
}