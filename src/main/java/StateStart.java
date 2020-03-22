public class StateStart implements State {

    @Override
    public void next(Controller controller) {
//        controller.takeAsManyAsPossibleFromQueueUp();

        controller.setState(new StateGoUp());
    }

    public String toString() {
        return printInfo();
    }
}
