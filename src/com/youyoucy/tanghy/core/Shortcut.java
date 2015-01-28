package com.youyoucy.tanghy.core;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

public class Shortcut {

    private Context context;
    private String appName;
    private int icon_launcher;

    /**
     * @param context 要生成快捷方式的上下文
     * @param appName 生成快捷方式的名称
     * @param icon_launcher 生成快捷方式的图片
     */
    public Shortcut(Context context,String appName,int icon_launcher ){
        this.context = context;
        this.appName = appName;
        this.icon_launcher = icon_launcher;
    }

    public boolean createShortcut(){
       boolean bl = haveShortcut();
        if (bl)return false;
        else addShortcut();
        return true;
    }

    /**
     *
     * 判断是否有快捷方式
     * @return
     */
    private boolean haveShortcut()
    {
        boolean isI = false;
        final ContentResolver cr = context.getContentResolver();
        String AUTHORITY ="com.android.launcher.settings";
        if (android.os.Build.VERSION.SDK_INT >= 8) {
            AUTHORITY = "com.android.launcher2.settings";
        } else {
            AUTHORITY = "com.android.launcher.settings";
        }
        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI,new String[] {"title","iconResource" },"title=?",
                new String[] {appName.trim()}, null);
        if(c!=null && c.getCount()>0){
            isI = true ;
        }
        return isI ;
    }

    /**
     * 为程序创建桌面快捷方式
     */
    private void addShortcut(){
        Intent stt = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        //快捷方式的名称
        stt.putExtra(Intent.EXTRA_SHORTCUT_NAME, appName);
        stt.putExtra("duplicate", false); //不允许重复创建
        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
        shortcutIntent.setClassName(context, context.getClass().getName());
        stt.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

        //快捷方式的图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, icon_launcher);
        stt.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        context.sendBroadcast(stt);
    }
}
