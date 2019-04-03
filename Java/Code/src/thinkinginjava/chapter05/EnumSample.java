enum Spiciness {
    NOT, MILD, MEDIUM, HOT, FLAMING
}

public class EnumSample {
    public static void main(String args[]) {
        for (Spiciness s: Spiciness.values()) {
            System.out.println(s + ", ordinal " + s.ordinal());
        }
        Spiciness s = Spiciness.HOT;
        switch(s) {
            case NOT:
                System.out.println("NOT");
                break;
            case MILD:
                System.out.println("MILD");
                break;
            case MEDIUM:
                System.out.println("MEDIUM");
                break;
            case HOT:
                System.out.println("HOT");
                break;
            case FLAMING:
                System.out.println("FLAMING");
                break;
            default:
                System.out.println("UNKONWN");
        }
    }
} /**
NOT, ordinal 0
MILD, ordinal 1
MEDIUM, ordinal 2
HOT, ordinal 3
FLAMING, ordinal 4
*/