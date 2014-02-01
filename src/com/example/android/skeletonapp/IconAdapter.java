package com.example.android.skeletonapp;

/*
 * Copyright (C) 2012 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IconAdapter extends BaseAdapter {
  private final Context mContext;
  private List<ApplicationInfo> packages = new ArrayList<ApplicationInfo>();
  
  public IconAdapter(Context context) {
    mContext = context;
  }

  @Override
  public int getCount() {
      return this.packages.size();
  }

  @Override
  public Integer getItem(int position) {
    return position;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder = null;

    if (convertView == null) {
      convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

      holder = new ViewHolder();
      holder.title = (TextView) convertView.findViewById(R.id.title);
      holder.image = (ImageView) convertView.findViewById(R.id.imageView1);

      convertView.setTag(holder);
    } else {
        holder = (ViewHolder) convertView.getTag();
    }
    
    ApplicationInfo appinfo = this.packages.get(position);  
    String appName = this.mContext.getPackageManager().getApplicationLabel(appinfo).toString();
    try {
      Drawable bitmap = this.mContext.getPackageManager().getApplicationIcon(appinfo.packageName);
      holder.image.setImageDrawable(bitmap);
    } catch(Exception e){      
    }
    holder.title.setText(appName);    

    return convertView;
  }

  class ViewHolder {
      public TextView title;
      public ImageView image;
  }

  public void setData(List<ApplicationInfo> packages) {
    this.packages = packages;
    this.notifyDataSetChanged();
  }
}