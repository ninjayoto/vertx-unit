package io.vertx.ext.unit;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.test.core.AsyncTestBase;
import org.junit.*;
import org.junit.Test;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class VerticleTest extends AsyncTestBase {

  @org.junit.Test
  public void testCoordinated() {
    Vertx vertx = Vertx.vertx();
    vertx.eventBus().<JsonObject>consumer("test").handler(msg -> {
      if (msg.body().getString("type").equals("endTestSuite")) {
        testComplete();
      }
    });
    vertx.deployVerticle("js:verticle/coordinated/test", ar -> {
      assertTrue(ar.succeeded());
    });
    await();
  }

  @org.junit.Test
  public void testJavaScriptTimer() {
    Vertx vertx = Vertx.vertx();
    vertx.eventBus().<JsonObject>consumer("test").handler(msg -> {
      if (msg.body().getString("type").equals("endTestSuite")) {
        testComplete();
      }
    });
    vertx.deployVerticle("js:verticle/timer", ar -> {
      assertTrue(ar.succeeded());
    });
    await();
  }

  @Test
  public void testGroovyTimer() {
    Vertx vertx = Vertx.vertx();
    vertx.eventBus().<JsonObject>consumer("test").handler(msg -> {
      if (msg.body().getString("type").equals("endTestSuite")) {
        testComplete();
      }
    });
    vertx.deployVerticle("verticle/timer.groovy", ar -> {
      assertTrue(ar.succeeded());
    });
    await();
  }
}
