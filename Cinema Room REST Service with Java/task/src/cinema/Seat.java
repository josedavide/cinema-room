package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Seat {
    private static final int PRICE = 8;
    private static final int PRICE_HIGH = 10;
    private static final int EXPENSIVE_SEATS = 4;


    private int row;
    private int column;
    private int price;

    @JsonIgnore
    private UUID bookedToken;


    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = PRICE;
        if (row <= EXPENSIVE_SEATS) {
            this.price = PRICE_HIGH;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public UUID getBookedToken() {
        return bookedToken;
    }

    public void setBookedToken(UUID bookedToken) {
        this.bookedToken = bookedToken;
    }

    @JsonIgnore
    public boolean isBooked() {
        return this.bookedToken != null;
    }

    public UUID book() {
        this.bookedToken = UUID.randomUUID();
        return this.bookedToken;
    }

    public void unBook() {
        this.bookedToken = null;
    }

}
