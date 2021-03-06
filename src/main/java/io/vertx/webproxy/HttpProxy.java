package io.vertx.webproxy;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.net.SocketAddress;
import io.vertx.webproxy.impl.HttpProxyImpl;

import java.util.function.Function;


@VertxGen
public interface HttpProxy extends Handler<HttpServerRequest> {

    static HttpProxy reverseProxy(HttpClient client) { return new HttpProxyImpl(client); }

    @Fluent
    HttpProxy target(SocketAddress address);

    @Fluent
    HttpProxy target(int port, String host);

    @Fluent
    HttpProxy selector(Function<HttpServerRequest, Future<SocketAddress>> selector);

    void handle(HttpServerRequest request);

    ProxyRequest proxy(HttpServerRequest request, SocketAddress target);
}
