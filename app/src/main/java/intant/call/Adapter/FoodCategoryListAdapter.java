package intant.call.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import intant.call.Model.FoodCategory;
import intant.call.R;

public class FoodCategoryListAdapter extends RecyclerView.Adapter<FoodCategoryListAdapter.ViewHolder> {
    private ArrayList<FoodCategory> foodCategories;
    private onItemClickListener listener;

    public FoodCategoryListAdapter(ArrayList foodCategories, FoodCategoryListAdapter.onItemClickListener listener) {
        this.foodCategories = foodCategories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_category_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(foodCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return foodCategories.size();
    }

    public interface onItemClickListener {
        void onClick(FoodCategory foodCategory);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView mcvFoodCategory;
        private ImageView ivCategoryIcon;
        private TextView tvCategoryTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mcvFoodCategory = itemView.findViewById(R.id.mcv_food_category);
            ivCategoryIcon = itemView.findViewById(R.id.iv_category_icon);
            tvCategoryTitle = itemView.findViewById(R.id.tv_category_title);
        }

        public void bind(final FoodCategory foodCategory) {
            ivCategoryIcon.setImageResource(foodCategory.getImageId());
            tvCategoryTitle.setText(foodCategory.getTitle());

            mcvFoodCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(foodCategory);
                }
            });
        }
    }
}