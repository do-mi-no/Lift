import java.util.Queue;

public class Controller {
    private Building building;
    private Lift lift;
    private State state = new StateStart();

    public Controller(Building building, Lift lift) {
        this.building = building;
        this.lift = lift;
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

    void nextFloor() {
        state.next(this);
    }                     // State Pattern

    public void takeAsManyAsPossibleFromQueueUp() {
        final Queue<Integer> queueToTake = building.getFloorQueueUp(lift.getLevel());
        while (queueToTake.size() > 0 && !lift.isFull()) {
            lift.getPassengers().add(queueToTake.poll());
        }
    }

    public void takeAsManyAsPossibleFromQueueDown() {
        final Queue<Integer> queueToTake = building.getFloorQueueDown(lift.getLevel());
        while (queueToTake.size() > 0 && !lift.isFull()) {
            lift.getPassengers().add(queueToTake.poll());
        }
    }

    public void sendLiftToTheFloor(Integer floorNumber) {
        lift.setLevel(floorNumber);
    }

    public Integer getNextStopUpFromFloors() {
        return building.getFloors().stream()
                .filter(floor -> floor.getLevel() > lift.getLevel())
                .filter(floor -> floor.getQueueUp().size() > 0)
                .findFirst().map(Floor::getLevel).orElse(null);
        //.findFirst().map(Floor::getLevel).orElseThrow(() -> new IllegalArgumentException("No calls from floors above."));
    }

    public Integer getNextStopDownFromFloors() {
        return building.getFloors().stream()
                .filter(floor -> floor.getLevel() < lift.getLevel())
                .filter(floor -> floor.getQueueDown().size() > 0)
                .reduce((floor, floor2) -> floor2).map(Floor::getLevel).orElse(null);
    }

    public Integer getNextStopUpFromLift() {
        return lift.getPassengers().stream()
                .filter(person -> person > lift.getLevel())
                .findFirst().orElse(null);
    }

    public Integer getNextStopDownFromLift() {
        return lift.getPassengers().stream()
                .filter(person -> person < lift.getLevel())
                .reduce((integer, integer2) -> integer2).orElse(null);
    }

    public Integer getNextStopUp() {
        final Integer s1 = getNextStopUpFromFloors();
        final Integer s2 = getNextStopUpFromLift();
        Integer result = null;
        if (s1 != null && s2 != null)
            result = Math.min(s1, s2);
        else if (s1 == null)
            result = s2;
        else if (s2 == null)
            result = s1;
        return result;
    }

    public Integer getNextStopDown() {
        final Integer s1 = getNextStopDownFromFloors();
        final Integer s2 = getNextStopDownFromLift();
        Integer result = null;
        if (s1 != null && s2 != null)
            result = Math.max(s1, s2);
        else if (s1 == null)
            result = s2;
        else if (s2 == null)
            result = s1;
        return result;
    }

    public Integer getTopFloorWithCallDown() {
        return building.getFloors().stream()
                .filter(floor -> floor.getQueueDown().size() > 0)
                .reduce((floor, floor2) -> floor2)
                .map(Floor::getLevel).orElseThrow(() -> new IllegalArgumentException("No lift calls down."));
    }

    public void dropOffPassengers() {
//        if (lift.getPassengers().size() > 0)
//            lift.getPassengers().removeIf(person -> person.equals(lift.getLevel()));        //todo: instead of removing, implement moving to queueDelivered
        while (lift.getPassengers().contains(lift.getLevel())) {
            final boolean droppedOff = lift.getPassengers().remove(lift.getLevel());
            final Queue<Integer> queueDelivered = building.getFloor(lift.getLevel()).getQueueDelivered();
            if (droppedOff) {
                queueDelivered.add(lift.getLevel());
            }
        }
    }

//    public int getRequestCount() {
//        final int liftOccupancy = lift.getOccupancy();
//        final int waitingCount = (int) building.getFloors().stream()
//                .map(floor -> floor.getQueueUp().size() + floor.getQueueDown()
//                        .size()).count();
//        return liftOccupancy + waitingCount;
//    }

    public boolean isAnyRequest() {
        final int liftOccupancy = lift.getOccupancy();
        final long waitingCount = building.getFloors().stream()
                .map(floor -> (floor.getQueueUp().size() + floor.getQueueDown().size()))
                .reduce(0, Integer::sum);
        return liftOccupancy + waitingCount > 0;
    }
}
