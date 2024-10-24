public class SmartHomeSystem {

    interface Command {
        void execute();
        void undo();
    }

    class TurnTVOn implements Command {
        private TV tv;

        public TurnTVOn(TV tv) {
            this.tv = tv;
        }

        @Override
        public void execute() {
            tv.on();
        }

        @Override
        public void undo() {
            tv.off();
        }
    }

    class SetVolume implements Command {
        private Stereo stereo;
        private int volume;

        public SetVolume(Stereo stereo, int volume) {
            this.stereo = stereo;
            this.volume = volume;
        }

        @Override
        public void execute() {
            stereo.setVolume(volume);
        }

        @Override
        public void undo() {
            stereo.setVolume(0);
        }
    }

    class DimLights implements Command {
        private Light light;

        public DimLights(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.dim();
        }

        @Override
        public void undo() {
            light.bright();
        }
    }

    class TV {
        public void on() {
            System.out.println("TV is ON");
        }

        public void off() {
            System.out.println("TV is OFF");
        }
    }

    class Stereo {
        public void setVolume(int volume) {
            System.out.println("Stereo volume set to " + volume);
        }
    }

    class Light {
        public void dim() {
            System.out.println("Lights are dimmed");
        }

        public void bright() {
            System.out.println("Lights are bright");
        }
    }

    class RemoteControl {
        private Command[] slots;
        private Command lastCommand;

        public RemoteControl() {
            slots = new Command[3];
        }

        public void setCommand(int slot, Command command) {
            slots[slot] = command;
        }

        public void pressButton(int slot) {
            if (slots[slot] != null) {
                slots[slot].execute();
                lastCommand = slots[slot];
            } else {
                System.out.println("No command set for this slot.");
            }
        }

        public void pressUndo() {
            if (lastCommand != null) {
                lastCommand.undo();

