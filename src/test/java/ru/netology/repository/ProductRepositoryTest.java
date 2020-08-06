package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.domain.Book;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Book book = new Book(1,"Book", 10, "Author", 100, 2019);
    private TShirt tShirt = new TShirt(2,"TShirt", 20, "Color", 2);

    @BeforeEach
    void setUp() {
        repository.save(book);
        repository.save(tShirt);
    }

    @Test
    public void shouldRemoveByIdIfExists(){
        repository.removeById(1);

        Product[] expected = new Product[]{tShirt};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfNotExists(){
        assertThrows(NotFoundException.class, () -> repository.removeById(5));
    }
}