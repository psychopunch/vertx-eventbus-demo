package me.psychopunch.lab.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;

public class DemoVerticle extends AbstractVerticle {

    private final Delegate delegate;

    public DemoVerticle(Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void start(Future<Void> future) throws Exception {
        vertx.eventBus().consumer("event.channel", message -> {
            Event event = Json.decodeValue(message.body().toString(), Event.class);
            delegate.invokeMethod(event.getId(), event.getDescription());
        }).completionHandler(result -> {
            if (result.succeeded()) {
                future.complete();
            } else  {
                future.fail(result.cause());
            }
        });
    }

}
