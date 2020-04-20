package com.lightbend.akkassembly

import akka.Done
import akka.event.LoggingAdapter
import akka.stream.scaladsl.Sink

import scala.concurrent.Future

class Auditor {
  val count: Sink[Any, Future[Int]] = Sink.fold(0)((x, _) => x + 1)

  def log(implicit loggingAdapter:LoggingAdapter):Sink[Any,Future[Done]] = Sink.foreach(x => loggingAdapter.debug(x.toString))

}
