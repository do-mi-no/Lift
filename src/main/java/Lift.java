import java.util.List;

public class Lift {
    private static Lift lift = null;
    private final int capacity;

    private int level = 0;
    private Queue passengers = new Queue();
    private State state = new StateStart();

    private Lift(int capacity) {
        this.capacity = capacity;
    }

    synchronized static void init(int capacity){
        if(lift != null){
            throw new AssertionError("Lift already initialized.");
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
    void addPassengers(Integer[] passengers) {
        this.passengers.addPersons(passengers);
    }
    State getState() {
        return state;
    }

    void setState(State state) {
        this.state = state;
    }
    void takeThePassenger(int passenger){
        if(isFull()){
            throw new IllegalArgumentException("The lift is already full.");
        }
        passengers.addPerson(passenger);
    }

    void dropOffThePassenger(int passenger){
        passengers.removePerson(passenger);
    }

    int getOccupancy(){
        return passengers.getQueue().size();
    }

    boolean isFull(){
        return passengers.getQueue().size() >= capacity;
    }

    void nextFloor(){
        state.next(this);
    }

    @Override
    public String toString() {
        return "Lift{" +
                "capacity=" + capacity +
                ", level=" + level +
                ", passengers=" + passengers +
                ", state=" + state +
                '}';
    }
}
