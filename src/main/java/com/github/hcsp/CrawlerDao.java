package com.github.hcsp;

import java.sql.SQLException;

public interface CrawlerDao {
    String getNextLink(String sql) throws SQLException;

    String getNextLinkThenDelete() throws SQLException;

    void updateDatabase(String link, String sql) throws SQLException;

    void insertNewsIntoDatabase(String url, String title, String content) throws SQLException;

    boolean isLinkedProcessed(String link) throws SQLException;
}
