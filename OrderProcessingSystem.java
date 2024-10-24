public class OrderProcessingSystem {

    interface State {
        void payOrder(Order order);
        void shipOrder(Order order);
        void deliverOrder(Order order);
        void cancelOrder(Order order);
    }

    class NewState implements State {
        @Override
        public void payOrder(Order order) {
            System.out.println("Order paid.");
            order.setState(order.paidState);
        }

        @Override
        public void shipOrder(Order order) {
            System.out.println("Order cannot be shipped. Please pay first.");
        }

        @Override
        public void deliverOrder(Order order) {
            System.out.println("Order cannot be delivered. Please pay first.");
        }

        @Override
        public void cancelOrder(Order order) {
            System.out.println("Order canceled.");
            order.setState(order.cancelledState);
        }
    }

    class PaidState implements State {
        @Override
        public void payOrder(Order order) {
            System.out.println("Order is already paid.");
        }

        @Override
        public void shipOrder(Order order) {
            System.out.println("Order shipped.");
            order.setState(order.shippedState);
        }

        @Override
        public void deliverOrder(Order order) {
            System.out.println("Order cannot be delivered. Please ship first.");
        }

        @Override
        public void cancelOrder(Order order) {
            System.out.println("Order cannot be canceled after payment.");
        }
    }

    class ShippedState implements State {
        @Override
        public void payOrder(Order order) {
            System.out.println("Order is already paid.");
        }

        @Override
        public void shipOrder(Order order) {
            System.out.println("Order is already shipped.");
        }

        @Override
        public void deliverOrder(Order order) {
            System.out.println("Order delivered.");
            order.setState(order.deliveredState);
        }

        @Override
        public void cancelOrder(Order order) {
            System.out.println("Order cannot be canceled after shipping.");
        }
    }

    class DeliveredState implements State {
        @Override
        public void payOrder(Order order) {
            System.out.println("Order is already paid.");
        }

        @Override
        public void shipOrder(Order order) {
            System.out.println("Order is already delivered.");
        }

        @Override
        public void deliverOrder(Order order) {
            System.out.println("Order is already delivered.");
        }

        @Override
        public void cancelOrder(Order order) {
            System.out.println("Order cannot be canceled after delivery.");
        }
    }

    class CancelledState implements State {
        @Override
        public void payOrder(Order order) {
            System.out.println("Order is canceled and cannot be paid.");
        }

        @Override
        public void shipOrder(Order order) {
            System.out.println("Order is canceled and cannot be shipped.");
        }

        @Override
        public void deliverOrder(Order order) {
            System.out.println("Order is canceled and cannot be delivered.");
        }

        @Override
        public void cancelOrder(Order order) {
            System.out.println("Order is already canceled.");
        }
    }

    class Order {
        State newState;
        State paidState;
        State shippedState;
        State deliveredState;
        State cancelledState;

        State currentState;

        public Order() {
            newState = new NewState();
            paidState = new PaidState();
            shippedState = new ShippedState();
            deliveredState = new DeliveredState();
            cancelledState = new CancelledState();
            currentState = newState;
        }

        public void setState(State state) {
            currentState = state;
        }

        public void payOrder() {
            currentState.payOrder(this);
        }

        public void shipOrder() {
            currentState.shipOrder(this);
        }

        public void deliverOrder() {
            currentState.deliverOrder(this);
        }

        public void cancelOrder() {
            currentState.cancelOrder(this);
        }
    }

    public static void main(String[] args) {
        OrderProcessingSystem system = new OrderProcessingSystem();
        Order order = system.new Order();

        order.payOrder();  // Order paid
        order.shipOrder(); // Order shipped
        order.deliverOrder(); // Order delivered

        order.cancelOrder(); // Order cannot be canceled after delivery
    }
}
