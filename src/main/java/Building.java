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

    @Override
    public String toString() {
        return "Building{" +
                "floors=" + floors +
                '}';
    }

    public Floor getFloor(int floorNumber) {
        return floors.get(floorNumber);
    }

    public List<Integer> getFloorsQueue(int floorNumber) {
        return floors.get(floorNumber).getLiftQueue().getQueue();
    }

    public List<Integer> getFloorsNumbers() {
        return new ArrayList<>(floors.keySet());
    }


}
