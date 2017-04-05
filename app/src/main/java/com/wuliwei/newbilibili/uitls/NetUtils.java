package com.wuliwei.newbilibili.uitls;

import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.Call;

/**
 * Created by
 * 武立伟
 * 2017/4/5.
 * <p>
 * 作用：联网的抽取
 */

public class NetUtils {

    public NetUtils() {
    }

    private static class Tool {
        private static NetUtils okhttpUtils = new NetUtils();
    }

    public static NetUtils getInstance() {
        return Tool.okhttpUtils;
    }

    public RequestCall getOkHttpUtils(String url, final resultJson resultJson) {
        if (resultJson == null) {
            return null;
        }
        if (TextUtils.isEmpty(url)) {
            resultJson.onError("地址为空，请求失败");
            return null;
        }
        RequestCall build = OkHttpUtils.get().url(url).build();
        build.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                resultJson.onError(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                if (TextUtils.isEmpty(response)) {
                    resultJson.onError("请求结果为空，解析失败");
                    return;
                }
                resultJson.onResponse(response);
            }
        });
        return build;
    }

    public interface resultJson {
        void onResponse(String json);

        void onError(String error);
    }
}
