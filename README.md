### 书城 - 基于JavaWeb实现的在线购书网

----

![](https://img.shields.io/badge/JDK-17-yellow) ![](https://img.shields.io/badge/IDE-IntelliJ_IDEA-red) ![](https://img.shields.io/badge/Database-MySQL-green)

#### 描述

----

书城项目涉及的内容有 Java、Mysql、JDBC、DBUtils、C3P0、JavaWEB、JQuery、Ajax、JSON等。涉及到的编程思想有：面向接口编程、通用翻页、DAO、反射等。

开发所需环境：JDK、Tomcat、Mysql。

#### 已实现

----

- [x] 书籍展示、查看书籍详情
- [x] 分页显示
- [x] 书籍筛选
- [x] 页面跳转
- [x] 加入购物车、购物车书籍数量异步修改
- [x] 移除购物车某书籍、清空购物车
- [x] 支付、支付验证
- [x] 事务管理

#### 待实现

----

- [ ] 管理员
- [ ] 书籍的增删改
- [ ] 用户登陆与注册
- [ ] 书籍搜索
- [ ] 查看购买记录

#### 阅读建议

----

先看model部分，把数据库结构缕清。再看DAO部分，大部分操作都需要通过DAO来完成。其中DAO是接口，由BaseDAO来实现，剩余的DAO实现皆由BaseDAO辅助完成。servlet包中的BookServlet是入口，由其子包service中的BookService完成绝大部分页面功能。

#### 目录结构

----

```
├── java
│   └── com
│       └── bookstore
│           ├── controller
│           │   ├── dao
│           │   │   ├── AccountDAO.java
│           │   │   ├── BookDAO.java
│           │   │   ├── DAO.java
│           │   │   ├── Impl
│           │   │   │   ├── AccountDAOImpl.java
│           │   │   │   ├── BaseDAO.java
│           │   │   │   ├── BookDAOImpl.java
│           │   │   │   ├── TradeDAOImpl.java
│           │   │   │   ├── TradeItemDAOImpl.java
│           │   │   │   └── UserDAOImpl.java
│           │   │   ├── TradeDAO.java
│           │   │   ├── TradeItemDAO.java
│           │   │   └── UserDAO.java
│           │   ├── filter
│           │   │   └── TransactionFilter.java
│           │   ├── servlet
│           │   │   ├── BookServlet.java
│           │   │   └── service
│           │   │       ├── AccountService.java
│           │   │       ├── BookService.java
│           │   │       └── UserService.java
│           │   ├── utils
│           │   │   ├── JDBCUtils.java
│           │   │   ├── ReflectionUtils.java
│           │   │   └── ShoppingCartUtils.java
│           │   └── webpage
│           │       ├── Page.java
│           │       └── PriceLimit.java
│           └── model
│               ├── Account.java
│               ├── Book.java
│               ├── ShoppingCart.java
│               ├── ShoppingCartItem.java
│               ├── Trade.java
│               ├── TradeItem.java
│               └── User.java
├── resources
│   ├── c3p0-config.xml
│   └── jdbc.properties
└── webapp
    ├── WEB-INF
    │   ├── jdbc.properties
    │   ├── lib
    │   │   ├── c3p0-0.9.1.2.jar
    │   │   ├── commons-dbutils-1.3.jar
    │   │   ├── gson-2.2.4.jar
    │   │   ├── jstl.jar
    │   │   ├── mysql-connector-java-8.0.28.jar
    │   │   └── standard.jar
    │   ├── view
    │   │   ├── bookInfo.jsp
    │   │   ├── bookInfoFromCart.jsp
    │   │   ├── books.jsp
    │   │   ├── deal.jsp
    │   │   ├── dealSuccess.jsp
    │   │   ├── errors
    │   │   │   └── errorAddToShoppingCart.jsp
    │   │   └── shoppingCart.jsp
    │   └── web.xml
    ├── common
    │   └── param.jsp
    ├── errors
    │   └── error-pay.jsp
    ├── index.jsp
    ├── jquery
    │   └── jquery-3.6.0.min.js
    └── js
        ├── validate-cart-quantity.js
        └── validate-price.js
```

#### 部分截图

----

![](https://i.drop.cm/a3c9f5354acded33d28dafeee.png)

----

![](https://i.drop.cm/d2e3b7725344ffa087cfa9cef.png)

----

![](https://i.drop.cm/133481d81de03e9317a6f63c6.png)

----

![](https://i.drop.cm/0c399c18a4b069dde7bf710f8.png)
