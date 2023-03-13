package shopping.cart;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.actor.ActorSystem;
import akka.actor.ActorRef;


/*
 * actor that keeps state
 */
public class App {
    
    static class Counter extends AbstractLoggingActor {

        //protocol
        static class Message { }

        private int counter = 0;

        private void onMessage(Message message) {
            counter++;
            log().info("Increasead counter " + counter);
        }

        public static Props props() {
            return Props.create(Counter.class);
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder().match(Message.class, this::onMessage).build();
        }
    }


    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sample1");

        final ActorRef counter  = system.actorOf(Counter.props(), "counter");

        for (int i = 0; i <5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <5; j++) {
                        counter.tell(new Counter.Message(), ActorRef.noSender());
                    }
                }
            }).run();
        }

        



    }
    
}
