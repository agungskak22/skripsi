package intant.call.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import intant.call.Model.FoodItem;
import intant.call.R;

public class FoodItemSearchAdapter extends RecyclerView.Adapter<FoodItemSearchAdapter.ViewHolder> {

    private ArrayList<FoodItem> foodItems;
    private Context context;
    private FoodItemSearchAdapter.onItemClickListener listener;

    public FoodItemSearchAdapter(ArrayList<FoodItem> foodItems, Context context, FoodItemSearchAdapter.onItemClickListener listener) {
        this.foodItems = foodItems;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_food_item, parent, false);
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

    public interface onItemClickListener {
        void onClick(FoodItem foodItem);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFoodName;
        private TextView tvFoodDescription;
        private TextView tvFoodCalory;
        private ImageView ivFoodImage;
        private ImageView ivAddFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodCalory = itemView.findViewById(R.id.tv_food_calory);
            tvFoodDescription = itemView.findViewById(R.id.tv_food_description);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            ivAddFood = itemView.findViewById(R.id.iv_add_food);
            ivFoodImage = itemView.findViewById(R.id.iv_food_image);
        }

        public void bind(final FoodItem foodItem) {
            tvFoodName.setText(foodItem.getName());
            tvFoodDescription.setText(foodItem.getDescription());
            tvFoodCalory.setText(String.format("%d Kalori", foodItem.getCalory()));

            Glide.with(context)
                    .load(foodItem.getImageUrl())
                    .placeholder(R.drawable.food_placeholder)
                    .into(ivFoodImage);

            ivAddFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(foodItem);
                }
            });
        }
    }

}
