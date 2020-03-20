import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Queue {
    private List<Integer> queue = new LinkedList<>();

    public Queue() {
    }

    public Queue(Integer[] persons) {
        this.queue.addAll(Arrays.asList(persons));
    }

    public List<Integer> getQueue() {
        return queue;
    }

    public void addPerson(Integer person) {
        queue.add(person);
    }

    public void addPersons(Integer[] persons) {
        queue.addAll(Arrays.asList(persons));
    }

    public boolean removePerson(Integer person) {
        if(!queue.contains(person)){
            throw new IllegalArgumentException("Queue doesn't contain '" + person + "'.");
        }
        return queue.remove(person);
    }

}
