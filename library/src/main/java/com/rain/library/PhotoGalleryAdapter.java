package com.rain.library;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rain.library.bean.Photo;
import com.rain.library.bean.PhotoDirectory;
import com.rain.library.controller.PhotoPickConfig;
import com.rain.library.utils.UtilsHelper;
import com.rain.library.weidget.GalleryImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe : 相册列表展示
 * Created by Rain on 17-4-28.
 */
public class PhotoGalleryAdapter extends RecyclerView.Adapter {

    private Context context;
    private int selected;
    private ArrayList<PhotoDirectory> directories = new ArrayList<>();
    private ArrayList<Photo> photoList = new ArrayList<>();
    private int imageSize;

    public PhotoGalleryAdapter(Context context) {
        this.context = context;
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        this.imageSize = metrics.widthPixels / 6;
    }

    public void refresh(List<PhotoDirectory> directories) {
        this.directories.clear();
        this.directories.addAll(directories);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_gallery, null);
        return new PhotoGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PhotoGalleryViewHolder) holder).showData(getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return directories.size();
    }

    private PhotoDirectory getItem(int position) {
        return this.directories.get(position);
    }

    private void changeSelect(int position) {
        this.selected = position;
        notifyDataSetChanged();
    }

    private class PhotoGalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private GalleryImageView imageView;
        private ImageView photo_gallery_select;
        private TextView name, num;

        public PhotoGalleryViewHolder(View view) {
            super(view);
            imageView = (GalleryImageView) itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.name);
            num = (TextView) itemView.findViewById(R.id.num);
            photo_gallery_select = (ImageView) itemView.findViewById(R.id.photo_gallery_select);
            imageView.getLayoutParams().height = imageSize;
            imageView.getLayoutParams().width = imageSize;
            itemView.setOnClickListener(this);
        }

        public void showData(PhotoDirectory directory, int position) {
            if (directory == null || directory.getCoverPath() == null) {
                return;
            }
            if (selected == position) {
                photo_gallery_select.setImageResource(R.mipmap.select_icon);
            } else {
                photo_gallery_select.setImageBitmap(null);
            }
            name.setText(directory.getName());
            num.setText(context.getString(R.string.gallery_num, String.valueOf(directory.getPhotoPaths().size())));
            if (PhotoPickConfig.imageLoader != null)
                PhotoPickConfig.imageLoader.displayImage(context, directory.getCoverPath(), imageView, true);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (view.getId() == R.id.photo_gallery_rl) {
                if (onItemClickListener != null) {
                    changeSelect(position);
                    photoList.clear();
                    List<Photo> photos = getItem(position).getPhotos();
                    for (int i = 0; i < photos.size(); i++) {
                        if (UtilsHelper.isFileExist(photos.get(i).getOriginalImagePath())) {
                            photoList.add(photos.get(i));
                        }
                    }
                    onItemClickListener.onClick(photoList);
                }
            }
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(ArrayList<Photo> photos);
    }
}
