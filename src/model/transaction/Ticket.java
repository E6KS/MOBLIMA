package model.transaction;

import controller.TicketPriceController;
import model.Model;
import model.cinema.CinemaType;
import model.movie.Movie;
import model.movie.MovieEnums;

import static controller.TicketPriceController.getBasePrice;

public class Ticket implements Model {
    private Movie movie;
    private MovieEnums.MovieType movieType;
    private CinemaType cinemaType;
    private double basePrice;

    public Ticket(Movie movie, MovieEnums.MovieType movieType, CinemaType cinemaType) {
        this.movie = movie;
        this.movieType = movieType;
        this.cinemaType = cinemaType;
        this.basePrice = getBasePrice();
    }

    public double getTicketCharges(MovieEnums.MovieType movieType, CinemaType cinemaType, boolean isHoliday, boolean isWeekend) {
        double ticketPrice = basePrice;
        ticketPrice += cinemaType.getTicketPrice();
        ticketPrice += movieType.getTicketPrice();
        if (isHoliday) {
            ticketPrice += TicketPriceController.getPublicHolidayCharges();
        } else if (isWeekend) {
            ticketPrice += TicketPriceController.getWeekendCharges();
        }
        return ticketPrice;
    }

    public Movie getMovie() {
        return movie;
    }

    public MovieEnums.MovieType getMovieType() {
        return movieType;
    }

    public CinemaType getCinemaType() {
        return cinemaType;
    }
}