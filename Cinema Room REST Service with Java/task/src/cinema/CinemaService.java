package cinema;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CinemaService {
    Room room;

    public CinemaService() {
        this.room = new Room(9, 9);
    }

    public List<Seat> getSeats() {
        return room.getSeats();
    }

    public Seat bookSeat(int row, int column) {
        Seat seat = room.getSeat(row, column);
        room.bookSeat(seat);
        return seat;
    }

    public Seat returnSeat(UUID token) {
        return room.returnSeat(token);
    }

    public int getIncome() {
        return room.getIncome();
    }

    public int bookedSeats() {
        return room.bookedSeats();
    }

    public int availableSeats() {
        return room.availableSeats();
    }


}
