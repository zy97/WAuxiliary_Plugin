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

    get(apiUrl, null, new PluginCallBack.HttpCallback() {
        public void onSuccess(int respCode, String respContent) {
            JSONObject json = new JSONObject(respContent);
            String title = json.getString("title");
            sendText(getTargetTalker(), "文章标题:" + title);
            downloadSequentially(json, 0);
        }

        public void onError(Exception e) {
            sendText(getTargetTalker(), "API错误:" + e.getMessage());
        }
    });

    return true;
}

private void downloadSequentially(JSONObject json, int index) {
    final String finaljpgFileName = "imageFetch.jpg";
    final String finalpngFileName = "imageFetch.png";
    final String finalgifFileName = "imageFetch.gif";
    final String finalwebpFileName = "imageFetch.webp";

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
    int idx = url.lastIndexOf(".");
    String ext = url.substring(idx + 1);
    String finalFileName = "imageFetch.jpg";
    if (ext.equals("jpg")) {
        finalFileName = finaljpgFileName;
    }
    if (ext.equals("png")) {
        finalFileName = finalpngFileName;
    }
    if (ext.equals("gif")) {
        finalFileName = finalgifFileName;
    }
    if (ext.equals("webp")) {
        finalFileName = finalwebpFileName;
    }
    sendText(getTargetTalker(), ext + finalFileName);

    download(url, pluginDir + "/" + finalFileName, null, new PluginCallBack.DownloadCallback() {
        public void onSuccess(File file) {
            // String fileName = file.getAbsolutePath();
            // sendText(getTargetTalker(), fileName);
            // int idx = fileName.lastIndexOf(".");
            // String ext = fileName.substring(idx + 1);
            // if (ext.equals("jpg")) {
            // sendImage(getTargetTalker(), file.getAbsolutePath(), "wxe3ad19e142df87b3");
            // }
            // if (ext.equals("png")) {
            // sendImage(getTargetTalker(), file.getAbsolutePath(), "wxe3ad19e142df87b3");
            // }
            // if (ext.equals("gif")) {
            // shareFile(getTargetTalker(), "", file.getAbsolutePath(),
            // "wxe3ad19e142df87b3");
            // }
            // if (ext.equals("webp")) {
            // shareFile(getTargetTalker(), "", file.getAbsolutePath(),
            // "wxe3ad19e142df87b3");
            // }
            shareFile(getTargetTalker(), "123", file.getAbsolutePath(), "wxe3ad19e142df87b3");
            // 下载成功后，继续下一个
            downloadSequentially(json, index + 1);
        }

        public void onError(Exception e) {
            sendText(getTargetTalker(), "下载异常:" + e.getMessage());
        }
    });
}