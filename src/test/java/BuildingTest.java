import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    @Test
    void emptyArgumentCreationTest() {
        //given
        int[][] emptyQueue = {};
        System.out.println("emptyQueue.length = " + emptyQueue.length);

        //when empty arrays passed to the constructor
        //then
        assertThrows(IllegalArgumentException.class, () -> new Building(emptyQueue));
    }

    @Test
    void validCreationTest() {
        //given
        int[][] queues1 = {
                new int[]{4},      // 0 (ground floor)
                new int[]{2, 3}, // 1
                new int[]{1},    // 2
                new int[0],      // 3
                new int[]{6, 2}, // 4
                new int[0],      // 5
                new int[]{4, 1}, // 6
        };
        //when
        final Building building = new Building(queues1);

        //then
        assertTrue(building.getFloor(0).getQueueUp().contains(4));
        assertTrue(building.getFloor(1).getQueueUp().contains(2));
        assertTrue(building.getFloor(1).getQueueUp().contains(3));
        assertTrue(building.getFloor(2).getQueueDown().contains(1));
        assertTrue(building.getFloor(4).getQueueUp().contains(6));
        assertTrue(building.getFloor(4).getQueueDown().contains(2));
        assertTrue(building.getFloor(6).getQueueDown().contains(4));
        assertTrue(building.getFloor(6).getQueueDown().contains(1));
    }

}