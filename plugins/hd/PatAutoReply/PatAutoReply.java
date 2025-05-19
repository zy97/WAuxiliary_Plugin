
void onHandleMsg(Object msgInfo) {
    if (msgInfo.isPat()) {
        String myWxid = getLoginWxid();
        String fromUser = msgInfo.getSendTalker();
        String pattedUser = msgInfo.getContent();
        if (!fromUser.equals(myWxid) && pattedUser.equals(myWxid)) {
            sendText(msgInfo.getTalker(), "[AtWx=" + fromUser + "] 干啥子?");
        }
    }
}
