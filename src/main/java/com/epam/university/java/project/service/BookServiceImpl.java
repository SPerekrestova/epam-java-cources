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
    private final Resource stateMachineDefinitionXml;
    private StateMachineManager stateMachineManager;
    private StateMachineDefinition<BookStatus, BookEvent> definition;

    @SuppressWarnings("unchecked")
    public BookServiceImpl(
            StateMachineManager stateMachineManager) {
        final String contextPath = getClass()
                .getResource("/project/DefaultBookStateMachineDefinition.xml")
                .getFile();
        stateMachineDefinitionXml = new XmlResource(contextPath);
        definition = (StateMachineDefinition<BookStatus, BookEvent>) stateMachineManager
                .loadDefinition(stateMachineDefinitionXml);
        bookDao = new BookDaoXmlImpl();
    }

    @Override
    public Book createBook() {
        Book book = bookDao.createBook();
        stateMachineManager.handleEvent(stateMachineManager.initStateMachine(book, definition),
                BookEvent.CREATE);
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

        return null;
    }

    @Override
    public Book issue(Book book, LocalDate returnDate) {
        return null;
    }

    @Override
    public Book returnFromIssue(Book book) {
        return null;
    }
}
