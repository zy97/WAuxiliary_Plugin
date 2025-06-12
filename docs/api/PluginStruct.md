
# 相关结构

::: warning 警告
本文档适用于 WAuxiliary v1.2.3.r722.c2ba115 版本
:::

## 消息结构

```java
MsgInfo {
    long getMsgId();// 消息Id
    int getType();// 消息类型
    String getTalker();// 发送者(接收的 群聊Id/好友Id)
    String getSendTalker();// 发送者(群聊中 发送者Id)
    String getContent();// 消息内容
    String getMsgSource();// 消息来源
    List<String> getAtUserList();// 艾特列表

    boolean isAnnounceAll();// 公告通知全体
    boolean isNotifyAll();// 艾特通知全体
    boolean isAtMe();// 艾特我
    
    boolean isPrivateChat();// 私聊
    boolean isGroupChat();// 群聊
    boolean isOfficialAccount();// 公众号
    boolean isOpenIM();// 企业微信
    boolean isSend();// 自己发的
    
    boolean isText();// 文本
    boolean isImage();// 图片
    boolean isVoice();// 语音
    boolean isShareCard();// 名片
    boolean isVideo();// 视频
    boolean isEmoji();// 表情
    boolean isLocation();// 位置
    boolean isCard();// 卡片
    boolean isVoip();// 通话
    boolean isVoipVoice();// 语音通话
    boolean isVoipVideo();// 视频通话
    boolean isSystem();// 系统
    boolean isLink();// 链接
    boolean isTransfer();// 转账
    boolean isRedPacket();// 红包
    boolean isVideoNumberVideo();// 视频号视频
    boolean isNote();// 接龙
    boolean isQuote();// 引用
    boolean isPat()();// 拍一拍
    boolean isFile();// 文件
}
```
