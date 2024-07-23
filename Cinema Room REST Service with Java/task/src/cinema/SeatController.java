package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SeatController {

    private final CinemaService cinemaService;

    @Autowired
    public SeatController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Map<String, Object> getSeats() {
        int rows = 9;
        int columns = 9;

        List<Seat> availableSeats = cinemaService.getSeats();


        Map<String, Object> response = new HashMap<>();
        response.put("rows", rows);
        response.put("columns", columns);
        response.put("seats", availableSeats);

        return response;
    }
}


