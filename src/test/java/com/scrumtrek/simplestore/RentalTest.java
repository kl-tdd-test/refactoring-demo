package com.scrumtrek.simplestore;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by User on 10.07.2017.
 */
public class RentalTest {
    @Test
    public void shoutHaveMovieAndDaysRentedWhenConstructed() {
        Movie film = new Movie("test title", PriceCodes.Regular);
        int days = 7;

        Rental sut = new Rental(film, days);

        Assert.assertEquals(days, sut.getDaysRented());
        Assert.assertSame(film, sut.getMovie());
    }
}
