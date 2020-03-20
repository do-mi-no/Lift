import java.util.List;

public class Lift {
    private static Lift lift = null;
    private final int capacity;

    private int level = 0;
    private final Queue passengers = new Queue();

    private Lift(int capacity) {
        this.capacity = capacity;
    }

    synchronized static void init(int capacity){
        if(lift != null){
            throw new AssertionError("Lift already initialized.");
        }
        if(capacity<5){
            throw new IllegalArgumentException("Lift capacity must be at least 5.");
        }
        lift = new Lift(capacity);
    }
    static Lift getInstance(){
        if(lift == null){
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
    List<Integer> getPassengers() {
        return passengers.getQueue();
    }

    void addPersons(Integer[] passengers) {
        this.passengers.addPersons(passengers);
    }

    void addPerson(int passenger){
        if(isFull()){
            throw new IllegalArgumentException("The lift is already full.");
        }
        passengers.addPerson(passenger);
    }

    void removePerson(int passenger){
        passengers.removePerson(passenger);
    }

    int getOccupancy(){
        return passengers.getQueue().size();
    }

    boolean isFull(){
        return passengers.getQueue().size() >= capacity;
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
