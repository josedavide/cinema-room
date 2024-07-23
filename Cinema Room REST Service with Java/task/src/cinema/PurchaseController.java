package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PurchaseController {

    private final CinemaService cinemaService;

    @Autowired
    public PurchaseController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }


    /*@PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestParam String row, @RequestParam String column) {
        try {
            int rowNumber = Integer.parseInt(row);
            int columnNumber = Integer.parseInt(column);
            Seat seat = cinemaService.bookSeat(rowNumber, columnNumber);
            return ResponseEntity.ok(seat);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid row or column");
        }
    }*/

    @PostMapping("/purchase")
    public Map<String, Object> purchase(@RequestBody Map<String, Integer> request) {
        int row = request.get("row");
        int column = request.get("column");;
        Seat seat = cinemaService.bookSeat(row, column);

        Map<String, Object> response = new HashMap<>();
        response.put("token", seat.getBookedToken());
        response.put("ticket", seat);

        return response;
    }

    @PostMapping("/return")
    public Map<String, Object> returnSeat(@RequestBody Map<String, UUID> request) {
        UUID token = request.get("token");
        Seat seat = cinemaService.returnSeat(token);

        Map<String, Object> response = new HashMap<>();
        response.put("ticket", seat);

        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
}
