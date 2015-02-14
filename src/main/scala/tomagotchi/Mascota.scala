import java.util.Formatter.DateTime
import scala.concurrent.duration._

object Tipos {
  type Deadline = scala.concurrent.duration.Deadline
}

class Mascota(protected var estado: EstadoMascota) {

  def comer = {
    this.estado = this.estado match {
      case Contento(nivelContentitud) =>
        Contento(nivelContentitud + 1)
      case Aburrido(aburridoDeadline) if !aburridoDeadline.hasTimeLeft =>
        Contento()
    }
  }

  def jugar = {
    this.estado = this.estado match {
      case Contento(nivelContentitud) =>
        Contento(nivelContentitud + 2)
      case Aburrido(aburridoDeadline) =>
        Contento()
    }
  }

  def puedeJugar = this.estado.puedeJugar

  def setEstado(estado: EstadoMascota) {
    this.estado = estado
  }

  def seAburre() {
    this.setEstado(Aburrido(80.minute.fromNow)) //los int tiene metodos implicitamente para setear un Deadline
  }

}

sealed trait EstadoMascota {
  def puedeJugar: Boolean = return true
}

case class Contento(nivelContentitud: Int = 1) extends EstadoMascota {
  override def puedeJugar: Boolean = return true
}

case class Aburrido(aburridoDeadline: Tipos.Deadline) extends EstadoMascota {
  override def puedeJugar: Boolean = return true
}

case class Hambriento() extends EstadoMascota {
  override def puedeJugar: Boolean = return false
}