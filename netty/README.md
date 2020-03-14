# netty入门

## 概念

- `Channel`: 管道，对socket的封装。
- `EventLoopGroup`:  EventLoop的线程池，每个Channel分配一个EventLoop，用于处理客户端连接请求、数据处理等事件，eventLoop是一个线程驱动，其生命周期内只绑定一个线程，让该线程处理一个Channel的所用IO事件。一个Channel只能绑定一个eventLoop，但是一个eventLoop可以处理多个Channel的请求。
- `ServerBootStrap`: 配置netty，将各个组件串联起来，服务端使用ServerBootStrap，客户端使用BootStrap
- `ChannelHandler/ChannlePipeline`:  ChannelHandler是对Channel中数据的处理器，这些处理器可以是netty定义好的编解码器，也可以是用户自定义的。这些处理器会被统一添加到一个ChannelPipeline对象中，根据添加的先后顺序对Channel中的数据进行处理。
- `ChannelFuture`: netty中的io操作是异步的，ChannelFuture是这个操作的结果