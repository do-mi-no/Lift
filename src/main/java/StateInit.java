public class StateInit implements State {

    @Override
    public void next(Controller controller) {

        controller.setState(new StateArrived());

    }

    public String toString() {
        return printInfo();
    }
}
