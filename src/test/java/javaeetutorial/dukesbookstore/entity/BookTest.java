package javaeetutorial.dukesbookstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest
{
   @Test
   public void testHashCode()
   {
      Book book = new Book();
      book.setBookId("500");
      int hash = book.hashCode();

      book.setBookId("800");
      int hash2 = book.hashCode();

      assertFalse(hash == hash2);
   }

   @Test
   public void testEqualsObject()
   {
      Book book1 = new Book();
      book1.setBookId("500");

      assertNotEquals(book1, null);
      assertNotEquals(book1, "500");
      assertEquals(book1, book1);

      Book book2 = new Book();
      book2.setBookId("500");

      assertEquals(book1, book2);

      book2 = new Book();
      book2.setBookId("201");

      System.out.println("book1=" + book1);
      System.out.println("book2=" + book2);
      System.out.println("book1.equals.book2=" + book1.equals(book2));
      System.out.println("book1 == book2=" + (book1 == book2));
      assertNotEquals(book1, book2);
   }
}
