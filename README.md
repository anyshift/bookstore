## 书城 - 基于JavaWeb实现的在线购书网

![](https://img.shields.io/badge/JDK-17-yellow) ![](https://img.shields.io/badge/IDE-IntelliJ_IDEA-red) ![](https://img.shields.io/badge/Database-MySQL-green)

### 🟥 描述

书城项目涉及的内容有 Java、Mysql、JDBC、DBUtils、C3P0、JavaWEB、JQuery、Ajax、JSON等。涉及到的编程思想有：面向接口编程、通用翻页、DAO、反射等。

开发所需环境：JDK、Tomcat、Mysql。

### ✅ 已实现

- [x] 书籍展示、查看书籍详情
- [x] 分页显示
- [x] 书籍筛选
- [x] 页面跳转
- [x] 加入购物车、购物车书籍数量异步修改
- [x] 移除购物车某书籍、清空购物车
- [x] 支付、支付验证
- [x] 事务管理
- [x] 书籍搜索
- [x] 登陆与注册(密码采用SHA-256加密算法)
- [x] 查看购买记录

### 😉 待实现
- [ ] 管理员
- [ ] 书籍的增删改

### 🟦 ️阅读建议

先看model部分，把数据库结构缕清。再看DAO部分，大部分操作都需要通过DAO来完成。其中DAO是接口，由BaseDAO来实现，剩余的DAO实现皆由BaseDAO辅助完成。servlet包中的BookServlet是入口，由其子包service中的BookService完成绝大部分页面功能。

### ⌛️ 目录结构

```
├── main
│   ├── java
│   │   └── com
│   │       └── bookstore
│   │           ├── controller
│   │           │   ├── dao
│   │           │   │   ├── AccountDAO.java
│   │           │   │   ├── BookDAO.java
│   │           │   │   ├── DAO.java
│   │           │   │   ├── Impl
│   │           │   │   │   ├── AccountDAOImpl.java
│   │           │   │   │   ├── BaseDAO.java
│   │           │   │   │   ├── BookDAOImpl.java
│   │           │   │   │   ├── TradeDAOImpl.java
│   │           │   │   │   ├── TradeItemDAOImpl.java
│   │           │   │   │   └── UserDAOImpl.java
│   │           │   │   ├── TradeDAO.java
│   │           │   │   ├── TradeItemDAO.java
│   │           │   │   └── UserDAO.java
│   │           │   ├── filter
│   │           │   │   └── TransactionFilter.java
│   │           │   ├── servlet
│   │           │   │   ├── BookServlet.java
│   │           │   │   ├── UserServlet.java
│   │           │   │   └── service
│   │           │   │       ├── AccountService.java
│   │           │   │       ├── BookService.java
│   │           │   │       └── UserService.java
│   │           │   ├── utils
│   │           │   │   ├── JDBCUtils.java
│   │           │   │   ├── ReflectionUtils.java
│   │           │   │   ├── ShoppingCartUtils.java
│   │           │   │   └── UserUtils.java
│   │           │   └── webpage
│   │           │       ├── Page.java
│   │           │       └── PriceLimit.java
│   │           └── model
│   │               ├── Account.java
│   │               ├── Book.java
│   │               ├── ShoppingCart.java
│   │               ├── ShoppingCartItem.java
│   │               ├── Trade.java
│   │               ├── TradeItem.java
│   │               └── User.java
│   ├── resources
│   │   ├── c3p0-config.xml
│   │   └── jdbc.properties
│   └── webapp
│       ├── WEB-INF
│       │   ├── jdbc.properties
│       │   ├── lib
│       │   │   ├── c3p0-0.9.1.2.jar
│       │   │   ├── commons-dbutils-1.3.jar
│       │   │   ├── gson-2.2.4.jar
│       │   │   ├── jsp-api.jar
│       │   │   ├── jstl.jar
│       │   │   ├── mysql-connector-java-8.0.28.jar
│       │   │   └── standard.jar
│       │   └── web.xml
│       ├── common
│       │   ├── common.jsp
│       │   └── head.jsp
│       ├── errors
│       │   └── error_500.jsp
│       ├── index.jsp
│       ├── page
│       │   ├── book_info.jsp
│       │   ├── bookstore.jsp
│       │   ├── bookstore_search.jsp
│       │   ├── deal.jsp
│       │   ├── deal_success.jsp
│       │   ├── errors
│       │   │   └── error_add_to_cart.jsp
│       │   ├── login.jsp
│       │   ├── login_success.jsp
│       │   ├── logout.jsp
│       │   ├── register.jsp
│       │   ├── register_success.jsp
│       │   ├── shopping_cart.jsp
│       │   ├── space_admin.jsp
│       │   └── space_user.jsp
│       └── static
│           ├── jquery
│           │   └── jquery-3.6.0.min.js
│           └── js
│               ├── validate_cart_quantity.js
│               └── validate_price.js
└── test
    ├── java
    │   └── Test
    │       ├── AccountDAOTest.java
    │       ├── BaseDAOTest.java
    │       ├── BookDAOTest.java
    │       ├── TradeDAOTest.java
    │       ├── TradeItemDAOTest.java
    │       └── UserDAOTest.java
    └── resources
```

### 🔘 部分截图
----

![](https://i.drop.cm/be2c23e899bcd3ed4a58aef00.png)

----

![](https://i.drop.cm/11bb7fb5da64baf389821d2d8.png)

----

![](https://i.drop.cm/1308ef54f7c9d4f3abe69439d.png)

----

![](https://i.drop.cm/de690cda4e21e9e39ea204bac.png)

----

![](https://i.drop.cm/f929978f672455053b72764d5.png)
