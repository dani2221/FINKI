package WeatherForecast;

import java.util.*;

public class WeatherApplication {

    public static void main(String[] args) {
        WeatherDispatcher weatherDispatcher = new WeatherDispatcher();

        CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherDispatcher);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDispatcher);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            weatherDispatcher.setMeasurements(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
            if(parts.length > 3) {
                int operation = Integer.parseInt(parts[3]);
                if(operation==1) {
                    weatherDispatcher.remove(forecastDisplay);
                }
                if(operation==2) {
                    weatherDispatcher.remove(currentConditions);
                }
                if(operation==3) {
                    weatherDispatcher.register(forecastDisplay);
                }
                if(operation==4) {
                    weatherDispatcher.register(currentConditions);
                }

            }
        }
    }
}
interface ISubscriber {
    void update(double temp, double humidity, double pressure);
}
class ForecastDisplay implements ISubscriber {
    private double pressure;

    public ForecastDisplay(WeatherDispatcher dispatcher) {
        pressure=0.0;
        dispatcher.register(this);
    }

    @Override
    public void update(double temp, double humidity, double pressure) {
        if(pressure>this.pressure) System.out.println("Forecast: Improving");
        else if(pressure==this.pressure) System.out.println("Forecast: Same");
        else System.out.println("Forecast: Cooler");
        this.pressure=pressure;
    }
}

class CurrentConditionsDisplay implements ISubscriber {
    public CurrentConditionsDisplay(WeatherDispatcher dispatcher) {
        dispatcher.register(this);
    }

    @Override
    public void update(double temp, double humidity, double pressure) {
        System.out.printf("Temperature: %.1fF\nHumidity: %.1f%%\n",temp,humidity);
    }
}
class WeatherDispatcher{
    Set<ISubscriber> subs;

    public WeatherDispatcher() {
        this.subs = new HashSet<ISubscriber>();
    }

    public void register(ISubscriber sub){
        subs.add(sub);
    }
    public void remove(ISubscriber sub){
        subs.remove(sub);
    }
    public void setMeasurements(float temperature, float humidity, float pressure){
        subs.forEach(x->x.update(temperature,humidity,pressure));
        System.out.println();
    }
}