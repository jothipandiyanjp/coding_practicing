package com.akka.eventsourcing;

import java.util.Arrays;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import akka.persistence.SnapshotOffer;
import akka.persistence.UntypedPersistentActor;

public class ExamplePersistenceActor extends UntypedPersistentActor{
	final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
    private ExampleState state = new ExampleState();

	public String persistenceId() {
		return "sample-id-5";
	}


    public int getNumEvents() {
        return state.size();

    }

    @Override
	public void onReceiveCommand(Object message) throws Exception {
		if(message instanceof Command){

			final String data = ((Command)message).getData();
            final Event evt1 = new Event(data + "-" + getNumEvents());
            final Event evt2 = new Event(data + "-" + (getNumEvents() + 1));
            
            persistAll(Arrays.asList(evt1,evt2), new Procedure<Event>() {
            	public void apply(Event evt) throws Exception {
            		state.update(evt);
            		if (evt.equals(evt2)) {
                        getContext().system().eventStream().publish(evt);
                    }
            	}
			});
		}else if(message.equals("snap")){
            saveSnapshot(state.copy());
        }else if (message.equals("print")) {
        	log.info("State -> "+state);
        }else {        	
            unhandled(message);
          }
	}

	@Override
	public void onReceiveRecover(Object message) throws Exception {		
		log.info("recover "+message);
		if(message instanceof Event){		
			state.update((Event)message);
		}
		else if(message instanceof SnapshotOffer){
			state = (ExampleState) ((SnapshotOffer)message).snapshot();
		}
		else
			unhandled(message);
	}

	
}
