package com.lovejoy.views.fragment;

import java.util.ArrayList;
import java.util.List;

import com.lovejoy.views.activity.R;
import com.lovejoy.views.view.AddPopWindow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;



public class BotNavDynamicFragment extends Fragment implements OnCheckedChangeListener{

	private View parentView;
	private RadioGroup radioGroup;
	private RadioButton rbJoined, rbRecomm, rbFriend;
	private ViewPager  viewpager;
    private ImageView dynamicIvAdd;
	List<Fragment> list = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		parentView = inflater.inflate(R.layout.fragment_bot_nav_dynamic, container, false);
		
		radioGroup = (RadioGroup) parentView.findViewById(R.id.dynamic_radioGroup);

		rbRecomm = (RadioButton) parentView.findViewById(R.id.rbRecomm);
		rbJoined = (RadioButton) parentView.findViewById(R.id.rbJoined);
		rbFriend = (RadioButton) parentView.findViewById(R.id.rbFriend);
		
		viewpager = (ViewPager)parentView.findViewById(R.id.vp_Dynamic);
		
		dynamicIvAdd = (ImageView) parentView.findViewById(R.id.dynamic_iv_add);
		
		list = new ArrayList<>();
		DynamicTopRecomFragment tRecomm = new DynamicTopRecomFragment();
		DynamicTopJoinedFragment tJoined = new DynamicTopJoinedFragment();
        DynamicTopFriendFragment tFriend = new DynamicTopFriendFragment();
        
		list.add(tRecomm);
		list.add(tJoined);
        list.add(tFriend);
		
		ZxzcAdapter zxzc = new ZxzcAdapter(getChildFragmentManager(), list);
		viewpager.setAdapter(zxzc);
		zxzc.notifyDataSetChanged();
		
		radioGroup.setOnCheckedChangeListener(this);
        rbRecomm.setChecked(true);

		//滑动切换
		viewpager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
                    case 0:
						rbRecomm.setChecked(true);
                        break;
                    case 1:
						rbJoined.setChecked(true);
                        break;
                    case 2:
                        rbFriend.setChecked(true);
                        break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		
		//点击右边显示
		dynamicIvAdd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AddPopWindow addPopWindow = new AddPopWindow(getActivity());
                addPopWindow.showPopupWindow(dynamicIvAdd);
            }

        });

		return parentView;
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		if (checkedId == rbRecomm.getId()) {
			viewpager.setCurrentItem(0);
		} else if (checkedId == rbJoined.getId()) {
			viewpager.setCurrentItem(1);
		} else if(checkedId == rbFriend.getId()){
            viewpager.setCurrentItem(2);
        }
	}

	
private class ZxzcAdapter extends FragmentStatePagerAdapter {

		List<Fragment> list;			
		private ZxzcAdapter(FragmentManager fm,List<Fragment> list) {
			super(fm);
			this.list=list;			
		}
		@Override
		public Fragment getItem(int arg0) {			

            return list.get(arg0);
		}
		@Override
		public int getCount() {
		
			return list.size();
		}

	}
}
