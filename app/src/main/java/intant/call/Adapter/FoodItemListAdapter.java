package intant.call.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import intant.call.Model.FoodItem;
import intant.call.R;

public class FoodItemListAdapter extends RecyclerView.Adapter<FoodItemListAdapter.ViewHolder> {
    private ArrayList<FoodItem> foodItems;

    public FoodItemListAdapter(ArrayList foodItems) {
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(foodItems.get(position));
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFoodImage;
        private TextView tvFoodName;
        private TextView tvFoodDescription;
        private TextView tvFoodCalory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoodImage = itemView.findViewById(R.id.iv_food_image);
            tvFoodCalory = itemView.findViewById(R.id.tv_food_calory);
            tvFoodDescription = itemView.findViewById(R.id.tv_food_description);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
        }

        public void bind(FoodItem foodItem) {
            tvFoodName.setText(foodItem.getName());
            tvFoodDescription.setText(foodItem.getDescription());
            tvFoodCalory.setText(String.format("%d Kalori", foodItem.getCalory()));
        }
    }
}
