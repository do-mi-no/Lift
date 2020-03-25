import org.junit.jupiter.api.Test;

class BuildingViewTest {

    @Test
    void buildingViewTest1() {
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
        final Building building = new Building(queues1);
        final Lift lift = new Lift(10);
        Controller controller = new Controller(building, lift);
        final BuildingView buildingView = new BuildingView(controller);

        //when
        controller.next();
        controller.next();

        //then
        buildingView.printSnapshot();
    }

    @Test
    void roofTest() {
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
        final Building building = new Building(queues1);
        final Lift lift = new Lift(10);
        Controller controller = new Controller(building, lift);
        final BuildingView buildingView = new BuildingView(controller);

        //when
        controller.next();
        controller.next();

        //then
        buildingView.printRoof();
        buildingView.printCeiling();
    }
}