package com.vagrancy.study.module.common.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vagrancy.study.module.knowledge.fragment.KnowledgeFragment;
import com.vagrancy.study.module.mine.fragment.MineFragment;

/**
 * @author Vagrancy
 * @date 2021/2/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 公共fragment适配
 */
public class CommonFragmentAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragment;

    public CommonFragmentAdapter(FragmentManager fm,int size) {
        super(fm);
        mFragment = new Fragment[size];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(mFragment[position] == null){
            switch (position){
                case 0:
                    mFragment[position] = KnowledgeFragment.newInstance();
                    break;
                case 1:
                    mFragment[position] = MineFragment.newInstance();
                    break;
            }
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mFragment.length;
    }
}
