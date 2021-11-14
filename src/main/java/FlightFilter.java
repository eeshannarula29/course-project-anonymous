import java.time.LocalDate;
import java.util.ArrayList;

public class FlightFilter {
    public ArrayList<Flight> getFlightFromAirline(Airline airline, String from, String to, LocalDate date) {
        /**
         * Return all the flights of airline <airline> from <from> to <to> on <date>
         * @param airline  only require flights from this airline
         * @param from  departure location of flight
         * @param to  destination of flight
         * @param date  date on which flight is needed
         * @retun   A list of flights
         */
        ArrayList<Flight> flightsOnDate = new ArrayList<>();

        for (Flight flight : airline) {
            if (flight.getDepartureTime().toLocalDate().equals(date)) {
                if (flight.getFrom().equals(from) && flight.getTo().equals(to)) {
                    flightsOnDate.add(flight);
                }
            }
        }

        return flightsOnDate;
    }

    public ArrayList<Flight> getFlightsFromAllAirlines(AirlinesManager airlinesManager, String from, String to, LocalDate date) {
        /**
        Collect flights with the specific parameters from all the airlines in the manager, and return a combined list.

        - it uses Airline.getFlightByFilter function
        - it uses addAll function of arraylist to combine all arraylists.
         **/
        ArrayList<Flight> flights = new ArrayList<>();

        for (Airline airline: airlinesManager) {
            flights.addAll(this.getFlightFromAirline(airline, from, to, date));
        }

        return flights;
    }

    ArrayList<Flight> getSimilarFlightsInAirline(Airline airline, Flight flight) {
        /**
         *Returns all the flights in <airline> that are departing after <flight> but have the same
         * takeoff and landing locations
         */
        ArrayList<Flight> flight_so_far = new ArrayList<>();

        for (Flight f : airline) {
            if (f.getTo().equals(flight.getTo()) && f.getFrom().equals(flight.getFrom())) {
                if (f.getDepartureTime().isAfter(flight.getDepartureTime()) ) {
                    flight_so_far.add(f);
                }
            }
        }
        return flight_so_far;
    }

    ArrayList<Flight> getSimilarFlights(AirlinesManager airlinesManager, Flight flight) {
        /**
         *Returns all the flights from all airlines in <airlineManager> that are departing
         * after <flight> but have the same takeoff and landing locations.
         */
        ArrayList<Flight> flight_so_far = new ArrayList<>();

        for (Airline airline : airlinesManager) {
            flight_so_far.addAll(this.getSimilarFlightsInAirline(airline, flight));
        }

        return flight_so_far;
    }
}
