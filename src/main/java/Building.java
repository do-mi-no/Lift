import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Building {
    private Map<Integer, Floor> floors = new HashMap<>();

    public Building(int[][] queues) {
        if (queues.length == 0) {
            throw new IllegalArgumentException("Input queues can't be empty.");
        }
        for (int i = 0; i < queues.length; i++) {
            Queue queue = new Queue(queues[i]);
            floors.put(i, new Floor(queue));
        }
    }

    public List<Floor> getAllQueues(int floorNum) {
        return new ArrayList<>(floors.values());
    }

    public Floor getFloor(int floorNum) {
        return floors.get(floorNum);
    }

    public List<Integer> getFloorQueue(int floorNumber) {
        return floors.get(floorNumber).getLiftQueue().getQueue();
    }

    public List<Integer> getFloorIndexes() {
        return new ArrayList<>(floors.keySet());
    }



    @Override
    public String toString() {
        return "Building{" +
                "floors=" + floors +
                '}';
    }
}
