package me.psychopunch.lab.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(VertxUnitRunner.class)
public class DemoVerticleTest {

    private Vertx vertx;

    @Mock
    private Delegate delegate;

    @Rule
    public RunTestOnContext runTestOnContext = new RunTestOnContext();

    @Before
    public void setUp(TestContext context) {
        initMocks(this);
        vertx = runTestOnContext.vertx();
        vertx.deployVerticle(new DemoVerticle(delegate), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testDelegate() {
        Event event = new Event("id", "description");
        vertx.eventBus().publish("event.channel", Json.encode(event));

        verify(delegate).invokeMethod("id", "description");
    }

}
