import java.util.*;

public class Lift {
    private static Lift lift = null;
    private final int capacity;

    private int level = 0;
    private final Queue<Integer> passengers = new LinkedList<>();

    private Lift(int capacity) {
        this.capacity = capacity;
    }

    synchronized static void init(int capacity) {
        if (lift != null) {
            throw new AssertionError("Lift already initialized.");
        }
        if (capacity < 5) {
            throw new IllegalArgumentException("Lift capacity must be at least 5.");
        }
        lift = new Lift(capacity);
    }

    static Lift getInstance() {
        if (lift == null) {
            throw new AssertionError("You have to call init first.");
        }
        return lift;
    }

    public int getCapacity() {
        return capacity;
    }

    int getLevel() {
        return level;
    }

    void setLevel(int level) {
        this.level = level;
    }

    Queue<Integer> getPassengers() {
        return passengers;
    }

    void addPerson(Integer passenger) {
        if (isFull()) {
            throw new IllegalArgumentException("The lift is already full.");
        }
        passengers.add(passenger);
    }

    void dropPersonOff(Integer passenger) {
        passengers.remove(passenger);
    }

    int getOccupancy() {
        return (int) passengers.stream().filter(Objects::nonNull).count();
    }

    boolean isFull() {
        return passengers.stream().filter(Objects::nonNull).count() >= capacity;
    }

    @Override
    public String toString() {
        return "Lift{" +
                "capacity=" + capacity +
                ", level=" + level +
                ", passengers=" + passengers +
                '}';
    }
}
