package top.javahai.datasource.config;

/**
 * @author Ethan Huang
 * @create 2020-11-19 14:34
 */
public enum DataSourceType {
    CHATROOM("chatroom"),
    BOOK_DB("book_db"),
    MYDB("mydb");

    private String name;

    DataSourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
