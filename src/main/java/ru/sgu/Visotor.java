package main.java.ru.sgu;
import java.util.List;

interface Item {
    void accept(ItemVisitor visitor);
}

class Book implements Item {
    private String title;
    private int numberOfPages;

    public Book(String title, int numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}

class Magazine implements Item {
    private String title;
    private int numberOfPages;

    public Magazine(String title, int numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}

interface ItemVisitor {
    void visit(Book book);

    void visit(Magazine magazine);
}

class ItemPrintVisitor implements ItemVisitor {
    @Override
    public void visit(Book book) {
        System.out.println("Book: " + book.getTitle() + ", Pages: " + book.getNumberOfPages());
    }

    @Override
    public void visit(Magazine magazine) {
        System.out.println("Magazine: " + magazine.getTitle() + ", Pages: " + magazine.getNumberOfPages());
    }
}

class PageCountVisitor implements ItemVisitor {
    private int totalPageCount = 0;

    @Override
    public void visit(Book book) {
        totalPageCount += book.getNumberOfPages();
    }

    @Override
    public void visit(Magazine magazine) {
        totalPageCount += magazine.getNumberOfPages();
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }
}

public class Visotor {

    public static void main(String[] args) {
        List<Item> items = List.of(
                new Book("Alex the great", 277),
                new Magazine("Time", 45),
                new Book("Bill", 336),
                new Magazine("National Geographic", 97)
        );

        // Создание посетителей
        ItemPrintVisitor printVisitor = new ItemPrintVisitor();
        PageCountVisitor pageCountVisitor = new PageCountVisitor();

        // Посещение каждого элемента с помощью посетителей
        for (Item item : items) {
            item.accept(printVisitor);
            item.accept(pageCountVisitor);
        }

        System.out.println("Total number of pages: " + pageCountVisitor.getTotalPageCount());
    }
}
