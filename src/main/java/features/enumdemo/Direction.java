package features.enumdemo;

public enum Direction {
    NORTH(), NORTH_EAST(), EAST(), SOUTH_EAST(), SOUTH(), SOUTH_WEST(), WEST(), NORTH_WEST();

    public Direction nextDirection() {
        Direction[] allDirections = Direction.values();
        return allDirections[(this.ordinal() + 1)%allDirections.length];
    }

    public static void main(String[] args) {
        Direction d = Direction.valueOf("EAST");
        System.out.println("Direction is " + d.nextDirection());
    }

}
