package com.lightbend.akkassembly

import akka.NotUsed
import akka.stream.scaladsl.{Flow, Source}

class WheelShop {
  val wheels: Source[Wheel, NotUsed] = Source.repeat(Wheel())

  val installWheels: Flow[UnfinishedCar, UnfinishedCar, NotUsed] = {
    Flow[UnfinishedCar].zip(wheels.grouped(4)).map {
      case (car, wheels) => car.installWheels(wheels)
    }
  }
}
