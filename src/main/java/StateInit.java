public class StateInit implements State {

    @Override
    public void next(Controller controller) {

        controller.setState(new StateArrived());

        controller.addStopToRoute();

    }

    public String toString() {
        return printInfo();
    }
}
