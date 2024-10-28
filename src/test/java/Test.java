import net.staro.bot.api.bus.EventBus;
import net.staro.bot.api.bus.impl.EventBusImpl;
import net.staro.bot.api.bus.impl.Listener;
import net.staro.bot.api.bus.impl.SubscriberImpl;

public class Test {
    public static void main(String[] args) {
        // testing eventbus here
        EventBus eventBus = new EventBusImpl();
        eventBus.subscribe(new TestListener());
        var timer = System.currentTimeMillis();
        for (int i = 0; i <= 1000000; i++) {
            eventBus.post(new Event());
        }

        System.out.println(System.currentTimeMillis() - timer);
        // pretty fast, still some work left to do tho...
    }

    public static class Event {}

    public static class TestListener extends SubscriberImpl {
        public TestListener() {
            listen(new Listener<Event>() {
                @Override
                public void onEvent(Event event) {

                }
            });
        }
    }

}
