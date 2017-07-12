package com.scrumtrek.simplestore;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by User on 10.07.2017.
 */
public class CustomerTest {
    @Test
    public void shouldHaveNameWhenConstructed() {
        String name = "Vasya";
        Customer customer = new Customer(name);
        Assert.assertEquals(name, customer.getName());
    }

    @Test
    public void shouldStatementContainWhenStatement() {
        String name = "Vasya";
        Customer customer = new Customer(name);
        String statement = customer.Statement();

//       Movie movie1 = new Movie("movie1", PriceCodes.Regular);
        Movie movie1 = mock(Movie.class);
        when(movie1.getPriceCode()).thenReturn(PriceCodes.XXX);
        when(movie1.getTitle()).thenReturn("movie1");

        Movie movie2 = mock(Movie.class);
        when(movie2.getPriceCode()).thenReturn(PriceCodes.Childrens);
        when(movie2.getTitle()).thenReturn("movie2");

        Movie movie3 = mock(Movie.class);
        when(movie3.getPriceCode()).thenReturn(PriceCodes.NewRelease);
        when(movie3.getTitle()).thenReturn("movie3");

        Rental rent1 = mock(Rental.class);
        when(rent1.getDaysRented()).thenReturn(3);
        when(rent1.getMovie()).thenReturn(movie1);

        Rental rent2 = mock(Rental.class);
        when(rent2.getDaysRented()).thenReturn(1);
        when(rent2.getMovie()).thenReturn(movie2);

        Rental rent3 = mock(Rental.class);
        when(rent3.getDaysRented()).thenReturn(4);
        when(rent3.getMovie()).thenReturn(movie3);

        customer.addRental(rent1);

        Assert.assertTrue(statement.startsWith("Rental record for " + name + "\n"));
        //System.out.println(customer.Statement());
        Assert.assertTrue(statement.endsWith("Amount owed is " + 0.0 + "\n" +
                "You earned " + 0 + " frequent renter points."));

        //System.out.println(customer.Statement());
        double price = 2 * 0.85;
        price += 1.5*0.85;
        System.out.println("\tmovie1\t" + price);
        Assert.assertTrue(customer.Statement().contains("\tmovie1\t" + price));
        verify(movie1).getTitle();
        Assert.assertTrue(customer.Statement().endsWith("Amount owed is " + price + "\n" +
                "You earned " + 1 + " frequent renter points."));

        customer.addRental(rent2);
        //Assert.assertTrue(customer.Statement().contains("\tmovie1\t3.5"));
        Assert.assertTrue(customer.Statement().contains("\tmovie2\t1.5"));
        //System.out.println(customer.Statement());
        //Assert.assertTrue(customer.Statement().endsWith("Amount owed is " + (5.0) + "\n" +
        //        "You earned " + 2 + " frequent renter points."));

        customer.addRental(rent3);
        //Assert.assertTrue(customer.Statement().contains("\tmovie1\t3.5"));
        Assert.assertTrue(customer.Statement().contains("\tmovie2\t1.5"));
        Assert.assertTrue(customer.Statement().contains("\tmovie3\t12.0"));
        //System.out.println(customer.Statement());
        //Assert.assertTrue(customer.Statement().endsWith("Amount owed is " + (17.0) + "\n" +
        //        "You earned " + 4 + " frequent renter points."));
    }
}
