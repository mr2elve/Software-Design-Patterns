import java.util.ArrayList;
import java.util.List;

public class ChatRoomApplication {

    interface ChatMediator {
        void sendMessage(String message, User user);
        void addUser(User user);
    }

    class ChatRoom implements ChatMediator {
        private List<User> users;

        public ChatRoom() {
            users = new ArrayList<>();
        }

        @Override
        public void sendMessage(String message, User user) {
            for (User u : users) {
                // Exclude the sender from receiving their own message
                if (u != user) {
                    u.receiveMessage(message);
                }
            }
        }

        @Override
        public void addUser(User user) {
            users.add(user);
        }
    }

    abstract class User {
        protected ChatMediator mediator;
        protected String name;

        public User(ChatMediator mediator, String name) {
            this.mediator = mediator;
            this.name = name;
        }

        public abstract void sendMessage(String message);
        public abstract void receiveMessage(String message);
    }

    class ChatUser extends User {
        public ChatUser(ChatMediator mediator, String name) {
            super(mediator, name);
        }

        @Override
        public void sendMessage(String message) {
            System.out.println(name + " sending: " + message);
            mediator.sendMessage(message, this);
        }

        @Override
        public void receiveMessage(String message) {
            System.out.println(name + " received: " + message);
        }
    }

    public static void main(String[] args) {
        ChatRoomApplication chatApp = new ChatRoomApplication();
        ChatRoom chatRoom = chatApp.new ChatRoom();

        User user1 = chatApp.new ChatUser(chatRoom, "Alice");
        User user2 = chatApp.new ChatUser(chatRoom, "Bob");
        User user3 = chatApp.new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.sendMessage("Hello everyone!");
        user2.sendMessage("Hi Alice!");
        user3.sendMessage("Hey there!");
    }
}
