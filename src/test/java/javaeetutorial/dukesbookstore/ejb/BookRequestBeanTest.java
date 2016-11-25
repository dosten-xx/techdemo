/**
 * 
 */
package javaeetutorial.dukesbookstore.ejb;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;

import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.exception.BookNotFoundException;
import javaeetutorial.dukesbookstore.exception.BooksNotFoundException;
import javaeetutorial.dukesbookstore.exception.OrderException;
import javaeetutorial.dukesbookstore.web.managedbeans.ShoppingCart;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * BookRequest EJB unit test.
 * 
 * @author dosten-xx
 */
@RunWith(Arquillian.class)
public class BookRequestBeanTest
{
   private static final Logger logger = Logger.getLogger("dukesbookstore.ejb.BookRequestBeanTest");

   @Deployment
   public static Archive<?> createTestArchive()
   {
      return ShrinkWrap.create(WebArchive.class, "dukes-bookstore-test.war").addPackages(true, "javaeetutorial.dukesbookstore")
      // .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
      // .addAsResource("META-INF/persistence.xml")
               .addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml")
      // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
      // Deploy our test datasource
      // .addAsWebInfResource("test-ds.xml");
      ;
   }

   @EJB
   BookRequestBean bookRequestBean;

   /**
    * Test method for
    * {@link javaeetutorial.dukesbookstore.ejb.BookRequestBean#createBook(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Boolean, java.lang.Integer, java.lang.String, java.lang.Integer)}
    * .
    * 
    * @throws BookNotFoundException
    */
   @Test
   public void testCreateBook() throws BookNotFoundException
   {
      bookRequestBean.createBook("500", "Osten", "Darren", "Techdemo for Dummies", 24.99, false, 2015, "Techdemo manual", 400);
      Book result = bookRequestBean.getBook("500");
      assertNotNull(result);
      assertEquals("Darren", result.getFirstname());
      assertEquals(new Double(24.99), result.getPrice());

      try {
         // try adding the same book id
         bookRequestBean.createBook("500", "Osten", "Darren", "Techdemo for Dummies", 24.99, false, 2015, "Techdemo manual", 400);
         fail("Should get duplicat ID exception here");

         // null id
         bookRequestBean.createBook(null, null, null, null, null, null, null, null, null);
         fail("Should get ID not null exception here");
      }
      catch (Exception e) {
         // expected
      }
   }

   /**
    * Test method for {@link javaeetutorial.dukesbookstore.ejb.BookRequestBean#getBooks()}.
    * 
    * @throws BooksNotFoundException
    */
   @Test
   public void testGetBooks() throws BooksNotFoundException
   {
      List<Book> results = bookRequestBean.getBooks();
      assertNotNull(results);
      logger.log(Level.INFO, "found " + results.size() + " books");
   }

   /**
    * Test method for {@link javaeetutorial.dukesbookstore.ejb.BookRequestBean#getBook(java.lang.String)}.
    * 
    * @throws BookNotFoundException
    */
   @Test
   public void testGetBook() throws BookNotFoundException
   {
      Book result = null;
      try {
         result = bookRequestBean.getBook("bogusid");
         logger.log(Level.FINER, result.toString());
         fail("should get BookNotFoundException here");
      }
      catch (BookNotFoundException e) {
         // expected
      }

      result = bookRequestBean.getBook("201");
      assertNotNull(result);
   }

   /**
    * Test method for {@link javaeetutorial.dukesbookstore.ejb.BookRequestBean#buyBooks(javaeetutorial.dukesbookstore.web.managedbeans.ShoppingCart)}.
    * 
    * @throws BookNotFoundException
    */
   @Test
   public void testBuyBooks() throws OrderException, BookNotFoundException
   {
      ShoppingCart cart = new ShoppingCart();

      Book result = bookRequestBean.getBook("201");
      Integer startingInv = result.getInventory();

      cart.add("201", result);
      bookRequestBean.buyBooks(cart);

      result = bookRequestBean.getBook("201");
      Integer endingInv = result.getInventory();
      assertTrue(endingInv == (startingInv - 1));
   }

   /**
    * Test method for {@link javaeetutorial.dukesbookstore.ejb.BookRequestBean#buyBook(java.lang.String, int)}.
    * 
    * @throws OrderException
    * @throws BookNotFoundException
    */
   @Test
   public void testBuyBook() throws OrderException, BookNotFoundException
   {
      Book result = bookRequestBean.getBook("201");
      Integer startingInv = result.getInventory();

      bookRequestBean.buyBook("201", 1);

      result = bookRequestBean.getBook("201");
      Integer endingInv = result.getInventory();
      assertTrue(endingInv == (startingInv - 1));

      try {
         bookRequestBean.buyBook("201", Integer.MAX_VALUE);
         fail("Should get order exception here");
      }
      catch (OrderException e) {
         // expected
      }
   }

   /**
    * Test method for {@link javaeetutorial.dukesbookstore.ejb.BookRequestBean#updateInventory(javaeetutorial.dukesbookstore.web.managedbeans.ShoppingCart)}.
    * 
    * @throws BookNotFoundException
    * @throws OrderException
    */
   @Test
   public void testUpdateInventory() throws BookNotFoundException, OrderException
   {
      ShoppingCart cart = new ShoppingCart();

      Book result = bookRequestBean.getBook("201");
      Integer startingInv = result.getInventory();

      cart.add("201", result);
      bookRequestBean.updateInventory(cart);

      result = bookRequestBean.getBook("201");
      Integer endingInv = result.getInventory();
      assertTrue(endingInv == (startingInv - 1));
   }
}
