package Events;


import java.util.EventObject;

public class StateChangeEvent extends EventObject {
    private String targetState;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public StateChangeEvent(Object source,String targetState) {
        super(source);
        this.targetState = targetState;
    }

    public String getTargetState(){
        return this.targetState;
    }
}
