import java.util.concurrent.TimeUnit;

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
        final Building building = new Building(queues1);
        Lift.init(10);
        final Lift lift = Lift.getInstance();
        final Controller controller = new Controller(building, lift);

        System.out.println("building = " + building);

        while (controller.isAnyRequest()){
            TimeUnit.SECONDS.sleep(1);
            System.out.println("controller.getState() = " + controller.getState());
            controller.nextFloor();
            System.out.println("lift = " + lift);
            System.out.println("controller.getRequestCount() = " + controller.getRequestCount());
            System.out.println("lift.getOccupancy() = " + lift.getOccupancy());
        }



    }
}

//          BEFORE (people waiting in queues)             AFTER (people at their destinations)
//          +--+                                          +--+
//          /----------------|  |----------------\        /----------------|  |----------------\
//        10|                |  | 1,4,3,2        |      10|             10 |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         9|                |  | 1,10,2         |       9|                |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         8|                |  |                |       8|                |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         7|                |  | 3,6,4,5,6      |       7|                |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         6|                |  |                |       6|          6,6,6 |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         5|                |  |                |       5|            5,5 |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         4|                |  | 0,0,0          |       4|          4,4,4 |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         3|                |  |                |       3|            3,3 |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         2|                |  | 4              |       2|          2,2,2 |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         1|                |  | 6,5,2          |       1|            1,1 |  |                |
//          |----------------|  |----------------|        |----------------|  |----------------|
//         G|                |  |                |       G|          0,0,0 |  |                |
//          |====================================|        |====================================|