package observerPattern;

import java.util.ArrayList;
import java.util.List;

public class WeatherMonitoringApp {
  public static void main(String[] args) {
    System.out.println("WeatherMonitoringApp");
    WeatherData weatherData = new WeatherData();
    CurrentConditionsDisplay currentConditionsDisplay =
        new CurrentConditionsDisplay(weatherData);
    weatherData.setMeasurements(80, 65, 30.4f);
    weatherData.setMeasurements(70, 60, 30.4f);
    weatherData.setMeasurements(60, 55, 30.4f);
  }
}

/**
 * The Observer Pattern.
 */

interface Subject {
  void registerObserver(Observer observer);

  void removeObserver(Observer observer);

  void notifyObservers();
}

interface Observer {
  void update(float temp, float humidity, float pressure);
}

interface DisplayElement {
  void display();
}

class WeatherData implements Subject {
  private final List<Observer> observers;
  private float temperature;
  private float humidity;
  private float pressure;

  public WeatherData() {
    observers = new ArrayList<>();
  }

  @Override
  public void registerObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update(temperature, humidity, pressure);
    }
  }

  public void measurementsChanged() {
    notifyObservers();
  }

  public void setMeasurements(float temperature, float humidity, float pressure) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    measurementsChanged();
  }
}

class CurrentConditionsDisplay implements Observer, DisplayElement {
  private float temperature;
  private float humidity;
  private float pressure;
  private WeatherData weatherData;

  public CurrentConditionsDisplay(WeatherData weatherData) {
    this.weatherData = weatherData;
    weatherData.registerObserver(this);
  }

  @Override
  public void display() {
    System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity and " + pressure + "bar pressure");
  }

  @Override
  public void update(float temp, float humidity, float pressure) {
    this.temperature = temp;
    this.humidity = humidity;
    this.pressure = pressure;
    display();
  }
}