package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Cinema {
    private final int total_rows = 9;
    private final int total_columns = 9;
    private List<Seat> available_seats = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();
    private CinemaStats cinemaStats;

    public Cinema() {
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                available_seats.add(new Seat(i + 1, j + 1));
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public synchronized Ticket purchase(Seat seat) {
        if (available_seats.contains(seat)) {
            Ticket newTicket = new Ticket(seat);
            ticketList.add(newTicket);
            available_seats.remove(seat);
            return newTicket;
        }
        return null;
    }

    public synchronized Map<String, Seat> returnTicket(UUID token) {
        for (Ticket ticket : ticketList) {
            if (ticket.getToken().equals(token)) {
                available_seats.add(ticket.getTicket());
                ticketList.remove(ticket);
                return Map.of("returned_ticket", ticket.getTicket());
            }
        }
        return null;
    }

    public synchronized CinemaStats updateCinemaStats() {
        this.cinemaStats = new CinemaStats();
        cinemaStats.setNumber_of_available_seats(available_seats.size());
        cinemaStats.setNumber_of_purchased_tickets(ticketList.size());
        cinemaStats.setCurrent_income(ticketList);
        return cinemaStats;
    }
}
