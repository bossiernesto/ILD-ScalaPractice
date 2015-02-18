package tomagotchi
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
      case Hambriento() => Contento()
      case otro => otro
    }
  }

  def jugar = {
    this.estado = this.estado match {
      case Contento(nivelContentitud) =>
        Contento(nivelContentitud + 2)
      case Aburrido(aburridoDeadline) =>
        Contento()
      case otro => otro
    }
  }

  def puedeJugar = this.estado.puedeJugar

  def getEstado = this.estado
  
  def setEstado(estado: EstadoMascota) {
    this.estado = estado
  }

  def seAburre() {
    this.setEstado(Aburrido()) //los int tiene metodos implicitamente para setear un Deadline
  }

}

sealed trait EstadoMascota {
  def puedeJugar: Boolean = return true
}

case class Contento(nivelContentitud: Int = 1) extends EstadoMascota {
  override def puedeJugar: Boolean = return true
}

case class Aburrido(aburridoDeadline: Tipos.Deadline = 80.minute.fromNow) extends EstadoMascota {
  override def puedeJugar: Boolean = return true
}

case class Hambriento() extends EstadoMascota {
  override def puedeJugar: Boolean = return false
}