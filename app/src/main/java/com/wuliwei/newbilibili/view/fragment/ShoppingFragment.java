package com.wuliwei.newbilibili.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.anye.greendao.gen.GoodsBeanDao;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.presenter.adapter.ShoppingCartAdapter;
import com.wuliwei.newbilibili.app.MyApplication;
import com.wuliwei.newbilibili.view.base.BaseFragment;
import com.wuliwei.newbilibili.bean.GoodsBean;
import com.wuliwei.newbilibili.uitls.pay.PayResult;
import com.wuliwei.newbilibili.uitls.pay.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by
 * 武立伟
 * 2017/3/27.
 * <p>
 * 作用：购物车
 */

public class ShoppingFragment extends BaseFragment {

    @BindView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;

    private GoodsBeanDao goodsBeanDao;
    private List<GoodsBean> goodsBeen;

    private ShoppingCartAdapter adapter;
    private List<GoodsBean> newGoodsBeen;
    private List<GoodsBean> newAddGoodsBean;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_shopping, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initAdapter();
        //总价
        showTotalPrice();
    }

    private void showTotalPrice() {
        tvShopcartTotal.setText("合计：" + getTotalPrice());
    }

    private double getTotalPrice() {
        double totalPrice = 0.0;
        if (goodsBeen != null && goodsBeen.size() > 0) {
            for (int i = 0; i < goodsBeen.size(); i++) {
                GoodsBean goodsBean = goodsBeen.get(i);
//                if (goodsBean.isChecked()) {
                //被选中的
                totalPrice = totalPrice + goodsBean.getNumber() * Double.parseDouble(goodsBean.getPrice());
            }
        }
//        }
        return totalPrice;
    }

    private void initAdapter() {

        goodsBeanDao = MyApplication.getInstances().getDaoSession().getGoodsBeanDao();
        goodsBeen = goodsBeanDao.loadAll();


        if (goodsBeen != null && goodsBeen.size() > 0) {
            //设置适配器
            adapter = new ShoppingCartAdapter(context, goodsBeen, tvShopcartTotal, checkboxAll);
            listView.setAdapter(adapter);
        }

        if (adapter != null) {
            newAddGoodsBean = goodsBeanDao.loadAll();
            adapter.setAdd(newAddGoodsBean);
            adapter.notifyDataSetChanged();
            adapter.showTotalPrice();
        }
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                goodsBeanDao.deleteAll();
                newGoodsBeen = goodsBeanDao.loadAll();
                adapter.setData(newGoodsBeen);
                adapter.notifyDataSetChanged();
                adapter.showTotalPrice();
                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_all:
                break;
            case R.id.btn_check_out:
//                Toast.makeText(context, "支付", Toast.LENGTH_SHORT).show();
                pay();
                break;
        }
    }

    //----------------------------支付

    // 商户PID
    public static final String PARTNER = "2088911876712776";
    // 商户收款账号   917356107@qq.com
    public static final String SELLER = "chenlei@atguigu.com";
    // 商户私钥，pkcs8格式  MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL51jaxQhxW9PnWpW+nz6yJ76tp9eGFXmfGnuxMK+Pmx/qavdsewXOLBfI2OSCR39TzxwMYvCmUrnrt0fVSa7mblbNos2FnMM9ijnx8bsAAhm+i7BKhuaHMunJKH69L+D753zH3P1YIh0ly5DnAr3WPqHydp384qBvb8NS9Tay0HAgMBAAECgYB82PIVknP6fCMFXg8yPQJViIVa1ASlSpdPIXQv93FdvKABA+QI4kMBIXRUFoCT506KtK55OzzFNOLIXoQJgcXj69z0l6pmjJJgXMaBW/9rOzelot13CiGatrIrGngEZO+bCBTud/jQA598zjZ1g182tT+FLDL7GIftW2hC8GqtAQJBAN+XrYsyfL+uSmLdAVEz1vzziU1naGr10Msm1jMnnO/JYdB+84j7FSHxsQ4YOgsmeN5YVsJcVfc/CReOxknns38CQQDaEHnVPDt+Z7sqT7bN0UKh0/CrqkDTiIjhz1lJyIIoqVRoeJjJn1wlEKBV5R9gkTJutQTVU19XFtblMEnOy6p5AkEAw170rEmMSa0QoHw+d2bVtydR1QnDapqqO6kOx5oYfkm4J4eWYx4J5CQdMpSmuzF9scL85E3sa+NvnV8LEm7cHwJALtXzFPWG4bNt47yTSslzQka/Hl/G5Kginj1mtA44xnr4AihEyKlNpThY95nqj1cgOd7vVtI9W/sv1LH2aFAeIQJBAIqXbMc6xGVfuiFAJKtg+AFNMBP0UOEgMEoKo4RPFp21nBhFgL9/WYM4ZjyHUdr45rCySAqQovw4DCHLfQZC23I=

    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANDl5XiZplckwpJ/\n" +
            "EVejypog8kwjqVPAWQ0tGYWG/E4g1ccF+KjlICSWlkPiJY6JTpVwQ9xbfA12HVEZ\n" +
            "6hH4k9GIIJI7+H5/dDOFdCRyJUcXGx/6cTHiK1oe3BxJ/+vtYRCmqX6FaiChXSmr\n" +
            "nero6+QtMhElWVlxXsQoll6rcQg5AgMBAAECgYBrW4tIJKcBKCo7AcTW5p2MApyj\n" +
            "lrxCB5t8kw4HuqzWNjfHtgDqQ/717tJBButISraxRGYLzJR/kpFUid3q6HaGjdcs\n" +
            "8IHPTAtTaOMVrp3MVizoJgd2JQ5ATO0loMVqAzqCftmr/UsKYzt/r/ghBQUq73cQ\n" +
            "KHPLpTq98Z+GONvxJQJBAPKup+v5NAJARPfl4s6j/j/JJ8B4E5VXm0CFjR2dCLk6\n" +
            "e15lQ1U2Sc7XJHm5gJUmxAVMlVhohDLFr+chwx0aAisCQQDcXJ6mUhB5TnpMWljD\n" +
            "Kn4CJA0bK5vQhZmOIcKrPfb/q90qzgqJTEckz1CPY+Erx/vbiiAxVNuXR8ADmtco\n" +
            "cYErAkA+1BbnUco0Nv1kDKEujGh7jRF8k9nGFTs9dhX1r70db1WXN7I58mcjZLGt\n" +
            "zefLNZEUKiKUE+L93g5PUX9ZHm2nAkEAnOxgFjTuglQ+h0z3VNRXs5KUurqNVSsu\n" +
            "tKaaCHQyBtQOxcLAghGNwEraJaKM4S6izi5IypiRX+O6e1ayzZw2zQJBAIMDTt5g\n" +
            "j1lZEBrLIxo2mRrTJKLQCLuW77CrMJtN6+79G39X9DBeao7dGP2agyVE7SYTJiV+\n" +
            "E0hkilFDq3xItew=";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKY1+fAeORVpYw2dzIRH/2aiYm\n" +
            "Il7Rg8MfJ3u+I0kmM7X3Quu2CrZ8ek9GnySzWcVmODDBN28ICd+unCsthMqQSPrg\n" +
            "udKfJNB34jEozNFMOaJ0CvraRa9NHDSSFPjAtbcUtnDRjJ6mvI81J0DG0CeJwv5O\n" +
            "bGahv4Vid9Y8mNS1KwIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(context, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(context).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {

//                            finish();
                        }
                    }).show();
            return;
        }

        String orderInfo = getOrderInfo("硅谷商城", "硅谷商城商品的详细描述", getTotalPrice() + "");

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                //子线程
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) context);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);

        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
