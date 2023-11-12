/*
 * Created by ArduinoGetStarted, https://arduinogetstarted.com
 *
 * Arduino - Ultrasonic Sensor HC-SR04
 *
 * Wiring: Ultrasonic Sensor -> Arduino:
 * - VCC  -> 5VDC
 * - TRIG -> Pin 6
 * - ECHO -> Pin 5
 * - GND  -> GND
 *
 * Tutorial is available here: https://arduinogetstarted.com/tutorials/arduino-ultrasonic-sensor
 */


 /*-----( Import needed libraries )-----*/
#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = {
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

/*-----( Declare objects )-----*/
IPAddress arduinoIp(192,168,0, 100);
IPAddress gateway(192, 168, 0, 1);
IPAddress subnet(255, 255, 255, 0);
IPAddress serverIp(204, 77, 50, 53);
int serverPort = 80;
char pageName[] = "/propertymonitor/api/sensorreadings";
EthernetClient client;
// Declare http Post request data
char json[41];  // Must be large enough to hold entire JSON payload
#define SENSORID 4046

#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
//byte mac[] = {
 // 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

/*-----( Declare objects )-----*/
//IPAddress arduinoIp(192,168,0, 100);
//IPAddress gateway(192, 168, 0, 1);
//IPAddress subnet(255, 255, 255, 0);
//IPAddress serverIp(204, 77, 50, 53);
//int serverPort = 80;
//char pageName[] = "/propertymonitor/api/sensorreadings";
//EthernetClient client;
// Declare http Post request data
//char json[41];  // Must be large enough to hold entire JSON payload
//#define SENSORID ???

int trigPin = 6;    // TRIG pin
int echoPin = 5;    // ECHO pin
#define FILTER_N 9

int distances[FILTER_N];

float duration_us, distance_cm, avgDistance, tolerance, prevDistance;

void setup() {
  // begin serial port
  Serial.begin (115200);

  // configure the trigger pin to output mode
  pinMode(trigPin, OUTPUT);
  // configure the echo pin to input mode
  pinMode(echoPin, INPUT);

    //----------------- Start ethernet ---------
  if (Ethernet.begin(mac) == 0) Ethernet.begin(mac, arduinoIp, gateway, subnet);
  delay(1000);
  //--------------------------------------------
}

void loop() {
  // generate 10-microsecond pulse to TRIG pin

  Ethernet.maintain();

  for(int i =0; i < FILTER_N; i++) {
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10);
    digitalWrite(trigPin, LOW);

    // measure duration of pulse from ECHO pin
    duration_us = pulseIn(echoPin, HIGH);

    // calculate the distance
    distance_cm = 0.017 * duration_us;

    if (distance_cm < 400 && distance_cm > 0)
    {
      distances[i] = distance_cm;
    }
    delay(5);
  }
  bubbleSort(distances, FILTER_N);
  avgDistance = calcAvg(distances, FILTER_N);
  tolerance = (long) (avgDistance * 0.05);

  if(avgDistance < prevDistance - tolerance || avgDistance > prevDistance + tolerance) {
    for(int i = 0; i < FILTER_N; i++) {
      Serial.print(distances[i]);
      Serial.print(' ');
    }
       sprintf(json, "{'SensorId':%d,'Value':%d}", SENSORID, (int)avgDistance);
 // Cast currTempReading to int
    Serial.println(json);
    postPage(json);
    delay(1000);
  }
  prevDistance = avgDistance;
}

void bubbleSort(int arr[], int n)
{
  int i, j;
  for(i=0; i < n-1; i++)
    for(j = 0; j < n-i-1; j++)
      if(arr[j] > arr[j+1]) {
        int temp = arr[j];
        arr[j] = arr[j+1];
        arr[j+1] = temp;
      }
}

int calcAvg (int arr[], int n) {
  int sum = 0;
  for(int i = 1; i < n - 5; i++) {
    sum += arr[i];
  }
  return sum / (n - 6);
}

//------------------- ex2b -------------------
// --------------------------------- postPage()  ---------------------------------
// ---------------------- Performs http Post of json payload ---------------------
byte postPage(char* json)
{
  int inChar;
  Serial.println("postPage() connecting...");

  if (client.connect(serverIp, serverPort)) {
    Serial.println("postPage() connected");

    // send http header
//    client.println("POST /propertymonitor/api/sensorreadings HTTP/1.1");
    client.print("POST ");
    client.print(pageName);
    client.println(" HTTP/1.1");
    client.println("Host: 192.168.0.100"); // or generate from your server variable to not hardwire
    client.println("User-Agent: Arduino/uno ethernet");
    client.println("Connection: close");
    client.println("Content-Type: application/json");
    client.print("Content-Length: ");
    client.println(strlen(json));// number of bytes in the payload
    client.println();// important: need an empty line here
    // send payload
    client.println(json);

    Serial.print("Distances array: ");
for (int i = 0; i < FILTER_N; i++) {
  Serial.print(distances[i]);
  Serial.print(' ');
}
Serial.println();

avgDistance = calcAvg(distances, FILTER_N);
Serial.print("Calculated avgDistance: ");
Serial.println(avgDistance);

  }
  else
  {
    Serial.println(F("postPage() connect failed"));
    return 0;
  }

  int connectLoop = 0;

  while(client.connected())
  {
    while(client.available())
    {
      inChar = client.read();
//      Serial.write(inChar);
      connectLoop = 0;
    }

    delay(1);
    connectLoop++;
    if(connectLoop > 10000)
    {
      Serial.println();
      Serial.println(F("Timeout"));
      client.stop();
    }
  }

  Serial.println(F("disconnecting."));
  Serial.println();
  
  client.stop();
  return 1;
}
  //--------------------------------------------

// // An optimized version of Bubble Sort
// void bubbleSort(int arr[], int n)
// {
//     int i, j;
//     bool swapped;
//     for (i = 0; i < n - 1; i++) {
//         swapped = false;
//         for (j = 0; j < n - i - 1; j++) {
//             if (arr[j] > arr[j + 1]) {
//                 swap(&arr[j], &arr[j + 1]);
//                 swapped = true;
//             }
//          }
//   // If no two elements were swapped
//         // by inner loop, then break
//         if (swapped == false)
//             break;
//     }
// }
