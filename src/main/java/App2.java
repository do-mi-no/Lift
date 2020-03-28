import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class App2 {
    public static void main(String[] args) throws InterruptedException {

        final Building building = new Building(getRandomMatrix(7, 9));
//        final Building building = new Building(getRandomMatrix(7,3));
        final Lift lift = new Lift(10);
        Controller controller = new Controller(building, lift);
        final BuildingView buildingView = new BuildingView(controller);

//        while (controller.isAnyRequest() || lift.getLevel() != 0) {
//            pressAnyKeyToContinue();
//            controller.next();
//            buildingView.printSnapshot();
//        }

        while (controller.isAnyRequest() || lift.getLevel() != 0) {
//            TimeUnit.MILLISECONDS.sleep(100);
            controller.next();
            buildingView.printSnapshot();
        }



    }

    private static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    private static int[][] getRandomMatrix(int floorCount, int maxQueueLength) {
        int[][] queues = new int[floorCount][maxQueueLength];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < floorCount; i++) {
            for (int j = 0; j < maxQueueLength; j++) {
                queues[i][j] = random.nextInt(floorCount);
            }
        }
        return queues;
    }

}
