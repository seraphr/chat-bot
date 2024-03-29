package jp.seraphr.event

import org.scalatest.FlatSpec
import scala.collection.mutable.ArrayBuffer
import org.scalatest.Matchers

class GenericEventSourceTest extends FlatSpec with Matchers {
  "emit" should "fire Event" in {
    val tSource = new GenericEventSource[Int]
    val tBuffer = new ArrayBuffer[Int]
    val tEvent = tSource.event
    tEvent.subscribe(tBuffer += _)

    tSource.emit(1)
    tBuffer should have size 1
    tBuffer(0) should be(1)

    tSource.emit(11)
    tBuffer should have size 2
    tBuffer(1) should be(11)
  }

  "dispose" should "disable event handler" in {
    val tSource = new GenericEventSource[Int]
    val tBuffer = new ArrayBuffer[Int]
    val tEvent = tSource.event
    val tObserver = tEvent.subscribe(tBuffer += _)

    tSource.emit(1)
    tBuffer should have size 1
    tBuffer(0) should be(1)

    tObserver.dispose()
    tSource.emit(11)
    tBuffer should have size 1
  }
}