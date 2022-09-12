package features.enumdemo;

public enum SingletonDemo {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    public static void main(String[] args) {
        SingletonDemo.INSTANCE.leaveTheBuilding();
    }
}
