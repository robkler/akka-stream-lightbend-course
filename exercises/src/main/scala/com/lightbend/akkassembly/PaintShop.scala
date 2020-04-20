package com.lightbend.akkassembly

import akka.NotUsed
import akka.stream.scaladsl.Source

import scala.util.Random

class PaintShop(colorSet: Set[Color]) {
  val colors: Source[Color,NotUsed] = Source.cycle(() => colorSet.toIterator)
}
