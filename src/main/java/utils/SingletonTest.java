package utils;

public enum SingletonTest {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    public static void main(String[] args) {
        SingletonTest.INSTANCE.leaveTheBuilding();
    }
}
