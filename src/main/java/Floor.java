public class Floor {
    private Queue liftQueue = new Queue();

    public Floor() {
    }

    public Floor(Queue liftQueue) {
        this.liftQueue = liftQueue;
    }

    public Queue getLiftQueue() {
        return liftQueue;
    }

    public void setLiftQueue(Queue liftQueue) {
        this.liftQueue = liftQueue;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "liftQueue=" + liftQueue +
                '}';
    }
}
