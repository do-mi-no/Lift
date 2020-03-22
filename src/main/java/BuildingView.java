import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuildingView {

    Controller controller;

    String wallH = "\u2501";
    String wallV = "\u2503";
    String cornerTL = "\u250F";
    String cornerTR = "\u2513";
    String joinUDR = "\u2523";
    String joinUDL = "\u252B";
    String joinULR = "\u253B";
    String joinDLR = "\u2533";

//    String wallH = "\u2550";
//    String wallV = "\u2551";
//    String cornerTL = "\u2554";
//    String cornerTR = "\u2557";
//    String joinUDR = "\u2560";
//    String joinUDL = "\u2563";
//    String joinULR = "\u2569";
//    String joinDLR = "\u2566";

    public BuildingView(Controller controller) {
        this.controller = controller;
    }

    public String getFloorQueueUp(int level) {
        String queue = controller.getBuilding().getFloor(level).getQueueUp().stream()
                .map(integer -> String.format("%d-", integer))
                .reduce("", String::concat);
        return  removeLastCharIfNotEmpty("\u2191" + queue);
    }
    public String getFloorQueueDown(int level) {
        String queue = controller.getBuilding().getFloor(level).getQueueDown().stream()
                .map(integer -> String.format("%d-", integer))
                .reduce("", String::concat);
        return  removeLastCharIfNotEmpty("\u2193" + queue);
    }
    public String getFloorQueueDelivered(int level) {
        return controller.getBuilding().getFloor(level).getQueueDelivered().stream()
                .map(integer -> String.format("%d-", integer))
                .reduce("", String::concat);
    }
    public String getLiftPassengers() {
        final String passengers = controller.getLift().getPassengers().stream()
                .map(integer -> String.format("%d-", integer))
                .reduce("", String::concat);
        return removeLastCharIfNotEmpty(passengers);
    }

    public String getFloorLiftInfo(int level) {
        String pass = String.format("%22s", "");
        if (level == controller.getLift().getLevel()) {
            pass = String.format("[%20s]", getLiftPassengers());
        }
        return  String.format(" %dp %20s ", level, pass);
    }

    public String getFloorInfo(int level) {
        return String.format("%s%40s%s%s%s%-40s%s",
                wallV, getFloorQueueDelivered(level),
                wallV, getFloorLiftInfo(level),
                wallV, getFloorQueueUp(level) + getFloorQueueDown(level),
                wallV);
    }

    public int getFloorLength() {
        return getFloorInfo(0).length();
    }

    public void printFloorInfo(int level) {
        System.out.println(getFloorInfo(level));
    }

    public void printCeiling() {
        System.out.printf("%s%40s%s%s%s%-40s%s%n",
                joinUDR, wallH.repeat(40),
                joinUDL, " ".repeat(27),
                joinUDR, wallH.repeat(40),
                joinUDL);
    }

    public void printRoof() {
        System.out.printf("%s%40s%s%s%s%-40s%s%n",
                cornerTL, wallH.repeat(40),
                joinDLR, wallH.repeat(27),
                joinDLR, wallH.repeat(40),
                cornerTR);
    }

    public void printFoundation() {
        System.out.printf("%s%40s%s%s%s%-40s%s%n",
                joinULR, wallH.repeat(40),
                joinULR, wallH.repeat(27),
                joinULR, wallH.repeat(40),
                joinULR);
    }

    public void printState() {
        final List<Floor> floors = controller.getBuilding().getFloors();
        List<Floor> floorsReversed = new ArrayList<>(floors);
        Collections.reverse(floorsReversed);

        final int floorsAmount = floorsReversed.size();
        printRoof();
        for (int i = 0; i < floorsAmount; i++) {
            printFloorInfo(floorsReversed.get(i).getLevel());
            if (i < floorsAmount - 1) {
                printCeiling();
            }
        }
        printFoundation();
        System.out.println();
    }

    public String removeLastCharIfNotEmpty(String s) {
        return s.length() > 0 ? s.substring(0, s.length() - 1) : s;
    }

}

//https://unicode.org/charts/nameslist/n_2500.html
//https://unicode.org/charts/nameslist/n_2190.html