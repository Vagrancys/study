package com.vagrancy.study.module.mine.fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseFragmentView;
import com.vagrancy.study.common.base.IViewAdapter;
import com.vagrancy.study.common.contract.mine.MineContract;
import com.vagrancy.study.model.mine.MineModel;
import com.vagrancy.study.model.mine.entity.Mine;
import com.vagrancy.study.model.mine.entity.MineItem;
import com.vagrancy.study.module.mine.adapter.MineItemAdapter;
import com.vagrancy.study.presenter.mine.MinePresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2021/2/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 中心fragment层
 */
public class MineFragment extends BaseFragmentView<MinePresenter,MineContract.View<Mine>> {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    public static MineFragment newInstance(){
        return new MineFragment();
    }

    private int[] mIcon = {R.drawable.mine_statistics_normal};
    private String[] mTitle ={"统计"};
    private ArrayList<MineItem> mineItems = new ArrayList<>();
    private MineItemAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        initMineItem();
        mAdapter = new MineItemAdapter(getContext(),mineItems);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new IViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                switch (position){
                    case 0:
                        //统计
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });
    }

    //初始化item
    private void initMineItem() {
        for (int i = 0; i < mTitle.length; i++) {
            mineItems.add(new MineItem(mIcon[i],mTitle[i]));
        }
    }

    @Override
    public MineContract.View<Mine> getContract() {
        return new MineContract.View<Mine>() {
        };
    }

    @Override
    public MinePresenter getPresenter() {
        return new MinePresenter();
    }
}
