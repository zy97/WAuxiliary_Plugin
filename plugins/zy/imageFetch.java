import org.json.JSONObject;
import org.json.JSONArray;
import java.io.File;
import me.hd.wauxv.plugin.api.callback.PluginCallBack;

boolean onLongClickSendBtn(String text) {
    String apiUrl = null;
    String fileName = null;
    if (text.startsWith("/imageFetch ")) {
        String str = text.substring(12);
        apiUrl = "https://apps-voluntary-average-ccd.trycloudflare.com/image/" + str;
    } else {
        return false;
    }

    final String finaljpgFileName = "imageFetch.jpg";
    final String finalpngFileName = "imageFetch.png";
    final String finalgifFileName = "imageFetch.gif";
    final String finalwebpFileName = "imageFetch.webp";

    get(apiUrl, null, new PluginCallBack.HttpCallback() {
        public void onSuccess(int respCode, String respContent) {
            JSONObject json = new JSONObject(respContent);
            String title = json.getString("title");
            sendText(getTargetTalker(), "文章标题:" + title);
            downloadSequentially(json, 0, finalFileName);
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

private void downloadSequentially(JSONObject json, int index, String finalFileName) {
    JSONArray images = json.getJSONArray("images");
    if (index >= images.length()) {
        JSONArray videos = json.getJSONArray("videos");
        for (int i = 0; i < videos.length(); i++) {
            String url = videos.getString(i);
            sendText(getTargetTalker(), "视频地址:" + url);
        }
        return;
    }

    String url = images.getString(index);
    int index = url.lastIndexOf(".");
    String ext = url.substring(index + 1);
    string finalFileName = "";
    if(ext == "jpg")
        finalFileName = finaljpgFileName;
    if(ext == "png")
        finalFileName = finalpngFileName;
    if(ext == "gif")
        finalFileName = finalgifFileName;
    if(ext == "webp")
        finalFileName = finalwebpFileName;
    download(url, pluginDir + "/" + finalFileName, null, new PluginCallBack.DownloadCallback() {
        public void onSuccess(File file) {
            sendImage(getTargetTalker(), file.getAbsolutePath(), "wxe3ad19e142df87b3");
            // 下载成功后，继续下一个
            downloadSequentially(images, index + 1, finalFileName);
        }

        public void onError(Exception e) {
            sendText(getTargetTalker(), "下载异常:" + e.getMessage());
        }
    });
}
