import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CodeWarsTest {


    @Test
    public void testApp1() {
        //given
        final int[][] queues = {
                new int[0], // G
                new int[0], // 1
                new int[]{5,5,5}, // 2
                new int[0], // 3
                new int[0], // 4
                new int[0], // 5
                new int[0], // 6
        };
        Building building = new Building(queues);
        Lift lift = new Lift(10);
        Controller controller = new Controller(building, lift);
        BuildingView buildingView = new BuildingView(controller);

        //when
        while (controller.isAnyRequest() || lift.getLevel() != 0) {
            controller.next();
            buildingView.printSnapshot();
        }

        //then
        int[] result = controller.getResult();
        assertArrayEquals(new int[]{0,2,5,0}, result);
    }

    @Test
    public void testApp2() {
        //given
        final int[][] queues = {
                new int[0], // G
                new int[0], // 1
                new int[]{1,1}, // 2
                new int[0], // 3
                new int[0], // 4
                new int[0], // 5
                new int[0], // 6
        };
        Building building = new Building(queues);
        Lift lift = new Lift(10);
        Controller controller = new Controller(building, lift);
        BuildingView buildingView = new BuildingView(controller);

        //when
        while (controller.isAnyRequest() || lift.getLevel() != 0) {
            controller.next();
            buildingView.printSnapshot();
        }

        //then
        int[] result = controller.getResult();
        assertArrayEquals(new int[]{0,2,1,0}, result);
    }

    @Test
    public void testApp3() {
        //given
        final int[][] queues = {
                new int[0], // G
                new int[]{3}, // 1
                new int[]{4}, // 2
                new int[0], // 3
                new int[]{5}, // 4
                new int[0], // 5
                new int[0], // 6
        };
        Building building = new Building(queues);
        Lift lift = new Lift(10);
        Controller controller = new Controller(building, lift);
        BuildingView buildingView = new BuildingView(controller);

        //when
        while (controller.isAnyRequest() || lift.getLevel() != 0) {
            controller.next();
            buildingView.printSnapshot();
        }

        //then
        int[] result = controller.getResult();
        assertArrayEquals(new int[]{0,1,2,3,4,5,0}, result);
    }

    @Test
    public void testApp4() {
        //given
        final int[][] queues = {
                new int[0], // G
                new int[]{0}, // 1
                new int[0], // 2
                new int[0], // 3
                new int[]{2}, // 4
                new int[]{3}, // 5
                new int[0], // 6
        };
        Building building = new Building(queues);
        Lift lift = new Lift(10);
        Controller controller = new Controller(building, lift);
        BuildingView buildingView = new BuildingView(controller);

        //when
        while (controller.isAnyRequest() || lift.getLevel() != 0) {
            controller.next();
            buildingView.printSnapshot();
        }

        //then
        int[] result = controller.getResult();
        assertArrayEquals(new int[]{0,5,4,3,2,1,0}, result);
    }
}
