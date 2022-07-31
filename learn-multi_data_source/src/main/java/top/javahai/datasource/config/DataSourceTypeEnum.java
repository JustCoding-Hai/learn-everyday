package top.javahai.datasource.config;

/**
 * 数据源枚举类
 *
 * @author Ethan Huang
 * @create 2020-11-19 14:34
 */
public enum DataSourceTypeEnum {
    /**
     * chatroom
     */
    CHATROOM("chatroom"),
    /**
     * book_db
     */
    BOOK_DB("book_db"),
    /**
     * mydb
     */
    MY_DB("mydb");

    private final String name;

    DataSourceTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
