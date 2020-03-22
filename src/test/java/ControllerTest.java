import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void creationTest() {
        //given
        int[][] queues1 = {
                new int[]{4},    // 0 (ground floor)
                new int[]{2, 3}, // 1
                new int[]{1},    // 2
                new int[0],      // 3
                new int[]{6, 2}, // 4
                new int[0],      // 5
                new int[]{4, 1}, // 6
        };

        //when
        final Building building = new Building(queues1);
        Lift.init(10);
        final Lift lift = Lift.getInstance();
        Controller controller = new Controller(building, lift);
        //then
        assertEquals(0, controller.getLift().getLevel());
        assertEquals(7, controller.getBuilding().getFloors().size());
        assertEquals(6, controller.getTopFloorWithCallDown());
        assertEquals(1, controller.getNextStopUpFromFloors());

        //when
        controller.takeAsManyAsPossibleFromQueueUp();
        //then
        assertEquals(1, controller.getLift().getOccupancy());
        assertTrue(lift.getPassengers().contains(4));

        //when
        controller.sendLiftToTheFloor(1);
        //then
        assertEquals(1, controller.getLift().getLevel());



        //when
        controller.sendLiftToTheFloor(4);
        //then
        System.out.println("controller.getNextStopUpFromFloors() = " + controller.getNextStopUpFromFloors());;



    }
}