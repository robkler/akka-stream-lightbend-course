package com.lightbend.akkassembly

import akka.NotUsed
import akka.stream.scaladsl.Flow
import akka.stream.{ActorAttributes, Supervision}

object QualityAssurance{
  case class CarFailedInspection(car: UnfinishedCar) extends IllegalStateException(s"Car failed inspection: $car")
}

class QualityAssurance {
  import QualityAssurance._
  val decider: Supervision.Decider = {
    case CarFailedInspection(car) => Supervision.Resume
  }

  val inspect: Flow[UnfinishedCar, Car, NotUsed] = Flow[UnfinishedCar].map {
    case UnfinishedCar(Some(color), Some(engine), wheels, upgrade) if wheels.size == 4 =>
      Car(SerialNumber(), color, engine, wheels, upgrade)
    case car => throw CarFailedInspection(car)
  }.withAttributes(ActorAttributes.supervisionStrategy(decider))

}
