import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {
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
    Building building = new Building(queues1);
    Lift lift = new Lift(10);
    Controller controller = new Controller(building, lift);
    BuildingView buildingView = new BuildingView(controller);

    @Test
    void stateTransitionTest() {
        //when initial state
        //then
        buildingView.printSnapshot();

        //when
        controller.next();
        buildingView.printSnapshot();
        //then level 0 - AFTER ARRIVED
        assertEquals(0, liftLevel());
        assertEquals(0, liftOccupancy());
        assertEquals(1, nextStop());
        assertTrue(liftIsEmpty());
        assertTrue(queueUpFromFloorContains(0, 4));

        //when
        controller.next();
        buildingView.printSnapshot();
        //then level 0 - AFTER TRANSFER
        assertEquals(0, liftLevel());
        assertEquals(1, liftOccupancy());
        assertEquals(1, nextStop());
        assertTrue(liftContains(4));
        assertTrue(queueUpIsEmpty(0));

        //when
        controller.next();
        buildingView.printSnapshot();
        //then level 1 - before transfer
        assertEquals(1, liftLevel());
        assertEquals(1, liftOccupancy());
//        assertEquals(2, nextStop());
        assertTrue(liftContains(4));
        assertTrue(queueUpFromFloorContains(1, 2, 3));

        //when
        controller.next();
        controller.next();
        buildingView.printSnapshot();
        //then level 1 - after transfer
        assertEquals(1, liftLevel());
        System.out.println("lift = " + lift);
        assertEquals(3, liftOccupancy());
        assertEquals(2, nextStop());
        assertTrue(liftContains(4, 2, 3));
        assertEquals(0, queueUp(1).size());

        //when
        controller.next();
        controller.next();
        buildingView.printSnapshot();
        //then level 2 - before transfer
        assertEquals(2, liftLevel()); //todo:
        assertEquals(3, liftOccupancy());
        assertEquals(3, nextStop());
        assertTrue(liftContains(4, 2, 3));
        assertTrue(queueDownFromFloorContains(2, 1));

        //when
        controller.next();
        buildingView.printSnapshot();
        //then level 2 - after transfer
        assertEquals(2, liftLevel());
        assertEquals(2, liftOccupancy());
        assertEquals(3, nextStop());
        assertTrue(liftContains(4, 3));
        assertTrue(queueDownFromFloorContains(2, 1));

    }

    boolean queueUpIsEmpty(int floor) {
        return building.getFloor(floor).getQueueUp().size() == 0;
    }

    private Integer nextStop() {
        return controller.getNextStop();
    }

    private Queue<Integer> queueUp(int floor) {
        return building.getFloor(floor).getQueueUp();
    }

    private Queue<Integer> queueDown(int floor) {
        return building.getFloor(floor).getQueueDown();
    }

    private Queue<Integer> queueLift() {
        return lift.getPassengers();
    }

    private int liftLevel() {
        return lift.getLevel();
    }

    private int liftOccupancy() {
        return lift.getOccupancy();
    }

    private boolean queueDownFromFloorContains(int floor, Integer... numbers) {
        return building.getFloor(floor).getQueueDown().containsAll(Arrays.asList(numbers));
    }

    private boolean queueUpFromFloorContains(int floor, Integer... numbers) {
        return building.getFloor(floor).getQueueUp().containsAll(Arrays.asList(numbers));
    }

    private boolean liftContains(Integer... numbers) {
        return lift.getPassengers().containsAll(Arrays.asList(numbers));
    }

    private boolean liftIsEmpty() {
        return lift.getPassengers().size() == 0;
    }

}