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
        assertThrows(IllegalArgumentException.class,() -> new Building(emptyQueue));
    }

    @Test
    void validCreationTest() {
        //given
        int[][] queues1 = {
                new int[0],     // 0 (ground floor)
                new int[]{2,3}, // 1
                new int[]{1},   // 2
                new int[0],     // 3
                new int[]{6,2}, // 4
                new int[0],     // 5
                new int[]{4,1}, // 6
        };
        //when
        final Building building = new Building(queues1);

        //then
        assertEquals(0, building.getFloor(0).getLiftQueue().getQueue().size());
        assertEquals(2, building.getFloor(1).getLiftQueue().getQueue().size());
        assertEquals(1, building.getFloor(2).getLiftQueue().getQueue().size());
        assertEquals(0, building.getFloor(3).getLiftQueue().getQueue().size());
        assertTrue(building.getFloorQueue(1).contains(2));
        assertTrue(building.getFloorQueue(1).contains(3));
        assertTrue(building.getFloorQueue(2).contains(1));
        assertTrue(building.getFloorQueue(6).contains(4));
        assertEquals(7, building.getFloorIndexes().size());
    }

}