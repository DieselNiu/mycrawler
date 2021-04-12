# mycrawler - 一个简单的网络爬虫小例子
[![](https://img.shields.io/badge/CirCle-CI-red)](https://circleci.com/gh/DieselNiu/mycrawler) 
![](https://img.shields.io/github/license/DieselNiu/mycrawler?style=plastic)
![](https://img.shields.io/github/issues/DieselNiu/mycrawler?style=plastic)
![](https://img.shields.io/github/forks/DieselNiu/mycrawler?style=plastic)
![](https://img.shields.io/github/stars/DieselNiu/mycrawler?style=plastic)


## About the project 
这是一个特别针对[手机新浪网](https://sina.cn/)的多线程的网络爬虫,并可以用Elasticsearch新闻搜索引擎实现了快速的文本内容搜索.


###  Build With
 * [Docker](https://www.docker.com/)
 * [flyway](https://flywaydb.org/)
 * [mybatis](https://mybatis.org/mybatis-3/)
 * [Elasticsearch](https://www.elastic.co/)
## 项目原则

* 使用Git进行版本控制，使用Github+主干分支模型进行开发,禁止直接push到主干分支,所有的变更都要小步提交 PR 至 Github 主分支.
*用 Maven 进行依赖包的管理，用自动化代码质量检查工具 CircleCI 进行自动化测试，在生命周期绑定 Checkstyle、SpotBugs 插件保证代码质量。 
* 使用 Flyway 自动迁移工具完成数据库初始化建表及添加原始数据工作，用 MyBatis 实现数据与 Java 对象的关系映射，对 MySQL 数据库进行索引优化，使百万级新闻内容的查找效率提升近 2 倍。
*  采用多线程完成爬虫任务，提高爬取效率约 3 倍，使用 Elasticsearch 搜索引擎进行新闻内容的全文检索，实现了百万级文本内容的快速搜索功能。



## Getting Started

clone 项目至本地目录：

```shell
git clone git@github.com:DieselNiu/mycrawler.git
```

从 Docker 启动 MySQL 数据库：

- [Docker 下载地址](https://www.docker.com/)
- 如果需要持久化数据需要配置 -v 磁盘文件映射

```shell
docker run --name mycrawler -p 3306:3306 -e MYSQL_ROOT_PASSWORD=yourpassword -d mysql:5.7.27
```

使用 IDEA 打开项目，刷新 Maven，再使用开源数据库迁移工具 Flyway 完成自动建表工作：

```shell
mvn flyway:migrate
```

项目测试：

```shell
mvn verify
```

运行项目：

- Run Main 类，就开始爬取数据啦！

### 效果图：

![cD9E4J.png](https://z3.ax1x.com/2021/04/12/cD9E4J.png)[![cD9mg1.md.png](https://z3.ax1x.com/2021/04/12/cD9mg1.md.png)](https://imgtu.com/i/cD9mg1)

### 爬虫逻辑图：

![](https://s2.ax1x.com/2020/03/06/3bZrX6.png)
