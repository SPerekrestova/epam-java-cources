package com.epam.university.java.project.service;

import com.epam.university.java.project.core.cdi.impl.io.XmlResource;
import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;
import java.time.LocalDate;
import java.util.Collection;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final StateMachineManager stateMachineManager;
    private final StateMachineDefinition<BookStatus, BookEvent> definition;

    @SuppressWarnings("unchecked")
    public BookServiceImpl() {
        final String contextPath = getClass()
                .getResource("/project/DefaultBookStateMachineDefinition.xml")
                .getFile();
        Resource stateMachineDefinitionXml = new XmlResource(contextPath);
        stateMachineManager = new StateMachineManagerImpl();
        definition = (StateMachineDefinition<BookStatus, BookEvent>) stateMachineManager
                .loadDefinition(stateMachineDefinitionXml);
        bookDao = new BookDaoXmlImpl();
    }

    @Override
    public Book createBook() {
        Book book = bookDao.createBook();
        stateMachineManager.handleEvent(stateMachineManager.initStateMachine(book, definition),
                BookEvent.CREATE);
        book.setState(BookStatus.DRAFT);
        return book;
    }

    @Override
    public Book getBook(int id) {
        return bookDao.getBook(id);
    }

    @Override
    public Collection<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public void remove(Book book) {
        bookDao.remove(book);
    }

    @Override
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book accept(Book book, String number) {
        stateMachineManager.handleEvent(stateMachineManager.initStateMachine(book, definition),
                BookEvent.ACCEPT);
        book.setState(BookStatus.ACCOUNTED);
        return book;
    }

    @Override
    public Book issue(Book book, LocalDate returnDate) {
        stateMachineManager.handleEvent(stateMachineManager.initStateMachine(book, definition),
                BookEvent.ISSUE);
        book.setReturnDate(returnDate);
        book.setState(BookStatus.ISSUED);
        return book;
    }

    @Override
    public Book returnFromIssue(Book book) {
        stateMachineManager.handleEvent(stateMachineManager.initStateMachine(book, definition),
                BookEvent.RETURN);
        book.setState(BookStatus.ACCOUNTED);
        return book;
    }
}
