package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.domain.Book;
import ru.netology.domain.TShirt;
import ru.netology.repository.ProductRepository;

public class ProductManager {
    private static ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save(product);
    }

    public static Product[] searchBy(String text) {
        Product[] items = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[items.length + 1];
                System.arraycopy(items, 0, tmp, 0, items.length);
                tmp[items.length] = product;
                items = tmp;
            }
        }
        return items;
    }

    private static boolean matches(Product product, String search) {
        if (product instanceof Book) {
            Book book = (Book) product;
            if (book.getAuthor().equalsIgnoreCase(search)) {
                return true;
            }
        }
        if (product instanceof TShirt) {
            TShirt tshirt = (TShirt) product;
            if (tshirt.getColor().equalsIgnoreCase(search)) {
                return true;
            }
        }
        return false;
    }
}