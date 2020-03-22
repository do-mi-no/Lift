public class StateGoDown implements State {
    @Override
    public void next(Controller controller) {
        controller.dropOffPassengers();
        controller.takeAsManyAsPossibleFromQueueDown();

        final Integer nextStopDown = controller.getNextStopDown();

        if (nextStopDown != null) {
            controller.sendLiftToTheFloor(nextStopDown);
        } else {
            controller.setState(new StateGoUp());
        }
    }

    public String toString() {
        return printInfo();
    }
}
