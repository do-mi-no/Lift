public class StateGoUp implements State {
    @Override
    public void next(Controller controller) {
        controller.dropOffPassengers();
        controller.takeAsManyAsPossibleFromQueueUp();

        final Integer nextStopUp = controller.getNextStopUp();

        if (nextStopUp != null) {
            controller.sendLiftToTheFloor(nextStopUp);
        } else {
            controller.setState(new StateGoDown());
        }
    }

    public String toString() {
        return printInfo();
    }
}
