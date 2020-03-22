import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Floor {
    private int level;
    private Queue<Integer> queueUp = new LinkedList<>();
    private Queue<Integer> queueDown = new LinkedList<>();
    private Queue<Integer> queueDelivered = new LinkedList<>();

    public Floor(int level, int[] persons) {
        this.level = level;
        for (Integer p : persons) {
            if (p > level) {
                this.queueUp.add(p);
            } else if (p < level) {
                this.queueDown.add(p);
            }
        }
        List<Integer> list = Arrays.stream(persons).boxed().collect(Collectors.toList());
    }

    public int getLevel() {
        return level;
    }
    public Queue<Integer> getQueueUp() {
        return queueUp;
    }
    public Queue<Integer> getQueueDown() {
        return queueDown;
    }
    public Queue<Integer> getQueueDelivered() {
        return queueDelivered;
    }
    //---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Floor{" +
                "level=" + level +
                ", queueUp=" + queueUp +
                ", queueDown=" + queueDown +
                '}';
    }
}