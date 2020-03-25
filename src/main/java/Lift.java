import java.util.*;

public class Lift {
    private final int capacity;
    private int level = 0;
    private Route route = new Route();
    private final Queue<Integer> passengers = new LinkedList<>();

    Lift(int capacity) {
        int minCap = 1;
        if (capacity < minCap) {
            throw new IllegalArgumentException("Lift capacity must be at least " + minCap + ".");
        }
        this.capacity = capacity;
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

    public Route getRoute() {
        return route;
    }

    Queue<Integer> getPassengers() {
        return passengers;
    }

    int getOccupancy() {
        return (int) passengers.stream().filter(Objects::nonNull).count();
    }

    boolean lastMoveUp(){
        return route.lastMoveUp();
    }

    boolean isNotFull() {
        return passengers.stream().filter(Objects::nonNull).count() < capacity;
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
