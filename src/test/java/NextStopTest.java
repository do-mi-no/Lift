import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NextStopTest {

    @Test
    void emptyLiftNextStopTest() {
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
        buildingView.printSnapshot();

        //when
        lift.setLevel(0);
        controller.addStopToRoute();
        //then
        assertEquals(1, controller.getNextStop());

        //when
        lift.setLevel(1);
        controller.addStopToRoute();
        //then
        assertEquals(4, controller.getNextStop());

        //when
        lift.setLevel(2);
        controller.addStopToRoute();
        //then
        assertEquals(4, controller.getNextStop());

        //when
        lift.setLevel(3);
        controller.addStopToRoute();
        //then
        assertEquals(4, controller.getNextStop());

        //when
        lift.setLevel(4);
        controller.addStopToRoute();
        //then
        assertEquals(6, controller.getNextStop());

        //when
        lift.setLevel(5);
        controller.addStopToRoute();
        //then
        assertEquals(6, controller.getNextStop());

        //when
        lift.setLevel(6);
        controller.addStopToRoute();
        //then
        assertEquals(4, controller.getNextStop());

        //when
        lift.setLevel(4);
        controller.addStopToRoute();
        //then
        assertEquals(2, controller.getNextStop());

        //when
        lift.setLevel(2);
        controller.addStopToRoute();
        //then
        assertEquals(0, controller.getNextStop());

        //when
        lift.setLevel(0);
        controller.addStopToRoute();
        //then
        assertEquals(1, controller.getNextStop());

    }

    @Test
    void nonemptyLockedLiftNextStopTest() {
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
        lift.getPassengers().addAll(Arrays.asList(2, 3, 5));           // non-epmty lift locked (no transfers between floors)
        Controller controller = new Controller(building, lift);
        BuildingView buildingView = new BuildingView(controller);
        buildingView.printSnapshot();

        //when
        lift.setLevel(0);
        controller.addStopToRoute();
        //then
        assertEquals(1, controller.getNextStop());

        //when
        lift.setLevel(1);
        controller.addStopToRoute();
        //then
        assertEquals(2, controller.getNextStop());

        //when
        lift.setLevel(2);
        controller.addStopToRoute();
        //then
        assertEquals(3, controller.getNextStop());

        //when
        lift.setLevel(3);
        controller.addStopToRoute();
        //then
        assertEquals(4, controller.getNextStop());

        //when
        lift.setLevel(4);
        controller.addStopToRoute();
        //then
        assertEquals(5, controller.getNextStop());

        //when
        lift.setLevel(5);
        controller.addStopToRoute();
        //then
        assertEquals(6, controller.getNextStop());

        //when
        lift.setLevel(6);
        controller.addStopToRoute();
        //then
        assertEquals(5, controller.getNextStop());

        //when
        lift.setLevel(5);
        controller.addStopToRoute();
        //then
        assertEquals(4, controller.getNextStop());

        //when
        lift.setLevel(4);
        controller.addStopToRoute();
        //then
        assertEquals(3, controller.getNextStop());

        //when
        lift.setLevel(3);
        controller.addStopToRoute();
        //then
        assertEquals(2, controller.getNextStop());

        //when
        lift.setLevel(2);
        controller.addStopToRoute();
        //then
        assertEquals(0, controller.getNextStop());

        //when
        lift.setLevel(0);
        controller.addStopToRoute();
        //then
        assertEquals(1, controller.getNextStop());

    }

    @Test
    void nonemptyLockedLiftWithEmptyBuildingNextStopTest() {
        //given
        int[][] queues1 = {
                new int[0],     // 0 (ground floor)
                new int[0],     // 1
                new int[0],     // 2
                new int[0],     // 3
                new int[0],     // 4
                new int[0],     // 5
                new int[0]      // 6
        };
        Building building = new Building(queues1);
        Lift lift = new Lift(10);
        lift.getPassengers().addAll(Arrays.asList(2, 3, 5));           // non-epmty lift locked (no transfers between floors)
        Controller controller = new Controller(building, lift);
        BuildingView buildingView = new BuildingView(controller);
        buildingView.printSnapshot();

        //when
        lift.setLevel(0);
        controller.addStopToRoute();
        //then
        assertEquals(2, controller.getNextStop());

        //when
        lift.setLevel(2);
        controller.addStopToRoute();
        //then
        assertEquals(3, controller.getNextStop());

        //when
        lift.setLevel(3);
        controller.addStopToRoute();
        //then
        assertEquals(5, controller.getNextStop());

        //when
        lift.setLevel(5);
        controller.addStopToRoute();
        //then
        assertEquals(3, controller.getNextStop());

        //when
        lift.setLevel(3);
        controller.addStopToRoute();
        //then
        assertEquals(2, controller.getNextStop());

        //when
        lift.setLevel(2);
        controller.addStopToRoute();
        //then
        assertEquals(3, controller.getNextStop());
    }

    @Test
    void fullLockedLiftWithEmptyBuildingNextStopTest() {
        //given
        int[][] queues1 = {
                new int[0],     // 0 (ground floor)
                new int[0],     // 1
                new int[0],     // 2
                new int[0],     // 3
                new int[0],     // 4
                new int[0],     // 5
                new int[0]      // 6
        };
        Building building = new Building(queues1);
        Lift lift = new Lift(10);
        lift.getPassengers().addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4));           // non-epmty lift locked (no transfers between floors)
        Controller controller = new Controller(building, lift);
        BuildingView buildingView = new BuildingView(controller);
        buildingView.printSnapshot();

        //when
        lift.setLevel(0);
        controller.addStopToRoute();
        //then
        assertEquals(1, controller.getNextStop());

        //when
        lift.setLevel(1);
        controller.addStopToRoute();
        //then
        assertEquals(2, controller.getNextStop());

        //when
        lift.setLevel(2);
        controller.addStopToRoute();
        //then
        assertEquals(3, controller.getNextStop());

        //when
        lift.setLevel(3);
        controller.addStopToRoute();
        //then
        assertEquals(4, controller.getNextStop());

        //when
        lift.setLevel(4);
        controller.addStopToRoute();
        //then
        assertEquals(5, controller.getNextStop());

        //when
        lift.setLevel(5);
        controller.addStopToRoute();
        //then
        assertEquals(6, controller.getNextStop());

        //when
        lift.setLevel(6);
        controller.addStopToRoute();
        //then
        assertEquals(5, controller.getNextStop());

        //when
        lift.setLevel(5);
        controller.addStopToRoute();
        //then
        assertEquals(4, controller.getNextStop());

        //when
        lift.setLevel(4);
        controller.addStopToRoute();
        //then
        assertEquals(3, controller.getNextStop());

        //when
        lift.setLevel(3);
        controller.addStopToRoute();
        //then
        assertEquals(2, controller.getNextStop());

        //when
        lift.setLevel(2);
        controller.addStopToRoute();
        //then
        assertEquals(1, controller.getNextStop());

        //when
        lift.setLevel(1);
        controller.addStopToRoute();
        //then
        assertEquals(0, controller.getNextStop());

        //when
        lift.setLevel(0);
        controller.addStopToRoute();
        //then
        assertEquals(1, controller.getNextStop());
    }

}