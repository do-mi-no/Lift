import java.util.Arrays;

public class App1 {
    public static void main(String[] args) throws InterruptedException {

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
        final BuildingView buildingView = new BuildingView(controller);

        while (controller.isAnyRequest() || lift.getLevel() != 0) {
//            pressAnyKeyToContinue();
            controller.next();
            buildingView.printSnapshot();
        }

        int[] result = controller.getResult();

        System.out.println("result = " + Arrays.toString(result));
        System.out.println("new int[]{0,2,5,0} = " + Arrays.toString(new int[]{0, 2, 5, 0}));

    }

    private static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}
