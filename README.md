# ILD-ScalaPractice

##Ejercicio 2 – mascota virtual

Modelar una mascota virtual, onda Tamagotchi, incluyendo los mensajes correspondientes a las
acciones de comer y jugar, y la pregunta acerca de si puede jugar o no.
También hay que poder conocer el nivel de contenta de una mascota, que es un número entero
mayor o igual que 0, donde a mayor nivel, más contenta está la mascota.
Una mascota puede estar aburrida, hambrienta o contenta; y su comportamiento depende de en qué
estado esté. Veamos:
Cuando una mascota come, pasa lo siguiente
• Si está hambrienta, se pone contenta.
• Si está contenta, su nivel se incrementa en una unidad.
• Si está aburrida, y hace más de 80 minutos que está aburrida, entonces se pone contenta.
• Si está aburrida desde hace 80 minutos o menos, entonces no le pasa nada, no cambia nada.

Cuando una mascota juega, pasa lo siguiente
• Si está contenta, su nivel se incrementa en dos unidades.
• Si está aburrida, se pone contenta.
• No produce ningún efecto jugar con la mascota si esta hambrienta.
Una mascota puede jugar si está contenta o aburrida, si está hambrienta no.
