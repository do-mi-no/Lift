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


    void nextFloor(){                               // State Pattern
        state.next(this);
    }


//    public boolean isUpRequest() {
//        building.
//        return false;//todo:
//    }
//    public boolean isDownRequest(int floorNumber, int liftLevel) {
//        return getFloorQueue(floorNumber).stream()
//                .anyMatch(num -> num < liftLevel);
//    }

    public int getNextStopGoingUp() {
        //todo:
        return -1;
    }


}
