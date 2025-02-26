package com.example.finalp.planner.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalp.R;
import com.example.finalp.favorites.view.FavMealsAdapter;
import com.example.finalp.favorites.view.OnClickFavAdapter;
import com.example.finalp.model.pojos.Meal;

import java.util.List;

    public class PlaneMealsAdapter  extends RecyclerView.Adapter<PlaneMealsAdapter.ViewHolder> {
        private  final Context context ;

        public void setMealList(List<Meal> mealList) {
            this.mealList = mealList;
            notifyDataSetChanged();
        }

        List<Meal>mealList ;

        private onPlanedMealClick listener ;
        private Handler handler = new Handler(Looper.getMainLooper());
        private static final String  TAG = "Recycle";
        public PlaneMealsAdapter (Context context, List<Meal> mealList , onPlanedMealClick listener) {
            this.listener=listener;
            this.context = context;
            this.mealList=mealList;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.fav_meal,parent,false);
            ViewHolder holder = new ViewHolder(v);
            Log.i(TAG, "onCreateViewHolder: ");
            return holder;

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.title.setText(mealList.get(position).getStrMeal());
            Glide.with(context).load(mealList.get(position).getStrMealThumb()).apply(
                            new RequestOptions().override(200,200))
                    .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(holder.image);
            holder.icon.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onPlanedMealClick(mealList.get(position),view);
                }

            });
        }

        @Override
        public int getItemCount() {
            return mealList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView image ;
            TextView title ;
            ImageView icon ;

            public ConstraintLayout constraintLayout ;
            public  View layout ;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                layout = itemView ;
                image=itemView.findViewById(R.id.favimage);
                title=itemView.findViewById(R.id.favname);
                icon =itemView.findViewById(R.id.delete);
                constraintLayout =itemView.findViewById(R.id.favcard);
            }
        }
}
