public class StateEnd implements State {

    @Override
    public void next(Controller controller) {
        System.out.println("Each passenger reached his destination.\nExit.");
    }


    public String toString() {
        return printInfo();
    }
}
