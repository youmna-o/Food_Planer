package com.example.finalp.home.view;

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
import com.example.finalp.model.data_models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
        private  final Context context ;

        public void setCategoryList(List<Category> categoryList) {
            this.categoryList = categoryList;
        }

        List<Category>categoryList ;

        private onClickAdapter listener ;
        private Handler handler = new Handler(Looper.getMainLooper());
        // private L
        private static final String  TAG = "Recycle";
        //product
        public CategoryAdapter(Context context, List<Category> categoryList , onClickAdapter listener) {
            this.listener=listener;
            this.context = context;
            this.categoryList=categoryList;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.single_category,parent,false);
           ViewHolder holder = new ViewHolder(v);
            Log.i(TAG, "onCreateViewHolder: ");
            return holder;

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.title.setText(categoryList.get(position).getStrCategory());
            Glide.with(context).load(categoryList.get(position).getStrCategoryThumb()).apply(
                            new RequestOptions().override(200,200))
                    .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(holder.image);
            holder.constraintLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onCategoryClick(categoryList.get(position),view);
                }
            });
        }

        @Override
        public int getItemCount() {
            return categoryList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView image ;
            TextView title ;

            public ConstraintLayout constraintLayout ;
            public  View layout ;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                layout = itemView ;
                image=itemView.findViewById(R.id.ingredImage);
                title=itemView.findViewById(R.id.ingredname);
                constraintLayout =itemView.findViewById(R.id.singleIngred);
            }
        }

    }



