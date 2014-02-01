/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.skeletonapp;

import java.util.List;

import org.lucasr.twowayview.TwoWayView;

import com.visionarybits.core.PackageManagerService;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

/**
 * This class provides a basic demonstration of how to write an Android
 * activity. Inside of its window, it places a single view: an EditText that
 * displays and edits some internal text.
 */
public class SkeletonActivity extends Activity {

  private TwoWayView mListView;
  private PackageManagerService packageManager;
  private IconAdapter listAdapter = new IconAdapter(SkeletonActivity.this);

  public SkeletonActivity() {
  }

  private boolean isSystemPackage(PackageInfo pkgInfo) {
    return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
        : false;
  }

  /** Called with the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Inflate our UI from its XML layout description.
    setContentView(R.layout.skeleton_activity);

    mListView = (TwoWayView) findViewById(R.id.list);
    mListView.setItemMargin(10);

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View child, int position,
          long id) {
        System.out.println("Item clicked: " + position);
      }
    });

    packageManager = new PackageManagerService(getApplicationContext());
    List<ApplicationInfo> packages = packageManager.getAllAppInstalled();
    listAdapter.setData(packages);
    mListView.setAdapter(listAdapter);
  }

  /**
   * Called when the activity is about to start interacting with the user.
   */
  @Override
  protected void onResume() {
    super.onResume();
  }

  /**
   * Called when your activity's options menu needs to be created.
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    return true;
  }

  /**
   * Called right before your activity's option menu is displayed.
   */
  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    super.onPrepareOptionsMenu(menu);
    return true;
  }

  /**
   * A call-back for when the user presses the back button.
   */
  OnClickListener mBackListener = new OnClickListener() {
    public void onClick(View v) {
      finish();
    }
  };
}
