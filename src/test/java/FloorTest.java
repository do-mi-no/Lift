import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void creationAddRemoveTest() {
        //given
        final Floor floor = new Floor(3, new int[]{0, 1, 2, 4, 5});

        //when created
        //then
        assertEquals(3, floor.getLevel());
        assertEquals(0, floor.getQueueDelivered().size());
        assertTrue(floor.getQueueUp().containsAll(Arrays.asList(4, 5)));
        assertTrue(floor.getQueueDown().containsAll(Arrays.asList(0, 1, 2)));
    }
}