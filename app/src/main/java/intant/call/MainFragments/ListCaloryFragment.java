package intant.call.MainFragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import intant.call.Adapter.FoodCategoryListAdapter;
import intant.call.Adapter.FoodItemListAdapter;
import intant.call.Model.FoodCategory;
import intant.call.Model.FoodItem;
import intant.call.R;


public class ListCaloryFragment extends Fragment {

    private TextView tvHeaderTitle;
    private RecyclerView rvFoodList;

    private FoodCategoryListAdapter adapterFoodCategoryList;
    private FoodItemListAdapter adapterFoodItemList;

    private boolean isShowingFoodItem;

    public ListCaloryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_calory, container, false);

        setOnBackPressedCallback();
        initView(v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFoodCategoryRecyclerView();
    }

    private void setOnBackPressedCallback() {
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (isShowingFoodItem) {
                    isShowingFoodItem = false;
                    setFoodCategoryRecyclerView();
                    tvHeaderTitle.setText("Daftar Kalori Makanan dan Minuman");
                } else {
                    getActivity().finishAffinity();
                }
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);
    }

    private void initView(View rootView) {
        isShowingFoodItem = false;

        rvFoodList = rootView.findViewById(R.id.rv_food_list);
        tvHeaderTitle = rootView.findViewById(R.id.tv_header_title);
    }

    private void setFoodCategoryRecyclerView() {
        adapterFoodCategoryList = new FoodCategoryListAdapter(getFoodCategories(), new FoodCategoryListAdapter.onItemClickListener() {
            @Override
            public void onClick(FoodCategory foodCategory) {
                isShowingFoodItem = true;
                setFoodItemRecyclerView();
                tvHeaderTitle.setText(String.format("Kalori %s", foodCategory.getTitle()));
            }
        });
        rvFoodList.setAdapter(adapterFoodCategoryList);
        rvFoodList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    private void setFoodItemRecyclerView() {
        adapterFoodItemList = new FoodItemListAdapter(getFoodItems());
        rvFoodList.setAdapter(adapterFoodItemList);
        rvFoodList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private ArrayList<FoodCategory> getFoodCategories() {
        ArrayList<FoodCategory> foodCategories = new ArrayList<>();

        foodCategories.add(new FoodCategory(0, R.drawable.ic_main_foods, "Makanan Pokok"));
        foodCategories.add(new FoodCategory(1, R.drawable.ic_side_dishes, "Lauk Pauk"));
        foodCategories.add(new FoodCategory(2, R.drawable.ic_vegetables, "Sayuran"));
        foodCategories.add(new FoodCategory(3, R.drawable.ic_fruits, "Buah-buahan"));
        foodCategories.add(new FoodCategory(4, R.drawable.ic_snacks, "Makanan Ringan"));
        foodCategories.add(new FoodCategory(5, R.drawable.ic_drinks, "Minuman"));

        return foodCategories;
    }

    private ArrayList<FoodItem> getFoodItems() {
        ArrayList<FoodItem> foodItems = new ArrayList<>();

        foodItems.add(new FoodItem("", "Nasi Putih", "dibuat dari potongan-potongan ayam yang digoreng dengan minya zaitun.", 200));
        foodItems.add(new FoodItem("", "Nasi Goreng", "dibuat dari potongan-potongan ayam yang digoreng dengan minya zaitun.", 300));
        foodItems.add(new FoodItem("", "Nasi Sayur", "dibuat dari potongan-potongan ayam yang digoreng dengan minya zaitun.", 150));
        foodItems.add(new FoodItem("", "Nasi Telur", "dibuat dari potongan-potongan ayam yang digoreng dengan minya zaitun.", 350));
        foodItems.add(new FoodItem("", "Mie dok dok", "dibuat dari potongan-potongan ayam yang digoreng dengan minya zaitun.", 210));
        foodItems.add(new FoodItem("", "Intel", "dibuat dari potongan-potongan ayam yang digoreng dengan minya zaitun.", 120));

        return foodItems;
    }
}
