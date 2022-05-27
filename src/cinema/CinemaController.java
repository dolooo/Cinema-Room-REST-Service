package cinema;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaController {
    Cinema cinema = new Cinema();

    @GetMapping(path = "/seats")
    Cinema getInfo() {
        return cinema;
    }

    @PostMapping(path = "/purchase")
    public ResponseEntity<Ticket> purchase(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        seat.setPrice();

        if (column > cinema.getTotal_columns() || row > cinema.getTotal_rows() || row < 0 || column < 0) {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        Ticket response = cinema.purchase(seat);

        if (response == null) {
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/return")
    public ResponseEntity<Map<String, Seat>> returnTicket(@RequestBody Token request) {
        UUID token = request.getToken();
        Map<String, Seat> response = cinema.returnTicket(token);
        if (response == null) {
            return new ResponseEntity(Map.of("error", "Wrong token!"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/stats")
    public ResponseEntity<CinemaStats> showStats(@RequestParam(name = "password", required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            return new ResponseEntity(Map.of("error", "The password is wrong!"),
                    HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(cinema.updateCinemaStats());
    }
}
