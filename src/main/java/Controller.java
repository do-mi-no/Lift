import java.util.Queue;

public class Controller {
    private Building building;
    private Lift lift;
    private State state = new StateArrived();
    private NextStop nextStop;

    public Controller(Building building, Lift lift) {
        this.building = building;
        this.lift = lift;
        this.nextStop = new NextStop(building, lift);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Building getBuilding() {
        return building;
    }

    public Lift getLift() {
        return lift;
    }

    public int getNextStop() {
        return nextStop.getNextStop();
    }

    void next() {
        state.next(this);
    }                     // State Pattern

    public void takeAsManyPassengersAsPossibleFromQueueUp() {
        Queue<Integer> queueToTake = building.getFloorQueueUp(lift.getLevel());
        while (queueToTake.size() > 0 && lift.isNotFull()) {
            lift.getPassengers().add(queueToTake.poll());
        }
    }

    public void takeAsManyPassengersAsPossibleFromQueueDown() {
        Queue<Integer> queueToTake = building.getFloorQueueDown(lift.getLevel());
        while (queueToTake.size() > 0 && lift.isNotFull()) {
            lift.getPassengers().add(queueToTake.poll());
        }
    }

    public void takeAsManyPassengersAsPossible() {
        if (nextStop.getNextStop() > lift.getLevel()) {
            takeAsManyPassengersAsPossibleFromQueueUp();
        } else {
            takeAsManyPassengersAsPossibleFromQueueDown();
        }
    }

    public void sendLiftTo(Integer floor) {
        lift.setLevel(floor);
    }

    public void dropOffPassengers() {
        while (lift.getPassengers().contains(lift.getLevel())) {
            final boolean droppedOff = lift.getPassengers().remove(lift.getLevel());
            final Queue<Integer> queueDelivered = building.getFloor(lift.getLevel()).getQueueDelivered();
            if (droppedOff) {
                queueDelivered.add(lift.getLevel());
            }
        }
    }

    public boolean isAnyRequest() {
        final int liftOccupancy = lift.getOccupancy();
        final long waitingCount = building.getFloors().stream()
                .map(floor -> (floor.getQueueUp().size() + floor.getQueueDown().size()))
                .reduce(0, Integer::sum);
        return liftOccupancy + waitingCount > 0;
    }

    public void addStopToRoute() {
        lift.getRoute().addNewStop(lift.getLevel());
    }


}
