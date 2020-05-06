package ru.bookstore.core.exception;

public class ExcelWritingException extends BookStoreException {
    public ExcelWritingException(Class c) {
        super("Количество загружаемых списков не совпадает с количеством зарегистрированных excel-листов в классе: " + c.getSimpleName());
    }
}
