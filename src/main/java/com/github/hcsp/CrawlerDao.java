package com.github.hcsp;

import java.sql.SQLException;

public interface CrawlerDao {

    String getNextLinkThenDelete() throws SQLException;


    void insertNewsIntoDatabase(String url, String title, String content) throws SQLException;

    boolean isLinkedProcessed(String link) throws SQLException;

    void insertProcessedLink(String link);

    void insertLinkToBeProcessed(String href);
}
