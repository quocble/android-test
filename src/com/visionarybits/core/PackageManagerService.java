package com.visionarybits.core;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import  android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public class PackageManagerService {
  
  Context context;
  public PackageManagerService(Context context) {
    this.context = context;
  }

  public boolean appInstalled(String uri) {
    final PackageManager pm = this.context.getPackageManager();
    boolean app_installed = false;
    try {
      pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
      app_installed = true;
    } catch (PackageManager.NameNotFoundException e) {
      app_installed = false;
    }
    return app_installed;
  }
 
  private boolean isSystemPackage(ApplicationInfo applicationInfo){
    return (applicationInfo.flags&ApplicationInfo.FLAG_IS_DATA_ONLY)!=0;
  }  
  
  @TargetApi(Build.VERSION_CODES.CUPCAKE)
  public List getAllAppInstalled() {
    final PackageManager pm = this.context.getPackageManager();
//    List<PackageInfo> results = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
    List<ApplicationInfo> results = pm.getInstalledApplications(PackageManager.GET_META_DATA);    

    List<ApplicationInfo> newResults = new ArrayList<ApplicationInfo>();
    for(ApplicationInfo pi : results) {
      Intent mainIntent = pm.getLaunchIntentForPackage(pi.packageName);
      if(!this.isSystemPackage(pi) && mainIntent != null)
      {
        newResults.add(pi);
      }
    }
    return newResults;
  }
}
