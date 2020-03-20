import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void creationAddRemoveTest() {
        //given
        final Queue queue = new Queue();

        //when created
        //then
        assertEquals(0, queue.getQueue().size());

        //when
        queue.addPerson(1);
        //then
        assertEquals(1, queue.getQueue().size());

        //when
        queue.addPersons(new Integer[]{2,3,4,5});
        //then
        assertEquals(5, queue.getQueue().size());

        //when
        queue.removePerson(5);
        //then
        assertFalse(queue.getQueue().contains(5));
        assertEquals(4, queue.getQueue().size());

        //when trying to remove not contained person
        //then
        assertThrows(IllegalArgumentException.class, () -> queue.removePerson(333));

        //when
        queue.removePerson(4);
        queue.removePerson(3);
        queue.removePerson(2);
        queue.removePerson(1);
        //then
        assertEquals(0, queue.getQueue().size());

        //when

        //then
//        assertThrows(IllegalArgumentException.class, () -> queue.removePerson(333));
    }
}