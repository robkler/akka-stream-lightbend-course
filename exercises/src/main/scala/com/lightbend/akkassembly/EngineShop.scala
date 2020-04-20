package com.lightbend.akkassembly

import akka.NotUsed
import akka.stream.scaladsl.Source

import scala.collection._

class EngineShop(shipmentSize: Int) {
  val shipments: Source[Shipment,NotUsed] =  Source.fromIterator{

    () => Iterator.continually{Shipment(immutable.Seq.fill(shipmentSize)(Engine()))}
  }
}
