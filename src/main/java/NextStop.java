public class NextStop {

    private final Building building;
    private final Lift lift;

    public NextStop(Building building, Lift lift) {
        this.building = building;
        this.lift = lift;
    }

    private Integer getNextStopUpFromFloors() {
        return building.getFloors().stream()
                .filter(floor -> floor.getLevel() > lift.getLevel())
                .filter(floor -> floor.getQueueUp().size() > 0)
                .findFirst().map(Floor::getLevel).orElse(null);
    }

    private Integer getNextStopDownFromFloors() {
        return building.getFloors().stream()
                .filter(floor -> floor.getLevel() < lift.getLevel())
                .filter(floor -> floor.getQueueDown().size() > 0)
                .reduce((floor, floor2) -> floor2).map(Floor::getLevel).orElse(null);
    }

    private Integer getNextStopUpFromLift() {
        return lift.getPassengers().stream()
                .filter(person -> person > lift.getLevel())
                .min(Integer::compareTo).orElse(null);
    }

    private Integer getNextStopDownFromLift() {
        return lift.getPassengers().stream()
                .filter(person -> person < lift.getLevel())
                .max(Integer::compareTo).orElse(null);
    }

    public Integer getNextStopUp() {
        final Integer s1 = getNextStopUpFromFloors();
        final Integer s2 = getNextStopUpFromLift();
        Integer result = null;
        if (s1 != null && s2 != null)
            result = Math.min(s1, s2);
        if (s1 == null)
            result = s2;
        if (s2 == null)
            result = s1;
        if (result == null)
            result = getTopFloorAboveLiftWithCallDown();
        return result;
    }

    public Integer getNextStopDown() {
        final Integer s1 = getNextStopDownFromFloors();
        final Integer s2 = getNextStopDownFromLift();
        Integer result = null;
        if (s1 != null && s2 != null)
            result = Math.max(s1, s2);
        if (s1 == null)
            result = s2;
        if (s2 == null)
            result = s1;
        if (result == null)
            result = getTopFloorAboveLiftWithCallDown();                    //todo: check whether it solves 'nullpointer at the end'
        if (result == null)
            result = 0;
        return result;
    }

    public Integer getNextStop() {          //todo: ITS CRAZY!
        Integer result = null;
        if (lift.lastMoveUp()) {
            result = getNextStopUp();
            if (result == null)
                result = getNextStopDown();
        } else {
            result = getNextStopDown();
            if (result == null)
                result = getNextStopUp();
//            if (result == null)
//                result = 0;
        }
        return result;         //todo: testing needed!!! and some refactoring...
    }

    private Integer getTopFloorAboveLiftWithCallDown() {
        return building.getFloors().stream()
                .filter(floor -> floor.getLevel() > lift.getLevel())
                .filter(floor -> floor.getQueueDown().size() > 0)
                .reduce((floor, floor2) -> floor2)
                .map(Floor::getLevel).orElse(null);
    }

}
