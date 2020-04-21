package com.lightbend.akkassembly

import akka.{Done, NotUsed}
import akka.event.LoggingAdapter
import akka.stream.scaladsl.{Flow, Sink}

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

class Auditor {
  val count: Sink[Any, Future[Int]] = Sink.fold(0)((x, _) => x + 1)

  def log(implicit loggingAdapter: LoggingAdapter): Sink[Any, Future[Done]] = Sink.foreach(x => loggingAdapter.debug(x.toString))

  def sample(sampleSize: FiniteDuration): Flow[Car, Car, NotUsed] = Flow[Car].takeWithin(sampleSize)

}
