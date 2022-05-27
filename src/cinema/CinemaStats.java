package cinema;

import java.util.List;

public class CinemaStats {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    public int getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(List<Ticket> ticketList) {
        int income = 0;
        for (Ticket ticket : ticketList) {
            income += ticket.getTicket().getPrice();
        }
        this.current_income = income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public void setNumber_of_available_seats(int number_of_available_seats) {
        this.number_of_available_seats = number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void setNumber_of_purchased_tickets(int number_of_purchased_tickets) {
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }


}
