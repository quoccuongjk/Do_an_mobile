package com.example.myapplication.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.model.Photo;
import com.example.myapplication.adapter.PhotoViewPageAdapter;
import com.example.myapplication.model.FoodType;
import com.example.myapplication.adapter.FoodTypeAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    MainActivity mainActivity;
    View mView;
    RecyclerView recyclerView;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == getListPhoto().size() - 1) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        init();
        actionViewPage();
        typeProduct();
        return mView;

    }
    private void init() {
        viewPager = mView.findViewById(R.id.view_pager);
        circleIndicator = mView.findViewById(R.id.circle_indicator);
        recyclerView = mView.findViewById(R.id.rcv_mhc);

    }
    private void typeProduct() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mainActivity,2);
        recyclerView.setLayoutManager(layoutManager);
        FoodTypeAdapter productTypeAdapter = new FoodTypeAdapter(getListType(), (FoodType productType) -> {
            //Todo: open example fragment
            NavController navController = NavHostFragment.findNavController(HomeFragment.this);

            Bundle bundle = new Bundle();
            bundle.putInt("product_id", productType.getId());
            navController.navigate(R.id.action_home_to_exampleFragment, bundle);

        });
        recyclerView.setAdapter(productTypeAdapter);
    }

    private List<FoodType> getListType() {
        List<FoodType> list = new ArrayList<>();
        list.add(new FoodType(1,"South Indian 1",R.drawable.picture_3));
        list.add(new FoodType(2,"South Indian 2",R.drawable.picture_3));
        list.add(new FoodType(3,"South Indian 3",R.drawable.picture_3));
        list.add(new FoodType(4,"South Indian 4",R.drawable.picture_3));
        list.add(new FoodType(5,"South Indian 5",R.drawable.picture_3));
        list.add(new FoodType(6,"South Indian 6",R.drawable.picture_3));
        return list;
    }

    private void actionViewPage() {
        PhotoViewPageAdapter adapter = new PhotoViewPageAdapter(getListPhoto());
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        handler.postDelayed(runnable,2000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,2000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.picture_1));
        list.add(new Photo(R.drawable.picture_2));
        list.add(new Photo(R.drawable.picture_3));
        list.add(new Photo(R.drawable.picture_4));

        return list;
    }
    private boolean isConnected (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi !=null && wifi.isConnected()) || (mobile != null && mobile.isConnected())) {
            return true;
        } else return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,2000);
    }
}