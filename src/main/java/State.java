public interface State {

    void next(Controller controller);

    default String printInfo(){
        return getClass().getName();
    }
}
