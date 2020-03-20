public class StateGoUp implements State {
    @Override
    public void next(Controller controller) {
        //todo:

        int nextStop = controller.getNextStopGoingUp();
    }

    public String toString() {
        return printInfo();
    }
}
