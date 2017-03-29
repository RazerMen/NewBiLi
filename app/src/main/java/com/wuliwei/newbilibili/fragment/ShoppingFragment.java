package com.wuliwei.newbilibili.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anye.greendao.gen.GoodsBeanDao;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.ShoppingCartAdapter;
import com.wuliwei.newbilibili.app.MyApplication;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.bean.GoodsBean;

import java.util.List;

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

                break;
        }
    }
}
