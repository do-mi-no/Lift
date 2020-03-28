import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws InterruptedException {

        int[][] queues1 = {
                new int[]{4},    // 0 (ground floor)
                new int[]{2, 3}, // 1
                new int[]{1},    // 2
                new int[0],      // 3
                new int[]{6, 2}, // 4
                new int[0],      // 5
                new int[]{4, 1}, // 6
        };
        int[][] queues2 = {
                new int[]{4, 3, 1, 1, 1, 1, 1},    // 0 (ground floor)
                new int[]{2, 3, 4, 5, 6}, // 1
                new int[]{1, 3, 5, 3, 1},    // 2
                new int[0],      // 3
                new int[]{6, 2, 4, 4, 4}, // 4
                new int[0],      // 5
                new int[]{4, 1, 0, 0, 0, 0 }, // 6
        };
        final Building building = new Building(queues1);
//        final Building building = new Building(queues2);
        final Lift lift = new Lift(10);
        Controller controller = new Controller(building, lift);
        final BuildingView buildingView = new BuildingView(controller);

//        int steps = 1;
//        while (steps > 0) {
//            TimeUnit.MILLISECONDS.sleep(100);
//            controller.next();
//            buildingView.printSnapshot();
//            steps--;
//        }

        while (controller.isAnyRequest() || lift.getLevel() != 0) {
            pressAnyKeyToContinue();
            controller.next();
            buildingView.printSnapshot();
        }

//        while (controller.isAnyRequest() || lift.getLevel() != 0) {
//            TimeUnit.MILLISECONDS.sleep(100);
//            controller.next();
//            buildingView.printSnapshot();
//        }
    }

    private static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

}
