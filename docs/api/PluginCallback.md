# 回调方法

::: warning 警告
本文档适用于 WAuxiliary v1.2.2.r623.655c448 版本
:::

## 监听收到消息

```java
void onHandleMsg(Object msgInfoBean);
```

## 长按发送按钮

```java
boolean onLongClickSendBtn(String text);
```

## 监听成员变动

```java
void onMemberChange(String type, String groupWxid, String userWxid, String userName);
```
