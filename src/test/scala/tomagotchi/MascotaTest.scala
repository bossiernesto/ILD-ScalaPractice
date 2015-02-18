package tomagotchi
import org.scalatest.FreeSpec
import org.scalatest.Matchers
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

class MascotaTest extends FreeSpec with Matchers {

  "La mascota come" - {
    "Si esta hambrienta y come se pone contenta" in {
      val mascota = new Mascota(Hambriento())
      mascota.comer
      mascota.getEstado should be(Contento())
    }

    "Si esta contenta su nivel de contentitud se incrementa en 1" in {
      val mascota = new Mascota(Contento(1))
      mascota.comer
      mascota.getEstado should be(Contento(2))
    }

    "Si esta aburrida y come y no pasaron aún 80 minutos, sigue aburrida" in {
      val aburrido = new Aburrido()
      val mascota = new Mascota(aburrido)
      mascota.comer
      mascota.getEstado should be(aburrido)
    }

    "Si esta aburrida y come y paso el deadline, se pone contenta" in {
      val deadline = 5.seconds.fromNow
      val aburrido = new Aburrido(deadline)
      val mascota = new Mascota(aburrido)
      Thread.sleep(deadline.timeLeft.toMillis)
      future { blocking(deadline.timeLeft.toMillis); "done" }
      mascota.comer
      mascota.getEstado should be(Contento())
    }
  }

  "La mascota juega" - {
    "Si esta contenta su nivel de contentitud se incrementa en 2" in {
      val mascota = new Mascota(Contento(3))
      mascota.jugar
      mascota.getEstado should be(Contento(5))
    }

    "Si esta aburrida, se pone contenta" in {
      val mascota = new Mascota(Aburrido())
      mascota.jugar
      mascota.getEstado should be(Contento())
    }

    "Si esta hambrienta, no pasa nada" in {
      val hambriento = new Hambriento()
      val mascota = new Mascota(hambriento)
      mascota.jugar
      mascota.getEstado should be(hambriento)
    }
  }

  "Puede jugar?" - {
    "Si esta contenta, devuelve true" in { new Mascota(Contento()).puedeJugar should be(true) }
    "Si esta aburrida, devuelve true" in { new Mascota(Aburrido()).puedeJugar should be(true) }
    "Si esta hambrienta, devuelve false" in { new Mascota(Hambriento()).puedeJugar should be(false) }
  }
}