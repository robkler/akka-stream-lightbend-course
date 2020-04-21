package com.lightbend.akkassembly

import akka.NotUsed
import akka.stream.scaladsl.{Flow, Source}

import scala.collection._

class EngineShop(shipmentSize: Int) {
  val shipments: Source[Shipment, NotUsed] = Source.fromIterator {
    () =>
      Iterator.continually {
        Shipment(immutable.Seq.fill(shipmentSize)(Engine()))
      }
  }

  val engines: Source[Engine, NotUsed] = shipments.mapConcat(_.engines)

  val installEngine: Flow[UnfinishedCar, UnfinishedCar, NotUsed] = {
    Flow[UnfinishedCar].zip(engines).map {
      case (car, engine) => car.installEngine(engine)
    }
  }

}
