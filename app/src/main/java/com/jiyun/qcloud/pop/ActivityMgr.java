package com.jiyun.qcloud.pop;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * 设计一个全局的Activity栈,使用这个栈来管理Activity
 */
public class ActivityMgr {
    private static Stack<Activity> activityStack;
    private static ActivityMgr instance;

    /**
     * 构造方法
     */
    private ActivityMgr() {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
    }
/**获取activity的manager*/
    public static ActivityMgr getActivityManager() {
        if (instance == null) {
            instance = new ActivityMgr();
        }
        return instance;
    }
    /**获取栈的大小*/
    public int size() {
        return activityStack.size();
    }



    //添加
    /**添加activity到栈中*/
    public void pushActivity(Activity activity) {
//        Log.e("ActivityMgr", "得到的className--" + activity.getClass().getName());
        activityStack.add(activity);
//    	Log.e("ActivityMgr", "得到的总个数 --" + activityStack.size());
    }


    //移除
    /**从栈中移除某一个activity*/
    public void clear(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**清空栈*/
    public void clear() {
        activityStack.clear();
    }





    /**从栈中移除最后一个activity*/
    public void popActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }
    /**移除指定的activity*/
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }
    /**获取当前的activity*/
    public Activity currentActivity() {
        if (activityStack.size() > 0) {
            Activity activity = activityStack.lastElement();
            return activity;
        } else {
            return null;
        }
    }

    /**获取倒数第二个activiyt，这个有点疑问*/
    public Activity getTopActivity() {
        if (activityStack.size() < 1) {
            return null;
        }
        Activity activity = activityStack.get(activityStack.size() - 1);
        return activity;
    }
    /**退出除指定activity之外的其他activity*/
    @SuppressWarnings("rawtypes")
    public void popAllActivityExceptOne(Class cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }
    /**移除所有activity*/
    public void popAllActivity() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 销毁所有Activity -- ybb
     */
    public void destroyAllActivity() {

        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            	activity.finish();
        }
        activityStack.clear();
    }
    /**销毁所有activity除了主页界面，直接回退到主activiyty*/
    public void destroyAllActivityBySingleLogin() {

        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            Log.e("ActivityMgr", "activit.name=" + activity.getClass().getName());
            if(!"com.lecarlink.lf.activity.MainActivity".equals(activity.getClass().getName())
            		&& !"com.lecarlink.pltpsuper.integration.home.MainActivity".equals(activity.getClass().getName())){
            	activity.finish();
            }
        }
    }

    /**
     * 获取上一个activity
     */
    public Activity getParentActivity(int index) {
        if (activityStack.size() < index) {
            return null;
        }
        Activity activity = activityStack.get(activityStack.size() - index);
        return activity;
    }
    /**回退到上一个activiyt*/
    public Activity getTopStackActivity() {
        if(activityStack.size() <= 0 ) return null;
        Activity activity = activityStack.get(activityStack.size()-1);
        return activity;
    }
}
