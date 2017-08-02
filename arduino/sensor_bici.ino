#include <ESP8266WiFi.h>
//Necesarios para la libreria de wifimanager
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <WiFiManager.h>
/*
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);
*/
const char* servidor = "192.168.1.106";
int puerto = 4444;

int pinBike = D6;
int pinLed = D7;

int vueltas = 0;
int vueltasAcum = 0;

const char* ssid = "linksys";
const char* password = "perromateo";
WiFiClient client;
boolean conectado = false;
void setup() {

  Serial.begin(9600);
  /*
  lcd.begin();
  lcd.backlight();
  lcd.print("iniciando...");
  */
  pinMode(pinBike, INPUT);
  attachInterrupt(digitalPinToInterrupt(pinBike), cuentaVueltas, RISING);
  pinMode(pinLed, OUTPUT);

  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);

    //lcd.clear();
    Serial.println(WiFi.localIP());
    
  }
  if (client.connect(servidor, puerto)) {
    Serial.println("conectado");
    conectado = true;
  }
  //lcd.clear();
}


unsigned long currentTime;
float frequency;

void loop() {

  currentTime = millis();
  frequency = getFrecuencia();

  Serial.println(frequency);

  /*
  lcd.setCursor(0, 0);
  lcd.print(frequency);
  */
  if (conectado) {
    client.print(frequency);
    client.print("\r\n");
  }
  //client.stop();

}

void brillo() {
    digitalWrite(pinLed, HIGH);
    delay(300);
    digitalWrite(pinLed, LOW);
    delay(300);
    digitalWrite(pinLed, HIGH);
    delay(300);
    digitalWrite(pinLed, LOW);
}



void cuentaVueltas() {
  vueltas++;
}
int measureInterval = 1000;

float getFrecuencia() {
  vueltas = 0;

  interrupts();
  delay(measureInterval);
  noInterrupts();

  return (float)vueltas * 1000 / measureInterval;
}

