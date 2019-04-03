//: SpaceShip.java

public class SpaceShip extends SpaceShipControls {
    private String name;
    public SpaceShip(String name) {
        this.name = name;
    }
    public String toString() { return name; }
    public static void main(String args[]) {
        SpaceShip ss = new SpaceShip("NSEA Protector");
        ss.forward(100);
    }
}