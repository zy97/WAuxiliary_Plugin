import org.json.JSONObject;
import java.io.File;
import me.hd.wauxv.plugin.api.callback.PluginCallBack;

boolean onLongClickSendBtn(String text) {
    String apiUrl = null;
    String fileName = null;
    if (text.startsWith("/imageFetch ")) {
        String str = text.substring(12);
        apiUrl = "https://apps-voluntary-average-ccd.trycloudflare.com/image/" + str;
        fileName = "imageFetch.jpg";
    } else {
        return false;
    }

    final String finalFileName = fileName;

    get(apiUrl, null, new PluginCallBack.HttpCallback() {
        public void onSuccess(int respCode, String respContent) {
            JSONObject json = new JSONObject(respContent);
            JSONArray images = json.getJSONArray("images");
            for (int i = 0; i < images.length(); i++) {
                String url = images.getString(i); // Get each URL from the array
                download(url, pluginDir + "/" + finalFileName, null, new PluginCallBack.DownloadCallback() {
                    public void onSuccess(File file) {
                        sendImage(getTargetTalker(), file.getAbsolutePath(), "wxe3ad19e142df87b3");
                    }

                    public void onError(Exception e) {
                        sendText(getTargetTalker(), "下载异常:" + e.getMessage());
                    }
                });
            }
            JSONArray images = json.getJSONArray("videos");
            for (int i = 0; i < images.length(); i++) {
                String url = images.getString(i);
                sendText(getTargetTalker(), "视频地址:" + url);
            }
        }

        public void onError(Exception e) {
            sendText(getTargetTalker(), "API错误:" + e.getMessage());
        }
    });

    return true;
}