public class Floor {
    private Queue liftQueue = new Queue();

    public Floor() {
    }

    public Queue getLiftQueue() {
        return liftQueue;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "liftQueue=" + liftQueue +
                '}';
    }
}
