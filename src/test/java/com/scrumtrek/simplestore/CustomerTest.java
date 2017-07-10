package com.scrumtrek.simplestore;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(name, customer.getName());

        String statement = customer.Statement();

        Assert.assertTrue(statement.startsWith("Rental record for " + name + "\n"));
        //System.out.println(customer.Statement());
        Assert.assertTrue(statement.endsWith("Amount owed is " + 0.0 + "\n" +
            "You earned " + 0 + " frequent renter points."));

        Movie movie1 = new Movie("movie1", PriceCodes.Regular);
        Movie movie2 = new Movie("movie2", PriceCodes.Childrens);
        Movie movie3 = new Movie("movie3", PriceCodes.NewRelease);

        Rental rent1 = new Rental(movie1, 3);
        Rental rent2 = new Rental(movie2, 1);
        Rental rent3 = new Rental(movie3, 4);

        customer.addRental(rent1);
        //System.out.println(customer.Statement());
        Assert.assertTrue(customer.Statement().contains("\tmovie1\t3.5"));
        Assert.assertTrue(customer.Statement().endsWith("Amount owed is " + (2+1.5) + "\n" +
                "You earned " + 1 + " frequent renter points."));

        customer.addRental(rent2);
        Assert.assertTrue(customer.Statement().contains("\tmovie1\t3.5"));
        Assert.assertTrue(customer.Statement().contains("\tmovie2\t1.5"));
        //System.out.println(customer.Statement());
        Assert.assertTrue(customer.Statement().endsWith("Amount owed is " + (5.0) + "\n" +
                "You earned " + 2 + " frequent renter points."));

        customer.addRental(rent3);
        Assert.assertTrue(customer.Statement().contains("\tmovie1\t3.5"));
        Assert.assertTrue(customer.Statement().contains("\tmovie2\t1.5"));
        Assert.assertTrue(customer.Statement().contains("\tmovie3\t12.0"));
        //System.out.println(customer.Statement());
        Assert.assertTrue(customer.Statement().endsWith("Amount owed is " + (17.0) + "\n" +
                "You earned " + 4 + " frequent renter points."));
    }
}
