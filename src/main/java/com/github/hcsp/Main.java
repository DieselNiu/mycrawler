package com.github.hcsp;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        //待处理的链接池
        List<String> linkPool = new ArrayList<>();
        //已经处理的链接池子
        Set<String> processedLinks = new HashSet<>();
        linkPool.add("https://sina.cn");
        while (true) {
            if (linkPool.isEmpty()) {
                break;
            }
            String link = linkPool.remove(linkPool.size() - 1);
            if (processedLinks.contains(link)) {
                continue;
            }

            if (isInterestingLink(link)) {
                //这是我们感兴趣的，我们只处理新浪站内的链接
                Document doc = httpGetAndParseHtml(link);
                doc.select("a").stream().map(aTag -> aTag.attr("href")).forEach(linkPool::add);
                storeIntoDatabaseIfItIsNewPage(doc);
                //假如这是一个新闻的详情页面，就存入数据库，否则就什么都不做
                processedLinks.add(link);
            } else {
                //这是我们不感兴趣的，不处理他
            }
        }
    }

    private static Document httpGetAndParseHtml(String link) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        if (link.startsWith("//")) {
            link = "https:" + link;
            System.out.println(link);
        }

        HttpGet httpGet = new HttpGet(link);
        httpGet.addHeader("User-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_1_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.96 Safari/537.36");

        try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            String html = EntityUtils.toString(entity1);
            return Jsoup.parse(html);
        }
    }

    private static boolean isInterestingLink(String link) {
        return (isNewsPage(link) || isIndexPage(link) && isNotLoginPage(link));
    }

    private static boolean isNotLoginPage(String link) {
        return !link.contains("passport.sina.cn");
    }

    private static boolean isIndexPage(String link) {
        return "https://sina.cn".equals(link);
    }


    private static boolean isNewsPage(String link) {
        return link.contains("news.sina.cn");
    }


    private static void storeIntoDatabaseIfItIsNewPage(Document doc) {
        ArrayList<Element> articleTags = doc.select("article");
        if (!articleTags.isEmpty()) {
            for (Element articleTag : articleTags) {
                String title = articleTags.get(0).child(0).text();
                System.out.println(title);
            }
        }
    }
}