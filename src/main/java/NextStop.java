import java.util.Arrays;
import java.util.Objects;

public class NextStop {

    private final Building building;
    private final Lift lift;

    public NextStop(Building building, Lift lift) {
        this.building = building;
        this.lift = lift;
    }

    public Integer getNextStop() {
        Integer result = null;
        boolean isCallAbove = getNextStopAbove() != null;
        boolean isCallBelow = getNextStopBelow() != null;
        boolean isLastMoveUp = lift.lastMoveUp();
        if (isLastMoveUp) {
            if (isCallAbove) {      //todo: incorrect name...
                result = getNextStopAbove();        // lift (still) goes up
            } else {
                result = getNextStopBelow();        // changing direction (lift goes down)
                if (result == null)
                    result = 0;                     // no more calls - return to the ground floor
            }
        } else {                                    // isLastMoveDown
            if (isCallBelow) {
                result = getNextStopBelow();        // lift (still) goes down
            } else {
                result = getNextStopAbove();        // changing direction (lift goes up)
                if (result == null)
                    result = 0;                     // no more calls - return to the ground floor
            }
        }
        System.out.println("nextStop = " + result);
        return result;
    }

    public Integer getNextStopAbove() {
        return getMinOfFirstThreeIfNullGetLast(
                getCallUpFromFloorAbove(),
                getCallUpFromCurrentFloor(),
                getCallUpFromLift(),
                getCallDownFromTopFloorAboveLift());

    }

    public Integer getNextStopBelow() {
        return getMaxOfFirstThreeIfNullGetLast(
                getCallDownFromFloorBelow(),
                getCallDownFromCurrentFloor(),
                getCallDownFromLift(),
                getCallUpFromLowermostFloorBelowLift());
    }

    private Integer getCallUpFromFloorAbove() {
        return building.getFloors().stream()
                .filter(floor -> floor.getLevel() > lift.getLevel())
                .filter(floor -> floor.getQueueUp().size() > 0)
                .findFirst().map(Floor::getLevel).orElse(null);
    }

    private Integer getCallUpFromCurrentFloor() {   //todo:...........................
        return building.getFloors().get(lift.getLevel()).getQueueUp().stream()
                .min(Integer::compareTo).orElse(null);
    }

    private Integer getCallDownFromCurrentFloor() {   //todo:...........................
        return building.getFloors().get(lift.getLevel()).getQueueDown().stream()
                .max(Integer::compareTo).orElse(null);
    }

    private Integer getCallUpFromLift() {
        return lift.getPassengers().stream()
                .filter(person -> person > lift.getLevel())
                .min(Integer::compareTo).orElse(null);
    }

    private Integer getCallDownFromTopFloorAboveLift() {
        return building.getFloors().stream()
                .filter(floor -> floor.getLevel() > lift.getLevel())
                .filter(floor -> floor.getQueueDown().size() > 0)
                .reduce((floor, floor2) -> floor2)
                .map(Floor::getLevel).orElse(null);
    }

    private Integer getCallDownFromFloorBelow() {
        return building.getFloors().stream()
                .filter(floor -> floor.getLevel() < lift.getLevel())
                .filter(floor -> floor.getQueueDown().size() > 0)
                .reduce((floor, floor2) -> floor2).map(Floor::getLevel).orElse(null);
    }

    private Integer getCallDownFromLift() {
        return lift.getPassengers().stream()
                .filter(person -> person < lift.getLevel())
                .max(Integer::compareTo).orElse(null);
    }

    private Integer getCallUpFromLowermostFloorBelowLift() {
        return building.getFloors().stream()
                .filter(floor -> floor.getLevel() < lift.getLevel())
                .filter(floor -> floor.getQueueUp().size() > 0)
                .findFirst()
                .map(Floor::getLevel).orElse(null);
    }

    public Integer getMaxOfFirstThreeIfNullGetLast(Integer w, Integer x, Integer y, Integer z) {
        return Arrays.asList(w, x, y).stream().filter(Objects::nonNull)
                .max(Integer::compareTo).orElse(z);
    }

    public Integer getMinOfFirstThreeIfNullGetLast(Integer w, Integer x, Integer y, Integer z) {
        return Arrays.asList(w, x, y).stream().filter(Objects::nonNull)
                .min(Integer::compareTo).orElse(z);
    }

}

