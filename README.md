# sensor_bici
La idea es modificar el sensor de una bicicleta estatica, para que reporte mas datos de lo que tiene; para compararlos y tener un historial de como evoluciona.

02/08/2017
El ESP8266 por el momento solo se utiliza de sensor; se conecta a la red wifi y transmite a la aplicacion la frecuencia de las vueltas. El calculo de la frecuencia hay que revisarlo.
La aplicacion abre un puerto en la pc (4444), y recibe los datos del sensor, los cuales muestra en pantalla mediante un velocimetro. Hay que ponerlo a escala; puede que otro tipo de grafico sea mas adecuado.
