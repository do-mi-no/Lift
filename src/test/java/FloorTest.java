import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void creationAddRemoveTest() {
        //given
        final Floor floor = new Floor(3, new int[]{0, 1, 2, 3, 4, 5});

        //when created (using constructor) queueUp and queueDown
        //then
        assertTrue(floor.getQueueUp().contains(4));
        assertTrue(floor.getQueueUp().contains(5));
        assertTrue(floor.getQueueDown().contains(0));
        assertTrue(floor.getQueueDown().contains(1));
        assertTrue(floor.getQueueDown().contains(2));
        assertFalse(floor.getQueueUp().contains(3));
        assertFalse(floor.getQueueDown().contains(3));

        //when
        floor.takePersonUp();
        //then
        assertFalse(floor.getQueueUp().contains(4));

        //when
        floor.takePersonUp();
        //then
        assertFalse(floor.getQueueUp().contains(5));
        assertEquals(0, floor.getQueueUp().size());

        //when
        floor.takePersonDown();
        //then
        assertFalse(floor.getQueueDown().contains(0));

        //when
        floor.takePersonDown();
        //then
        assertFalse(floor.getQueueDown().contains(1));
        assertEquals(1,floor.getQueueDown().size());
    }
}