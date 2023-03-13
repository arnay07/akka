package shopping.cart;


import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.actor.ActorSystem;
import akka.actor.ActorRef;




/*
 * actor that changes behavior
 */
public class App1 {

    static class Alarm extends AbstractLoggingActor {
        
        //protocol
        static class Activity {}

        static class Disable {
            private final String password;
            public Disable(String password) {
                this.password = password;
            }
        }

        static class Enable {
            private final String password;
            public Enable(String password) {
                this.password = password;
            }
        }


        private final String password;
        private final PartialFunction<Object, BoxedUnit> enabled;
        private final PartialFunction<Object, BoxedUnit> disabled;

        public Alarm(String password){
            this.password = password;

            enabled = ReceiveBuilder.match(Activity.class, this::onActivity);
        }
    }


    
}
