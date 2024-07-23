package cinema;

import java.util.*;

public class Room {
    private final Seat[][] room;
    private final HashMap<UUID, Seat> bookedSeats = new HashMap<>();
    private final int rows;
    private final int columns;

    public Room(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.room = new Seat[this.rows][this.columns];
        initializeRoom();
    }

    private void initializeRoom() {
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                room[row][column] = new Seat(row + 1, column + 1);
            }
        }
    }

    public List<Seat> getSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < this.rows; i++) {
            seats.addAll(Arrays.asList(room[i]).subList(0, this.columns));
        }
        return seats;
    }

    public Seat getSeat(int row, int column) throws IllegalArgumentException {
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        } else {
            return room[row - 1][column - 1];
        }
    }

    public UUID bookSeat(Seat seat) {
        if (seat.isBooked()) {
            throw new IllegalArgumentException("The ticket has been already purchased!");
        }
        UUID token = seat.book();
        bookedSeats.put(token, seat);
        return token;
    }

    public Seat returnSeat(UUID token) {
        Seat seat = bookedSeats.remove(token);
        if (seat == null) {
            throw new IllegalArgumentException("Wrong token!");
        }
        seat.unBook();
        return seat;
    }

    public int getIncome() {
        return bookedSeats.values().stream().mapToInt(Seat::getPrice).sum();
    }

    public int roomSize() {
        return rows * columns;
    }

    public int bookedSeats() {
        return bookedSeats.size();
    }

    public int availableSeats() {
        return roomSize() - bookedSeats.size();
    }
}
