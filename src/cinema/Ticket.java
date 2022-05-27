package cinema;

import java.util.UUID;

public class Ticket {
    private UUID token;
    private Seat ticket;

    public Ticket(Seat seat) {
        this.ticket = seat;
        setToken();
    }

    public UUID getToken() {
        return token;
    }

    public void setToken() {
        this.token = UUID.randomUUID();
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
