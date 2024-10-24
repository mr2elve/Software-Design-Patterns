public class WeatherMonitoringSystem {

    interface WeatherDisplay {
        void update(float temperature, float humidity, float pressure);
    }

    class CurrentConditionsDisplay implements WeatherDisplay {
        @Override
        public void update(float temperature, float humidity, float pressure) {
            System.out.println("Current conditions: " + temperature + "°C, " + humidity + "% humidity, " + pressure + " Pa pressure.");
        }
    }

    class StatisticsDisplay implements WeatherDisplay {
        @Override
        public void update(float temperature, float humidity, float pressure) {
            System.out.println("Weather statistics: " + temperature + "°C, " + humidity + "% humidity, " + pressure + " Pa pressure.");
        }
    }

    class ForecastDisplay implements WeatherDisplay {
        @Override
        public void update(float temperature, float humidity, float pressure) {
            System.out.println("Weather forecast: temperature " + (temperature + 2) + "°C, pressure " + (pressure + 1) + " Pa.");
        }
    }

    class WeatherStation {
        private float temperature;
        private float humidity;
        private float pressure;

        private List<WeatherDisplay> displays = new ArrayList<>();

        public void addDisplay(WeatherDisplay display) {
            displays.add(display);
        }

        public void removeDisplay(WeatherDisplay display) {
            displays.remove(display);
        }

        public void setMeasurements(float temperature, float humidity, float pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            notifyDisplays();
        }

        private void notifyDisplays() {
            for (WeatherDisplay display : displays) {
                display.update(temperature, humidity, pressure);
            }
        }
    }

    public static void main(String[] args) {
        WeatherMonitoringSystem system = new WeatherMonitoringSystem();
        WeatherStation weatherStation = system.new WeatherStation();

        CurrentConditionsDisplay currentDisplay = system.new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = system.new StatisticsDisplay();
        ForecastDisplay forecastDisplay = system.new ForecastDisplay();

        weatherStation.addDisplay(currentDisplay);
        weatherStation.addDisplay(statisticsDisplay);
        weatherStation.addDisplay(forecastDisplay);

        weatherStation.setMeasurements(25, 65, 1013);
        weatherStation.setMeasurements(28, 70, 1010);
    }
}
