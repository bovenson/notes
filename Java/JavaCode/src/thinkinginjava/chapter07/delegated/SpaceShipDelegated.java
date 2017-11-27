//: SpaceShipDelegated.java

public class SpaceShipDelegated {
    private String name;
    private SpaceShipControls controls = new SpaceShipControls();
    public SpaceShipDelegated(String name) {
        this.name = name;
    }
    // Delegated methods
    public void back(int velocity) {
        controls.back(velocity);
    }

    public void down(int velocity) {
        controls.down(velocity);
    }

    public void left(int velocity) {
        controls.left(velocity);
    }

    public void right(int velocity) {
        controls.right(velocity);
    }

    public void forward(int velocity) {
        controls.forward(velocity);
    }

    public void turboBoost() {
        controls.turboBoost();
    }

    public static void main(String args[]) {
        SpaceShipDelegated ssd = new SpaceShipDelegated("NSEA Protector");
        ssd.forward(100);
    }
}