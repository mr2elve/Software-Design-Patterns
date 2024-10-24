public class PaymentSystem {

    interface PaymentStrategy {
        void processPayment(double amount);
    }

    class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public void processPayment(double amount) {
            System.out.println("Processing credit card payment of $" + amount + " using card: " + cardNumber);
        }
    }

    class PayPalPayment implements PaymentStrategy {
        private String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void processPayment(double amount) {
            System.out.println("Processing PayPal payment of $" + amount + " using email: " + email);
        }
    }

    class CryptoPayment implements PaymentStrategy {
        private String walletAddress;

        public CryptoPayment(String walletAddress) {
            this.walletAddress = walletAddress;
        }

        @Override
        public void processPayment(double amount) {
            System.out.println("Processing cryptocurrency payment of $" + amount + " to wallet: " + walletAddress);
        }
    }

    class ShoppingCart {
        private double total;
        private PaymentStrategy paymentStrategy;

        public void addItem(double price) {
            total += price;
        }

        public double getTotal() {
            return total;
        }

        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void checkout() {
            if (paymentStrategy == null) {
                throw new IllegalStateException("Payment method not set!");
            }
            paymentStrategy.processPayment(total);
        }
    }

    public static void main(String[] args) {
        PaymentSystem system = new PaymentSystem();
        ShoppingCart cart = system.new ShoppingCart();

        cart.addItem(50);
        cart.addItem(100);

        cart.setPaymentStrategy(system.new CreditCardPayment("1234-5678-9012-3456"));
        cart.checkout();

        cart.setPaymentStrategy(system.new PayPalPayment("user@example.com"));
        cart.checkout();

        cart.setPaymentStrategy(system.new CryptoPayment("1A2b3C4d5E6F7g8H9I"));
        cart.checkout();
    }
}
