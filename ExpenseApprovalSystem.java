public class ExpenseApprovalSystem {

    abstract class Approver {
        protected Approver nextApprover;

        public void setNextApprover(Approver nextApprover) {
            this.nextApprover = nextApprover;
        }

        public abstract void handleRequest(ExpenseRequest request);
    }

    class TeamLead extends Approver {
        @Override
        public void handleRequest(ExpenseRequest request) {
            if (request.getAmount() <= 1000) {
                System.out.println("Team Lead approved expense: " + request);
            } else if (nextApprover != null) {
                nextApprover.handleRequest(request);
            }
        }
    }

    class Manager extends Approver {
        @Override
        public void handleRequest(ExpenseRequest request) {
            if (request.getAmount() <= 5000) {
                System.out.println("Manager approved expense: " + request);
            } else if (nextApprover != null) {
                nextApprover.handleRequest(request);
            }
        }
    }

    class Director extends Approver {
        @Override
        public void handleRequest(ExpenseRequest request) {
            if (request.getAmount() <= 10000) {
                System.out.println("Director approved expense: " + request);
            } else if (nextApprover != null) {
                nextApprover.handleRequest(request);
            }
        }
    }

    class CEO extends Approver {
        @Override
        public void handleRequest(ExpenseRequest request) {
            System.out.println("CEO approved expense: " + request);
        }
    }

    class ExpenseRequest {
        private double amount;
        private String purpose;

        public ExpenseRequest(double amount, String purpose) {
            this.amount = amount;
            this.purpose = purpose;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "ExpenseRequest{" +
                    "amount=" + amount +
                    ", purpose='" + purpose + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        ExpenseApprovalSystem system = new ExpenseApprovalSystem();

        Approver teamLead = system.new TeamLead();
        Approver manager = system.new Manager();
        Approver director = system.new Director();
        Approver ceo = system.new CEO();

        teamLead.setNextApprover(manager);
        manager.setNextApprover(director);
        director.setNextApprover(ceo);

        ExpenseRequest request1 = system.new ExpenseRequest(500, "Team lunch");
        ExpenseRequest request2 = system.new ExpenseRequest(3000, "Project expenses");
        ExpenseRequest request3 = system.new ExpenseRequest(8000, "Conference sponsorship");
        ExpenseRequest request4 = system.new ExpenseRequest(15000, "New software licenses");

        teamLead.handleRequest(request1);
        teamLead.handleRequest(request2);
        teamLead.handleRequest(request3);
        teamLead.handleRequest(request4);
    }
}
