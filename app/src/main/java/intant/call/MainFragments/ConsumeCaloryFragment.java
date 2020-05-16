package intant.call.MainFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

import intant.call.Adapter.FoodItemSearchAdapter;
import intant.call.Model.FoodItem;
import intant.call.R;


public class ConsumeCaloryFragment extends Fragment implements View.OnClickListener {

    private FoodItemSearchAdapter foodItemSearchAdapter;

    private BottomSheetBehavior bsbFoodSearch;
    private LinearLayout llFoodSearchContainer;
    private ImageView ivAddBreakfast;
    private RecyclerView rvFoodListSearch;

    public ConsumeCaloryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_consume_calory, container, false);

        initView(v);
        setOnBackPressedCallback();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFoodItemSearchRecyclerView();
    }

    private void initView(View rootView) {
        llFoodSearchContainer = rootView.findViewById(R.id.ll_bottomsheet_container);
        ivAddBreakfast = rootView.findViewById(R.id.iv_add_breakfast);
        rvFoodListSearch = rootView.findViewById(R.id.rv_food_list_search);

        bsbFoodSearch = BottomSheetBehavior.from(llFoodSearchContainer);

        ivAddBreakfast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_breakfast:
                bsbFoodSearch.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
        }
    }

    private void setOnBackPressedCallback() {
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (bsbFoodSearch.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bsbFoodSearch.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    getActivity().finishAffinity();
                }
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);
    }

    private void setFoodItemSearchRecyclerView() {
        foodItemSearchAdapter = new FoodItemSearchAdapter(getFoodItemsSearch(), getActivity(), new FoodItemSearchAdapter.onItemClickListener() {
            @Override
            public void onClick(FoodItem foodItem) {
                Spinner dialogSpinner;
                TextView dialogTvFoodName;
                TextView dialogTvFoodCalory;
                ImageView dialogIvFoodImage;

                String[] listActivityLevel = {
                        "1 buah (300 gr)",
                        "3/4 buah (75 gr)",
                        "1 buah (300 gr)"
                };
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listActivityLevel);

                Dialog dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.dialog_add_consume_calory);
                dialogSpinner = dialog.findViewById(R.id.spinner_food_quantity);
                dialogTvFoodName = dialog.findViewById(R.id.tv_food_name);
                dialogTvFoodCalory = dialog.findViewById(R.id.tv_food_calory);
                dialogIvFoodImage = dialog.findViewById(R.id.iv_food_image);

                dialogSpinner.setAdapter(spinnerAdapter);
                dialogTvFoodName.setText(foodItem.getName());
                dialogTvFoodCalory.setText(String.format("%d Kalori", foodItem.getCalory()));
                Glide.with(getActivity())
                        .load(foodItem.getImageUrl())
                        .placeholder(R.drawable.food_placeholder)
                        .into(dialogIvFoodImage);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                Window window = dialog.getWindow();
                window.setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            }
        });
        rvFoodListSearch.setAdapter(foodItemSearchAdapter);
        rvFoodListSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private ArrayList<FoodItem> getFoodItemsSearch() {
        ArrayList<FoodItem> foodItems = new ArrayList<>();

        foodItems.add(new FoodItem("", "https://firebasestorage.googleapis.com/v0/b/tantanprojek.appspot.com/o/images%2Fapel_1-removebg-preview%202.png?alt=media&token=b6e5ec33-c9e9-452f-8376-db5cf59fe2d1", "Apel Merah", "1 buah (300 gr).", 200));
        foodItems.add(new FoodItem("", "https://firebasestorage.googleapis.com/v0/b/tantanprojek.appspot.com/o/images%2Fapel2_1-removebg-preview%202.png?alt=media&token=6a0fd9a8-f3eb-4c9d-98a0-3104e69f9e5b", "Apel Hijau", "1 buah (300 gr).", 300));
        foodItems.add(new FoodItem("", "", "Nasi Sayur", "1 buah (300 gr).", 150));
        foodItems.add(new FoodItem("", "", "Nasi Telur", "1 buah (300 gr).", 350));

        return foodItems;
    }
}
