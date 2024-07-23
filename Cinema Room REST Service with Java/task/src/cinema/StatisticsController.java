package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatisticsController {
    @Autowired
    private CinemaService cinemaService;

    public StatisticsController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStatistics(@RequestParam(required = false) String password) {

        if (password == null) {
            throw new IllegalArgumentException("The password is wrong!");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("income", cinemaService.getIncome());
        response.put("available", cinemaService.availableSeats());
        response.put("purchased", cinemaService.bookedSeats());

        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
    }
}
