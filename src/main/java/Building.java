import java.util.*;

public class Building {
    private List<Floor> floors = new ArrayList<>();

    public Building(int[][] queues) {
        if (queues.length == 0) {
            throw new IllegalArgumentException("Input queues can't be empty.");
        }
        for (int i = 0; i < queues.length; i++) {
            floors.add(new Floor(i, queues[i]));
        }
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public Floor getFloor(int floorNum) {
        return  floors.stream().filter(floor -> floor.getLevel() == floorNum)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Floor '" + floorNum + "' is out of range."));
    }

    public Queue<Integer> getFloorQueueUp(int floorNumber) {
        return getFloor(floorNumber).getQueueUp();
    }
    public Queue<Integer> getFloorQueueDown(int floorNumber) {
        return getFloor(floorNumber).getQueueDown();
    }

    @Override
    public String toString() {
        return "Building{" +
                "floors=" + floors +
                '}';
    }
}
