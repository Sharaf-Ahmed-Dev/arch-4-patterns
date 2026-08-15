public class CustomerNotificationListener implements Observer {
    @Override
    public void update(String eventType, String message) {
        if ("PURCHASE_COMPLETE".equals(eventType)) {
            System.out.println("Purchase completed. " + message);
        }
    }
}