
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;

import me.hd.wauxv.plugin.api.callback.PluginCallBack;

void sendMusic(String talker, String title) {
    get("https://api.vkeys.cn/v2/music/netease?word=" + title + "&choose=1", null, new PluginCallBack.HttpCallback() {
        public void onSuccess(int respCode, String respContent) {
            JSONObject jsonObject = JSON.parseObject(respContent);
            String id = JSONPath.eval(jsonObject, "$.data.id").toString();
            String name = JSONPath.eval(jsonObject, "$.data.song");
            String singer = JSONPath.eval(jsonObject, "$.data.singer");
            String url = JSONPath.eval(jsonObject, "$.data.url");
            String link = JSONPath.eval(jsonObject, "$.data.link");
            sendMusicCard(talker, name, singer, url, link, "wx8dd6ecd81906fd84");
        }

        public void onError(Exception e) {
            sendText(talker, "[落月API]请求异常:" + e.getMessage());
        }
    });
}

void onHandleMsg(Object msgInfoBean) {
    if (msgInfoBean.isText()) {
        String content = msgInfoBean.getContent();
        String talker = msgInfoBean.getTalker();
        if (content.startsWith("/点歌 ")) {
            String title = content.substring(4);
            sendMusic(talker, title);
        }
    }
}
