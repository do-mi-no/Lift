public class StateArrived implements State {

    @Override
    public void next(Controller controller) {

        controller.addStopToRoute();

        controller.setState(new StateTransfer());

    }

    public String toString() {
        return printInfo();
    }
}
